package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.ddd.ValueObject;

public class PercentagePositionSensorValue implements ValueObject {
    private int _percentage;

    public PercentagePositionSensorValue(int percentage) {
        validatePercentage(percentage);
    }
    private void validatePercentage(int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0 and 100.");
        }
        this._percentage = percentage;
    }

    public String toString() {
        return this._percentage + "";
    }

}
