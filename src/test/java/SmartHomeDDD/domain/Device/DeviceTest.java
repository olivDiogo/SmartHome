package SmartHomeDDD.domain.Device;

import SmartHomeDDD.ValueObject.*;
import SmartHomeDDD.domain.Measurement.MeasurementType;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class DeviceTest {

    /**
     * Test that the Device class can be instantiated with valid parameters.
     */
    @Test
    public void shouldInstantiateANewDevice() {
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        new Device(roomID, deviceName, deviceStatus);
    }

    /**
     * Test that the Device class throws an IllegalArgumentException when the constructor is called with a null HouseID.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullHouseID() {
        //Arrange
        RoomID roomID = null;
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Device(roomID, deviceName, deviceStatus));
    }

    /**
     * Test that the Device class throws an IllegalArgumentException when the constructor is called with a null DeviceName.
     */

    @Test
    public void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullDeviceName() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = null;
        DeviceStatus deviceStatus = mock(DeviceStatus.class);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Device(roomID, deviceName, deviceStatus));
    }

    /**
     * Test that the Device class throws an IllegalArgumentException when the constructor is called with a null DeviceStatus.
     */

    @Test
    public void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullDeviceStatus() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = null;

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Device(roomID, deviceName, deviceStatus));
    }

    /**
     * Tests if the roomID is returned correctly.
     */

    @Test
    public void shouldReturnRoomID() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);

        Device device = new Device(roomID, deviceName, deviceStatus);

        //Act
        RoomID result = device.getRoomID();

        //Assert
        assertTrue(device.toString().contains(result.toString()));
    }

    /**
     * Tests if the deviceID is returned correctly.
     */
    @Test
    public void shouldReturnDeviceID() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);

        Device device = new Device(roomID, deviceName, deviceStatus);

        //Act
        DeviceID result = device.getID();

        //Assert
        assertTrue(device.toString().contains(result.toString()));
    }

    /**
     * Tests if the deviceName is returned correctly.
     */
    @Test
    public void shouldReturnDeviceName() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);

        Device device = new Device(roomID, deviceName, deviceStatus);

        //Act
        DeviceName result = device.getDeviceName();

        //Assert
        assertTrue(device.toString().contains(result.toString()));
    }

    /**
     * Tests if the deviceStatus is returned correctly.
     */
    @Test
    public void shouldReturnDeviceStatus() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);

        Device device = new Device(roomID, deviceName, deviceStatus);

        //Act
        DeviceStatus result = device.getDeviceStatus();

        //Assert
        assertTrue(device.toString().contains(result.toString()));
    }

    /**
     * Tests if the deviceStatus is changed correctly.
     */

    @Test
    public void shouldReturnChangedDeviceStatus() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceStatus deviceStatus2 = mock(DeviceStatus.class);
        Device device = new Device(roomID, deviceName, deviceStatus);

        //Act
        device.changeDeviceStatus(deviceStatus2);
        DeviceStatus result = device.getDeviceStatus();

        //Assert
        assertTrue(device.toString().contains(result.toString()));
        assertEquals(result, deviceStatus2);
    }

    @Test
    public void shouldThrowIllegalArgumentException_WhenChangeDeviceStatusIsCalledWithNullDeviceStatus() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceStatus deviceStatus2 = null;
        Device device = new Device(roomID, deviceName, deviceStatus);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> device.changeDeviceStatus(deviceStatus2));


    @Test
    public void shouldReturnTrueWhenGivenSameObject() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        Device device = new Device(roomID, deviceName, deviceStatus);
        //Act
        boolean result = device.equals(device);
        //Assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenComparingTwoObjectsWithSameID() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        Device device = new Device(roomID, deviceName, deviceStatus);
        Device device2 = new Device(roomID, deviceName, deviceStatus);

        //Act
        boolean result = device.equals(device2);
        //Assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenComparingTwoObjectsWithDifferentID() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        RoomID roomID2 = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        Device device = new Device(roomID, deviceName, deviceStatus);
        Device device2 = new Device(roomID2, deviceName, deviceStatus);

        //Act
        boolean result = device.equals(device2);
        //Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenComparingObjectWithNull() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        Device device = new Device(roomID, deviceName, deviceStatus);
        //Act
        boolean result = device.equals(null);
        //Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnExpectedStringWhenToStringIsCalled() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        Device device = new Device(roomID, deviceName, deviceStatus);
        String expectedString = "Device{" + "_roomID=" + roomID + ", _deviceID=" + device.getID() + ", _deviceName=" + deviceName + ", _deviceStatus=" + deviceStatus + '}';
        //Act
        String result = device.toString();
        //Assert
        assertEquals(expectedString, result);
    }


}
