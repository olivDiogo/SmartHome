package SmartHome.actuators;

import SmartHome.domain.Actuator;
import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import SmartHome.domain.Value;

public class SetIntegerActuator implements Actuator {
    private ActuatorType _actuatorType;
    private SetIntegerValue _value;
    private int _lowerLimit;
    private int _upperLimit;


    /**
     * Instantiates a new SetIntegerActuator.
     *
     * @param catalogue is the catalogue of the actuator
     */
    public SetIntegerActuator(CatalogueActuator catalogue) throws InstantiationException {
        setActuatorType(catalogue);
    }

    /**
     * Sets the actuator type.
     *
     * @param catalogue the catalogue
     * @throws InstantiationException if the actuator type cannot be created
     */
    private void setActuatorType(CatalogueActuator catalogue) throws InstantiationException {

        ActuatorType actuatorType = catalogue.getActuatorType("SetInteger");
        if (actuatorType == null)
            throw new InstantiationException("ActuatorType with description 'SetInteger' does not exist.");
        else {
            this._actuatorType = actuatorType;
        }
    }

    /**
     * Sets the lower limit.
     * @param lowerLimit the lower limit
     */
    private int setLowerLimit(int lowerLimit) {
        this._lowerLimit = lowerLimit;
        return lowerLimit;
    }

    /**
     * Sets the upper limit.
     * @param upperLimit the upper limit
     */
    private int setUpperLimit(int upperLimit) {
        this._upperLimit = upperLimit;
        return upperLimit;
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
     * Sets the value if within range.
     * @param value the value
     */
    public Value setValueInRange(int lowerLimit, int upperLimit, Value value) {
        int nValue = Integer.parseInt(value.toString());

        if (nValue < setLowerLimit(lowerLimit)) {
            throw new IllegalArgumentException("Value cannot be less than the lower limit.");

        } else if (nValue > setUpperLimit(upperLimit)) {
            throw new IllegalArgumentException("Value cannot be greater than the upper limit.");

        } else {
            this._value = new SetIntegerValue(nValue);

            return this._value.clone();
        }
    }
}
