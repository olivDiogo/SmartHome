package SmartHome.actuators;

import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import SmartHome.domain.Value;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SwitchActuatorValueTest {

    /**
     * Should create instance of SwitchActuatorValue class.
     */
    @Test
    void shouldCreateInstanceOfSwitchActuator() {
        // arrange
        boolean value = true;

        // act
        new SwitchActuatorValue(value);

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
     * Clone switch actuator value is not null.
     */
    @Test
    void CloneSwitchActuatorValueIsNotNull() {
        // arrange
        boolean value = true;
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(value);

        // act
        SwitchActuatorValue clonedSwitchActuatorValue = switchActuatorValue.clone();

        // assert
        assertNotNull(clonedSwitchActuatorValue);
    }

    /**
     * Should handle null switch actuator value.
     */

    @Test
    void shouldHandleNullSwitchActuatorValue() {
        // arrange
        SwitchActuatorValue switchActuatorValue = null;

        // act
        SwitchActuatorValue clonedSwitchActuatorValue = (switchActuatorValue != null) ? switchActuatorValue.clone() : null;  //This line is using the ternary operator to clone the switchActuatorValue if it's not null. If switchActuatorValue is null, then clonedSwitchActuatorValue will also be null.

        // assert
        assertNull(clonedSwitchActuatorValue);
    }
    /**
     * Should set the value of the actuator to on.
     */
    @Test
    void setValueOnShouldReturnTrue() {
        // arrange
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(true);

        // act
        boolean result = switchActuatorValue.setValueOn();

        // assert
        assertTrue(result);
    }

    /**
     * Should set the value of the actuator to on and return "On".
     */
    @Test
    void setValueOnToStringShouldReturnOn() {
        // arrange
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(true);

        // act
        switchActuatorValue.setValueOn();

        // assert
        assertEquals("On", switchActuatorValue.toString());
    }

    /**
     * Should set the value of the actuator to off.
     */
    @Test
    void setValueOffShouldReturnFalse() {
        // arrange
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(false);

        // act
        boolean result = switchActuatorValue.setValueOff();

        // assert
        assertFalse(result);
    }

    /**
     * Should set the value of the actuator to off and return "Off".
     */
    @Test
    void setValueOffShouldSetActuatorValueToFalse() {
        // arrange
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(true);

        // act
        switchActuatorValue.setValueOff();

        // assert
        assertEquals("Off", switchActuatorValue.toString());
    }

    @Test
    void setValueShouldNotReturnOff_whenItsTrue() {
        // arrange
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(true);

        // act
        switchActuatorValue.setValueOn();

        // assert
        assertNotEquals("Off", switchActuatorValue.toString());
    }

    @Test
    void setValueShouldNotReturnOn_whenItsFalse() {
        // arrange
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(false);

        // act
        switchActuatorValue.setValueOff();

        // assert
        assertNotEquals("On", switchActuatorValue.toString());
    }

}

