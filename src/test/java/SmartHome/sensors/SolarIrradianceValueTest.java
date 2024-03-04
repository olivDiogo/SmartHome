package SmartHome.sensors;

import SmartHome.actuators.SetIntegerValue;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SolarIrradianceValueTest {
    /**
     * Should create instance of SolarIrradianceValue class.
     */
    @Test
    void shouldCreateInstanceOfSolarIrradianceValue() {
        // Arrange
        double value = 450;

        // Act
        SolarIrradianceValue solarIrradianceValue = new SolarIrradianceValue(value);

        // Assert
        assertNotNull(solarIrradianceValue);
    }

    /**
     * Should clone setInteger actuator value.
     */
    @Test
    void shouldCloneSolarIrradianceValue() {
        // arrange
        double value = 10;

        SolarIrradianceValue solarIrradianceValue = new SolarIrradianceValue(value);

        // act
        SolarIrradianceValue clonedSolarIrradianceValue = solarIrradianceValue.clone();

        // assert
        assertNotEquals(solarIrradianceValue, clonedSolarIrradianceValue);
    }


    @Test
    void shouldConvertToString(){
        // Arrange
        double value = 10;

        SolarIrradianceValue setIntegerValue = new SolarIrradianceValue(value);

        // Act
        String result = setIntegerValue.toString();

        // Assert
        assertEquals("10.0", result);
    }
}
