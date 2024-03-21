package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

/**
 * Data Transfer Object (DTO) representing a sensor.
 */
public class SensorDTO implements DTO {

    /**
     * Unique identifier for the sensor.
     */
    public final String _id;

    /**
     * Name of the sensor.
     */
    public final String _name;

    /**
     * Type of the sensor.
     */
    public final String _sensorType;

    /**
     * Constructs a new SensorDTO object.
     *
     * @param sensorID      The unique identifier for the sensor.
     * @param sensorName    The name of the sensor.
     * @param sensorType    The type of the sensor.
     */
    public SensorDTO(String sensorID, String sensorName, String sensorType) {
        this._id = sensorID;
        this._name = sensorName;
        this._sensorType = sensorType;
    }

    /**
     * Returns a string representation of the SensorDTO object.
     *
     * @return A string representation of the SensorDTO object.
     */
    @Override
    public String toString() {
        return "SensorDTO{" +
                "_id='" + _id + '\'' +
                ", _name='" + _name + '\'' +
                ", _sensorType='" + _sensorType + '\'' +
                '}';
    }
}

