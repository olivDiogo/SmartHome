package SmartHome.actuators;

import SmartHome.domain.Actuator;
import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;

public class BlindRollerActuator implements Actuator {

    private ActuatorType _actuatorType;
    private int _openingLevel; //Level of the blind roller actuator between 0% and 100%

    /**
     * Instantiates a new BlindRollerActuator.
     * @param catalogue is the catalogue of the actuator
     */
    public BlindRollerActuator(CatalogueActuator catalogue) throws InstantiationException {
        setActuatorType(catalogue);
        this._openingLevel = 0; //Initial closed position
    }

    /**
     * Sets the actuator type.
     * @param catalogue the catalogue
     * @throws InstantiationException if the actuator type cannot be created
     */

    private void setActuatorType(CatalogueActuator catalogue) throws InstantiationException {
        ActuatorType actuatorType = catalogue.getActuatorType("BlindRollerActuator");
        if (actuatorType == null)
            throw new InstantiationException("ActuatorType with description 'BlindRollerActuator' does not exist.");
        else {
            this._actuatorType = actuatorType;
        }
    }
    /**
     * Getter for the actuator type
     */
        public ActuatorType getActuatorType() {
            return this._actuatorType;
        }




}


