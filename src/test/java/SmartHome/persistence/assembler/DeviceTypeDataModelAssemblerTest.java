package smartHome.persistence.assembler;

import org.junit.jupiter.api.Test;
import smartHome.domain.deviceType.IDeviceTypeFactory;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class DeviceTypeDataModelAssemblerTest {

  /** Test to check if the constructor throws an exception when the device type factory is null */
  @Test
  void shouldThrowException_whenDeviceTypeFactoryIsNull() {
    // Arrange
    IDeviceTypeFactory deviceTypeFactory = null;

    String expected = "Device Type Factory cannot be null";

    // Act
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new DeviceTypeDataModelAssembler(deviceTypeFactory));

    // Assert
    String actual = exception.getMessage();

    assertEquals(expected, actual);
  }

  /**
   * Test to check if the constructor instantiates the DeviceTypeDataModelAssembler when the device type factory is valid
   */
  @Test
  void shouldInstantiateDeviceTypeDataModelAssembler_whenDeviceTypeFactoryIsValid() {
    // Arrange
    IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);

    // Act
    DeviceTypeDataModelAssembler deviceTypeDataModelAssembler =
        new DeviceTypeDataModelAssembler(deviceTypeFactory);

    // Assert
    assertNotNull(deviceTypeDataModelAssembler);
  }
}
