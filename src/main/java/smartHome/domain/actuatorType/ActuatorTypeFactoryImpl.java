package smartHome.domain.actuatorType;

import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;

public class ActuatorTypeFactoryImpl implements IActuatorTypeFactory {

    /**
     * Creates and returns a new {@link SmartHome.domain.ActuatorType} instance with the provided description.
     *
     * @param name the description of the actuatorType
     * @return a newly created ActuatorType instance
     */
    @Override
    public ActuatorType createActuatorType(TypeDescription name, UnitID unitID) {
        return new ActuatorType(name, unitID);
    }
}
