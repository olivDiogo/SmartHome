package SmartHome.dto;

import SmartHome.domain.SensorType;
import SmartHome.domain.Unit;

public class SensorTypeDTO {
    public final String sensorTypeDescription;
    public final Unit unit;

    /**
     * Constructor for the SensorTypeDTO class.
     *
     * @param sensorType The SensorType object from which to create the DTO.
     */
    public SensorTypeDTO (SensorType sensorType){
        this.sensorTypeDescription = sensorType.getDescription();
        this.unit = sensorType.getUnit();
    }
}
