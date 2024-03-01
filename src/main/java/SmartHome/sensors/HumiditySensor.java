package SmartHome.sensors;



import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

import java.util.Random;

public class HumiditySensor implements Sensor {
    private final SensorType _sensorType;

    private HumiditySensorValue _value;

    public HumiditySensor(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("Humidity");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'Humidity' does not exist.");
        else
            this._sensorType = sensorType;
    }

    public SensorType getSensorType() {
        return this._sensorType;
    }

    public Value getValue() {
        Random rand = new Random();
        int nValue = rand.nextInt(100); // values between 0 and 100
        _value = new HumiditySensorValue(nValue);

        return _value.clone();
    }
}
