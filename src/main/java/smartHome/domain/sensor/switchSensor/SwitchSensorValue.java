package smartHome.domain.sensor.switchSensor;

import smartHome.ddd.IValueObject;

public class SwitchSensorValue implements IValueObject {
    private final boolean _bValue;

    /**
     * Constructs a SwitchSensorValue with a specified state.
     *
     * @param bValue The state of the switch sensor.
     */
    public SwitchSensorValue(boolean bValue) {
        this._bValue = bValue;
    }

    /**
     * Returns a string representation of the switch sensor value.
     *
     * @return The switch sensor value as a string.
     */
    public String toString() {
        return this._bValue ? "On" : "Off";
    }

    /**
     * Method to check if the switch sensor value is equal to another switch sensor value.
     */
    public boolean equals(Object o) {
        if (o instanceof SwitchSensorValue switchSensorValue) {
            return this._bValue == switchSensorValue._bValue;
        }
        return false;
    }

    /**
     * Method to generate a hash code for the switch sensor value.
     */
    public int hashCode() {
        return Boolean.hashCode(this._bValue);
    }
}
