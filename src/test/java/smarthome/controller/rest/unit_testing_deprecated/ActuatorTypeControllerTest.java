/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.rest.unit_testing_deprecated;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import smarthome.service.IActuatorTypeService;
import smarthome.utils.dto.ActuatorTypeDTO;


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
}