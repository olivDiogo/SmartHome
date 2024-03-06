package SmartHome.sensors;

import SmartHome.domain.Value;

public class InstantPowerConsumptionValue implements Value {
    private double _instantPowerConsumptionValue;

    /**
     * Constructor of the class. Creates a new instant power consumption value.
     *
     * @param instantPowerConsumptionValue The value of the instant power consumption.
     */
    public InstantPowerConsumptionValue(double instantPowerConsumptionValue) {
       if (instantPowerConsumptionValue < 0)
           throw new IllegalArgumentException("The value of the instant power consumption cannot be negative.");

        this._instantPowerConsumptionValue = instantPowerConsumptionValue;
    }

    /**
     * Gets the value of the instant power consumption in a String.
     *
     * @return The value of the instant power consumption in a String.
     */
    public String toString() {
        return this._instantPowerConsumptionValue + "";
    }


}
