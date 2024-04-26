package smarthome.domain.device;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class ImpDeviceFactoryTest {

    /**
     * Test to ensure that a Device can be created successfully when createDevice is called with valid parameters.
     */
    @Test
    void shouldCreateDevice_WhenCreateDeviceIsCalledWithValidParameters() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        DeviceFactoryImpl factory = new DeviceFactoryImpl();

        //Act
        Device result = factory.createDevice(roomID, deviceName, deviceStatus, deviceTypeID);

        //Assert
        assertNotNull(result);
    }

    /**
     * Test to ensure that a Device can be created successfully when createDevice is called with ID.
     */
    @Test
    void shouldCreateDevice_WhenCreateDeviceIsCalledWithID() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);
        DeviceID deviceID = mock(DeviceID.class);

        DeviceFactoryImpl factory = new DeviceFactoryImpl();

        //Act
        Device result = factory.createDevice(deviceID, roomID, deviceName, deviceStatus, deviceTypeID);

        //Assert
        assertNotNull(result);
    }
}