package smart_home.domain.sensor_type;

import smart_home.value_object.SensorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

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

    /**
     * Creates a new {@link SensorType} instance using the provided sensor type ID, name and unit.
     *
     * @param sensorTypeID the sensor type ID, must not be null
     * @param name         the sensor type name, must not be null
     * @param unitID       the unit of the sensor type, must not be null
     * @return a fully initialized {@link SensorType} instance
     */
    @Override
    public SensorType createSensorType(SensorTypeID sensorTypeID, TypeDescription name, UnitID unitID) {
        return new SensorType(sensorTypeID, name, unitID);
    }
}
