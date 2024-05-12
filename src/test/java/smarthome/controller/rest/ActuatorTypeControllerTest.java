package smarthome.controller.rest;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.service.IActuatorTypeService;
import smarthome.domain.value_object.ActuatorTypeID;
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
    ActuatorTypeDTO actuatorTypeDTO = mock(ActuatorTypeDTO.class);
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
        .andExpect(status().isNoContent());
  }

  /**
   * This test case verifies that the ActuatorTypeController returns an actuator type when it is
   * found.
   */
  @Test
  void shouldReturnActuatorType_WhenFound() throws Exception {
    // Arrange
    ActuatorType actuatorType = mock(ActuatorType.class);
    ActuatorTypeDTO actuatorTypeDTO = mock(ActuatorTypeDTO.class);
    Link selfLink = Link.of("/actuator-types/1").withSelfRel();
    when(actuatorTypeService.getActuatorTypeByID(any())).thenReturn(Optional.of(actuatorType));
    when(actuatorTypeAssembler.domainToDTO(actuatorType)).thenReturn(actuatorTypeDTO);
    when(actuatorTypeDTO.getActuatorTypeID()).thenReturn("1");
    when(actuatorTypeDTO.getActuatorTypeDescription()).thenReturn("Test");
    when(actuatorTypeDTO.getUnit()).thenReturn("Test");
    when(actuatorTypeDTO.add(any(Link.class))).thenReturn(actuatorTypeDTO);
    when(actuatorTypeDTO.getLinks()).thenReturn(Links.of(selfLink));

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
    ActuatorTypeDTO actuatorTypeDTO = mock(ActuatorTypeDTO.class);
    Link selfLink = Link.of("/actuator-types/add-actuator-type").withSelfRel();
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorType.getID()).thenReturn(actuatorTypeID); // Mock the getID method
    when(actuatorTypeID.getID()).thenReturn("1"); // Mock the getID method of ActuatorTypeID
    when(actuatorTypeService.createActuatorType(any(), any())).thenReturn(actuatorType);
    when(actuatorTypeAssembler.domainToDTO(actuatorType)).thenReturn(actuatorTypeDTO);
    when(actuatorTypeDTO.getActuatorTypeID()).thenReturn("1");
    when(actuatorTypeDTO.getActuatorTypeDescription()).thenReturn("Test");
    when(actuatorTypeDTO.getUnit()).thenReturn("Test");
    when(actuatorTypeDTO.getLinks()).thenReturn(Links.of(selfLink));

    // Act & Assert
    mockMvc.perform(post("/actuator-types/add-actuator-type")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"actuatorTypeDescription\": \"Test\", \"unit\": \"Test\"}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.actuatorTypeDescription").exists())
        .andExpect(jsonPath("$.unit").exists())
        .andExpect(jsonPath("$._links.self.href").exists());
  }

  @Test
  void shouldReturnBadRequest_WhenInvalidActuatorTypeAdded() throws Exception {
    // Act & Assert
    mockMvc.perform(post("/actuator-types/add-actuator-type")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"actuatorTypeDescription\": \"\", \"unit\": \"\"}"))
        .andExpect(status().isBadRequest());
  }
}