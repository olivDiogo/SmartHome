package smartHome.domain.device;

import smartHome.valueObject.DeviceName;
import smartHome.valueObject.DeviceStatus;
import smartHome.valueObject.DeviceTypeID;
import smartHome.valueObject.RoomID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
}