package SmartHomeDDD.domain.DeviceType;

import SmartHomeDDD.domain.ActuatorType.ActuatorType;
import SmartHomeDDD.domain.Unit.Unit;
import SmartHomeDDD.valueObject.DeviceTypeID;
import SmartHomeDDD.valueObject.TypeDescription;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeviceTypeAggregateTest {

    /**
     * Should create an instance of {@link DeviceType} when the constructor attributes are valid.
     */
    @Test
     void shouldCreateInstanceOfDeviceType_whenConstructorAttributesAreValid() {
        // Arrange
        String description = "Device Type Description";
        TypeDescription deviceTypeDescription = new TypeDescription(description);

        //Act
        DeviceType deviceType = new DeviceType(deviceTypeDescription);

        // Assert
        assertNotNull(deviceType);
    }


    /**
     * Should return the device type ID.
     */
    @Test
     void shouldReturnDeviceTypeID_whenGetIdIsCalled() {
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
     void shouldReturnTrue_whenInstancesAreSameObject() {
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
     * Should return false when the objects are not the same.
     */
    @Test
     void shouldReturnFalse_whenObjectIsNotTheSame () {
        // Arrange
        String description = "Device Type Description";
        String description2 = "DeviceType";
        TypeDescription deviceTypeDescription = new TypeDescription(description);
        TypeDescription deviceTypeDescription1 = new TypeDescription(description2);

        DeviceType deviceType = new DeviceType(deviceTypeDescription);
        DeviceType deviceType1 = new DeviceType(deviceTypeDescription1);

        // Act
        boolean result = deviceType.equals(deviceType1);

        // Assert
        assertFalse(result);
    }

    /**
     * Should return false when the object is not an instance of DeviceType.
     */
    @Test
     void shouldReturnFalse_whenObjectIsNotInstanceOfDeviceType() {
         // Arrange
         String description = "Device Type Description";
         TypeDescription deviceTypeDescription = new TypeDescription(description);

         ActuatorType actuatorType = mock(ActuatorType.class);

         DeviceType deviceType = new DeviceType(deviceTypeDescription);


         // Act
         boolean result = deviceType.equals(actuatorType);

         // Assert
         assertFalse(result);
     }


     /**
     * Test case to verify the behavior of getDescription method in DeviceType class.
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

    /**
     * Test case to verify the behavior of hashCode method in DeviceType class.
     */
    @Test
    void shouldReturnHashCode_whenHashCodeIsCalled() {
        // Arrange
        String description = "Device Type Description";
        TypeDescription deviceTypeDescription = new TypeDescription(description);

        DeviceType deviceType = new DeviceType(deviceTypeDescription);

        DeviceTypeID deviceTypeID = deviceType.getID();

        int expected = deviceTypeID.hashCode();

        // Act
        int result = deviceType.hashCode();

        // Assert
        assertEquals(expected, result);

    }

    /**
     * Test case to verify the behavior of toString method in DeviceType class.
     */
    @Test
    void shouldReturnStringRepresentation_whenToStringIsCalled() {
        // Arrange
        String description = "Device Type Description";
        TypeDescription deviceTypeDescription = new TypeDescription(description);

        DeviceType deviceType = new DeviceType(deviceTypeDescription);

        String expected = "Device Type:  Device Description= " + description + " ID= " + deviceType.getID().getId();

        // Act
        String result = deviceType.toString();

        // Assert
        assertEquals(expected, result);
    }
}