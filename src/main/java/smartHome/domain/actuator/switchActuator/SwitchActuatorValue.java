package smartHome.domain.actuator.switchActuator;

import smartHome.ddd.IValueObject;

public class SwitchActuatorValue implements IValueObject {


    private boolean _value;

    /**
     * Instantiates a new Switch actuator value.
     *
     * @param value the value
     */

    public SwitchActuatorValue(boolean value) {
        this._value = value;
    }


    /**
     * Performs change the value of the actuator.
     *
     * @return the boolean
     */
    public boolean performAction() {
        _value = !_value;
        return _value;
    }


    /**
     * Returns the value of the actuator as a string.
     *
     * @return the string
     */

    @Override
    public String toString() {
        return _value ? "On" : "Off";
    }

}
