package SmartHome.actuators;


import SmartHome.domain.Actuator;
import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import SmartHome.domain.Value;


public class SetDecimalActuator implements Actuator {
    private ActuatorType _actuatorType; // The type of the actuator
    private SetDecimalValue _value; // The decimal value to be set
    private double _lowerLimit; // The lower limit of the value range
    private double _upperLimit; // The upper limit of the value range



    public SetDecimalActuator(CatalogueActuator catalogue) throws InstantiationException {
        setActuatorType(catalogue);
    }


    private void setActuatorType(CatalogueActuator catalogue) throws InstantiationException {
        ActuatorType actuatorType = catalogue.getActuatorType("SetDecimal");
        if (actuatorType == null)
            throw new InstantiationException("ActuatorType with description 'SetDecimal' does not exist.");
        else {
            this._actuatorType = actuatorType;
        }
    }

    private double setLowerLimit(double lowerLimit) {
        this._lowerLimit = lowerLimit;
        return lowerLimit;

    }


    private double setUpperLimit(double upperLimit) {
        this._upperLimit = upperLimit;
        return upperLimit;
    }

    public ActuatorType getActuatorType() {
        return _actuatorType;
    }


    public Value setValueInRange(Value value, double lowerLimit, double upperLimit) {

        double nValue = Double.parseDouble(value.toString());

        if (nValue < lowerLimit) {
            throw new IllegalArgumentException("Value cannot be less than the lower limit.");
        } else if (nValue > upperLimit) {
            throw new IllegalArgumentException("Value cannot be greater than the upper limit.");
        } else if (value instanceof SetDecimalValue) {
            this._value = (SetDecimalValue) value;
            return _value.clone();
        }
        else {
            return null;
        }

    }
}

