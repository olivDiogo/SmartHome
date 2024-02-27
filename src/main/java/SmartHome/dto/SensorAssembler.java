package SmartHome.dto;

import SmartHome.domain.Sensor;

public class SensorAssembler {

    public static SensorDTO domain2DTO(Sensor sensor){
        return new SensorDTO( sensor);
    }
}
