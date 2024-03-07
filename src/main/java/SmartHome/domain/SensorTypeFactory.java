package SmartHome.domain;

public class SensorTypeFactory {

    /**
     * Creates a new SensorType object with the given description and unit.
     *
     * @param strDescription The description of the sensor type.
     * @param unit           The unit of the sensor type.
     * @return A new SensorType object with the specified description and unit.
     * @throws InstantiationException if the SensorType class for the specified description and unit cannot be instantiated.
     */
    public SensorType createSensorType(String strDescription, Unit unit) throws InstantiationException {
        return new SensorType(strDescription, unit);
    }
}
