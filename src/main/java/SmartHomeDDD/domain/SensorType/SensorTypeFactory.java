package SmartHomeDDD.domain.SensorType;

import SmartHomeDDD.ValueObject.TypeDescription;
import SmartHomeDDD.ValueObject.UnitDescription;
import SmartHomeDDD.domain.Room.Room;

/**
 * Interface defining a factory for creating {@link SmartHome.domain.SensorType} instances.
 * Provides a method to create a sensorType with specific description and unit.
 */
public interface SensorTypeFactory {
    /**
     * Creates and returns a new {@link SmartHome.domain.SensorType} instance with the provided description and unit.
     *
     * @param sensorTypeName the description of the sensorType
     * @param unit the unit of the sensorType
     * @return a newly created SensorType instance
     */
    SensorType createSensorType(TypeDescription sensorTypeName, UnitDescription unit);
}
