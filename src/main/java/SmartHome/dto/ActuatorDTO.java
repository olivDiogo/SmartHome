package SmartHome.dto;

import SmartHome.domain.Actuator;
import SmartHome.domain.ActuatorType;

public class ActuatorDTO {

    public final ActuatorType _actuatorModel;

    public ActuatorDTO(Actuator actuator) {
        this._actuatorModel = actuator.getActuatorType();
    }

}
