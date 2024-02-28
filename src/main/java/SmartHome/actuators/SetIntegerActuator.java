package SmartHome.actuators;

import SmartHome.domain.Actuator;
import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import SmartHome.domain.SensorType;

public class SetIntegerActuator implements Actuator {
    private ActuatorType _actuatorType;

    /**
     * Instantiates a new SetIntegerActuator.
     *
     * @param catalogue is the catalogue of the actuator
     */
    public SetIntegerActuator(CatalogueActuator catalogue) throws InstantiationException {
        this._actuatorType = setActuatorType(catalogue);
    }

    /**
     * Sets the actuator type.
     *
     * @param catalogue the catalogue
     * @return the actuator type
     * @throws InstantiationException if the actuator type cannot be created
     */
    private ActuatorType setActuatorType(CatalogueActuator catalogue) throws InstantiationException {

        ActuatorType actuatorType = catalogue.getActuatorType("SetInteger");
        if (actuatorType == null)
            throw new InstantiationException("ActuatorType with description 'SetInteger' does not exist.");
        else {
            return actuatorType; // Return the actuatorType instead of setting it directly
        }
    }

    /**
     * Gets the actuator type.
     *
     * @return the actuator type
     */
    public ActuatorType getActuatorType() {
        return this._actuatorType;
    }
}
