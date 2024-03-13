package SmartHome.actuators;

import SmartHome.domain.Value;


/**
 * Implementation of a decimal value.
 */
public class SetDecimalValue implements Value, Cloneable {
    private double _value; // The decimal value

    /**
     * Constructs a SetDecimalValue object with the specified decimal value.
     *
     * @param value The decimal value to set.
     */
    public SetDecimalValue(double value) {
        setValue(value);
    }

    /**
     * Sets the decimal value.
     *
     * @param value The decimal value to set.
     */
    private void setValue(double value) {
        this._value = value;
    }

    /**
     * Clones the SetDecimalValue object.
     *
     * @return A cloned SetDecimalValue object.
     */
    @Override
    public SetDecimalValue clone() {
        try {
            return (SetDecimalValue) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * Returns the string representation of the decimal value.
     *
     * @return The string representation of the decimal value.
     */
    @Override
    public String toString() {
        return _value + "";
    }
}


