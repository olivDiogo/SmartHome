package smartHome.domain.sensor;

import smartHome.domain.sensor.dewPointSensor.DewPointValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DewPointValueTest {
    /**
     * Test if the class DewPointValue can be instantiated.
     */
    @Test
    public void shouldInstantiateDewPointValue() {
       //Arrange
        int value = -70;

        // Act
        DewPointValue result = new DewPointValue(value);

        // Assert
        assertNotNull(result);
    }

    /**
     * Test if the class DewPointValue throws an exception when the value is higher than 70.
     */
    @Test
    public void shouldThrowException_WhenValueIsLowerThanMinus70() {
        // Arrange
        int value = -71;

        String expectedMessage = "The value of the dew point cannot be lower than -70.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new DewPointValue(value));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());

    }

    /**
     * Test if the class DewPointValue throws an exception when the value is higher than 100.
     */
    @Test
    public void shouldReturnStringValue() {
        // Arrange
        int value = 100;
        DewPointValue dewPointValue = new DewPointValue(value);

        String expected = "100";

        // Act
        String result = dewPointValue.toString();

        // Assert
        assertEquals(expected, result);
    }
}