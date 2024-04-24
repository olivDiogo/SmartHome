package smart_home.domain.sensor.percentage_position_sensor;

import smart_home.ddd.IValueObject;

public class PercentagePositionSensorValue implements IValueObject {
    private int percentage;

    public PercentagePositionSensorValue(int percentage) {
        validatePercentage(percentage);
    }

    private void validatePercentage(int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0 and 100.");
        }
        this.percentage = percentage;
    }

    public String toString() {
        return this.percentage + "";
    }

}
