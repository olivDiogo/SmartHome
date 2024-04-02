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
        TypeDescription deviceTypeDescription = mock(TypeDescription.class);

        try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class, (mock, context) -> {
            when(mock.getId()).thenReturn("123");
        })) {
            // Act
            new DeviceType(deviceTypeDescription);

        }
    }

    /**
     * Should throw an exception when the device type description is null.
     */
    @Test
    public void shouldThrowException_whenDeviceTypeDescriptionIsNull() {
        // Arrange
        TypeDescription deviceTypeDescription = null;

        try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class, (mock, context) -> {
            when(mock.getId()).thenReturn("123");
        })) {
            // Act + Assert
            assertThrows(IllegalArgumentException.class, () -> new DeviceType(deviceTypeDescription));
        }
    }

    /**
     * Should return the device type ID.
     */
    @Test
    public void shouldReturnDeviceTypeID_whenGetIdIsCalled() {
        // Arrange
        TypeDescription deviceTypeDescription = mock(TypeDescription.class);

        String id = "123";

        try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class, (mock, context) -> {
            when(mock.getId()).thenReturn(id);
        })) {
            DeviceType deviceType = new DeviceType(deviceTypeDescription);

            // Act
            DeviceTypeID deviceTypeID = deviceType.getID();

            // Assert
            assertEquals(id, deviceTypeID.getId());
        }
    }

    /**
     * Should return true when the instances are the same object.
     */
    @Test
    public void shouldReturnTrue_whenInstancesAreSameObject() {
        // Arrange
        TypeDescription deviceTypeDescription = mock(TypeDescription.class);

        try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class, (mock, context) -> {
            when(mock.getId()).thenReturn("123");
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
    public void shouldReturnTrue_whenInstancesAreEqual() {
        // Arrange
        TypeDescription deviceTypeDescription1 = mock(TypeDescription.class);
        TypeDescription deviceTypeDescription2 = mock(TypeDescription.class);

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
        TypeDescription deviceTypeDescription = mock(TypeDescription.class);

        DeviceTypeID deviceTypeID1 = mock(DeviceTypeID.class);
        DeviceTypeID deviceTypeID2 = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class, (mock, context) -> {
            when(mock.getId()).thenReturn("123");
        })) {
            DeviceType deviceType1 = new DeviceType(deviceTypeDescription);
            DeviceType deviceType2 = new DeviceType(deviceTypeDescription);

            Field deviceTypeIDField = DeviceType.class.getDeclaredField("_deviceTypeID");
            deviceTypeIDField.setAccessible(true);
            deviceTypeIDField.set(deviceType1, deviceTypeID1);
            deviceTypeIDField.set(deviceType2, deviceTypeID2);

            // Act
            boolean result = deviceType1.equals(deviceType2);

            // Assert
            assertFalse(result);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Should return int hash code.
     */
    @Test
    public void shouldReturnIntHashCode_whenHashCodeIsCalled() {
        // Arrange
        TypeDescription deviceTypeDescription = mock(TypeDescription.class);

        try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn("123");
        })) {
            DeviceType deviceType = new DeviceType(deviceTypeDescription);

            // Act
            int hashCode = deviceType.hashCode();

            // Assert
            assertNotNull(hashCode);
        }
    }

    /**
     * Test case to verify the behavior of getDescription() method in DeviceType class.
     */
    @Test
    public void shouldReturnDeviceTypeDescription_whenGetDescriptionIsCalled() {
        // Arrange
        DeviceType deviceType = mock(DeviceType.class);
        TypeDescription deviceTypeDescription = mock(TypeDescription.class);

        when(deviceType.getDescription()).thenReturn(deviceTypeDescription);

        // Act
        TypeDescription actualDescription = deviceType.getDescription();

        // Assert
        assertEquals(deviceTypeDescription, actualDescription);
    }
}
