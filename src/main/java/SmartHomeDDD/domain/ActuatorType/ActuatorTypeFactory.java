package SmartHomeDDD.domain.ActuatorType;

import SmartHomeDDD.ValueObject.TypeDescription;

/**
 * Interface defining a factory for creating {@link SmartHome.domain.ActuatorType} instances.
 * Provides a method to create a actuatorType with specific description.
 */
public interface ActuatorTypeFactory {
    /**
     * Creates and returns a new {@link SmartHome.domain.ActuatorType} instance with the provided description.
     *
     * @param actuatorTypeName the description of the actuatorType
     * @return a newly created ActuatorType instance
     */
    ActuatorType createActuatorType(TypeDescription actuatorTypeName);
}
