package smarthome.controller.rest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
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
import smarthome.utils.dto.data_dto.ActuatorTypeDataDTO;


@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class ActuatorTypeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  /**
   * Tests successful retrieval of all actuator types.
   *
   * @throws Exception if the MVC request building or execution fails
   */
  @Test
  void shouldReturnAllActuatorTypes_WhenRequested() throws Exception {
    // Act & Assert
    mockMvc.perform(get("/actuator-types/")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.actuatorTypeDTOList[0].actuatorTypeID", is("BlindRoller")))
        .andExpect(
            jsonPath("$._embedded.actuatorTypeDTOList[0].actuatorTypeDescription", is("BlindRoller")))
        .andExpect(jsonPath("$._embedded.actuatorTypeDTOList[0].unit", is("Percent")))
        .andExpect(jsonPath("$._links.self.href").exists());
  }

  /**
   * Tests the scenario where no actuator types are found.
   *
   * @throws Exception if the MVC request building or execution fails
   */
  @Test
  void shouldReturnEmptyList_WhenNoActuatorTypesAvailable() throws Exception {
    // Act & Assert
    mockMvc.perform(get("/actuator-types/")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self.href").exists());
  }

  /**
   * This test case verifies that the ActuatorTypeController returns an actuator type when it is
   * found.
   */
  @Test
  void shouldReturnActuatorType_WhenFound() throws Exception {
    // Arrange
    ActuatorTypeDataDTO actuatorTypeDataDTO = new ActuatorTypeDataDTO("Test", "Celsius");
    String jsonContent = objectMapper.writeValueAsString(actuatorTypeDataDTO);

    mockMvc.perform(post("/actuator-types/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonContent)
        .accept(MediaType.APPLICATION_JSON));

    // Act & Assert
    mockMvc.perform(get("/actuator-types/Test")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.actuatorTypeID", is("Test")))
        .andExpect(jsonPath("$.actuatorTypeDescription", is("Test")))
        .andExpect(jsonPath("$.unit", is("Celsius")))
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
    mockMvc.perform(get("/actuator-types/{id}/", "")
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
    ActuatorTypeDataDTO actuatorTypeDataDTO = new ActuatorTypeDataDTO("Test", "Celsius");
    String jsonContent = objectMapper.writeValueAsString(actuatorTypeDataDTO);

    // Act & Assert
    mockMvc.perform(post("/actuator-types/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.actuatorTypeID").exists())
        .andExpect(jsonPath("$.actuatorTypeDescription", is("Test")))
        .andExpect(jsonPath("$.unit", is("Celsius")))
        .andExpect(jsonPath("$._links.self.href").exists())
        .andExpect(jsonPath("$._links.actuator-types.href").exists())
        .andExpect(header().string("Location", containsString("/actuator-types/Test")));
  }

  /**
   * This test case verifies that the ActuatorTypeController returns a bad request status when
   * invalid measurement type
   */
  @Test
  void shouldReturnBadRequest_WhenInvalidMeasurementType() throws Exception {
    // Arrange
    ActuatorTypeDataDTO actuatorTypeDataDTO = new ActuatorTypeDataDTO("Test", "C");
    String jsonContent = objectMapper.writeValueAsString(actuatorTypeDataDTO);

    // Act & Assert
    mockMvc.perform(post("/actuator-types/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", is("Please enter a valid measurement type.")));
  }

  /**
   * This test case verifies that the ActuatorTypeController returns a bad request status when
   * actuator type description is null
   */
  @Test
  void shouldReturnBadRequest_WhenActuatorTypeDescriptionIsNull() throws Exception {
    // Arrange
    ActuatorTypeDataDTO actuatorTypeDataDTO = new ActuatorTypeDataDTO(null, "Celsius");
    String jsonContent = objectMapper.writeValueAsString(actuatorTypeDataDTO);

    // Act & Assert
    mockMvc.perform(post("/actuator-types/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message",
            is("The value of 'description' should not null, blank, or empty.")));
  }

  /**
   * This test case verifies that the ActuatorTypeController returns a bad request status when
   * actuator type description is empty
   */
  @Test
  void shouldReturnBadRequest_WhenActuatorTypeDescriptionIsEmpty() throws Exception {
    // Arrange
    ActuatorTypeDataDTO actuatorTypeDataDTO = new ActuatorTypeDataDTO("", "Celsius");
    String jsonContent = objectMapper.writeValueAsString(actuatorTypeDataDTO);

    // Act & Assert
    mockMvc.perform(post("/actuator-types/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message",
            is("The value of 'description' should not null, blank, or empty.")));
  }

  /**
   * This test case verifies that the ActuatorTypeController returns a bad request status when
   * actuator type unit is null
   */
  @Test
  void shouldReturnBadRequest_WhenActuatorTypeUnitIsNull() throws Exception {
    // Arrange
    ActuatorTypeDataDTO actuatorTypeDataDTO = new ActuatorTypeDataDTO("Test", null);
    String jsonContent = objectMapper.writeValueAsString(actuatorTypeDataDTO);

    // Act & Assert
    mockMvc.perform(post("/actuator-types/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(
            jsonPath("$.message", is("The value of 'UnitID' should not null, blank, or empty.")));
  }

  /**
   * This test case verifies that the ActuatorTypeController returns a bad request status when
   * request body is empty
   */
  @Test
  void shouldReturnBadRequest_WhenRequestBodyIsEmpty() throws Exception {
    // Arrange
    ActuatorTypeDataDTO actuatorTypeDataDTO = new ActuatorTypeDataDTO("", "");
    String jsonContent = objectMapper.writeValueAsString(actuatorTypeDataDTO);

    // Act & Assert
    mockMvc.perform(post("/actuator-types/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message",
            is("The value of 'description' should not null, blank, or empty.")));
  }
}
