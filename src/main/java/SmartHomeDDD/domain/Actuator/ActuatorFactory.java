package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.valueObject.*;

public interface ActuatorFactory {
    public Actuator createActuator(Object... parameters);
}
