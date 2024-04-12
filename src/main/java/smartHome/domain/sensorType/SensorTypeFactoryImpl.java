package smartHome.domain.sensorType;

import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;

/**
 * Implementation of the {@link ISensorTypeFactory} interface, responsible for creating
 * {@link SensorType} instances.
 */
public class SensorTypeFactoryImpl implements ISensorTypeFactory {
    /**
     * Creates a new {@link SensorType} instance using the provided sensor type name and unit.
     *
     * @param name the sensor type name, must not be null
     * @param unit the unit of the sensor type, must not be null
     * @return a fully initialized {@link SensorType} instance
     * @throws IllegalArgumentException if any of the parameters are null, handled by the {@link SensorType} constructor
     */
    @Override
    public SensorType createSensorType(TypeDescription name, UnitID unit) {
        return new SensorType(name, unit);
    }
}
