package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

/**
 * Data Transfer Object (DTO) representing a sensor type.
 */
public class SensorTypeDTO implements DTO {

    /**
     * Unique identifier for the sensor type.
     */
    public final String _id;

    /**
     * Description of the sensor type.
     */
    public final String _sensorTypeDescription;

    /**
     * Unit of measurement for the sensor type.
     */
    public final String _unit;

    /**
     * Constructs a new SensorTypeDTO object.
     *
     * @param sensorTypeID           The unique identifier for the sensor type.
     * @param sensorTypeDescription  The description of the sensor type.
     * @param unit                   The unit of measurement for the sensor type.
     */
    public SensorTypeDTO(String sensorTypeID, String sensorTypeDescription, String unit) {
        this._id = sensorTypeID;
        this._sensorTypeDescription = sensorTypeDescription;
        this._unit = unit;
    }

    /**
     * Returns a string representation of the SensorTypeDTO object.
     *
     * @return A string representation of the SensorTypeDTO object.
     */
    @Override
    public String toString() {
        return "SensorTypeDTO{" +
                "_id='" + _id + '\'' +
                ", _sensorTypeDescription='" + _sensorTypeDescription + '\'' +
                ", _unit='" + _unit + '\'' +
                '}';
    }
}

