package smartHome.dto;

import smartHome.ddd.IDTO;

/**
 * Data Transfer Object (DTO) representing a sensor type.
 */
public class SensorTypeDTO implements IDTO {
    public final String sensorTypeID;
    public final String sensorTypeDescription;
    public final String unit;

    /**
     * Constructs a new SensorTypeDTO object.
     *
     * @param sensorTypeDescription The description of the sensor type.
     * @param unit                  The unit of measurement for the sensor type.
     */
    public SensorTypeDTO(String sensorTypeID, String sensorTypeDescription, String unit) {
        this.sensorTypeID = sensorTypeID;
        this.sensorTypeDescription = sensorTypeDescription;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return sensorTypeID + " " + sensorTypeDescription + " " + unit;
    }
}
