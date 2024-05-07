package smarthome.controller.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.actuator_model.ActuatorModelFactoryImpl;
import smarthome.domain.actuator_model.IActuatorModelFactory;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.repository.IActuatorModelRepository;
import smarthome.domain.repository.ISensorModelRepository;
import smarthome.domain.repository.IUnitRepository;
import smarthome.domain.sensor_model.ISensorModelFactory;
import smarthome.domain.service.IActuatorModelService;
import smarthome.domain.unit.IUnitFactory;
import smarthome.domain.unit.UnitFactoryImpl;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;
import smarthome.mapper.ActuatorModelAssembler;
import smarthome.persistence.mem.ActuatorModelRepository;
import smarthome.persistence.mem.SensorModelRepository;
import smarthome.persistence.mem.UnitRepository;
import smarthome.utils.LoadModelsAndUnit;
import smarthome.utils.dto.ActuatorModelDTO;
import smarthome.utils.dto.ActuatorTypeDTO;
import smarthome.utils.dto.actuator_data_dto.ActuatorDataGenericDTOImp;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ActuatorModelTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IActuatorModelService actuatorModelService;

  @MockBean
  private ActuatorModelAssembler actuatorModelAssembler;

  @Autowired
  private IActuatorModelFactory actuatorModelFactory;

  @Test
  void shouldReturnActuatorModelDTO_whenActuatorModelExists() throws Exception {
    // Arrange
    String actuatorModelPath = "path";
    String actuatorName = "Thermostat";
    String actuatorTypeID = "1";

    ModelPath modelPath = new ModelPath(actuatorModelPath);
    ActuatorTypeID typeID = new ActuatorTypeID(actuatorTypeID);
    ActuatorModelName actuatorName1 = new ActuatorModelName(actuatorName);

    //IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();
    ActuatorModel actuatorModel = actuatorModelFactory.createActuatorModel(actuatorName1, modelPath, typeID);

    ActuatorModelDTO actuatorModelDTO = new ActuatorModelDTO(actuatorModel.getActuatorTypeID().toString(),
        actuatorName1.getActuatorModelName(), modelPath.getID());
    List<ActuatorModelDTO> actuatorModelDTOs = List.of(actuatorModelDTO);

    // Set up mock to return the ActuatorModel object
    when(actuatorModelService.getActuatorModelsByActuatorTypeId(typeID)).thenReturn(List.of(actuatorModel));
    when(actuatorModelAssembler.domainToDTO(List.of(actuatorModel))).thenReturn(actuatorModelDTOs);

    // Act & Assert
    mockMvc.perform(get("/actuator-model/{actuatorTypeID}", actuatorTypeID)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.actuatorModelDTOList[0].actuatorModelName").value("Thermostat"))
        .andExpect(jsonPath("$._links.self").exists());
  }
}
