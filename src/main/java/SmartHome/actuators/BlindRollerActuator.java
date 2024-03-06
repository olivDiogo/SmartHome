package SmartHome.actuators;

import SmartHome.domain.Actuator;
import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import SmartHome.domain.Value;

/**
 * Represents a blind roller actuator in a smart home system.
 * This actuator is responsible for controlling the position of a blind roller,
 * allowing it to be set to a specific value within an acceptable range.
 */
public class BlindRollerActuator implements Actuator {

    private ActuatorType _actuatorType;
    private BlindRollerValue _value;

    /**
     * Constructs a BlindRollerActuator and sets its actuator type based on the provided catalogue.
     *
     * @param catalogue A CatalogueActuator object used to look up the ActuatorType for this actuator.
     * @throws InstantiationException If the actuator type "BlindRollerActuator" does not exist in the catalogue.
     */
    public BlindRollerActuator(CatalogueActuator catalogue) throws InstantiationException {
        setActuatorType(catalogue);
    }

    /**
     * Sets the actuator type of this actuator using the given catalogue.
     * If the actuator type "BlindRollerActuator" is not found, it throws an InstantiationException.
     *
     * @param catalogue A CatalogueActuator used to retrieve the ActuatorType.
     * @throws InstantiationException If the ActuatorType with description "BlindRollerActuator" does not exist.
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
     * Retrieves the actuator type of this actuator.
     *
     * @return The ActuatorType of this actuator.
     */
    public ActuatorType getActuatorType() {
        return this._actuatorType;
    }

    /**
     * Sets the value of the blind roller to a specific position after validating the input.
     * converted to an integer.
     * @param value The desired position of the blind roller, potentially including non-digit characters.
     * @return A cloned instance of BlindRollerValue representing the sanitized and validated position.
     * @throws NumberFormatException If the string value after sanitization is not a valid integer.
     */
    public Value setValue(Value value) {
        if(value instanceof BlindRollerValue){
            this._value = (BlindRollerValue) value;
            return _value.clone();
        }
        else {
            return null;
        }
    }
}
