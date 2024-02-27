package SmartHome.sensors;

import SmartHome.domain.Value;

public class SwitchSensorValue implements Value {
    public boolean _bValue;

    public SwitchSensorValue(boolean bValue) {
        this._bValue = bValue;
    }

    public String toString() {
        return _bValue ? "On" : "Off";
    }
}
