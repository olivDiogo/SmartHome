/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.rest.unit_testing_deprecated;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.controller.rest.ActuatorTypeController;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.service.IActuatorTypeService;
import smarthome.utils.dto.ActuatorTypeDTO;
import smarthome.utils.dto.data_dto.ActuatorTypeDataDTO;


@WebMvcTest(ActuatorTypeController.class)
class ActuatorTypeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IActuatorTypeService actuatorTypeService;

  @MockBean
  private IAssembler<ActuatorType, ActuatorTypeDTO> actuatorTypeAssembler;

  /**
   * This test case verifies that the ActuatorTypeController returns a list of actuator types when they are found.
   */
  @Test
  void shouldReturnActuatorTypes_WhenFound() throws Exception {
    // Arrange
    ActuatorType actuatorType = mock(ActuatorType.class);
    ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO("Test", "Test", "Celsius");
    when(actuatorTypeService.getAllActuatorTypes()).thenReturn(
        Collections.singletonList(actuatorType));
    when(actuatorTypeAssembler.domainToDTO(Collections.singletonList(actuatorType)))
        .thenReturn(Collections.singletonList(actuatorTypeDTO));

    // Act & Assert
    mockMvc.perform(get("/actuator-types")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.actuatorTypeDTOList").exists())
        .andExpect(jsonPath("$._links.self").exists());
  }

  /**
   * This test case verifies that the ActuatorTypeController returns a not found status when no actuator types are available.
   */
  @Test
  void shouldReturnNotFound_WhenNoActuatorTypesAvailable() throws Exception {
    when(actuatorTypeService.getAllActuatorTypes()).thenReturn(Collections.emptyList());

    mockMvc.perform(get("/actuator-types")
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
    ActuatorType actuatorType = mock(ActuatorType.class);
    ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO("Test", "Test", "Celsius");
    when(actuatorTypeService.getActuatorTypeByID(any())).thenReturn(Optional.of(actuatorType));
    when(actuatorTypeAssembler.domainToDTO(actuatorType)).thenReturn(actuatorTypeDTO);

    // Act & Assert
    mockMvc.perform(get("/actuator-types/1")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.actuatorTypeDescription").exists())
        .andExpect(jsonPath("$.unit").exists())
        .andExpect(jsonPath("$._links.self.href").exists());
  }

  /**
   * This test case verifies that the ActuatorTypeController returns a not found status when an
   * actuator type is not found.
   */
  @Test
  void shouldReturnNotFound_WhenActuatorTypeNotFound() throws Exception {
    // Arrange
    when(actuatorTypeService.getActuatorTypeByID(any())).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(get("/actuator-types/1")
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
    ActuatorType actuatorType = mock(ActuatorType.class);
    ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO("Test", "Test", "Celsius");
    ActuatorTypeDataDTO actuatorTypeDataDTO = new ActuatorTypeDataDTO("Test", "Test");
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonContent = objectMapper.writeValueAsString(actuatorTypeDataDTO);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorType.getID()).thenReturn(actuatorTypeID);
    when(actuatorTypeID.getID()).thenReturn("1");
    when(actuatorTypeService.createActuatorType(any(), any())).thenReturn(actuatorType);
    when(actuatorTypeAssembler.domainToDTO(actuatorType)).thenReturn(actuatorTypeDTO);

    // Act & Assert
    mockMvc.perform(post("/actuator-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.actuatorTypeDescription").exists())
        .andExpect(jsonPath("$.unit").exists())
        .andExpect(jsonPath("$._links.self.href").exists())
        .andExpect(header().string("Location", containsString("/actuator-types/1")));
  }

  /**
   * This test case verifies that the ActuatorTypeController returns a bad request status when an
   * invalid actuator type is added.
   */
  @Test
  void shouldReturnBadRequest_WhenInvalidActuatorTypeAdded() throws Exception {
    ActuatorTypeDataDTO actuatorTypeDataDTO = new ActuatorTypeDataDTO("", "");
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonContent = objectMapper.writeValueAsString(actuatorTypeDataDTO);
    // Act & Assert
    mockMvc.perform(post("/actuator-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }
}