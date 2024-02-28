package SmartHome.actuators;


import SmartHome.domain.Actuator;
import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;

public class SetDecimalActuator implements Actuator {
    private ActuatorType _actuatorType;
    private double _lowerLimit;
    private double _upperLimit;
    private double _value;
    public SetDecimalActuator(CatalogueActuator catalogue, double lowerLimit, double upperLimit) throws InstantiationException {
        setActuatorType(catalogue);
        setLowerLimit(lowerLimit);
        setUpperLimit(upperLimit);
    }

    private void setActuatorType(CatalogueActuator catalogue) throws InstantiationException {
        ActuatorType actuatorType = catalogue.getActuatorType("SetDecimal");
        if (actuatorType == null)
            throw new InstantiationException("ActuatorType with description 'SetDecimal' does not exist.");
        else {
            this._actuatorType = actuatorType;
        }
    }

    private void setLowerLimit(double lowerLimit) {
        this._lowerLimit = lowerLimit;
    }

    private void setUpperLimit(double upperLimit) {
        this._upperLimit = upperLimit;
    }

    public ActuatorType getActuatorType() {
        return _actuatorType;
    }

    public double setValueRange(double value) {
        if (value < _lowerLimit || value > _upperLimit) {
            throw new IllegalArgumentException("The value is out of the limits.");
        }
        this._value = value;
        return _value;
    }
}
