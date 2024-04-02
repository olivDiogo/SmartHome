package SmartHome.actuators;

import SmartHome.domain.Actuator;
import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import SmartHome.domain.Value;

public class SwitchActuator implements Actuator {

    private ActuatorType _actuatorType;
    private SwitchActuatorValue _value;

    /**
     * Instantiates a new SwitchActuator.
     *
     * @param catalogue is the catalogue of the actuator
     */

    public SwitchActuator(CatalogueActuator catalogue) throws InstantiationException {
        setActuatorType(catalogue);
        this._value = new SwitchActuatorValue(false);
    }

    /**
     * Sets the actuator type.
     *
     * @param catalogue the catalogue
     * @throws InstantiationException if the actuator type cannot be created
     */

    private void setActuatorType(CatalogueActuator catalogue) throws InstantiationException {
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
    public ActuatorType getActuatorType() {
        return this._actuatorType;
    }


    /**
     * Sets the value of the switch actuator.
     *
     * @return the value of the switch actuator.
     */
    public Value setValue(Value value) {

        if (value instanceof SwitchActuatorValue) {
            this._value = (SwitchActuatorValue) value;
            return this._value.clone();
        } else {
            return null;
        }
    }
}
