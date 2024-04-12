package smartHome.domain.deviceType;

import smartHome.valueObject.TypeDescription;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class DeviceTypeFactoryImpTest {

    /**
     * Should create device type when create device type is called with valid parameters.
     */
    @Test
    void shouldCreateDeviceType_WhenCreateDeviceTypeIsCalledWithValidParameters() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        DeviceTypeFactoryImpl factory = new DeviceTypeFactoryImpl();

        // Act
        DeviceType deviceType = factory.createDeviceType(typeDescriptionDouble);

        // Assert
        assertNotNull(deviceType);
    }

}
