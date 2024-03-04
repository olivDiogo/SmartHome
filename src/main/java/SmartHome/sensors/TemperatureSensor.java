package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

import java.util.Random;

/**
 * Represents a temperature sensor that measures the temperature in degrees Celsius.
 * This sensor is part of a Smart Home system and can provide current temperature readings.
 */
public class TemperatureSensor implements Sensor {
    /**
     * The type of sensor as defined in the sensor catalogue, specifically for temperature measurement.
     */
    private final SensorType _sensorType;

    /**
     * The current value of the temperature measured by the sensor.
     */
    private TemperatureSensorValue _value;

    /**
     * Constructs a TemperatureSensor with a specific SensorType from the given catalogue.
     *
     * @param catalogue The catalogue from which to retrieve the SensorType for "Temperature".
     * @throws InstantiationException if the SensorType for "Temperature" does not exist in the catalogue.
     */
    public TemperatureSensor(CatalogueSensor catalogue) throws InstantiationException {
        this._sensorType = setSensorType(catalogue);
    }

    /**
     * Retrieves and sets the sensor type from the catalogue based on the sensor's description.
     *
     * @param catalogue The catalogue from which to retrieve the sensor type.
     * @return The sensor type for "Temperature".
     * @throws InstantiationException if the sensor type with the specified description does not exist.
     */
    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("Temperature");
        if (sensorType == null) {
            throw new InstantiationException("SensorType with description 'Temperature' does not exist.");
        } else {
            return sensorType;
        }
    }

    /**
     * Returns the sensor type of this TemperatureSensor.
     *
     * @return The SensorType for temperature measurement.
     */
    @Override
    public SensorType getSensorType() {
        return this._sensorType;
    }

    /**
     * Generates and returns a new temperature value within a realistic range.
     * The temperature is generated randomly between -70 and 70 degrees Celsius to simulate a real temperature measurement.
     *
     * @return A Value object representing the current temperature.
     */
    @Override
    public Value getValue() {
        Random rand = new Random();
        double temperatureReading = rand.nextInt(140) - 70; // temperature between -70 and 70 degrees Celsius
        this._value = new TemperatureSensorValue(temperatureReading);

        return _value.clone();
    }
}