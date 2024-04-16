package smart_home.domain.sensor.instant_power_consumption_sensor;

import smart_home.ddd.IValueObject;

public class InstantPowerConsumptionValue implements IValueObject {
    private final double _instantPowerConsumptionValue;

    /**
     * Constructor of the class.
     *
     * @param instantPowerConsumptionValue The value of the instant power consumption.
     */
    public InstantPowerConsumptionValue(double instantPowerConsumptionValue) {
        if (instantPowerConsumptionValue < 0)
            throw new IllegalArgumentException("The value of the instant power consumption cannot be lower than 0.");
        this._instantPowerConsumptionValue = instantPowerConsumptionValue;
    }

    /**
     * Gets the value of the instant power consumption.
     *
     * @return The value of the instant power consumption.
     */
    public String toString() {
        return this._instantPowerConsumptionValue + "";
    }
}
