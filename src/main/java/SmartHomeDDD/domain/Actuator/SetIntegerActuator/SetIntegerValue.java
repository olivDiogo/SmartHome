package SmartHomeDDD.domain.Actuator.SetIntegerActuator;

import SmartHomeDDD.ddd.ValueObject;

public class SetIntegerValue implements ValueObject {
    private int _value;

    /**
     * Constructor for SetIntegerValue
     * @param value
     */
    public SetIntegerValue(int value) {
        this._value = value;
    }

    /**
     * Returns the value of the integer actuator as a string.
     * @return
     */
    @Override
    public String toString() {
        return _value + "";
    }
}
