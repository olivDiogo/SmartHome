package SmartHomeDDD.domain.Sensor.SwitchSensor;

import SmartHomeDDD.ddd.ValueObject;

public class SwitchSensorValue implements ValueObject {
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
}
