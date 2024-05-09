package smarthome.controller.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.ddd.IRepository;
import smarthome.domain.repository.IUnitRepository;
import smarthome.domain.service.IUnitService;
import smarthome.domain.unit.IUnitFactory;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.mapper.UnitAssembler;
import smarthome.utils.dto.UnitDTO;
import smarthome.utils.dto.UnitDataDTO;


@SpringBootTest
@AutoConfigureMockMvc
class UnitControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private IUnitFactory unitFactory;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private IUnitService unitService;

  @MockBean
  private UnitAssembler unitAssembler;

  @MockBean
  private IRepository unitRepository;

  @InjectMocks
  private UnitControlller unitController;

  @BeforeEach
  public void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * This test case verifies that the UnitController returns a list of units when they are found.
   */
  @Test
  void shouldReturnUnits_WhenFound() throws Exception {
    // Arrange
    String symbol = "C";
    String strDescription = "Celsius";
    UnitSymbol unitSymbol = new UnitSymbol(symbol);
    UnitDescription description = new UnitDescription(strDescription);
    Unit unit = unitFactory.createUnit(description, unitSymbol);
    UnitDTO unitDTO = new UnitDTO(unit.getID().toString(), description.toString(),
        unitSymbol.toString());
    List<UnitDTO> unitDTOs = List.of(unitDTO);

    when(unitService.getAllMeasurementTypes()).thenReturn(List.of(unit));
    when(unitAssembler.domainToDTO((List<Unit>) any())).thenReturn(unitDTOs);

    // Act & Assert
    mockMvc.perform(get("/unit/all")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self").exists());
  }

  /**
   * This test case verifies that the UnitController returns a 404 Not Found status when no units
   * are available.
   */
  @Test
  void shouldReturnNotFound_WhenNoUnitsAvailable() throws Exception {
    // Arrange
    when(unitService.getAllMeasurementTypes()).thenReturn(List.of());
    // Act & Assert
    mockMvc.perform(get("/unit/all")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  /**
   * This test case verifies that the UnitController returns a 201 Created status when a unit is
   * successfully created.
   */
  @Test
  void shouldReturnUnitDataDTO_whenUnitIsCreated() throws Exception {
    // Arrange
    String symbol = "C";
    String description = "Celsius";
    UnitDataDTO unitDataDTO = new UnitDataDTO(description, symbol);

    UnitSymbol unitSymbol = new UnitSymbol(symbol);
    UnitDescription unitDescription = new UnitDescription(description);
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);

    UnitDTO unitDTO = new UnitDTO(unit.getID().toString(), unitDescription.toString(),
        unitSymbol.toString());
    when(unitService.addMeasurementType(unitDescription, unitSymbol)).thenReturn(unit);
    when(unitAssembler.domainToDTO(unit)).thenReturn(unitDTO);

    // Act & Assert
    mockMvc.perform(post("/unit/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(unitDataDTO)))
        .andExpect(status().isCreated()) // Expecting 201 status code
        .andExpect(jsonPath("$.description").value(description))
        .andExpect(jsonPath("$.unitSymbol").value("Unit:" + symbol))
        .andExpect(jsonPath("$._links.self").exists());
  }
}