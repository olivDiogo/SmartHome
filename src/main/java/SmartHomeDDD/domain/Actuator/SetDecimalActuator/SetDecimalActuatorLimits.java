package SmartHomeDDD.domain.Actuator.SetDecimalActuator;

import SmartHomeDDD.ddd.ValueObject;

/**
 * Represents the limits within which a decimal actuator can set values.
 */
public class SetDecimalActuatorLimits implements ValueObject {

    private final double _lowerLimit;

    private final double _upperLimit;

    /**
     * Constructs a SetDecimalActuatorLimits object with the specified lower and upper limits.
     *
     * @param lowerLimit The lower limit for the actuator.
     * @param upperLimit The upper limit for the actuator.
     * @throws IllegalArgumentException if the lower limit is greater than the upper limit.
     */
    public SetDecimalActuatorLimits(double lowerLimit, double upperLimit) {
        validateLimits(lowerLimit, upperLimit);
        this._lowerLimit = lowerLimit;
        this._upperLimit = upperLimit;
    }

    /**
     * Validates the provided limits.
     *
     * @param lowerLimit The lower limit for the actuator.
     * @param upperLimit The upper limit for the actuator.
     */
    private void validateLimits(double lowerLimit, double upperLimit) {
        if (lowerLimit > upperLimit) {
            throw new IllegalArgumentException("Lower limit cannot be greater than upper limit");
        }
    }

    /**
     * Gets the lower limit for the actuator.
     *
     * @return The lower limit for the actuator.
     */
    public double getLowerLimit() {
        return _lowerLimit;
    }

    /**
     * Gets the upper limit for the actuator.
     *
     * @return The upper limit for the actuator.
     */
    public double getUpperLimit() {
        return _upperLimit;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SetDecimalActuatorLimits that)) return false;

        if (_lowerLimit != that._lowerLimit) return false;
        return _upperLimit == that._upperLimit;
    }
}

