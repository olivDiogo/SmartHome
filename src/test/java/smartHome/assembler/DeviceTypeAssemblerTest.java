package smartHome.assembler;

import smartHome.dto.DeviceTypeDTO;
import smartHome.domain.deviceType.DeviceType;
import smartHome.valueObject.DeviceTypeID;
import smartHome.valueObject.TypeDescription;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/** Test class for DeviceTypeAssembler. */
class DeviceTypeAssemblerTest {

  @Test
  void shouldReturnDeviceTypeAssemblerDTO_WhenDeviceTypeIsValid() {
    // Arrange
    String deviceTypeID = "Switch Device";
    String deviceTypeDescription = "Switch Device Description";

    DeviceType deviceType = mock(DeviceType.class);
    when(deviceType.getID()).thenReturn(mock(DeviceTypeID.class));
    when(deviceType.getID().toString()).thenReturn(deviceTypeID);
    when(deviceType.getDescription()).thenReturn(mock(TypeDescription.class));
    when(deviceType.getDescription().toString()).thenReturn(deviceTypeDescription);

    DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();
    String expected = deviceTypeID + " " + deviceTypeDescription;
    // Act
    DeviceTypeDTO deviceTypeDTO = deviceTypeAssembler.domainToDTO(deviceType);
    // Assert
    assertEquals(expected, deviceTypeDTO.toString());
  }

  @Test
  void shouldThrowIllegalArgumentException_WhenDeviceTypeIsNull() {
    // Arrange
    DeviceType deviceType = null;
    DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              deviceTypeAssembler.domainToDTO(deviceType);
            });
    // Assert
    assertEquals("The DeviceType cannot be null.", exception.getMessage());
  }

  @Test
  void shouldThrowIllegalArgumentException_WhenDeviceTypeListIsNull() {
    // Arrange
    List<DeviceType> deviceTypes = null;
    DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              deviceTypeAssembler.domainToDTO(deviceTypes);
            });
    // Assert
    assertEquals(
        "The list of DeviceTypes cannot be null, empty, or contain null elements.",
        exception.getMessage());
  }

  @Test
  void shouldThrowIllegalArgumentException_WhenDeviceTypeListIsEmpty() {
    // Arrange
    List<DeviceType> deviceTypes = List.of();
    DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              deviceTypeAssembler.domainToDTO(deviceTypes);
            });
    // Assert
    assertEquals(
        "The list of DeviceTypes cannot be null, empty, or contain null elements.",
        exception.getMessage());
  }

  @Test
  void shouldReturnDeviceTypeAssemblerDTO_WhenDeviceTypeListIsValid() {
    // Arrange
    String deviceTypeID = "Switch Device";
    String deviceTypeDescription = "Switch Device Description";

    DeviceType deviceType = mock(DeviceType.class);
    when(deviceType.getID()).thenReturn(mock(DeviceTypeID.class));
    when(deviceType.getID().toString()).thenReturn(deviceTypeID);
    when(deviceType.getDescription()).thenReturn(mock(TypeDescription.class));
    when(deviceType.getDescription().toString()).thenReturn(deviceTypeDescription);

    DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();
    List<DeviceType> deviceTypes = Arrays.asList(deviceType);
    DeviceTypeDTO expectedDeviceType = new DeviceTypeDTO(deviceTypeID, deviceTypeDescription);
    List<DeviceTypeDTO> expected = List.of(expectedDeviceType);

    // Act
    List<DeviceTypeDTO> deviceTypeDTOS = deviceTypeAssembler.domainToDTO(deviceTypes);

    // Assert
    assertEquals(expected.toString(), deviceTypeDTOS.toString());
  }
}
