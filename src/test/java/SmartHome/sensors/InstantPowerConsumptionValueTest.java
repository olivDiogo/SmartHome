package SmartHome.sensors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstantPowerConsumptionValueTest {

    /**
     * Test the InstantPowerConsumptionValue constructor
     */
    @Test
    public void testInstantPowerConsumptionValueConstructor() {
        // Arrange
        double value = 1.0;

        // Act
        new InstantPowerConsumptionValue(value);
    }

    /**
     * Test the InstantPowerConsumptionValue constructor with a negative value. Should throw an IllegalArgumentException.
     */
    @Test
    public void testInstantPowerConsumptionValueConstructorNegativeValue() {
        // Arrange
        double value = -1.0;

        String expectedMessage = "The value of the instant power consumption cannot be negative.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new InstantPowerConsumptionValue(value));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    /**
     * Should clone InstantPowerConsumptionValue.
     */
    @Test
    public void testInstantPowerConsumptionValueClone() {
        // Arrange
        double value = 1.0;
        InstantPowerConsumptionValue instantPowerConsumptionValue = new InstantPowerConsumptionValue(value);

        // Act
        InstantPowerConsumptionValue clonedInstantPowerConsumptionValue = instantPowerConsumptionValue.clone();

        // Assert
        assertNotEquals(instantPowerConsumptionValue, clonedInstantPowerConsumptionValue);
    }

    @Test
    public void testInstantPowerConsumptionValueToString() {
        // Arrange
        double value = 1.0;
        InstantPowerConsumptionValue instantPowerConsumptionValue = new InstantPowerConsumptionValue(value);

        // Act
        String result = instantPowerConsumptionValue.toString();

        // Assert
        assertEquals("1.0", result);
    }

}