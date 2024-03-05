package SmartHome.dto;

import SmartHome.domain.Actuator;

public class ActuatorAssembler {

    public static ActuatorDTO domain2DTO(Actuator actuator){
        return new ActuatorDTO( actuator);
    }
}
