package smart_home.value_object;

import smart_home.ddd.IValueObject;

/**
 * Represents the limits within which a decimal actuator can set values.
 */
public class DecimalLimits implements IValueObject {

    private final double _lowerLimit;

    private final double _upperLimit;

    /**
     * Constructs a SetDecimalActuatorLimits object with the specified lower and upper limits.
     *
     * @param lowerLimit The lower limit for the actuator.
     * @param upperLimit The upper limit for the actuator.
     * @throws IllegalArgumentException if the lower limit is greater than the upper limit.
     */
    public DecimalLimits(double lowerLimit, double upperLimit) {
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
     * Overrides the equals method to compare two SetDecimalActuatorLimits objects.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof DecimalLimits decimalLimits) {
            double epsilon = 0.001;
            return Math.abs(_lowerLimit - decimalLimits._lowerLimit) < epsilon && Math.abs(_upperLimit - decimalLimits._upperLimit) < epsilon;

        }
        return false;
    }
    @Override
    public int hashCode() {
        return Double.hashCode(_lowerLimit) + Double.hashCode(_upperLimit);
    }

    /**
     * Overrides the toString method to return a string representation of the SetDecimalActuatorLimits object.
     */
    public String toString() {
        return "Lower limit: " + this._lowerLimit + ", Upper limit: " + this._upperLimit;
    }
}

