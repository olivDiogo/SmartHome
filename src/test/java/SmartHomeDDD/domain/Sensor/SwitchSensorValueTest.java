package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.domain.Sensor.SwitchSensor.SwitchSensorValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SwitchSensorValueTest {
    /**
     * Tests the instantiation of SwitchSensorValue when the constructor arguments are valid.
     * The value is true.
     */
    @Test
    void shouldInstantiateSwitchSensorValue_whenConstructorArgumentsAreValidAndEqualsTrue() {
        // Arrange
        boolean value = true;

        // Act
        SwitchSensorValue result = new SwitchSensorValue(value);

        // Assert
        assertNotNull(result);
    }

    /**
     * Tests the instantiation of SwitchSensorValue when the constructor arguments are valid.
     * The value is false.
     */
    @Test
    void shouldInstantiateSwitchSensorValue_whenConstructorArgumentsAreValidAndEqualsFalse() {
        // Arrange
        boolean value = false;

        // Act
        SwitchSensorValue result = new SwitchSensorValue(value);

        // Assert
        assertNotNull(result);
    }

    /**
     * Tests the toString method of SwitchSensorValue when the value is true.
     */
    @Test
    void shouldReturnOn_whenValueIsTrue() {
        // Arrange
        boolean value = true;
        SwitchSensorValue switchSensorValue = new SwitchSensorValue(value);

        // Act
        String result = switchSensorValue.toString();

        // Assert
        assertEquals("On", result);
    }

    /**
     * Tests the toString method of SwitchSensorValue when the value is false.
     */
    @Test
    void shouldReturnOff_whenValueIsFalse() {
        // Arrange
        boolean value = false;
        SwitchSensorValue switchSensorValue = new SwitchSensorValue(value);

        // Act
        String result = switchSensorValue.toString();

        // Assert
        assertEquals("Off", result);
    }
}