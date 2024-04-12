package smartHome.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorModelNameTest {

    /**
     * Test the constructor of the class SensorModelName.
     */
    @Test
    public void shouldInstantiateSensorModelName_whenGivenValidParameters() {
        // Arrange
        SensorModelName sensorModelName;
        String name = "SensorModelName";

        // Act
        sensorModelName = new SensorModelName(name);

        // Assert
        assertNotNull(sensorModelName);
    }

    /**
     * Test the constructor of the class SensorModelName with an empty name.
     */
    @Test
    public void shouldThrowException_whenNameIsEmpty() {
        // Arrange
        String name = " ";

        // Act+Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new SensorModelName(name));
        assertEquals("The device name cannot be null, blank, or empty.", exception.getMessage());
    }

    /**
     * Test the constructor of the class SensorModelName with a null name.
     */
    @Test
    public void shouldThrowException_whenNameIsNull() {
        // Arrange
        String name = null;

        // Act+Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new SensorModelName(name));
        assertEquals("The device name cannot be null, blank, or empty.", exception.getMessage());
    }

    /**
     * Test the constructor of the class SensorModelName with a name that has more than 5 characters.
     */
    @Test
    public void shouldThrowException_whenNameContainsSpecialCharacters() {
        // Arrange
        String name = "SensorModelName!";

        // Act+Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new SensorModelName(name));
        assertEquals("The device name can only contain letters and numbers.", exception.getMessage());
    }

    /**
     * Test the getSensorModelName method of the class SensorModel
     */
    @Test
    public void shouldReturnSensorModelName_whenGetSensorModelNameIsCalled() {
        // Arrange
        SensorModelName sensorModelName;
        String name = "SensorModelName";

        // Act
        sensorModelName = new SensorModelName(name);

        // Assert
        assertEquals(name, sensorModelName.getSensorModelName());
    }

    /**
     * Test the equals method of the class SensorModelName.
     */
    @Test
    public void shouldReturnTrue_whenComparingTwoEqualSensorModelName() {
        // Arrange
        SensorModelName sensorModelName1;
        SensorModelName sensorModelName2;
        String name = "SensorModelName";

        // Act
        sensorModelName1 = new SensorModelName(name);
        sensorModelName2 = new SensorModelName(name);

        // Assert
        assertTrue(sensorModelName1.equals(sensorModelName2));
    }

    /**
     * Test the equals method of the class SensorModelName with two different sensor model names.
     */
    @Test
    public void shouldReturnFalse_whenComparingTwoDifferentSensorModelName() {
        // Arrange
        SensorModelName sensorModelName1;
        SensorModelName sensorModelName2;
        String name1 = "SensorModelName1";
        String name2 = "SensorModelName2";

        // Act
        sensorModelName1 = new SensorModelName(name1);
        sensorModelName2 = new SensorModelName(name2);

        // Assert
        assertFalse(sensorModelName1.equals(sensorModelName2));
    }

    /**
     * Test the equals method of the class SensorModelName with the same sensor model name.
     */
    @Test
    public void shouldReturnFalse_whenComparingSensorModelNameWithDifferentObject() {
        // Arrange
        SensorModelName sensorModelName;
        String name = "SensorModelName";

        // Act
        sensorModelName = new SensorModelName(name);

        // Assert
        assertFalse(sensorModelName.equals(name));
    }

    /**
     * Test hashCode method of the class SensorModel
     */
    @Test
    public void shouldReturnHashCode_whenCallingHashCode() {
        // Arrange
        String name = "SensorModelName";
        SensorModelName sensorModelName = new SensorModelName(name);

        int expected = name.hashCode();

        // Act
        int result = sensorModelName.hashCode();

        // Assert
        assertEquals(expected, result);

    }
}