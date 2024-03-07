package SmartHome.domain;

public class ActuatorTypeFactory {
    /**
     * Constructor for the ActuatorTypeFactory class.
     *
     * @param strDescription The description of the actuator type.
     * @return The actuator type.
     * @throws InstantiationException If the actuator type cannot be instantiated.
     */
    public ActuatorType createActuatorType(String strDescription) throws InstantiationException {
        return new ActuatorType(strDescription);
    }
}
