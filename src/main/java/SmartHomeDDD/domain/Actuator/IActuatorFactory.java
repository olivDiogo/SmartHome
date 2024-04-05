package SmartHomeDDD.domain.Actuator;

public interface IActuatorFactory {
    IActuator createActuator(Object... parameters);
}
