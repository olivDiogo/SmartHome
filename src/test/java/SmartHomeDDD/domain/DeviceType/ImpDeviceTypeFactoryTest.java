package SmartHomeDDD.domain.DeviceType;

import SmartHomeDDD.valueObject.TypeDescription;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

/**
 * Tests for the {@link DeviceTypeFactoryImpl} class.
 * This class contains tests to ensure that the ImpDeviceTypeFactory correctly creates device types
 * under various conditions.
 */
class ImpDeviceTypeFactoryTest {

    /**
     * Test to ensure that the {@link DeviceTypeFactoryImpl#createDeviceType(TypeDescription)} method
     * successfully creates a DeviceType object when called with valid parameters.
     * This test mocks the TypeDescription to simulate a valid scenario.
     */
    @Test
    void shouldCreateDeviceType_WhenCreateDeviceTypeIsCalledWithValidParameters() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        DeviceTypeFactoryImpl factory = new DeviceTypeFactoryImpl();

        // Act
        DeviceType deviceType = factory.createDeviceType(typeDescriptionDouble);

        // Assert
        assertEquals(typeDescriptionDouble, deviceType.getDescription());
    }

    /**
     * Test to ensure that the {@link DeviceTypeFactoryImpl#createDeviceType(TypeDescription)} method
     * throws an IllegalArgumentException when called with a null TypeDescription.
     * This tests the validation logic within the createDeviceType method to prevent null parameters.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenCreateDeviceTypeIsCalledWithNullDeviceTypeDescription() {
        // Arrange
        TypeDescription deviceTypeDescription = null;
        DeviceTypeFactoryImpl factory = new DeviceTypeFactoryImpl();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createDeviceType(deviceTypeDescription), "Factory should throw IllegalArgumentException for null DeviceTypeDescription.");
    }
}
