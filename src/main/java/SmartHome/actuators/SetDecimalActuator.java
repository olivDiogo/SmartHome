package SmartHome.actuators;


import SmartHome.domain.Actuator;
import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import SmartHome.domain.Value;


/**
 * Implementation of an Actuator for setting decimal values within a specified range.
 */
public class SetDecimalActuator implements Actuator {
    private ActuatorType _actuatorType; // The type of the actuator
    private SetDecimalValue _value; // The decimal value to be set
    private double _lowerLimit; // The lower limit of the value range
    private double _upperLimit; // The upper limit of the value range

    /**
     * Constructs a SetDecimalActuator object based on the provided CatalogueActuator.
     *
     * @param catalogue The CatalogueActuator to retrieve actuator type information from.
     * @throws InstantiationException If the actuator type with description 'SetDecimal' does not exist.
     */
    public SetDecimalActuator(CatalogueActuator catalogue) throws InstantiationException {
        setActuatorType(catalogue);
    }

    /**
     * Sets the actuator type based on the provided CatalogueActuator.
     *
     * @param catalogue The CatalogueActuator containing the actuator type information.
     * @throws InstantiationException If the actuator type with description 'SetDecimal' does not exist.
     */
    private void setActuatorType(CatalogueActuator catalogue) throws InstantiationException {
        ActuatorType actuatorType = catalogue.getActuatorType("SetDecimal");
        if (actuatorType == null)
            throw new InstantiationException("ActuatorType with description 'SetDecimal' does not exist.");
        else {
            this._actuatorType = actuatorType;
        }
    }

    /**
     * Sets the lower limit of the value range.
     *
     * @param lowerLimit The lower limit of the value range.
     * @return The lower limit value.
     */
    private double setLowerLimit(double lowerLimit) {
        this._lowerLimit = lowerLimit;
        return lowerLimit;
    }

    /**
     * Sets the upper limit of the value range.
     *
     * @param upperLimit The upper limit of the value range.
     * @return The upper limit value.
     */
    private double setUpperLimit(double upperLimit) {
        this._upperLimit = upperLimit;
        return upperLimit;
    }

    /**
     * Retrieves the actuator type.
     *
     * @return The actuator type.
     */
    public ActuatorType getActuatorType() {
        return _actuatorType;
    }

    /**
     * Sets a value within the specified range.
     *
     * @param value      The value to set.
     * @param lowerLimit The lower limit of the value range.
     * @param upperLimit The upper limit of the value range.
     * @return The cloned SetDecimalValue object if the value is within the range, null otherwise.
     * @throws IllegalArgumentException If the value is outside the specified range.
     */
    public Value setValueInRange(Value value, double lowerLimit, double upperLimit) {
        double nValue = Double.parseDouble(value.toString());

        if (nValue < setLowerLimit(lowerLimit)) {
            throw new IllegalArgumentException("Value cannot be less than the lower limit.");
        } else if (nValue > setUpperLimit(upperLimit)) {
            throw new IllegalArgumentException("Value cannot be greater than the upper limit.");
        } else if (value instanceof SetDecimalValue) {
            this._value = (SetDecimalValue) value;
            return _value.clone();
        } else {
            return null;
        }
    }
}


