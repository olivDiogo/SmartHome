package SmartHome.actuators;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class SwitchActuatorValueTest {

    /**
     * Should create instance of SwitchActuatorValue class.
     */
    @Test
    void switchActuatorValueConstructortest() {
        // arrange
        boolean value = true;

        // act
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(value);

        // assert
        assertNotNull(switchActuatorValue);
    }

    /**
     * Should clone switch actuator value.
     */

    @Test
    void shouldCloneSwitchActuatorValue() {
        // arrange
        boolean value = true;
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(value);

        // act
        SwitchActuatorValue clonedSwitchActuatorValue = switchActuatorValue.clone();

        // assert
        assertNotEquals(switchActuatorValue, clonedSwitchActuatorValue);
    }

    /**
     * Should set value on when boolean is false.
     */
    @Test
    void shouldSetValueOn_WhenBooleanIsFalse() {
        // arrange
        boolean value = false;
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(value);

        // act
        boolean result = switchActuatorValue.setValueOn();

        // assert
        assertTrue(result);
    }

    /**
     * Should set value off when boolean is true.
     */

    @Test
    void shouldSetValueOn_WhenBooleanIsTrue() {
        // arrange
        boolean value = true;
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(value);

        // act
        boolean result = switchActuatorValue.setValueOn();

        // assert
        assertTrue(result);

    }

    /**
     * Should set value off when boolean is false.
     */

    @Test
    void shouldSetValueOff_WhenBooleanIsFalse() {
        // arrange
        boolean value = false;
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(value);

        // act
        boolean result = switchActuatorValue.setValueOff();

        // assert
        assertFalse(result);
    }

    /**
     * Should set value off when boolean is true.
     */

    @Test
    void shouldSetValueOff_WhenBooleanIsTrue() {
        // arrange
        boolean value = true;
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(value);

        // act
        boolean result = switchActuatorValue.setValueOff();

        // assert
        assertFalse(result);
    }

    /**
     * Should return string "On".
     */

    @Test
    void shouldReturnStringOn() {
        // arrange
        boolean value = true;
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(value);

        // act
        String result = switchActuatorValue.toString();

        // assert
        assertEquals("On", result);
    }

    /**
     * Should return string "Off".
     */

    @Test
    void shouldReturnStringOff() {
        // arrange
        boolean value = false;
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(value);

        // act
        String result = switchActuatorValue.toString();

        // assert
        assertEquals("Off", result);
    }
}

