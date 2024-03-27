package SmartHomeDDD.domain.SensorType;

import SmartHomeDDD.valueObject.TypeDescription;
import SmartHomeDDD.valueObject.MeasurementID;

/**
 * Implementation of the {@link SensorTypeFactory} interface, responsible for creating
 * {@link SensorType} instances.
 */
public class ImpSensorTypeFactory implements SensorTypeFactory{
    /**
     * Creates a new {@link SensorType} instance using the provided sensor type name and unit.
     *
     * @param name the sensor type name, must not be null
     * @param unit the unit of the sensor type, must not be null
     * @return a fully initialized {@link SensorType} instance
     * @throws IllegalArgumentException if any of the parameters are null, handled by the {@link SensorType} constructor
     */
    @Override
    public SensorType createSensorType(TypeDescription name, MeasurementID unit) {
        return new SensorType(name, unit);
    }
}
