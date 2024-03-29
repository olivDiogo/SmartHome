package SmartHomeDDD.domain.Device;

import SmartHomeDDD.valueObject.*;
import org.junit.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeviceTest {

    /**
     * Test that the Device class can be instantiated with valid parameters.
     */
    @Test
    public void shouldInstantiateANewDevice() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            //Act
            new Device(roomID, deviceName, deviceStatus, deviceTypeID);
        }
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
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            //Act & Assert
            assertThrows(IllegalArgumentException.class, () -> new Device(roomID, deviceName, deviceStatus, deviceTypeID));
        }
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
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            //Act & Assert
            assertThrows(IllegalArgumentException.class, () -> new Device(roomID, deviceName, deviceStatus, deviceTypeID));
        }
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
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Device(roomID, deviceName, deviceStatus, deviceTypeID));
    }

    /**
     * Test that the Device class throws an IllegalArgumentException when the constructor is called with a null DeviceTypeID.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullDeviceTypeID() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = null;

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            //Act & Assert
            assertThrows(IllegalArgumentException.class, () -> new Device(roomID, deviceName, deviceStatus, deviceTypeID));
        }
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
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

            //Act
            RoomID result = device.getRoomID();

            //Assert
            assertEquals(result, roomID);
            assertTrue(device.toString().contains(result.toString()));

        }
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
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class, (mock, context) -> {
            when(mock.getId()).thenReturn("123");
        })) {
            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

            //Act
            DeviceID result = device.getID();

            //Assert
            assertEquals(result.getId(), "123");
            assertTrue(device.toString().contains(result.toString()));
        }

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
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

            //Act
            DeviceName result = device.getDeviceName();

            //Assert
            assertEquals(result, deviceName);
            assertTrue(device.toString().contains(result.toString()));
        }
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
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {

            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

            //Act
            DeviceStatus result = device.getDeviceStatus();

            //Assert
            assertEquals(result, deviceStatus);
            assertTrue(device.toString().contains(result.toString()));
        }
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
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

            //Act
            device.changeDeviceStatus(deviceStatus2);
            DeviceStatus result = device.getDeviceStatus();

            //Assert
            assertTrue(device.toString().contains(result.toString()));
            assertEquals(result, deviceStatus2);
        }
    }

    /**
     * Test that the Device class throws an IllegalArgumentException when the changeDeviceStatus is called with a null DeviceStatus.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenChangeDeviceStatusIsCalledWithNullDeviceStatus() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceStatus deviceStatus2 = null;
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

            //Act & Assert
            assertThrows(IllegalArgumentException.class, () -> device.changeDeviceStatus(deviceStatus2));
        }
    }

    /**
     * Test that the Equals method returns true when the Device is compared to itself.
     */
    @Test
    public void shouldReturnTrueWhenGivenSameObject() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

            //Act
            boolean result = device.equals(device);

            //Assert
            assertTrue(result);
        }
    }

    /**
     * Test that the Equals method returns false when the Device is compared to a different object.
     */

    @Test
    public void shouldReturnTrueWhenComparingTwoObjectsWithSameID() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> mocked = mockConstruction(DeviceID.class, (mock, context) ->
                when(mock.toString()).thenReturn("1"))) {

            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);
            Device device2 = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

            //Act
            boolean result = device.equals(device2);

            //Assert
            assertTrue(result);
        }
    }

    /**
     * Test that the Equals method returns false when the Device is compared to a different objects.
     */
    @Test
    public void shouldReturnFalseWhenComparingTwoObjectsWithDifferentID() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        RoomID roomID2 = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> mocked = mockConstruction(DeviceID.class)) {

            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);
            Device device2 = new Device(roomID2, deviceName, deviceStatus, deviceTypeID);

            //Act
            boolean result = device.equals(device2);

            //Assert
            assertFalse(result);
        }
    }

    /**
     * Test that the Equals method returns false when the Device is compared to a null object.
     */

    @Test
    public void shouldReturnFalseWhenComparingObjectWithNull() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> mocked = mockConstruction(DeviceID.class)) {

            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

            //Act
            boolean result = device.equals(null);

            //Assert
            assertFalse(result);
        }
    }

    /**
     * Test that the toString method returns the expected string.
     */

    @Test
    public void shouldReturnExpectedStringWhenToStringIsCalled() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> mocked = mockConstruction(DeviceID.class)) {

            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

            String expectedString = "Device{" + "_roomID=" + roomID + ", _deviceID=" + device.getID() + ", _deviceName=" + deviceName + ", _deviceStatus=" + deviceStatus + '}';

            //Act
            String result = device.toString();

            //Assert
            assertEquals(expectedString, result);
        }
    }

    /**
     * Test that the deactivateDevice method returns the expected DeviceStatus.
     */
    /*@Test
    public void shouldReturnDeactivatedDeviceStatus_WhenDeactivateDeviceIsCalled() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            MockedConstruction<DeviceStatus> mocked = mockConstruction(DeviceStatus.class, (mock, context) ->
                    when(mock.toString()).thenReturn("OFF"));
            {
                //Act
                DeviceStatus result = device.deactivateDevice();
                //Assert
                List<DeviceStatus> results = mocked.constructed();
                assertEquals(1, results.size());
                assertTrue(results.contains(result));
            }
        }
    }*/

    /**
     * Test that the getDeviceTypeID method returns the expected DeviceTypeID.
     */
    @Test
    public void shouldReturnDeviceTypeID() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> mocked = mockConstruction(DeviceID.class)) {

            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

            //Act
            DeviceTypeID result = device.getDeviceTypeID();

            //Assert
            assertEquals(result, deviceTypeID);
        }
    }
}
