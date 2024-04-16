package smart_home.domain.actuator_type;

import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

public class ActuatorTypeFactoryImpl implements IActuatorTypeFactory {

    /**
     * Creates and returns a new ActuatorType instance with the provided description.
     *
     * @param name the description of the actuatorType
     * @return a newly created ActuatorType instance
     */
    @Override
    public ActuatorType createActuatorType(TypeDescription name, UnitID unitID) {
        return new ActuatorType(name, unitID);
    }

    /**
     * @param name
     * @param unitID
     * @param actuatorTypeID
     * @return
     */
    @Override
    public ActuatorType createActuatorType(TypeDescription name, UnitID unitID, ActuatorTypeID actuatorTypeID) {
        return new ActuatorType(name, unitID, actuatorTypeID);
    }
}
