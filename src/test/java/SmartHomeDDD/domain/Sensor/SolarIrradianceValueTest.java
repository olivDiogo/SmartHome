package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.domain.Sensor.SolarIrradianceSensor.SolarIrradianceValue;
import org.junit.jupiter.api.Test;

public class SolarIrradianceValueTest {

    /**
     * Tests the instantiation of SolarIrradianceValue when the constructor arguments are valid.
     */
    @Test
    public void shouldInstantiateSolarIrradianceValue_whenConstructorArgumentsAreValid() {
        // Arrange
        int value = 1;

        // Act
        new SolarIrradianceValue(value);
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
        assert result.equals("1");
    }
}
