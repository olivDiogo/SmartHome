package smarthome.controller.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.service.ISensorTypeService;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.utils.dto.SensorTypeDataDTO;


@SpringBootTest
@AutoConfigureMockMvc
class SensorTypeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ISensorTypeService sensorTypeService;

  /**
   * Verify that a SensorType is correctly created
   */
  @Test
  void shouldReturnSensorTypeDTO_whenSensorTypeIsCreated() throws Exception {
    // Arrange
    String sensorTypeDescription = "Temperature";
    String unitID = "Celsius";
    SensorTypeDataDTO sensorTypeDataDTO = new SensorTypeDataDTO(sensorTypeDescription, unitID);

    TypeDescription typeDescription = new TypeDescription(sensorTypeDescription);
    UnitID unitID2 = new UnitID(unitID);
    SensorType sensorType = new SensorType(typeDescription, unitID2);

    // Set up mock to return the SensorType object
    when(sensorTypeService.createSensorType(any(), any())).thenReturn(sensorType);

    // Act & Assert
    mockMvc.perform(post("/sensorType/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorTypeDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.sensorTypeDescription").value(sensorTypeDescription))
        .andExpect(jsonPath("$.unitID").value(unitID));
  }

  /**
   * Verify that a SensorType is not created when the description is null
   */
  @Test
  void shouldReturnBadRequest_whenSensorTypeDescriptionIsNull() throws Exception {
    // Arrange
    String unitID = "Celsius";
    SensorTypeDataDTO sensorTypeDataDTO = new SensorTypeDataDTO(null, unitID);

    // Act & Assert
    mockMvc.perform(post("/sensorType/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorTypeDataDTO)))
        .andExpect(status().isBadRequest());
  }


  /**
   * Verify that a SensorType is not created when the unitID is null
   */
  @Test
  void shouldReturnBadRequest_whenUnitIDIsNull() throws Exception {
    // Arrange
    String sensorTypeDescription = "Temperature";
    SensorTypeDataDTO sensorTypeDataDTO = new SensorTypeDataDTO(sensorTypeDescription, null);

    // Act & Assert
    mockMvc.perform(post("/sensorType/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorTypeDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * This test case verifies that the ActuatorTypeController returns a 404 Not Found status when no
   * actuator types are available.
   */
  @Test
  void shouldReturnNotFoundWhenNoSensorTypesAvailable() throws Exception {
    // Arrange: Configure the service to return an empty list when called
    when(sensorTypeService.getAllSensorTypes()).thenReturn(null);

    // Act & Assert: Perform the request and expect a 404 Not Found status
    mockMvc.perform(post("/sensorType/all")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}

