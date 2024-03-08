package SmartHome.sensors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstantPowerConsumptionValueTest {

    /**
     * Test the InstantPowerConsumptionValue constructor
     */
    @Test
    public void shouldInstantiateInstantPowerConsumptionValue() {
        // Arrange
        double value = 1.0;

        // Act
        new InstantPowerConsumptionValue(value);
    }

    /**
     * Test the InstantPowerConsumptionValue constructor with a negative value. Should throw an IllegalArgumentException.
     */
    @Test
    public void shouldThrowException_whenInstantPowerConsumptionValueIsNegative() {
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
     * Test the InstantPowerConsumptionValue constructor with a value equal to zero.
     */
    @Test
    public void shouldInstantiateInstantPowerConsumptionValue_whenValueIsZero() {
        // Arrange
        double value = 0.0;

        // Act
        new InstantPowerConsumptionValue(value);
    }

    /**
     * Test the toString method of the InstantPowerConsumptionValue class.
     */
    @Test
    void shouldReturnInstantPowerConsumptionValueInString() {
        // Arrange
        double value = 1.0;
        InstantPowerConsumptionValue instantPowerConsumptionValue = new InstantPowerConsumptionValue(value);

        String expected = "1.0";
        // Act
        String result = instantPowerConsumptionValue.toString();
        // Assert
        assertEquals(expected, result);
    }


}