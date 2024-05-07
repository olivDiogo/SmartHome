package smarthome.controller.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.domain.service.IUnitService;
import smarthome.domain.unit.IUnitFactory;
import smarthome.domain.unit.Unit;
import smarthome.domain.unit.UnitFactoryImpl;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.mapper.UnitAssembler;
import smarthome.utils.dto.UnitDTO;

@SpringBootTest
@AutoConfigureMockMvc
class UnitControlllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IUnitService unitService;

  @MockBean
  private UnitAssembler unitAssembler;

  /**
   * This test case verifies that the UnitController returns a list of units when they are found.
   */
  @Test
  void shouldReturnUnits_WhenFound() throws Exception {
    // Arrange
    IUnitFactory unitFactory = new UnitFactoryImpl();
    UnitSymbol unitSymbol = new UnitSymbol("C");
    UnitDescription description = new UnitDescription("Celsius");
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
}