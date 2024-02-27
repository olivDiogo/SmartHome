package SmartHome.dto;

import SmartHome.domain.SensorType;
import SmartHome.domain.Unit;

public class SensorTypeDTO {
    public String sensorTypeDescription;
    public Unit unit;

    public SensorTypeDTO (SensorType sensorType){
        sensorTypeDescription = sensorType.getDescription();
        unit = sensorType.getUnit();
    }
}
