package SmartHome.sensors;

import SmartHome.domain.Value;

/**
 * Represents the value of a switch sensor in a Smart Home system, indicating whether the switch is on or off.
 */
public class SwitchSensorValue implements Value, Cloneable {
    /**
     * The boolean value representing the state of the switch sensor (true for on, false for off).
     */
    private boolean _bValue;

    /**
     * Constructs a new SwitchSensorValue with the specified boolean value.
     *
     * @param bValue The boolean state of the switch sensor (true for on, false for off).
     */
    public SwitchSensorValue(boolean bValue) {
        this._bValue = bValue;
    }

    /**
     * Creates and returns a copy of this SwitchSensorValue object.
     *
     * @return A clone of this instance.
     */
    @Override
    public SwitchSensorValue clone() {
        try {
            // Call the Object clone() method
            return (SwitchSensorValue) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen since we are Cloneable
            throw new AssertionError("SwitchSensorValue instance could not be cloned.");
        }
    }

    /**
     * Returns a string representation of the switch sensor value.
     *
     * @return A string "On" if the sensor is on, or "Off" if the sensor is off.
     */
    @Override
    public String toString() {
        return _bValue ? "On" : "Off";
    }
}