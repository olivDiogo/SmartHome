package SmartHome.actuators;

import SmartHome.domain.Value;

public class BlindRollerValue implements Value, Cloneable {

    private int _nValue;

    /**
     * Constructor of the class.
     *
     * @param nValue The value of the blind roller.
     */
    public BlindRollerValue(int nValue) {
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
            // This should never happen since we are Cloneable
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
