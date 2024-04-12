package smartHome.domain.actuator;

import smartHome.domain.actuator.switchActuator.SwitchActuatorValue;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class SwitchActuatorValueTest {

    /**
     * Should create instance of SwitchActuatorValue class.
     */
    @Test
    void shouldCreateInstanceOfSwitchActuator() {
        // Arrange
        boolean value = true;

        // Act
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(value);

        // Assert
        assertNotNull(switchActuatorValue);
    }

    /**
     * Should turn off the actuator when value is true.
     */
    @Test
    void shouldTurnOffTheActuator_whenValueIsTrue() {
        // Arrange
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(true);

        // Act
        boolean result = switchActuatorValue.performAction();

        // Assert
        assertFalse(result);
    }

    /**
     * Should turn on the actuator when value is false.
     */
    @Test
    void shouldTurnOnTheActuator_whenValueIsFalse() {
        // Arrange
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(false);

        // Act
        boolean result = switchActuatorValue.performAction();

        // Assert
        assertTrue(result);
    }

    /**
     * Should return "On" when value is true.
     */
    @Test
    void shouldToStringReturnOn_whenValueIsTrue() {
        // Arrange
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(true);

        // Act
        String result = switchActuatorValue.toString();

        // Assert
        assertTrue(result.equals("On"));
    }

    /**
     * Should return "Off" when value is false.
     */
    @Test
    void shouldToStringReturnOff_whenValueIsFalse() {
        // Arrange
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(false);

        // Act
        String result = switchActuatorValue.toString();

        // Assert
        assertTrue(result.equals("Off"));
    }
}
