package smartHome.domain.actuatorType;

import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;

/**
 * Interface defining a factory for creating {@link SmartHome.domain.ActuatorType} instances.
 * Provides a method to create a actuatorType with specific description.
 */
public interface IActuatorTypeFactory {
    /**
     * Creates and returns a new {@link SmartHome.domain.ActuatorType} instance with the provided description.
     *
     * @param name the description of the actuatorType
     * @return a newly created ActuatorType instance
     */
    ActuatorType createActuatorType(TypeDescription name, UnitID unitID);
}
