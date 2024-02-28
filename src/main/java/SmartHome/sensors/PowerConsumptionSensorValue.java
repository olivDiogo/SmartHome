package SmartHome.sensors;

import SmartHome.domain.Value;

public class PowerConsumptionSensorValue implements Value {
    public double _dValue;

    public PowerConsumptionSensorValue(double dValue) {
        this._dValue = dValue;
    }

    public String toString() {
        return this._dValue + "";
    }

}
