package smarthome.IT;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.repository.IUnitRepository;
import smarthome.domain.service.IActuatorTypeService;
import smarthome.domain.unit.IUnitFactory;
import smarthome.domain.unit.Unit;
import smarthome.domain.unit.UnitFactoryImpl;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.mapper.ActuatorTypeAssembler;
import smarthome.utils.dto.ActuatorTypeDTO;


@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class ActuatorTypeIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private IActuatorTypeService actuatorTypeService;

  @Autowired
  private IUnitRepository unitRepository;
  @Autowired
  private ActuatorTypeAssembler actuatorTypeAssembler;

  /**
   * Tests successful retrieval of all actuator types.
   *
   * @throws Exception if the MVC request building or execution fails
   */
  @Test
  void shouldReturnAllActuatorTypes_WhenRequested() throws Exception {
    // Arrange
    TypeDescription typeDescription = new TypeDescription("Test");
    IUnitFactory unitFactory = new UnitFactoryImpl();
    UnitDescription unitDescription = new UnitDescription("unitDescription");
    UnitSymbol unitSymbol = new UnitSymbol("C");
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);
    UnitID unitID = unit.getID();
    unitRepository.save(unit);
    ActuatorType actuatorType = actuatorTypeService.createActuatorType(typeDescription, unitID);
    actuatorTypeService.addActuatorType(actuatorType);

    // Act & Assert
    mockMvc.perform(get("/actuator-types")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.actuatorTypeDTOList").exists())
        .andExpect(jsonPath("$._embedded.actuatorTypeDTOList[0].actuatorTypeID",
            is(actuatorType.getID().getID())))
        .andExpect(jsonPath("$._links.self").exists());
  }

  /**
   * Tests the scenario where no actuator types are found.
   *
   * @throws Exception if the MVC request building or execution fails
   */
  @Test
  void shouldReturnEmptyList_WhenNoActuatorTypesAvailable() throws Exception {
    mockMvc.perform(get("/actuator-types")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  /**
   * This test case verifies that the ActuatorTypeController returns an actuator type when it is
   * found.
   */
  @Test
  void shouldReturnActuatorType_WhenFound() throws Exception {
    // Arrange
    TypeDescription typeDescription = new TypeDescription("Test");
    IUnitFactory unitFactory = new UnitFactoryImpl();
    UnitDescription unitDescription = new UnitDescription("unitDescription");
    UnitSymbol unitSymbol = new UnitSymbol("C");
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);
    UnitID unitID = unit.getID();
    unitRepository.save(unit);
    ActuatorType actuatorType = actuatorTypeService.createActuatorType(typeDescription, unitID);
    actuatorTypeService.addActuatorType(actuatorType);

    // Act & Assert
    mockMvc.perform(get("/actuator-types/" + actuatorType.getID().getID())
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.actuatorTypeID", is(actuatorType.getID().getID())))
        .andExpect(jsonPath("$.actuatorTypeDescription", is("Test")))
        .andExpect(jsonPath("$.unit", is("unitDescription")))
        .andExpect(jsonPath("$._links.self.href").exists());
  }

  /**
   * This test case verifies that the ActuatorTypeController returns a not found status when an
   * actuator type is not found.
   */
  @Test
  void shouldReturnNotFound_WhenActuatorTypeNotFound() throws Exception {
    mockMvc.perform(get("/actuator-types/1")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  /**
   * This test case verifies that the ActuatorTypeController returns a bad request status when
   * actuator type id is a single Space
   */
  @Test
  void shouldReturnBadRequest_WhenActuatorTypeIDIsSingleSpace() throws Exception {
    mockMvc.perform(get("/actuator-types/{id}", " ")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  /**
   * This test case verifies that the ActuatorTypeController returns a bad request status when
   * actuator type id is empty
   */
  @Test
  void shouldReturnBadRequest_WhenActuatorTypeIDisEmpty() throws Exception {
    mockMvc.perform(get("/actuator-types/{id}", "")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  /**
   * This test case verifies that the ActuatorTypeController returns a created status when an
   * actuator type is added.
   */
  @Test
  void shouldReturnCreated_WhenActuatorTypeAdded() throws Exception {
    // Arrange
    TypeDescription typeDescription = new TypeDescription("Test");
    IUnitFactory unitFactory = new UnitFactoryImpl();
    UnitDescription unitDescription = new UnitDescription("unitDescription");
    UnitSymbol unitSymbol = new UnitSymbol("C");
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);
    UnitID unitID = unit.getID();
    unitRepository.save(unit);
    ActuatorType actuatorType = actuatorTypeService.createActuatorType(typeDescription, unitID);
    actuatorTypeService.addActuatorType(actuatorType);

    ActuatorTypeDTO actuatorTypeDTO = actuatorTypeAssembler.domainToDTO(actuatorType);

    // Act & Assert
    mockMvc.perform(post("/actuator-types/add-actuator-type")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorTypeDTO))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.actuatorTypeID", is(actuatorType.getID().getID())))
        .andExpect(jsonPath("$.actuatorTypeDescription", is("Test")))
        .andExpect(jsonPath("$.unit", is("unitDescription")))
        .andExpect(jsonPath("$._links.self.href").exists());

  }

  /**
   * This test case verifies that the ActuatorTypeController returns a bad request status when
   * invalid measurement type
   */
  @Test
  void shouldReturnBadRequest_WhenInvalidMeasurementType() throws Exception {
    // Arrange
    IUnitFactory unitFactory = new UnitFactoryImpl();
    UnitDescription unitDescription = new UnitDescription("unitDescription");
    UnitSymbol unitSymbol = new UnitSymbol("C");
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);
    UnitID unitID = unit.getID();
    // did not save the unit in the repository
    ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO("Test", "unitDescription",
        unitID.getID());

    // Act & Assert
    mockMvc.perform(post("/actuator-types/add-actuator-type")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorTypeDTO))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  /**
   * This test case verifies that the ActuatorTypeController returns a bad request status when
   * actuator type is null
   */
  @Test
  void shouldReturnBadRequest_WhenActuatorTypeNull() throws Exception {
    // Arrange
    ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(null, null, null);

    // Act & Assert
    mockMvc.perform(post("/actuator-types/add-actuator-type")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorTypeDTO))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  /**
   * This test case verifies that the ActuatorTypeController returns a bad request status when
   * request body is empty
   */
  @Test
  void shouldReturnBadRequest_WhenRequestBodyIsEmpty() throws Exception {
    // Act & Assert
    mockMvc.perform(post("/actuator-types/add-actuator-type")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}")  // empty json
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }
}
