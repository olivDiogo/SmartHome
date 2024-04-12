package smartHome.domain.deviceType;

import smartHome.domain.actuatorType.ActuatorType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smartHome.valueObject.DeviceTypeID;
import smartHome.valueObject.TypeDescription;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DeviceTypeTest {

    /**
     * Should create an instance of DeviceType when the constructor attributes are valid.
     */
    @Test
    void shouldCreateInstanceOfDeviceType_whenConstructorAttributesAreValid() {
        // Arrange
        TypeDescription deviceTypeDescription = mock(TypeDescription.class);

        try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            // Act
            DeviceType deviceType = new DeviceType(deviceTypeDescription);

            // Assert
            assertNotNull(deviceType);

        }
    }

    /**
     * Should throw an exception when the device type description is null.
     */
    @Test
    void shouldThrowException_whenDeviceTypeDescriptionIsNull() {
        // Arrange
        TypeDescription deviceTypeDescription = null;

        String expectedMessage = "Device type description cannot be null.";

        try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            //Act + Assert

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new DeviceType(deviceTypeDescription);
            });

            assertEquals(expectedMessage, exception.getMessage());

        }
    }

    /**
     * Should return the device type ID.
     */
    @Test
    void shouldReturnDeviceTypeID_whenGetIdIsCalled() {
        // Arrange
        TypeDescription deviceTypeDescription = mock(TypeDescription.class);

        String expectedId = "123";

        try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            DeviceType deviceType = new DeviceType(deviceTypeDescription);

            // Act
            DeviceTypeID result = deviceType.getID();

            // Assert
            assertEquals(expectedId, result.getID());
        }
    }

    /**
     * Should return true when the instances are the same object.
     */
    @Test
    void shouldReturnTrue_whenInstancesAreSameObject() {
        // Arrange
        TypeDescription deviceTypeDescription = mock(TypeDescription.class);

        try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            DeviceType deviceType = new DeviceType(deviceTypeDescription);

            // Act
            boolean result = deviceType.equals(deviceType);

            // Assert
            assertTrue(result);
        }
    }

    /**
     * Should return true when the instances are equal.
     */
    @Test
    void shouldReturnFalse_whenInstancesAreNotEqual() {
        // Arrange
        TypeDescription deviceTypeDescription1 = mock(TypeDescription.class);
        TypeDescription deviceTypeDescription2 = mock(TypeDescription.class);

        try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            DeviceType deviceType1 = new DeviceType(deviceTypeDescription1);
            DeviceType deviceType2 = new DeviceType(deviceTypeDescription2);

            // Act
            boolean result = deviceType1.equals(deviceType2);

            // Assert
            assertFalse(result);
        }
    }

    /**
     * Should return false when the object is not an instance of DeviceType.
     */
    @Test
    void shouldReturnFalse_WhenObjectIsNotTheInstanceOfDeviceType() {
        // Arrange
        TypeDescription deviceTypeDescription = mock(TypeDescription.class);

        try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            DeviceType deviceType = new DeviceType(deviceTypeDescription);
            ActuatorType actuatorType = mock(ActuatorType.class);

            // Act
            boolean result = deviceType.equals(actuatorType);

            // Assert
            assertFalse(result);
        }
    }

    /**
     * Should return the device type description.
     */
    @Test
    void shouldReturnDeviceTypeDescription_whenGetDescriptionIsCalled() {
        // Arrange
        TypeDescription deviceTypeDescription = mock(TypeDescription.class);

        try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            DeviceType deviceType = new DeviceType(deviceTypeDescription);

            // Act
            TypeDescription result = deviceType.getDescription();

            // Assert
            assertEquals(deviceTypeDescription, result);
        }
    }

    /**
     * Should return the device type ID hash code.
     */
    @Test
    void shouldReturnDeviceTypeHashCode_whenHashCodeIsCalled() {
        // Arrange
        TypeDescription deviceTypeDescription = mock(TypeDescription.class);

        try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            DeviceType deviceType = new DeviceType(deviceTypeDescription);

            int expected = deviceType.getID().hashCode();

            // Act
            int result = deviceType.hashCode();

            // Assert
            assertEquals(expected, result);
        }
    }

    /**
     * Should return the device type string representation.
     */
    @Test
    void shouldReturnDeviceTypeStringRepresentation_whenToStringIsCalled() {
        // Arrange
        TypeDescription deviceTypeDescription = mock(TypeDescription.class);

        try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            DeviceType deviceType = new DeviceType(deviceTypeDescription);

            String expected = "Device Type:  Device Description= " + deviceTypeDescription.getDescription() + " ID= " + deviceType.getID().getID();

            // Act
            String result = deviceType.toString();

            // Assert
            assertEquals(expected, result);
        }
    }


}
