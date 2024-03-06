package SmartHome.actuators;

import SmartHome.domain.Value;

public class SwitchActuatorValue implements Value, Cloneable {

    private boolean _value;

    /**
     * Constructor of the class.
     *
     * @param value The value of the switch actuator.
     */
    public SwitchActuatorValue(boolean value) {
        this._value = value;
    }

    /**
     * Clones the value of switch actuator.
     *
     * @return The cloned value of the switch actuator.
     */
    @Override
    public SwitchActuatorValue clone() {
        try {
            // Call the Object clone() method
            return (SwitchActuatorValue) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen since we are Cloneable
            throw new AssertionError();
        }
    }

    /**
     * Sets the value of the switch actuator to on.
     *
     * @return The value of the switch actuator.
     */

    public boolean setValueOn(){
        return _value = true;
    }


    /**
     * Sets the value of the switch actuator to off.
     *
     * @return The value of the switch actuator.
     */
    public boolean setValueOff(){
        return _value = false;
    }


    /**
     * Sets the value of the switch actuator as a string.
     * "On" if the value is true, "Off" if the value is false.
     * @return The value of the switch actuator as a string.
     */
    @Override
    public String toString() {
        return (_value) ? "On" : "Off";
    }
}
