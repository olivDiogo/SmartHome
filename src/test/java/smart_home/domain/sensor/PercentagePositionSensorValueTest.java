package smart_home.domain.sensor;

import org.junit.jupiter.api.Test;
import smart_home.domain.sensor.percentage_position_sensor.PercentagePositionSensorValue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PercentagePositionSensorValueTest {
    /**
     * Tests the toString method of PercentagePosicionSensorValue when the percentage is 0.
     */
    @Test
    void shouldReturn0Percent_whenPercentageIs0() {
        // Arrange
        int percentage = 0;
        PercentagePositionSensorValue percentagePosicionSensorValue = new PercentagePositionSensorValue(percentage);

        // Act
        String result = percentagePosicionSensorValue.toString();

        // Assert
        assertEquals("0", result);
    }

    /**
     * Tests the toString method of PercentagePosicionSensorValue when the percentage is 100.
     */
    @Test
    void shouldReturn100Percent_whenPercentageIs100() {
        // Arrange
        int percentage = 100;
        PercentagePositionSensorValue percentagePosicionSensorValue = new PercentagePositionSensorValue(percentage);

        // Act
        String result = percentagePosicionSensorValue.toString();

        // Assert
        assertEquals("100", result);
    }
    /**
     * Tests the toString method of PercentagePosicionSensorValue when percentage above range.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenPercentageIsAboveRange() {
        // Arrange
        int percentage = 101;
        String expectedMessage = "The percentage must be between 0 and 100.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensorValue(percentage));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    /**
     * Tests the toString method of PercentagePosicionSensorValue when percentage below range.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenPercentageIsBelowRange() {
        // Arrange
        int percentage = -1;
        String expectedMessage = "The percentage must be between 0 and 100.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensorValue(percentage));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }


}