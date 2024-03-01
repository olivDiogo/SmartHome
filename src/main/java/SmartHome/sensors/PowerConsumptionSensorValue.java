package SmartHome.sensors;

import SmartHome.domain.Value;

public class PowerConsumptionSensorValue implements Value, Cloneable {
    public double _dValue;

    public PowerConsumptionSensorValue(double dValue) {
        setValue(dValue);
    }

    private void setValue(double dValue) {
        this._dValue = dValue;
    }

    public String toString() {
        return this._dValue + "";
    }

    @Override
    public PowerConsumptionSensorValue clone() {
        try {
            return (PowerConsumptionSensorValue) super.clone();

        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
