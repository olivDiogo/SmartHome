package SmartHome.sensors;

import SmartHome.domain.Value;

public class AveragePowerConsumptionSensorValue implements Value{
    public double _dValue;

    /**
     * Creates a new PowerConsumptionSensorValue with a given value.
     * @param dValue the value to be set.
     */

    public AveragePowerConsumptionSensorValue(double dValue) {
        setValue(dValue);
    }

    /**
     * Gets the value of the PowerConsumptionSensorValue.
     * @return the value of the PowerConsumptionSensorValue.
     */

    private void setValue(double dValue) {
        if (dValue < 0)
            throw new IllegalArgumentException("Value must be positive");
        this._dValue = dValue;
    }

    /**
     * Gets the value of the PowerConsumptionSensorValue.
     * @return the value of the PowerConsumptionSensorValue.
     */
    public double getValue() {
        return this._dValue;
    }

    /**
     * Clones the PowerConsumptionSensorValue.
     * @return a new PowerConsumptionSensorValue with the same value.
     */

    public String toString() {
        return this._dValue + "";
    }
}
