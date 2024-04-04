package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.ddd.ValueObject;

public class BlindRollerValue implements ValueObject {
    private final int _value;

    /**
     * Constructor for BlindRollerValue
     * @param value
     * It must be between 0 and 100.
     */
    protected BlindRollerValue (int value){
        validateValue(value);
        this._value = value;
    }

    /**
     * Validates the value of the blind roller.
     * @param value
     */
    private void validateValue(int value) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("The value must be between 0 and 100.");
        }
    }

    /**
     * Gets the value of the blind roller.
     * @return
     */
    public String toString() {
        return this._value + "";
    }

}
