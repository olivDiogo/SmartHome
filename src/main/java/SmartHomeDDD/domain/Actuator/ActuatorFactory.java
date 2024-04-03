package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.valueObject.*;

public interface ActuatorFactory {
    public Actuator createActuator(DeviceID deviceID,  ModelPath modelPath, ActuatorTypeID actuatorTypeID, ActuatorName actuatorName, Object... additionalParameters);
}
