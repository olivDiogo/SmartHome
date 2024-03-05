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
     * Tests if the {@link TemperatureSensorValue#clone} method creates a deep copy of the {@link TemperatureSensorValue} object.
     */
    @Test
    void seeIfCloneWorks() {
        // Arrange
        double nValue = 10;
        TemperatureSensorValue temperatureSensorValue = new TemperatureSensorValue(nValue);

        // Act
        TemperatureSensorValue clonedValue = temperatureSensorValue.clone();

        // Assert
        assertEquals(temperatureSensorValue.toString(), clonedValue.toString(), "Cloned TemperatureSensorValue should have the same string representation as the original.");
        assertNotSame(temperatureSensorValue, clonedValue, "Cloned object should not be the same instance as the original.");
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
