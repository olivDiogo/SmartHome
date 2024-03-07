package SmartHome.dto;

import SmartHome.domain.SensorType;

public class SensorTypeMapper {

    /**
     * Converts a SensorType domain object to a SensorTypeDTO.
     *
     * @param sensorType The SensorType domain object to be converted.
     * @return A SensorTypeDTO object that represents the given SensorType.
     */
    public static SensorTypeDTO domain2DTO(SensorType sensorType){
        return new SensorTypeDTO( sensorType);
    }
}
