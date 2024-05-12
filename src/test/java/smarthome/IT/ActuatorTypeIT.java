package smarthome.IT;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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


@AutoConfigureMockMvc
@SpringBootTest
class ActuatorTypeIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private IActuatorTypeService actuatorTypeService;

  @Autowired
  private IUnitRepository unitRepository;

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
}
