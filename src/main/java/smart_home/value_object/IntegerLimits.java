package smart_home.value_object;

import smart_home.ddd.IValueObject;

public class IntegerLimits implements IValueObject {
    private final int _lowerLimit;
    private final int _upperLimit;

    /**
     * Constructor for SetIntegerActuatorLimits
     *
     * @param lowerLimit
     * @param upperLimit
     */
    public IntegerLimits(int lowerLimit, int upperLimit) {
        validateLimits(lowerLimit, upperLimit);
        this._lowerLimit = lowerLimit;
        this._upperLimit = upperLimit;
    }

    /**
     * Validates the limits
     *
     * @param lowerLimit
     * @param upperLimit
     */
    private void validateLimits(int lowerLimit, int upperLimit) {
        if (lowerLimit > upperLimit) {
            throw new IllegalArgumentException("Lower limit cannot be greater than upper limit");
        }
    }

    /**
     * Gets the lower limit
     *
     * @return
     */
    public int getLowerLimit() {
        return _lowerLimit;
    }

    /**
     * Gets the upper limit
     *
     * @return
     */
    public int getUpperLimit() {
        return _upperLimit;
    }

    /**
     * Checks if the object is equal to this
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof IntegerLimits that){
            return _lowerLimit == that._lowerLimit && _upperLimit == that._upperLimit;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(_lowerLimit) + Integer.hashCode(_upperLimit);
    }
}
