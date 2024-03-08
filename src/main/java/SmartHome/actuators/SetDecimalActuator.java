package SmartHome.actuators;


import SmartHome.domain.Actuator;
import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import SmartHome.domain.Value;


public class SetDecimalActuator implements Actuator {
    private ActuatorType _actuatorType; // The type of the actuator
    private SetDecimalValue _value; // The decimal value to be set



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


    public ActuatorType getActuatorType() {
        return _actuatorType;
    }


    public Value setValue(Value value) {

        double nValue = Double.parseDouble(value.toString());

        if (value instanceof SetDecimalValue) {
            this._value = (SetDecimalValue) value;
            return _value.clone();
        }
        else {
            return null;
        }

    }
}

