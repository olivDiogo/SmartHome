package smart_home.visitor_pattern;

import smart_home.domain.actuator.blind_roller_actuator.BlindRollerActuator;
import smart_home.domain.actuator.set_decimal_actuator.SetDecimalActuator;
import smart_home.domain.actuator.set_integer_actuator.SetIntegerActuator;
import smart_home.domain.actuator.switch_actuator.SwitchActuator;

public interface IActuatorVisitor {

    String visitorSetIntegerActuator(SetIntegerActuator setIntegerActuator);
    String visitorSetDecimalActuator(SetDecimalActuator setDecimalActuator);
    String visitorSwitchActuator(SwitchActuator setBooleanActuator);
    String visitorBlindRollerActuator(BlindRollerActuator blindRollerActuator);


}
