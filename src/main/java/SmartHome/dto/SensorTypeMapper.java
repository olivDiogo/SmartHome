package SmartHome.dto;

import SmartHome.domain.SensorType;

public class SensorTypeMapper {
    public static SensorTypeDTO domain2DTO(SensorType sensorType){
        return new SensorTypeDTO( sensorType);
    }
}
