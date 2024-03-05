package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

/**
 * Represents a humidity sensor that measures the percentage of humidity in the air.
 */
public class HumiditySensor implements Sensor {
    /**
     * The type of sensor, specifically for humidity measurement.
     */
    private final SensorType _sensorType;

    /**
     * The current value of the humidity measured by the sensor.
     */
    private HumiditySensorValue _value;

    /**
     * Constructs a HumiditySensor with a specific SensorType from the given catalogue.
     *
     * @param catalogue The catalogue from which to retrieve the SensorType.
     * @throws InstantiationException if the SensorType for "Humidity" does not exist in the catalogue.
     */
    public HumiditySensor(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("Humidity");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'Humidity' does not exist.");
        else
            this._sensorType = sensorType;
    }

    /**
     * Returns the sensor type of this HumiditySensor.
     *
     * @return The SensorType for humidity.
     */
    public SensorType getSensorType() {
        return this._sensorType;
    }

    /**
     * Generates and returns a new humidity value.
     *
     * @return A Value object representing the current humidity.
     */

    public Value getValue() {

        int nValue = 100;
        _value = new HumiditySensorValue(nValue);

        return _value.clone();
    }
}
