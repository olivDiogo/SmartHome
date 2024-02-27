package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

public class SwitchSensor implements Sensor {
    private final SensorType _sensorType; // This needs to be initialized in the constructor
    private boolean status;

    // Modified to use the passed 'catalogue' parameter correctly
    public SwitchSensor(CatalogueSensor catalogue) throws InstantiationException {
        this._sensorType = setSensorType(catalogue);
        status = false;
    }

    // Modified to return SensorType and use the passed 'catalogue' object
    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {

        SensorType sensorType = catalogue.getSensorType("Switch");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'Switch' does not exist.");
        else {
            return sensorType; // Return the sensorType instead of setting it directly
        }
    }

    @Override
    public SensorType getSensorType() {
        return this._sensorType;
    }

    @Override
    public Value getValue() {
        return new SwitchSensorValue(status);
    }
}
