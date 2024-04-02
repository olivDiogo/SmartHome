package SmartHomeDDD.domain.Sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DewPointValueTest {
    /**
     * Test if the class DewPointValue can be instantiated.
     */
    @Test
    public void shouldInstantiateDewPointValue() {
       //Arrange
        int value = -100;
        // Act
        new DewPointValue(value);
    }

    /**
     * Test if the class DewPointValue throws an exception when the value is higher than 100.
     */
    @Test
    public void shouldThrowException_WhenValueIsLowerThanMinus100() {
        // Arrange
        int value = -101;

        String expectedMessage = "The value of the dew point cannot be lower than -100.";

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