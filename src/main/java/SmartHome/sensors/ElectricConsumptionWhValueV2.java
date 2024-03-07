package SmartHome.sensors;

import SmartHome.domain.Sensor;
import SmartHome.domain.Value;

public class ElectricConsumptionWhValueV2 implements Value {
    private int _value;

    public ElectricConsumptionWhValueV2(int value) {
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
