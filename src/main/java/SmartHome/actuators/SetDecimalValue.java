package SmartHome.actuators;

import SmartHome.domain.Value;


public class SetDecimalValue implements Value, Cloneable {
    private double _value; // The decimal value


    public SetDecimalValue(double value) {
        setValue(value);
    }

    private void setValue(double value) {
            this._value = value;
    }


    @Override
    public SetDecimalValue clone() {
        try {
            return (SetDecimalValue) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return _value + "";
    }
}

