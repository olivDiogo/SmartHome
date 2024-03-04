package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

import java.util.Random;

public class WindSensor implements Sensor {

    private final SensorType _sensorType;
    private WindSensorValue _value;

    /**
     * Constructor for WindSensor
     *
     * @param catalogue
     * @throws InstantiationException
     */
    public WindSensor(CatalogueSensor catalogue) throws InstantiationException {
        this._sensorType = setSensorType(catalogue);
    }

    /**
     * Method to set the sensor type
     *
     * @param catalogue
     * @return SensorType
     * @throws InstantiationException
     */
    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("WindSpeedAndDirection");
        if (sensorType == null)
            throw new InstantiationException("\"SensorType with description 'WindSpeedAndDirection' does not exist.\"");
        else {
            return sensorType;
        }
    }

    /**
     * Method to get the sensor type
     *
     * @return SensorType
     */
    public SensorType getSensorType() {
        return this._sensorType;
    }

    /**
     * Method to get the value of the sensor
     *
     * @return
     */
    public Value getValue() {
        Random rand = new Random();
        int speed = rand.nextInt(408); //wind speed world record
        double direction = rand.nextDouble() * 2 * Math.PI; // direction in radians
        this._value = new WindSensorValue(speed, direction);
        return this._value.clone();
    }
}
