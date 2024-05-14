package smarthome.controller.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.device_type.IDeviceTypeFactory;
import smarthome.domain.service.IDeviceTypeService;
import smarthome.domain.value_object.TypeDescription;
import smarthome.mapper.DeviceTypeAssembler;
import smarthome.utils.dto.DeviceTypeDTO;


import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DeviceTypeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private IDeviceTypeFactory deviceTypeFactory;

  @MockBean
  private IDeviceTypeService deviceTypeService;

  @MockBean
  private DeviceTypeAssembler deviceTypeAssembler;


  /**
   * Test to verify that a device type is created correctly and returned as a DTO.
   */
  @Test
  void shouldReturnDeviceTypeDTO_whenDeviceTypeIsCreated() throws Exception {
    // Arrange
    String deviceTypeDescription = "device";

    TypeDescription typeDescription = new TypeDescription(deviceTypeDescription);
    DeviceType deviceType = deviceTypeFactory.createDeviceType(typeDescription);

    DeviceTypeDTO deviceTypeDTO = new DeviceTypeDTO(deviceType.getID().toString(),
        deviceType.getDescription().toString());

    when(deviceTypeService.addDeviceType(typeDescription)).thenReturn(deviceType);
    when(deviceTypeAssembler.domainToDTO(deviceType)).thenReturn(deviceTypeDTO);

    // Act + Assert
    mockMvc.perform(post("/device-types/")
            .contentType(MediaType.APPLICATION_JSON)
            .content((deviceTypeDescription)))
        .andExpect(status().isCreated()) // Expecting a 201 status code
        .andExpect(jsonPath("$.description").value(deviceTypeDescription))
        .andExpect(jsonPath("$._links.self").exists());
  }


  /**
   * Test to verify that a device type is not created when the description is too long.
   */
  @Test
  void shouldReturnBadRequest_whenDeviceTypeDescriptionIsTooLong() throws Exception {
    // Arrange
    String deviceTypeDescription = "This is a very long description that is over 50 characters long";

    // Act + Assert
    mockMvc.perform(post("/device-types/")
            .contentType(MediaType.APPLICATION_JSON)
            .content((deviceTypeDescription)))
        .andExpect(status().isBadRequest()); // Expecting a 400 status code
  }

  /**
   * Test to verify that a device type is not created when the description is empty.
   */
  @Test
  void shouldReturnNotFound_WhenNoDevicesTypesAvailable() throws Exception {
    // Arrange
    when(deviceTypeService.getAllDeviceTypes()).thenReturn(Collections.emptyList());

    // Act + Assert
    mockMvc.perform(get("/device-types")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  /**
   * Test to verify that a device type is not created when the description is empty.
   */
  @Test
  void shouldReturnDeviceType_WhenFound() throws Exception {
    // Arrange
    String deviceTypeDescription = "device";
    DeviceType deviceType = mock(DeviceType.class);
    DeviceTypeDTO deviceTypeDTO = new DeviceTypeDTO("1", deviceTypeDescription);

    when(deviceTypeService.getDeviceTypeByID(any())).thenReturn(Optional.of(deviceType));
    when(deviceTypeAssembler.domainToDTO(deviceType)).thenReturn(deviceTypeDTO);

    // Act + Assert
    mockMvc.perform(get("/device-types/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.description").exists())
        .andExpect(jsonPath("$._links.self.href").exists());
  }

  @Test
  void shouldReturnBadRequest_whenInvalidDeviceTypeAdded() throws Exception {
    // Arrange
    String deviceTypeDescription = "device";

    // Act + Assert
    mockMvc.perform(post("/device-types/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(deviceTypeDescription))
        .andExpect(status().isBadRequest());

  }


}
