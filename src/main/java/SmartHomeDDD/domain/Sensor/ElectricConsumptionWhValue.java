package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.ddd.ValueObject;

/**
 * Represents the value of electric consumption in watt-hours.
 */
public class ElectricConsumptionWhValue implements ValueObject {
    /**
     * Constructs a new ElectricConsumptionWhValue with the given parameters.
     *
     * @param value the value of electric consumption in watt-hours
     */
    private int _value;

    /**
     * Constructs a new ElectricConsumptionWhValue with the given parameters.
     *
     * @param value the value of electric consumption in watt-hours
     */

    public ElectricConsumptionWhValue(int value) {
        setValue(value);
    }

    /**
     * Returns the value of electric consumption in watt-hours.
     *
     * @return the value of electric consumption in watt-hours
     */

    private void setValue(int value) {
        if (value < 0)
            throw new IllegalArgumentException("Consumption cannot be negative.");
        else _value = value;
    }

    /**
     * Returns the value of electric consumption in watt-hours.
     *
     * @return the value of electric consumption in watt-hours
     */
    @Override
    public String toString() {
        return "ElectricConsumptionWh{" + +_value +
                '}';
    }

    /**
     * Returns the value of electric consumption in watt-hours.
     *
     * @return the value of electric consumption in watt-hours
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ElectricConsumptionWhValue objectElectricConsumptionWhValue) {
            return _value == objectElectricConsumptionWhValue._value;
        }
        return false;
    }

    /**
     * Returns the value of electric consumption in watt-hours.
     *
     * @return the value of electric consumption in watt-hours
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(_value);
    }
}
