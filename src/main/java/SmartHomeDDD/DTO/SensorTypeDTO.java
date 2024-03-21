package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

/**
 * Data Transfer Object (DTO) representing a sensor type.
 */
public class SensorTypeDTO implements DTO {
    /**
     * Description of the sensor type.
     */
    public final String sensorTypeDescription;

    /**
     * Unit of measurement for the sensor type.
     */
    public final String unit;

    /**
     * Constructs a new SensorTypeDTO object.
     *
     * @param sensorTypeDescription  The description of the sensor type.
     * @param unit                   The unit of measurement for the sensor type.
     */
    public SensorTypeDTO(String sensorTypeDescription, String unit) {
        this.sensorTypeDescription = sensorTypeDescription;
        this.unit = unit;
    }

    /**
     * Returns a string representation of the SensorTypeDTO object.
     *
     * @return A string representation of the SensorTypeDTO object.
     */
    @Override
    public String toString() {
        return "SensorTypeDTO{" +
                ", _sensorTypeDescription='" + sensorTypeDescription + '\'' +
                ", _unit='" + unit + '\'' +
                '}';
    }
}

