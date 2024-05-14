package smarthome.controller.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import smarthome.utils.dto.data_dto.SensorTypeDataDTO;
import java.util.Collections;


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
    when(sensorTypeService.createSensorType(any(TypeDescription.class), any(UnitID.class))).thenReturn(sensorType);

    // Act & Assert
    mockMvc.perform(post("/sensor-types/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorTypeDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.description").value(sensorTypeDescription))
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
    mockMvc.perform(post("/sensor-types/")
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
    mockMvc.perform(post("/sensor-types/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorTypeDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * This test case verifies that the SensorTypeController returns a 404 Not Found status when no
   * actuator types are available.
   */
  @Test
  void shouldReturnNotFound_whenNoSensorTypesAvailable() throws Exception {
    when(sensorTypeService.getAllSensorTypes()).thenReturn(Collections.emptyList());

    mockMvc.perform(get("/sensor-types")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  void shouldReturnSensorTypes_whenFound() throws Exception {
    // Arrange
    String sensorTypeDescription = "Temperature";
    String unitID = "Celsius";
    SensorTypeDataDTO sensorTypeDataDTO = new SensorTypeDataDTO(sensorTypeDescription, unitID);

    TypeDescription typeDescription = new TypeDescription(sensorTypeDescription);
    UnitID unitID2 = new UnitID(unitID);
    SensorType sensorType = new SensorType(typeDescription, unitID2);

    // Set up mock to return the SensorType object
    when(sensorTypeService.createSensorType(any(TypeDescription.class), any(UnitID.class))).thenReturn(sensorType);

    // Act & Assert
    mockMvc.perform(post("/sensor-types/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorTypeDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.description").value(sensorTypeDescription))
        .andExpect(jsonPath("$.unitID").value(unitID));
  }

  /**
   * This test case verifies that the SensorTypeController returns a bad request status when an
   * invalid sensor type is added.
   */
  @Test
  void shouldReturnBadRequest_whenInvalidSensorTypeAdded() throws Exception {
    // Arrange
    SensorTypeDataDTO sensorTypeDataDTO = new SensorTypeDataDTO("", "");
    // Act & Assert
    mockMvc.perform(post("/sensor-types/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorTypeDataDTO)))
        .andExpect(status().isBadRequest());
  }


}

