package SmartHomeDDD.domain.Sensor;

import SmartHome.domain.Value;
import SmartHomeDDD.ddd.ValueObject;

public class ElectricConsumptionWhValue implements ValueObject {
    private int _value;

    public ElectricConsumptionWhValue(int value) {
        setValue(value);
    }

    private void setValue(int value) {
        if (value < 0)
            throw new IllegalArgumentException("Consumption cannot be negative.");
        else _value = value;
    }
    @Override
    public String toString() {
        return "ElectricConsumptionWh{" + +_value +
                '}';
    }
}
