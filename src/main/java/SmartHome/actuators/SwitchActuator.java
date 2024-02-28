package SmartHome.actuators;

import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import SmartHome.domain.Actuator;

public class SwitchActuator implements Actuator {

    private ActuatorType _actuatorType;

    /**
     * Instantiates a new SwitchActuator.
     *
     * @param catalogue is the catalogue of the actuator
     */

    public SwitchActuator(CatalogueActuator catalogue) throws InstantiationException {
        setActuatorType(catalogue);
    }

    /**
     * Sets the actuator type.
     *
     * @param catalogue the catalogue
     * @throws InstantiationException if the actuator type cannot be created
     */

    public void setActuatorType(CatalogueActuator catalogue) throws InstantiationException {
        ActuatorType actuatorType = catalogue.getActuatorType("SwitchActuator");
        if (actuatorType == null)
            throw new InstantiationException("ActuatorType with description 'SwitchActuator' does not exist.");
        else {
            this._actuatorType = actuatorType;
        }
    }

    /**
     * Gets the actuator type.
     *
     * @return the actuator type
     */
    public ActuatorType getActuatorType(){
        return this._actuatorType;
    }

}
