package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

import java.util.Random;

public class PowerConsumptionSensor implements Sensor {
    private final SensorType _sensorType;

    public PowerConsumptionSensor(CatalogueSensor catalogue) throws InstantiationException {
        this._sensorType = setSensorType(catalogue);
    }

    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("Power Consumption");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'Power Consumption' does not exist.");
        else {
            return sensorType;
        }
    }

    public SensorType getSensorType() {
        return this._sensorType;
    }

    public Value getValue() {
        Random rand = new Random();
        int dValue = rand.nextInt(2000); // simulate power consumption between 0 and 2000W

        return new PowerConsumptionSensorValue(dValue);
    }
}
