package smart_home.domain.sensor.solar_irradiance_sensor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SolarIrradianceValueTest {

    /**
     * Tests the instantiation of SolarIrradianceValue when the constructor arguments are valid.
     */
    @Test
    public void shouldInstantiateSolarIrradianceValue_whenConstructorArgumentsAreValid() {
        // Arrange
        int value = 1;

        // Act
        SolarIrradianceValue result = new SolarIrradianceValue(value);

        // Assert
        assertNotNull(result);
    }

    /**
     * Tests the toString method of SolarIrradianceValue.
     */
    @Test
    public void shouldReturnStringValue_whenToStringIsCalled() {
        // Arrange
        int value = 1;
        SolarIrradianceValue solarIrradianceValue = new SolarIrradianceValue(value);

        // Act
        String result = solarIrradianceValue.toString();

        // Assert
        assertEquals(result,"1");
    }
}
