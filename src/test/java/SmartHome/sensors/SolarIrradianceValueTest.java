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
        int value = 450;

        // Act
        SolarIrradianceValue solarIrradianceValue = new SolarIrradianceValue(value);

        // Assert
        assertNotNull(solarIrradianceValue);
    }


    @Test
    void shouldConvertToString(){
        // Arrange
        int value = 10;

        SolarIrradianceValue setIntegerValue = new SolarIrradianceValue(value);

        // Act
        String result = setIntegerValue.toString();

        // Assert
        assertEquals("10", result);
    }
}
