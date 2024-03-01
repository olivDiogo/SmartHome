package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

public class SwitchSensor implements Sensor {

    private final SensorType _sensorType;
    private SwitchSensorValue _value;

    public SwitchSensor(CatalogueSensor catalogue) throws InstantiationException {
        this._sensorType = setSensorType(catalogue);
        this._value = new SwitchSensorValue(false);
    }

    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {

        SensorType sensorType = catalogue.getSensorType("Switch");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'Switch' does not exist.");
        else {
            return sensorType;
        }
    }
    public SensorType getSensorType() {
        return this._sensorType;
    }

    public Value getValue() {
        return this._value.clone();
    }
}
