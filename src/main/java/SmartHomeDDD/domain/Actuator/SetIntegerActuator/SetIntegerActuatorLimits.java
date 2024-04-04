package SmartHomeDDD.domain.Actuator.SetIntegerActuator;

import SmartHomeDDD.ddd.ValueObject;

public class SetIntegerActuatorLimits implements ValueObject {
    private int _lowerLimit;
    private int _upperLimit;

    /**
     * Constructor for SetIntegerActuatorLimits
     * @param lowerLimit
     * @param upperLimit
     */
    public SetIntegerActuatorLimits(int lowerLimit, int upperLimit) {
        validateLimits(lowerLimit, upperLimit);
    }

    /**
     * Validates the limits
     * @param lowerLimit
     * @param upperLimit
     */
    private void validateLimits(int lowerLimit, int upperLimit) {
        if (lowerLimit > upperLimit) {
            throw new IllegalArgumentException("Lower limit cannot be greater than upper limit");
        }
        this._lowerLimit = lowerLimit;
        this._upperLimit = upperLimit;
    }

    /**
     * Gets the lower limit
     * @return
     */
    public int getLowerLimit() {
        return _lowerLimit;
    }

    /**
     * Gets the upper limit
     * @return
     */
    public int getUpperLimit() {
        return _upperLimit;
    }

    /**
     * Checks if the object is equal to this
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SetIntegerActuatorLimits)) return false;

        SetIntegerActuatorLimits that = (SetIntegerActuatorLimits) o;

        if (_lowerLimit != that._lowerLimit) return false;
        return _upperLimit == that._upperLimit;
    }
}
