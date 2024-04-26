package smarthome.domain.actuator.set_integer_actuator;

import smarthome.ddd.IValueObject;

public class SetIntegerValue implements IValueObject {
    private final int value;

    /**
     * Constructor for SetIntegerValue
     *
     * @param value
     */
    public SetIntegerValue(int value) {
        this.value = value;
    }

    /**
     * Returns the value of the integer actuator as a string.
     *
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof SetIntegerValue objectSetIntegerValue) {
            return value == objectSetIntegerValue.value;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    /**
     * Returns the value of the integer actuator as a string.
     *
     * @return the value of the integer actuator as a string
     */
    @Override
    public String toString() {
        return value + "";
    }
}
