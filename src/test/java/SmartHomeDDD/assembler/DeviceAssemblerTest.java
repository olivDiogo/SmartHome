package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.DeviceDTO;
import SmartHomeDDD.domain.Device.Device;
import SmartHomeDDD.valueObject.DeviceID;
import SmartHomeDDD.valueObject.DeviceName;
import SmartHomeDDD.valueObject.DeviceStatus;
import SmartHomeDDD.valueObject.RoomID;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeviceAssemblerTest {

    /**
     * Test if the domainToDTO method returns a DeviceDTO object when the device is valid.
     */
    @Test
    void shouldReturnADeviceDTO_WhenGivenADevice() {
        //Arrange
        String deviceID = "1";
        String roomID = "1";
        String deviceName = "Test Device";
        String deviceStatus = "Off";

        Device device = mock(Device.class);

        when(device.getID()).thenReturn(mock(DeviceID.class));
        when(device.getID().toString()).thenReturn(deviceID);

        when(device.getRoomID()).thenReturn(mock(RoomID.class));
        when(device.getRoomID().toString()).thenReturn(roomID);

        when(device.getDeviceName()).thenReturn(mock(DeviceName.class));
        when(device.getDeviceName().toString()).thenReturn(deviceName);

        when(device.getDeviceStatus()).thenReturn(mock(DeviceStatus.class));
        when(device.getDeviceStatus().toString()).thenReturn(deviceStatus);

        DeviceAssembler deviceAssembler = new DeviceAssembler();

        DeviceDTO expectedDevice = new DeviceDTO(deviceID, roomID, deviceName, deviceStatus);

        //Act
        DeviceDTO deviceDTO = deviceAssembler.domainToDTO(device);

        //Assert
        assertEquals(expectedDevice.deviceID, deviceDTO.deviceID);
        assertEquals(expectedDevice.roomID, deviceDTO.roomID);
        assertEquals(expectedDevice.deviceName, deviceDTO.deviceName);
        assertEquals(expectedDevice.deviceStatus, deviceDTO.deviceStatus);
    }

    /**
     * Test if the domainToDTO method throws an IllegalArgumentException when the device is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullDevice() {
        //Arrange
        Device device = null;
        DeviceAssembler deviceAssembler = new DeviceAssembler();

        String expectedMessage = "The Device cannot be null.";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> deviceAssembler.domainToDTO(device));

        //Assert
        String result = exception.getMessage();
        assertEquals(expectedMessage, result);
    }

    /**
     * Test if the domainToDTO method returns a list of DeviceDTO objects when the list of devices is valid.
     */
    @Test
    void shouldReturnListOfDeviceDTO_WhenGivenListOfDevices() {
        //Arrange
        String deviceID = "1";
        String roomID = "1";
        String deviceName = "Test Device";
        String deviceStatus = "Off";

        Device device = mock(Device.class);

        when(device.getID()).thenReturn(mock(DeviceID.class));
        when(device.getID().toString()).thenReturn(deviceID);

        when(device.getRoomID()).thenReturn(mock(RoomID.class));
        when(device.getRoomID().toString()).thenReturn(roomID);

        when(device.getDeviceName()).thenReturn(mock(DeviceName.class));
        when(device.getDeviceName().toString()).thenReturn(deviceName);

        when(device.getDeviceStatus()).thenReturn(mock(DeviceStatus.class));
        when(device.getDeviceStatus().toString()).thenReturn(deviceStatus);

        List<Device> devices = new ArrayList<>();
        devices.add(device);

        DeviceAssembler deviceAssembler = new DeviceAssembler();

        DeviceDTO expectedDevice = new DeviceDTO(deviceID, roomID, deviceName, deviceStatus);

        //Act
        List<DeviceDTO> deviceDTOs = deviceAssembler.domainToDTO(devices);

        //Assert
        assertEquals(expectedDevice.deviceID, deviceDTOs.get(0).deviceID);
        assertEquals(expectedDevice.roomID, deviceDTOs.get(0).roomID);
        assertEquals(expectedDevice.deviceName, deviceDTOs.get(0).deviceName);
        assertEquals(expectedDevice.deviceStatus, deviceDTOs.get(0).deviceStatus);
    }

    /**
     * Test if the domainToDTO method throws an IllegalArgumentException when the list of devices is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullDeviceList() {
        //Arrange
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        List<Device> devices = null;

        String expectedMessage = "The list of Devices cannot be null or empty.";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> deviceAssembler.domainToDTO(devices));

        //Assert
        String result = exception.getMessage();
        assertEquals(expectedMessage, result);
    }

    /**
     * Test if the domainToDTO method throws an IllegalArgumentException when the list of devices is empty.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenEmptyDeviceList() {
        //Arrange
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        List<Device> devices = new ArrayList<>();

        String expectedMessage = "The list of Devices cannot be null or empty.";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> deviceAssembler.domainToDTO(devices));

        //Assert
        String result = exception.getMessage();
        assertEquals(expectedMessage, result);
    }

    /**
     * Test if the domainToDTO method throws an IllegalArgumentException when the list of devices contains null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenDeviceListWithNull() {
        //Arrange
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        Device device = mock(Device.class);
        List<Device> devices = new ArrayList<>();
        devices.add(device);
        devices.add(null);

        String expectedMessage = "The list of Devices cannot be null or empty.";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> deviceAssembler.domainToDTO(devices));

        //Assert
        String result = exception.getMessage();
        assertEquals(expectedMessage, result);
    }
}