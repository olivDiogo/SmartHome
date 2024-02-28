package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

public class SwitchSensor implements Sensor {

    private SwitchSensorValueFactory _switchSensorValueFactory;
    private final SensorType _sensorType;
    private SwitchSensorValue _value;

    public SwitchSensor(CatalogueSensor catalogue, SwitchSensorValueFactory _switchSensorValueFactory, boolean initialState) throws InstantiationException {
        this._sensorType = setSensorType(catalogue);
        this._switchSensorValueFactory = _switchSensorValueFactory;
        this._value = _switchSensorValueFactory.create(initialState);
    }

    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {

        SensorType sensorType = catalogue.getSensorType("Switch");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'Switch' does not exist.");
        else {
            return sensorType;
        }
    }

    @Override
    public SensorType getSensorType() {
        return this._sensorType;
    }

    @Override
    public Value getValue() {
        return this._value.clone();
    }
}
