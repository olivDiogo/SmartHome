package smartHome.domain.actuatorType;

import smartHome.valueObject.ActuatorTypeID;
import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;

/**
 * Interface defining a factory for creating ActuatoryType instances.
 * Provides a method to create a actuatorType with specific description.
 */
public interface IActuatorTypeFactory {
    /**
     * Creates and returns a new  instance with the provided description.
     *
     * @param name the description of the actuatorType
     * @param unitID the unitID of the actuatorType
     * @return a newly created ActuatorType instance
     */
    ActuatorType createActuatorType(TypeDescription name, UnitID unitID);

    /**
     * Creates and returns a new  instance with the provided description.
     *
     * @param name the description of the actuatorType
     * @param unitID the unitID of the actuatorType
     * @param actuatorTypeID the actuatorTypeID of the actuatorType
     * @return a newly created ActuatorType instance
     */
    ActuatorType createActuatorType(TypeDescription name, UnitID unitID, ActuatorTypeID actuatorTypeID);
}

