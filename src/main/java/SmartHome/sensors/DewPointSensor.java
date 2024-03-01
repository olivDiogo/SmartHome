package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

import java.util.Random;

public class DewPointSensor implements Sensor {
    private SensorType _sensorType;
    private DewPointValue _dewPointValue;


    /**
     * Constructor of the class. It validates the sensor type.
     *
     * @param catalogue The catalogue of sensors.
     * @throws InstantiationException If the sensor type does not exist.
     */
    public DewPointSensor(CatalogueSensor catalogue) throws InstantiationException {
        setSensorType(catalogue);

    }


    /**
     * Validates the sensor type.
     *
     * @param catalogue The catalogue of sensors.
     * @throws IllegalArgumentException If the sensor type does not exist.
     */
    private void setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("DewPoint");

        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'DewPoint' does not exist.");


        this._sensorType = sensorType;

    }

    /**
     * Gets the sensor type.
     *
     * @return The sensor type.
     */
    public SensorType getSensorType() {
        return this._sensorType;
    }

    /**
     * Gets the value of the sensor. In this case is returning a fixed value, until the service with real data is implemented.
     *
     * @return The value measured by the sensor.
     */
    @Override
    public Value getValue() {
        int dewPointValue = 25;

        this._dewPointValue = new DewPointValue(dewPointValue);

        return this._dewPointValue.clone();
    }

}
