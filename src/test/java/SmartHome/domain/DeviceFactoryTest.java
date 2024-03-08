package SmartHome.domain;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;

/**
 * Test suite for the {@link DeviceFactory} class.
 */
class DeviceFactoryTest {

    /**
     * Test to check if the constructor works.
     */
    @Test
    void createDeviceFactory(){
        //Act
        new DeviceFactory();
    }

    /**
     * Test to check if the createDevice method returns a mocked device.
     */
    @Test
    void createDeviceShouldReturnMockedDevice(){
        //Arrange
        String name = "Device";
        DeviceFactory deviceFactory = new DeviceFactory();

        try (MockedConstruction<Device> mockedDevice = mockConstruction(Device.class)){
            //Act
            Device device = deviceFactory.createDevice(name);

            //Assert
            assertTrue(mockedDevice.constructed().contains(device));
        }
    }
}