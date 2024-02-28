package SmartHome.domain;

public class ActuatorTypeFactory {
    public ActuatorType createActuatorType(String strDescription) throws InstantiationException
    {
        return new ActuatorType(strDescription);
    }
}
