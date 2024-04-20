package smart_home.domain.actuator.set_integer_actuator;

import smart_home.ddd.IValueObject;

public class SetIntegerValue implements IValueObject {
    private final int _value;

    /**
     * Constructor for SetIntegerValue
     *
     * @param value
     */
    public SetIntegerValue(int value) {
        this._value = value;
    }

    /**
     * Returns the value of the integer actuator as a string.
     *
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof SetIntegerValue objectSetIntegerValue) {
            return _value == objectSetIntegerValue._value;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Integer.hashCode(_value);
    }

    /**
     * Returns the value of the integer actuator as a string.
     *
     * @return
     */
    @Override
    public String toString() {
        return _value + "";
    }
}
