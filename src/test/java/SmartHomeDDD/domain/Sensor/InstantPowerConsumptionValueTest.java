package SmartHomeDDD.domain.Sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstantPowerConsumptionValueTest {

    /**
     * Test to check if the InstantPowerConsumptionValue can be instantiated.
     */
    @Test
    public void shouldInstantiateInstantPowerConsumptionValue() {
        //arrange
        double instantPowerConsumptionValue =0.0;

        //act
        new InstantPowerConsumptionValue(instantPowerConsumptionValue);
    }

    /**
     * Test to check if the InstantPowerConsumptionValue throws an exception when the value is negative.
     */
    @Test
    public void shouldThrowExceptionWhenInstantPowerConsumptionValueIsNegative() {
        //arrange
        double instantPowerConsumptionValue =-1.0;

        String expectedMessage = "The value of the instant power consumption cannot be lower than 0.";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new InstantPowerConsumptionValue(instantPowerConsumptionValue);
        });
        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));

    }

    /**
     * Test to check if the InstantPowerConsumptionValue returns the value in string.
     */
    @Test
    public void shouldReturnInstantPowerConsumptionInString() {
        //arrange
        double instantPowerConsumptionValue =25.0;
        InstantPowerConsumptionValue instantPowerConsumptionValueObject = new InstantPowerConsumptionValue(instantPowerConsumptionValue);

        String expectedValue = "25.0";
        //act
        String actualValue = instantPowerConsumptionValueObject.toString();

        //assert
        assertEquals(expectedValue, actualValue);
    }

}