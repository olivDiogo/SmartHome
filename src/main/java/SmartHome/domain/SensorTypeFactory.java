package SmartHome.domain;

public class SensorTypeFactory {
    public SensorType createSensorType(String strDescription, Unit unit) throws InstantiationException {
        return new SensorType(strDescription, unit);
    }
}
