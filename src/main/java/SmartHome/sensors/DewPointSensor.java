package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

public class DewPointSensor implements Sensor {
    private final SensorType _sensorType;

    /**
     * Constructor of the class. It validates the sensor type.
     * @param catalogue The catalogue of sensors.
     * @throws InstantiationException If the sensor type does not exist.
     */
    public DewPointSensor(CatalogueSensor catalogue) throws InstantiationException {
        this._sensorType = validationOfSensorType(catalogue);
        }


    /**
     * Validates the sensor type.
     * @param catalogue The catalogue of sensors.
     * @return The sensor type.
     * @throws InstantiationException If the sensor type does not exist.
     */
    private SensorType validationOfSensorType (CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("DewPoint");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'DewPoint' does not exist.");
        else {
            return sensorType;
        }
    }

    /**
     * Gets the sensor type.
      * @return The sensor type.
     */
    @Override
    public SensorType getSensorType() {
        return this._sensorType;
    }

    /**
     * Gets the value of the sensor. In this case is returning a fixed value, unitl the service with real data is implemented.
      * @return The value measured by the sensor.
     */
    @Override
    public Value getValue() {
        int dewPointValue = 25;

        return new DewPointValue(dewPointValue) ;
    }
}
