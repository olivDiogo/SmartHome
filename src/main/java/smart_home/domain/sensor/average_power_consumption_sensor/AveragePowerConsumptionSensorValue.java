package smart_home.domain.sensor.average_power_consumption_sensor;


import smart_home.ddd.IValueObject;

public class AveragePowerConsumptionSensorValue implements IValueObject {
    public double _dValue;

    /**
     * Creates a new PowerConsumptionSensorValue with a given value.
     *
     * @param dValue the value to be set.
     */

    public AveragePowerConsumptionSensorValue(double dValue) {
        validateValue(dValue);
    }

    /**
     * Gets the value of the PowerConsumptionSensorValue.
     *
     * @return the value of the PowerConsumptionSensorValue.
     */
    public double getValue() {
        return this._dValue;
    }

    /**
     * Gets the value of the PowerConsumptionSensorValue.
     *
     * @return the value of the PowerConsumptionSensorValue.
     */

    private void validateValue(double dValue) {
        if (dValue < 0)
            throw new IllegalArgumentException("Value must be positive");
        this._dValue = dValue;
    }

    public String toString() {
        return this._dValue + "";
    }

    /**
     * Overrides the equals method to compare two PowerConsumptionSensorValue objects.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue) {
            double epsilon = 0.001;
            return Math.abs(_dValue - averagePowerConsumptionSensorValue._dValue) < epsilon;
        }
        return false;
    }
}
