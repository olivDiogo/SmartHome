package smartHome.domain.actuator;

public interface IActuatorFactory {
    IActuator createActuator(Object... parameters);
}
