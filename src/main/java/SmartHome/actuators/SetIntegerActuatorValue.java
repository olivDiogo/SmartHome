package SmartHome.actuators;

import SmartHome.domain.Value;

public class SetIntegerActuatorValue implements Value, Cloneable{
    private int _value;

    /**
     * Constructor of the class.
     *
     * @param value The value of the integer actuator.
     */
    public SetIntegerActuatorValue(int value) {
        setValue(value);
    }

    /**
     * Sets the value of the integer actuator.
     *
     * @param value The value of the integer actuator.
     */
    private void setValue(int value) {
        this._value = value;
    }

    /**
     * Clones the value of integer actuator.
     *
     * @return The cloned value of the integer actuator.
     */
    @Override
    public SetIntegerActuatorValue clone() {
        try {
            // Call the Object clone() method
            return (SetIntegerActuatorValue) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen since we are Cloneable
            throw new AssertionError();
        }
    }

    /**
     * Returns the value of the integer actuator as a string.
     *
     * @return The value of the integer actuator as a string.
     */
    @Override
    public String toString() {
        return _value + "";
    }
}
