package SmartHomeDDD.domain.ActuatorType;

import SmartHomeDDD.ValueObject.TypeDescription;

public class ImpActuatorTypeFactory implements ActuatorTypeFactory{

    /**
     * Creates and returns a new {@link SmartHome.domain.ActuatorType} instance with the provided description.
     *
     * @param actuatorTypeName the description of the actuatorType
     * @return a newly created ActuatorType instance
     */
    @Override
    public ActuatorType createActuatorType(TypeDescription actuatorTypeName) {
        return new ActuatorType(actuatorTypeName);
    }
}
