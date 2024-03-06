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
     * Set Invalid Value when actuator value is false
     *
     * @throws InstantiationException
     */
    @Test
    void toStringShouldReturnOn_whenValueIsTrue() {
        // arrange
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(false);

        // act
        switchActuatorValue.setActuatorValue(true);

        // assert
        assertEquals("On", switchActuatorValue.toString());
    }

    /**
     * Set Invalid Value when actuator value is false
     *
     * @throws InstantiationException
     */

    @Test
    public void setValueShouldNotReturnOn_whenItsFalse() throws InstantiationException {
        //arrange
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(false);

        //act
        switchActuatorValue.setActuatorValue(false);

        //assert
        assertNotEquals("On", switchActuatorValue.toString());
    }


    /**
     * Should set the value of the actuator to off.
     *
     * @throws InstantiationException
     */
    @Test
    void setValueShouldReturnOff_whenItsFalse() throws InstantiationException {
        //arrange
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(false);

        //act
        switchActuatorValue.setActuatorValue(false);

        //assert
        assertEquals("Off", switchActuatorValue.toString());
    }

    /**
     * Set Invalid Value when actuator value is true
     *
     * @throws InstantiationException
     */

    @Test
    void setValueShouldNotReturnOff_whenItsTrue() throws InstantiationException {
        // arrange
        SwitchActuatorValue switchActuatorValue = new SwitchActuatorValue(false);

        // act
        switchActuatorValue.setActuatorValue(true);

        // assert
        assertNotEquals("Off", switchActuatorValue.toString());
    }
}

