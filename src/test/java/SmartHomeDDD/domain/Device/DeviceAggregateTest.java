package SmartHomeDDD.domain.Device;

import SmartHomeDDD.valueObject.*;
import org.junit.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

public class DeviceAggregateTest {

    /**
     * Test that the Device class can be instantiated with valid parameters.
     */
    @Test
    public void shouldInstantiateANewDevice() {
        //Arrange
        String roomIDName = "1a24";
        String name = "lamp";
        boolean status = true;
        String typeID = "123";

        RoomID roomID = new RoomID(roomIDName);
        DeviceName deviceName = new DeviceName(name);
        DeviceStatus deviceStatus = new DeviceStatus(status);
        DeviceTypeID deviceTypeID = new DeviceTypeID(typeID);

        //Act
        Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

        //Assert
        assertNotNull(device);
    }

    /**
     * Tests if the roomID is returned correctly.
     */

    @Test
    public void shouldReturnRoomID() {
        //Arrange
        String roomIDName = "1a24";
        String name = "lamp";
        boolean status = true;
        String typeID = "123";

        RoomID roomID = new RoomID(roomIDName);
        DeviceName deviceName = new DeviceName(name);
        DeviceStatus deviceStatus = new DeviceStatus(status);
        DeviceTypeID deviceTypeID = new DeviceTypeID(typeID);

        Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

        //Act
        RoomID result = device.getRoomID();

        //Assert
        assertEquals(result.toString(), roomIDName);
    }

    /**
     * Tests if the deviceID is returned correctly.
     */
    @Test
    public void shouldReturnDeviceID() {
        //Arrange
        String roomIDName = "1a24";
        String name = "lamp";
        boolean status = true;
        String typeID = "123";

        RoomID roomID = new RoomID(roomIDName);
        DeviceName deviceName = new DeviceName(name);
        DeviceStatus deviceStatus = new DeviceStatus(status);
        DeviceTypeID deviceTypeID = new DeviceTypeID(typeID);


        Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

        //Act
        DeviceID result = device.getID();

        //Assert
        assertNotNull(result);

    }

    /**
     * Tests if the deviceName is returned correctly.
     */
    @Test
    public void shouldReturnDeviceName() {
        //Arrange
        String roomIDName = "1a24";
        String name = "lamp";
        boolean status = true;
        String typeID = "123";

        RoomID roomID = new RoomID(roomIDName);
        DeviceName deviceName = new DeviceName(name);
        DeviceStatus deviceStatus = new DeviceStatus(status);
        DeviceTypeID deviceTypeID = new DeviceTypeID(typeID);

        Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

        String expected = "Device name: lamp";

        //Act
        DeviceName result = device.getDeviceName();

        //Assert
        assertEquals(result.toString(), expected);
    }

    /**
     * Tests if the deviceStatus is returned correctly.
     */
    @Test
    public void shouldReturnDeviceStatus() {
        //Arrange
        String roomIDName = "1a24";
        String name = "lamp";
        boolean status = true;
        String typeID = "123";

        RoomID roomID = new RoomID(roomIDName);
        DeviceName deviceName = new DeviceName(name);
        DeviceStatus deviceStatus = new DeviceStatus(status);
        DeviceTypeID deviceTypeID = new DeviceTypeID(typeID);

        String expected = "ON";

        Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

        //Act
        DeviceStatus result = device.getDeviceStatus();

        //Assert
        assertEquals(result.toString(), expected);
    }

    /**
     * Test that the Equals method returns true when the Device is compared to itself.
     */
    @Test
    public void shouldReturnTrueWhenGivenSameObject() {
        //Arrange
        String roomIDName = "1a24";
        String name = "lamp";
        boolean status = true;
        String typeID = "123";

        RoomID roomID = new RoomID(roomIDName);
        DeviceName deviceName = new DeviceName(name);
        DeviceStatus deviceStatus = new DeviceStatus(status);
        DeviceTypeID deviceTypeID = new DeviceTypeID(typeID);


        Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

        //Act
        boolean result = device.equals(device);

        //Assert
        assertTrue(result);

    }

    /**
     * Test that the Equals method returns false when the Device is compared to a different object.
     */

