package SmartHome.sensors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the PercentagePositionSensorValue class.
 */
class PercentagePositionSensorValueTest {

    /**
     * Tests the constructor of PercentagePositionSensorValue when the value is 100.
     */
    @Test
    void seeIfConstructorWorksWhenValueIs100() {
        // Arrange
        double percented = 100;

        // Act
        new PercentagePositionSensorValue(percented);
    }

    /**
     * Tests the constructor of PercentagePositionSensorValue when the value is zero.
     */
    @Test
    void seeIfConstructorWorksWhenValueIsZero() {
        // Arrange
        double percented = 0;

        // Act
        new PercentagePositionSensorValue(percented);
    }

    /**
     * Tests if the constructor throws an exception when the value is under zero.
     */
    @Test
    void seeIfConstructorThrowsExceptionWhenValueUnderZero() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensorValue(-1));

        // Assert
        assertEquals("Percented value must be between 0 and 100", exception.getMessage());
    }

    /**
     * Tests if the constructor throws an exception when the value is over 100.
     */
    @Test
    void seeIfConstructorThrowsExceptionWhenValueOver100() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensorValue(101));

        // Assert
        assertEquals("Percented value must be between 0 and 100", exception.getMessage());
    }

    /**
     * Tests if the clone method works correctly.
     */
    @Test
    void seeIfCloneWorks() {
        // Arrange
        PercentagePositionSensorValue percentagePositionSensorValue = new PercentagePositionSensorValue(50);

        // Act
        PercentagePositionSensorValue result = percentagePositionSensorValue.clone();

        // Assert
        assertEquals(percentagePositionSensorValue.toString(), result.toString());
    }

    /**
     * Tests if the toString method returns the correct string representation.
     */
    @Test
    void seeIfToStringWorks() {
        // Arrange
        PercentagePositionSensorValue percentagePositionSensorValue = new PercentagePositionSensorValue(50);

        // Act
        String result = percentagePositionSensorValue.toString();

        // Assert
        assertEquals("50.0%", result);
    }
}