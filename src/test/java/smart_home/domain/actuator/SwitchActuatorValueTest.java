package smart_home.domain.actuator;

import org.junit.jupiter.api.Test;
import smart_home.domain.actuator.switch_actuator.SwitchActuatorValue;

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

    /**
     * Should return true when two SwitchActuatorValue objects are equal.
     */
    @Test
    void shouldReturnTrue_whenTwoSwitchActuatorValueObjectsAreEqual() {
        // Arrange
        SwitchActuatorValue switchActuatorValue1 = new SwitchActuatorValue(true);
        SwitchActuatorValue switchActuatorValue2 = new SwitchActuatorValue(true);

        // Act
        boolean result = switchActuatorValue1.equals(switchActuatorValue2);

        // Assert
        assertTrue(result);
    }

    /**
     * Should return false when two SwitchActuatorValue objects are not equal.
     */
    @Test
    void shouldReturnFalse_whenTwoSwitchActuatorValueObjectsAreNotEqual() {
        // Arrange
        SwitchActuatorValue switchActuatorValue1 = new SwitchActuatorValue(true);
        SwitchActuatorValue switchActuatorValue2 = new SwitchActuatorValue(false);

        // Act
        boolean result = switchActuatorValue1.equals(switchActuatorValue2);

        // Assert
        assertFalse(result);
    }

    /**
     * Should return false when the object is not an instance of SwitchActuatorValue.
     */
    @Test
    void shouldReturnFalse_whenObjectIsNotInstanceOfSwitchActuatorValue() {
        // Arrange
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(true);

        // Act
        boolean result = switchActuatorValue.equals(new Object());

        // Assert
        assertFalse(result);
    }

    /**
     * Should return the same hash code when two SwitchActuatorValue objects are equal.
     */
    @Test
    void shouldReturnSameHashCode_whenTwoSwitchActuatorValueObjectsAreEqual() {
        // Arrange
        SwitchActuatorValue switchActuatorValue1 = new SwitchActuatorValue(true);
        SwitchActuatorValue switchActuatorValue2 = new SwitchActuatorValue(true);

        // Act
        int hashCode1 = switchActuatorValue1.hashCode();
        int hashCode2 = switchActuatorValue2.hashCode();

        // Assert
        assertEquals(hashCode1, hashCode2);
    }
}
