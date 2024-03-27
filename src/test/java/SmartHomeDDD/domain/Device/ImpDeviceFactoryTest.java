package SmartHomeDDD.domain.Device;

import SmartHomeDDD.valueObject.DeviceName;
import SmartHomeDDD.valueObject.DeviceStatus;
import SmartHomeDDD.valueObject.DeviceTypeID;
import SmartHomeDDD.valueObject.RoomID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ImpDeviceFactoryTest {

    /**
     * Test to ensure that a Device can be created successfully when createDevice is called with valid parameters.
     */
    @Test
    public void shouldCreateDevice_WhenCreateDeviceIsCalledWithValidParameters () {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        ImpDeviceFactory factory = new ImpDeviceFactory();

        //Act & Assert
        factory.createDevice(roomID, deviceName, deviceStatus, deviceTypeID);
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when RoomID is null.
      */
    @Test
    public void shouldThrowIllegalArgumentException_WhenCreateDeviceIsCalledWithNullRoomID () {
        //Arrange
        RoomID roomID = null;
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        ImpDeviceFactory factory = new ImpDeviceFactory();

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createDevice(roomID, deviceName, deviceStatus, deviceTypeID), "Factory should throw IllegalArgumentException for null RoomID.");
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when DeviceName is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenCreateDeviceIsCalledWithNullDeviceName () {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = null;
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        ImpDeviceFactory factory = new ImpDeviceFactory();

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createDevice(roomID, deviceName, deviceStatus,deviceTypeID), "Factory should throw IllegalArgumentException for null DeviceName.");
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when DeviceStatus is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenCreateDeviceIsCalledWithNullDeviceStatus () {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = null;
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        ImpDeviceFactory factory = new ImpDeviceFactory();

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createDevice(roomID, deviceName, deviceStatus, deviceTypeID), "Factory should throw IllegalArgumentException for null DeviceStatus.");
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when DeviceTypeID is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenCreateDeviceIsCalledWithNullDeviceTypeID () {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = null;

        ImpDeviceFactory factory = new ImpDeviceFactory();

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createDevice(roomID, deviceName, deviceStatus, deviceTypeID), "Factory should throw IllegalArgumentException for null DeviceTypeID.");
    }

}