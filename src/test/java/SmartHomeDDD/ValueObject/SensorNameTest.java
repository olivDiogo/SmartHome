package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorNameTest {
    /**
     * Tests the SensorName constructor with a valid sensor name.
     */
    @Test
    void shouldGetValidObject_WhenUsingValidSensorName(){
        // Arrange
        String validSensorName = "Temperature Sensor 1";

        // Act & Assert
        new SensorName(validSensorName);
    }

    /**
     * Tests the SensorName constructor with a null sensor name.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenSensorNameNull(){
        // Arrange
        String nullSensorName = null;
        String expectedMessage = "The sensor name cannot be null, blank, or empty.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorName(nullSensorName)
        );

        // Assert
        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Tests the SensorName constructor with a blank sensor name.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenSensorNameBlank(){
        // Arrange
        String blankSensorName = " ";
        String expectedMessage = "The sensor name cannot be null, blank, or empty.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorName(blankSensorName)
        );

        // Assert
        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Tests the SensorName constructor with an empty sensor name.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenSensorNameEmpty(){
        // Arrange
        String emptySensorName = "";
        String expectedMessage = "The sensor name cannot be null, blank, or empty.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorName(emptySensorName)
        );

        // Assert
        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Tests the SensorName constructor with special characters in the sensor name.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenSensorNameContainsSpecialCharacters(){
        // Arrange
        String invalidSensorName = "Temperature Sensor 1!";
        String expectedMessage = "The sensor name can only contain letters and numbers.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorName(invalidSensorName)
        );

        // Assert
        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Tests the getSensorName method.
     */
    @Test
    void shouldGetSensorName_WhenGetSensorNameIsCalled(){
        // Arrange
        String sensorName = "Temperature Sensor 1";
        SensorName sensorNameObject = new SensorName(sensorName);

        // Act
        String result = sensorNameObject.getSensorName();

        // Assert
        assertEquals(sensorName, result);
    }
}