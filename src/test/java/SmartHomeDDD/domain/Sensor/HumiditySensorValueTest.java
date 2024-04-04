package SmartHomeDDD.domain.Sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HumiditySensorValueTest {
    /**
     * Tests the instantiation of HumiditySensorValue when the constructor arguments are valid.
     * The value is at the upper limit.
     */
    @Test
    void shouldInstantiateHumiditySensorValue_whenConstructorArgumentsAreWithinUpperLimit() {
        // Arrange
        int value = 100;

        // Act
        new HumiditySensorValue(value);
    }

    /**
     * Tests the instantiation of HumiditySensorValue when the constructor arguments are invalid.
     * The value is above the upper limit.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenConstructorArgumentsAreAboveTheUpperLimit() {
        // Arrange
        int value = 101;
        String expectedMessage = "Humidity value must be between 0 and 100";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new HumiditySensorValue(value));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Tests the instantiation of HumiditySensorValue when the constructor arguments are invalid.
     * The value is below the lower limit.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenConstructorArgumentsAreBelowTheLowerLimit() {
        // Arrange
        int value = -1;
        String expectedMessage = "Humidity value must be between 0 and 100";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new HumiditySensorValue(value));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Tests the instantiation of HumiditySensorValue when the constructor arguments are valid.
     * The value is at the lower limit.
     */
    @Test
    void shouldInstantiateHumiditySensorValue_whenConstructorArgumentsAreWithinLowerLimit() {
        // Arrange
        int value = 0;

        // Act
        new HumiditySensorValue(value);
    }

    /**
     * Tests the toString method of HumiditySensorValue.
     */
    @Test
    void shouldReturnStringValue_whenToStringIsCalled() {
        // Arrange
        int value = 50;
        HumiditySensorValue humiditySensorValue = new HumiditySensorValue(value);

        // Act
        String result = humiditySensorValue.toString();

        // Assert
        assertEquals("50", result);
    }
}