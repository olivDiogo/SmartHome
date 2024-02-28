package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

public class PercentagePositionSensor implements Sensor {
    private final SensorType _sensorType;
    private double _percented;
    public PercentagePositionSensor(CatalogueSensor catalogue) throws InstantiationException {
        this._sensorType = setSensorType(catalogue);
        this._percented = validatePositionValue();
    }
    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {

        SensorType sensorType = catalogue.getSensorType("Percented");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'Percented' does not exist.");
        else {
            return sensorType;
        }
    }
    private double validatePositionValue(){
        if (_percented < 0 || _percented > 100){
            throw new IllegalArgumentException("The value of the position must be between 0 and 100");
        }
        return _percented;
    }
    @Override
    public SensorType getSensorType() {
        return this._sensorType;
    }
    @Override
    public Value getValue() {
        return new PercentagePositionSensorValue(_percented);
    }
}
