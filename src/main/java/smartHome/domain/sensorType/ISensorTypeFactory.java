package smartHome.domain.sensorType;

import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;

/**
 * Interface defining a factory for creating {@link SmartHome.domain.SensorType} instances.
 * Provides a method to create a sensorType with specific description and unit.
 */
public interface ISensorTypeFactory {
    /**
     * Creates and returns a new {@link SmartHome.domain.SensorType} instance with the provided description and unit.
     *
     * @param name the description of the sensorType
     * @param unit the unit of the sensorType
     * @return a newly created SensorType instance
     */
    SensorType createSensorType(TypeDescription name, UnitID unit);
}
