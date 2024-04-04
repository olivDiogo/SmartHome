package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.ddd.ValueObject;

public class PercentagePositionSensorValue implements ValueObject {
    private int _percentage;

    public PercentagePositionSensorValue(int percentage) {
        this._percentage = percentage;
    }

    public String toString() {
        return this._percentage + "";
    }

}
