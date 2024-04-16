package smart_home.domain.actuator.blind_roller_actuator;

import smart_home.ddd.IValueObject;

public class BlindRollerValue implements IValueObject {
    private final int _value;

    /**
     * Constructor for BlindRollerValue
     *
     * @param value It must be between 0 and 100.
     */
    public BlindRollerValue(int value) {
        validateValue(value);
        this._value = value;
    }

    /**
     * Validates the value of the blind roller.
     *
     * @param value
     */
    private void validateValue(int value) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("The value must be between 0 and 100.");
        }
    }

    /**
     * Gets the value of the blind roller.
     *
     * @return
     */
    public String toString() {
        return this._value + "";
    }

}
