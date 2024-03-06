package SmartHome.actuators;

import SmartHome.domain.Value;

public class BlindRollerValue implements Value, Cloneable {

    private int _nValue;

    /**
     * Constructor of the class.
     * @param nValue The value of the blind roller.
     *It must be between 0 and 100.
     */
    public BlindRollerValue(int nValue) {
        validateValue(nValue);
    }

    private void validateValue(int nValue) {
        if (nValue < 0 || nValue > 100) {
            throw new IllegalArgumentException("The value must be between 0 and 100.");
        }
        this._nValue = nValue;
    }

    /**
     * Clones the value of the blind roller.
     *
     * @return The cloned value of the blind roller.
     */
    @Override
    public BlindRollerValue clone() {
        try {
            // Call the Object clone() method
            return (BlindRollerValue) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen
            throw new AssertionError();
        }
    }
    /**
     * Gets the value of the blind roller.
     */
    public String toString() {
        return this._nValue + "";

    }

}
