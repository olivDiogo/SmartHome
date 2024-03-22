package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the SensorModelID class.
 */
class SensorModelIDTest {

    /**
     * Test case: Should get a valid object when using a valid string in the constructor.
     */
    @Test
    void shouldGetValidObject_whenUsingValidStringInConstructor() {
        // Arrange
        String sensorModelID = "SensorModelXPTO";

        // Act
        new SensorModelID(sensorModelID);
    }

    /**
     * Test case: Should throw IllegalArgumentException when using null string in constructor.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenUsingNullStringInConstructor() {
        // Arrange
        String sensorModelID = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new SensorModelID(sensorModelID));
    }

    /**
     * Test case: Should throw IllegalArgumentException when using empty string in constructor.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenUsingEmptyStringInConstructor() {
        // Arrange
        String sensorModelID = "";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new SensorModelID(sensorModelID));
    }

    /**
     * Test case: Should throw IllegalArgumentException when using blank string in constructor.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenUsingBlankStringInConstructor() {
        // Arrange
        String sensorModelID = " ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new SensorModelID(sensorModelID));
    }

    /**
     * Test case: Should get SensorModelID when using valid string in constructor.
     */
    @Test
    void shouldGetSensorID_whenUsingValidStringInConstructor() {
        // Arrange
        String sensorModelID = "SensorModelXPTO";

        // Act
        SensorModelID sensorModelIDObject = new SensorModelID(sensorModelID);

        // Assert
        assertEquals(sensorModelID, sensorModelIDObject.getId());
    }

    /**
     * Test case: Should return true when comparing two equal SensorModelID objects.
     */
    @Test
    void shouldReturnTrue_whenComparingTwoEqualSensorModelID() {
        // Arrange
        String sensorModelID = "SensorModelXPTO";
        SensorModelID sensorModelID1 = new SensorModelID(sensorModelID);
        SensorModelID sensorModelID2 = new SensorModelID(sensorModelID);

        // Act
        boolean result = sensorModelID1.equals(sensorModelID2);

        // Assert
        assertTrue(result);
    }

    /**
     * Test case: Should return false when comparing two different SensorModelID objects.
     */
    @Test
    void shouldReturnFalse_whenComparingTwoDifferentSensorModelID() {
        // Arrange
        String sensorModelID1 = "SensorModelXPTO";
        String sensorModelID2 = "SensorModelABC";
        SensorModelID sensorModelIDObject1 = new SensorModelID(sensorModelID1);
        SensorModelID sensorModelIDObject2 = new SensorModelID(sensorModelID2);

        // Act
        boolean result = sensorModelIDObject1.equals(sensorModelIDObject2);

        // Assert
        assertFalse(result);
    }

    /**
     * Test case: Should return true when comparing the same SensorModelID object.
     */
    @Test
    void shouldReturnTrue_whenComparingTheSameSensorModelID() {
        // Arrange
        String sensorModelID = "SensorModelXPTO";
        SensorModelID sensorModelIDObject = new SensorModelID(sensorModelID);

        // Act
        boolean result = sensorModelIDObject.equals(sensorModelIDObject);

        // Assert
        assertTrue(result);
    }

    /**
     * Test case: Should return a hash code when calling hashCode method.
     */
    @Test
    void shouldReturnHashCode_whenCallingHashCodeMethod() {
        // Arrange
        String sensorModelID = "SensorModelXPTO";
        SensorModelID sensorModelIDObject = new SensorModelID(sensorModelID);

        // Act
        int hashCode = sensorModelIDObject.hashCode();

        // Assert
        assertNotNull(hashCode);
    }



}
