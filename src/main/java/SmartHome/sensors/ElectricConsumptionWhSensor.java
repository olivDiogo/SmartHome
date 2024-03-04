package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;

public class ElectricConsumptionWhSensor {
    private double _value;
    private SensorType _type;

    public ElectricConsumptionWhSensor(CatalogueSensor catalogue) throws InstantiationException {
        _type = setSensorType(catalogue);
    }

    public SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("ElectricConsumptionWh");
        if (sensorType == null) {
            throw new InstantiationException("SensorType with description 'Temperature' does not exist.");
        } else {
            return sensorType;
        }
    }

    public SensorType getSensorType() {
        return _type;
    }
}
