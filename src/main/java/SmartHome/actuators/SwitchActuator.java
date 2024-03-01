package SmartHome.actuators;

import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import SmartHome.domain.Actuator;
import SmartHome.domain.Value;

public class SwitchActuator implements Actuator {

    private ActuatorType _actuatorType;
    private boolean _value;

    /**
     * Instantiates a new SwitchActuator.
     *
     * @param catalogue is the catalogue of the actuator
     */

    public SwitchActuator(CatalogueActuator catalogue) throws InstantiationException {
        setActuatorType(catalogue);
        _value = false;
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

    /**
     * Set the actuator on
     */
    public boolean setTheActuatorOn(){
        if (!_value) {
            _value = true;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Set the actuator off
     */
    public boolean setTheActuatorOff(){
        if (_value) {
            _value = false;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public Value setValue(Value value) {
        return value;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    @Override
    public String toString() {
        return (_value) ? "On" : "Off";
    }

}
