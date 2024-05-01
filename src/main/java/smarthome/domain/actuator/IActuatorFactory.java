package smarthome.domain.actuator;

public interface IActuatorFactory {

  IActuator create(Object... parameters);
}
