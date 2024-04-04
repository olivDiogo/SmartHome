package SmartHomeDDD.domain.Actuator.SetDecimalActuator;

import SmartHomeDDD.ddd.ValueObject;

/**
 * Represents a decimal value that can be set for an actuator.
 */
public class SetDecimalValue implements ValueObject {

    private double _value;

    /**
     * Constructs a SetDecimalValue object with the specified decimal value.
     *
     * @param value The decimal value to be set.
     */
    public SetDecimalValue(double value) {
        this._value = value;
    }

    /**
     * Gets the decimal value.
     *
     * @return The decimal value.
     */
    public double getValue() {
        return _value;
    }

    /**
     * Returns a string representation of the decimal value.
     *
     * @return A string representation of the decimal value.
     */
    @Override
    public String toString() {
        return Double.toString(_value);
    }
}

