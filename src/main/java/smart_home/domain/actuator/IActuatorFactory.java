package smart_home.domain.actuator;

public interface IActuatorFactory {
    IActuator createActuator(Object... parameters);
}
