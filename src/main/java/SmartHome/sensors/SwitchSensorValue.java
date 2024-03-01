package SmartHome.sensors;

import SmartHome.domain.Value;

public class SwitchSensorValue implements Value, Cloneable{
    private boolean _bValue;

    public SwitchSensorValue(boolean bValue) {
        this._bValue = bValue;
    }

    @Override
    public SwitchSensorValue clone() {
        try {
            // Call the Object clone() method
            return (SwitchSensorValue) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen since we are Cloneable
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return _bValue ? "On" : "Off";
    }
}
