package SmartHome.sensors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link TemperatureSensorValue} class.
 * Validates the functionality of temperature value handling, including construction, cloning, and string representation.
 */
class TemperatureSensorValueTest {

    /**
     * Tests if the {@link TemperatureSensorValue} constructor correctly assigns the provided temperature value.
     */
    @Test
    void seeIfConstructorWorksWhenValueIsPositive() {
        // Arrange
        double nValue = 10;

        // Act
        new TemperatureSensorValue(nValue);
    }

    /**
     * Tests if the {@link TemperatureSensorValue} constructor correctly assigns the provided temperature value when it is at the lower boundary.
     */
    @Test
    void seeIfConstructorWorksWhenValueIsZero() {
        // Arrange
        double nValue = 0;

        // Act
        new TemperatureSensorValue(nValue);
    }

    /**
     * Tests if the {@link TemperatureSensorValue} constructor throws an {@link IllegalArgumentException} when the temperature value is below the lower boundary.
     */
    @Test
    void seeIfConstructorThrowsExceptionWhenValueUnderTheLimit() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new TemperatureSensorValue(-273.16));

        // Assert
        assertEquals("Temperature value must be above or equal to -273.15", exception.getMessage());
    }

    /**
     * Tests if the {@link TemperatureSensorValue} constructor correctly assigns the provided temperature value when it is at the lower boundary.
     */
    @Test
    void seeIfConstructorWorksWhenValueIsAtTheLimit() {
        // Arrange
        double nValue = -273.15;

        // Act
        new TemperatureSensorValue(nValue);
    }


    /**
     * Tests if the {@link TemperatureSensorValue#toString} method correctly converts the temperature value to a string.
     */
    @Test
    void seeIfToStringWorks() {
        // Arrange
        double nValue = 10.5;
        TemperatureSensorValue temperatureSensorValue = new TemperatureSensorValue(nValue);

        // Act
        String actualString = temperatureSensorValue.toString();

        // Assert
        assertEquals("10.5", actualString, "toString should return a string representation of the temperature value.");
    }
}
