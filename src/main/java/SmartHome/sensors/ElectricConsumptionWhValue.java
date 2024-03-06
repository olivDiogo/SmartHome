package SmartHome.sensors;

import SmartHome.domain.Value;

public class ElectricConsumptionWhValue implements Value, Cloneable {
    private double _value;

    public ElectricConsumptionWhValue(double value) {
        setValue(value);
    }

    private void setValue(double value) {
        if (value < 0)
            throw new IllegalArgumentException("Consumption cannot be negative.");
        else _value = value;
    }
    @Override
    public String toString() {
        return "ElectricConsumptionWh{" + +_value +
                '}';
    }
    @Override
    public ElectricConsumptionWhValue clone() {
        try {
            return (ElectricConsumptionWhValue) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("ElectricConsumptionWhValue instance could not be cloned.");
        }
    }

}