    @Test
    public void shouldReturnTrueWhenComparingTwoObjectsWithSameID() {
        //Arrange
        String roomIDName = "1a24";
        String name = "lamp";
        boolean status = true;
        String typeID = "123";

        RoomID roomID = new RoomID(roomIDName);
        DeviceName deviceName = new DeviceName(name);
        DeviceStatus deviceStatus = new DeviceStatus(status);
        DeviceTypeID deviceTypeID = new DeviceTypeID(typeID);

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
        String roomIDName = "1a24";
        String roomIDName2 = "1a25";
        String name = "lamp";
        boolean status = true;
        String typeID = "123";

        RoomID roomID = new RoomID(roomIDName);
        RoomID roomID2 = new RoomID(roomIDName2);
        DeviceName deviceName = new DeviceName(name);
        DeviceStatus deviceStatus = new DeviceStatus(status);
        DeviceTypeID deviceTypeID = new DeviceTypeID(typeID);

        Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);
        Device device2 = new Device(roomID2, deviceName, deviceStatus, deviceTypeID);

        //Act
        boolean result = device.equals(device2);

        //Assert
        assertFalse(result);

    }

    /**
     * Test that the Equals method returns false when the Device is compared to a null object.
     */

    @Test
    public void shouldReturnFalseWhenComparingObjectWithNullObject() {
        //Arrange
        String roomIDName = "1a24";
        String name = "lamp";
        boolean status = true;
        String typeID = "123";

        RoomID roomID = new RoomID(roomIDName);
        DeviceName deviceName = new DeviceName(name);
        DeviceStatus deviceStatus = new DeviceStatus(status);
        DeviceTypeID deviceTypeID = new DeviceTypeID(typeID);


        Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

        //Act
        boolean result = device.equals(null);

        //Assert
        assertFalse(result);

    }

    /**
     * Test that the toString method returns the expected string.
     */

    @Test
    public void shouldReturnExpectedStringWhenToStringIsCalled() {
        //Arrange
        String roomIDName = "1a24";
        String name = "lamp";
        boolean status = true;
        String typeID = "123";

        RoomID roomID = new RoomID(roomIDName);
        DeviceName deviceName = new DeviceName(name);
        DeviceStatus deviceStatus = new DeviceStatus(status);
        DeviceTypeID deviceTypeID = new DeviceTypeID(typeID);

        Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

        String expectedString = "Device:" +
                "roomID=" + roomID +
                ", deviceID=" + device.getID() +
                ", deviceName=" + deviceName +
                ", deviceStatus=" + deviceStatus;

        //Act
        String result = device.toString();

        //Assert
        assertEquals(result, expectedString);

    }

    /**
     * Test that the deactivateDevice method returns the expected DeviceStatus.
     */
    @Test
    public void shouldReturnDeactivatedDeviceStatus_WhenDeactivateDeviceIsCalled() {

        //Arrange
        String roomIDName = "1a24";
        String name = "lamp";
        boolean status = true;
        String typeID = "123";

        RoomID roomID = new RoomID(roomIDName);
        DeviceName deviceName = new DeviceName(name);
        DeviceStatus deviceStatus = new DeviceStatus(status);
        DeviceTypeID deviceTypeID = new DeviceTypeID(typeID);

        Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

        //Act
        DeviceStatus result = device.deactivateDevice();

        //Assert
        assertEquals(false, result.getStatus());


    }

    /**
     * Test that the getDeviceTypeID method returns the expected DeviceTypeID.
     */
    @Test
    public void shouldReturnDeviceTypeID() {
        //Arrange
        String roomIDName = "1a24";
        String name = "lamp";
        boolean status = true;
        String typeID = "123";

        RoomID roomID = new RoomID(roomIDName);
        DeviceName deviceName = new DeviceName(name);
        DeviceStatus deviceStatus = new DeviceStatus(status);
        DeviceTypeID deviceTypeID = new DeviceTypeID(typeID);


        Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

        //Act
        DeviceTypeID result = device.getDeviceTypeID();

        //Assert
        assertEquals(result, deviceTypeID);

    }
}
