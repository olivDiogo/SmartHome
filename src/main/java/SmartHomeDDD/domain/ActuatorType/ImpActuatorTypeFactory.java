package SmartHomeDDD.domain.ActuatorType;

import SmartHomeDDD.valueObject.TypeDescription;
import SmartHomeDDD.valueObject.UnitID;

public class ImpActuatorTypeFactory implements ActuatorTypeFactory {

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
