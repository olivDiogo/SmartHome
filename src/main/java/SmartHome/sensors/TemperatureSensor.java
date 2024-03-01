package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

import java.util.Random;

public class TemperatureSensor implements Sensor {
    private final SensorType _sensorType;
    private TemperatureSensorValue _value;

    public TemperatureSensor(CatalogueSensor catalogue) throws InstantiationException
    {
        this._sensorType = setSensorType(catalogue);
    }

    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException
    {
        SensorType sensorType = catalogue.getSensorType("Temperature");
        if( sensorType == null )
            throw new InstantiationException("SensorType with description 'Temperature' does not exist.");
        else
            return sensorType;
    }

    public SensorType getSensorType()
    {
        return this._sensorType;
    }

    public Value getValue()
    {
        Random rand = new Random();
        int temperatureReading = rand.nextInt(140) - 70; // valor entre -70 e 70
        this._value = new TemperatureSensorValue(temperatureReading);

        return _value.clone();
    }
}
