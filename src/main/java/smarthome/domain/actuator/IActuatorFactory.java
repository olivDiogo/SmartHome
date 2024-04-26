package smarthome.domain.actuator;

public interface IActuatorFactory {

  IActuator createActuator(Object... parameters);
}
