package SmartHome.actuators;


import SmartHome.domain.Actuator;
import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import SmartHome.domain.Value;

/**
 * This class represents an actuator that sets decimal values within a specified range.
 */
public class SetDecimalActuator implements Actuator {
    private ActuatorType _actuatorType; // The type of the actuator
    private SetDecimalValue _value; // The decimal value to be set
    private double _lowerLimit; // The lower limit of the value range
    private double _upperLimit; // The upper limit of the value range

    /**
     * Constructs a SetDecimalActuator object.
     *
     * @param catalogue The catalogue containing the actuator type information.
     * @throws InstantiationException if the actuator type does not exist in the catalogue.
     */
    public SetDecimalActuator(CatalogueActuator catalogue) throws InstantiationException {
        setActuatorType(catalogue);
    }

    /**
     * Sets the actuator type based on the provided catalogue.
     *
     * @param catalogue The catalogue containing the actuator type information.
     * @throws InstantiationException if the actuator type does not exist in the catalogue.
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
     * @return The lower limit that was set.
     */
    private double setLowerLimit(double lowerLimit) {
        this._lowerLimit = lowerLimit;
        return lowerLimit;
    }

    /**
     * Sets the upper limit of the value range.
     *
     * @param upperLimit The upper limit of the value range.
     * @return The upper limit that was set.
     */
    private double setUpperLimit(double upperLimit) {
        this._upperLimit = upperLimit;
        return upperLimit;
    }

    /**
     * Gets the type of the actuator.
     *
     * @return The type of the actuator.
     */
    public ActuatorType getActuatorType() {
        return _actuatorType;
    }

    /**
     * Sets the value range for the actuator and validates the input value.
     *
     * @param value       The value to be set.
     * @param lowerLimit  The lower limit of the value range.
     * @param upperLimit  The upper limit of the value range.
     * @return A cloned instance of the set value.
     * @throws IllegalArgumentException if the value is outside the specified range.
     */
    public Value setValueRange(Value value, double lowerLimit, double upperLimit) {

        double nValue = Double.parseDouble(value.toString());

        if (nValue < setLowerLimit(lowerLimit)) {
            throw new IllegalArgumentException("Value cannot be less than the lower limit.");

        } else if (nValue > setUpperLimit(upperLimit)) {
            throw new IllegalArgumentException("Value cannot be greater than the upper limit.");

        } else {
            this._value = new SetDecimalValue(nValue);

            return this._value.clone();
        }
    }
}

