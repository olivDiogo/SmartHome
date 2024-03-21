package SmartHomeDDD.domain.ActuatorType;

import SmartHomeDDD.ValueObject.TypeDescription;

public class ImpActuatorTypeFactory implements ActuatorTypeFactory{
    @Override
    public ActuatorType createActuatorType(TypeDescription actuatorTypeName) {
        return new ActuatorType(actuatorTypeName);
    }
}
