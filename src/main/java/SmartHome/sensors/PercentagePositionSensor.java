package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

/**
 * Represents a percentage position sensor.
 * This sensor generates a random percentage value and returns it as a {@link PercentagePositionSensorValue}.
 */
public class PercentagePositionSensor implements Sensor {

    private final SensorType _sensorType; // The type of the sensor
    private PercentagePositionSensorValue _value; // The current value of the sensor

    /**
     * Constructs a percentage position sensor with the specified catalogue.
     * @param catalogue The catalogue sensor used to retrieve sensor types.
     * @throws InstantiationException if the sensor type 'Percented' does not exist in the catalogue.
     */
    public PercentagePositionSensor(CatalogueSensor catalogue) throws InstantiationException {
        this._sensorType = setSensorType(catalogue);
    }

    /**
     * Sets the sensor type using the specified catalogue.
     * @param catalogue The catalogue sensor used to retrieve sensor types.
     * @return The sensor type 'Percented' if found in the catalogue.
     * @throws InstantiationException if the sensor type 'Percented' does not exist in the catalogue.
     */
    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("Percented");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'Percented' does not exist.");
        else {
            return sensorType;
        }
    }

    /**
     * Gets the sensor type.
     * @return The sensor type.
     */
    public SensorType getSensorType() {
        return this._sensorType;
    }

    /**
     * Gets the value of the sensor.
     * @return A {@link PercentagePositionSensorValue} representing the current value of the sensor.
     */
    public Value getValue() {
        double percentage = 100; // Placeholder value for demonstration purposes
        this._value = new PercentagePositionSensorValue(percentage);
        return this._value;
    }
}
