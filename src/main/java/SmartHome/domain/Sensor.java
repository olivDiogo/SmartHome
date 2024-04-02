package SmartHome.domain;

public interface Sensor {
    /**
     * Gets the type of the sensor.
     *
     * @return The type of the sensor.
     */
    SensorType getSensorType();

    /**
     * Gets the value of the sensor.
     *
     * @return The value of the sensor.
     */
    Value getValue();
}
