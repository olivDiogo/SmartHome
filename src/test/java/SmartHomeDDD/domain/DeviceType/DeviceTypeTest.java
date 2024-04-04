package SmartHomeDDD.domain.DeviceType;

import SmartHomeDDD.domain.ActuatorType.ActuatorType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import SmartHomeDDD.valueObject.TypeDescription;
import org.mockito.MockedConstruction;
import SmartHomeDDD.valueObject.DeviceTypeID;


import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

public class DeviceTypeTest {

    /**
     * Should create an instance of {@link DeviceType} when the constructor attributes are valid.
     */
    @Test
    public void shouldCreateInstanceOfDeviceType_whenConstructorAttributesAreValid() {
        // Arrange
        String description = "Device Type Description";
        TypeDescription deviceTypeDescription = new TypeDescription(description);

        //Act
        DeviceType deviceType = new DeviceType(deviceTypeDescription);

        // Assert
        assertNotNull(deviceType);
    }

    /**
     * Should throw an exception when the device type description is null.
     */
    @Test
    public void shouldThrowException_whenDeviceTypeDescriptionIsNull() {
        // Arrange
        TypeDescription deviceTypeDescription = null;

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new DeviceType(deviceTypeDescription);
        });

        // Assert
        assertEquals("Device type description cannot be null.", exception.getMessage());
    }

    /**
     * Should return the device type ID.
     */
    @Test
    public void shouldReturnDeviceTypeID_whenGetIdIsCalled() {
        // Arrange
        String description = "Device Type Description";
        TypeDescription deviceTypeDescription = new TypeDescription(description);

        DeviceType deviceType = new DeviceType(deviceTypeDescription);
        // Act
        DeviceTypeID result = deviceType.getID();

        // Assert
        assertNotNull(result);
    }

    /**
     * Should return true when the instances are the same object.
     */
    @Test
    public void shouldReturnTrue_whenInstancesAreSameObject() {
        // Arrange
        String description = "Device Type Description";
        TypeDescription deviceTypeDescription = new TypeDescription(description);

        DeviceType deviceType = new DeviceType(deviceTypeDescription);

        // Act
        boolean result = deviceType.equals(deviceType);

        // Assert
        assertTrue(result);
    }

    /**
     * Should return true when the instances are equal.
     */
    @Test
    public void shouldReturnTrue_whenInstancesAreEqual() {
        // Arrange
        String description = "Device Type Description";
        TypeDescription deviceTypeDescription1 = new TypeDescription(description);
        TypeDescription deviceTypeDescription2 = new TypeDescription(description);

        try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn("123");
        })) {
            DeviceType deviceType1 = new DeviceType(deviceTypeDescription1);
            DeviceType deviceType2 = new DeviceType(deviceTypeDescription2);

            // Act
            boolean result = deviceType1.equals(deviceType2);

            // Assert
            assertTrue(result);
        }
    }

    /**
     * Should return false when the object is not an instance of {@link DeviceType}.
     */
    @Test
    public void shouldReturnFalse_whenObjectIsNotInstanceOfDeviceType() {
        // Arrange
        String description = "Device Type Description";
        TypeDescription deviceTypeDescription = new TypeDescription(description);

        DeviceType deviceType = new DeviceType(deviceTypeDescription);
        ActuatorType actuatorType = new ActuatorType(deviceTypeDescription);

        // Act
        boolean result = deviceType.equals(actuatorType);

        // Assert
        assertFalse(result);
    }

    /**
     * Should return int hash code.
     */
    @Test
    public void shouldReturnIntHashCode_whenHashCodeIsCalled() {
        // Arrange
        String description = "Device Type Description";
        TypeDescription deviceTypeDescription = new TypeDescription(description);

        DeviceType deviceType = new DeviceType(deviceTypeDescription);

        // Act
        int result = deviceType.hashCode();

        // Assert
        assertNotNull(result);
    }

    /**
     * Test case to verify the behavior of getDescription() method in DeviceType class.
     */
    @Test
    void shouldReturnDeviceTypeDescription_whenGetDescriptionIsCalled() {
        // Arrange
        String description = "Device Type Description";
        TypeDescription deviceTypeDescription = new TypeDescription(description);
        DeviceType deviceType = new DeviceType(deviceTypeDescription);

        // Act
        TypeDescription result = deviceType.getDescription();

        // Assert
        assertEquals(description, result.toString());
    }
}