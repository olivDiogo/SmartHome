package smartHome.domain.sensorType;

import smartHome.valueObject.SensorTypeID;
import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;

/**
 * Interface defining a factory for creating sensorType instances.
 * Provides a method to create a sensorType with specific description and unit.
 */
public interface ISensorTypeFactory {
    /**
     * Creates and returns a new sensorType instance with the provided description and unit.
     *
     * @param name the description of the sensorType
     * @param unit the unit of the sensorType
     * @return a newly created SensorType instance
     */
    SensorType createSensorType(TypeDescription name, UnitID unit);

    /**
     * Create a sensorType with the following parameters:
     * @param sensorTypeID is the sensorTypeID
     * @param name is the sensor type name.
     * @param unitID is the unit.
     * @return a sensorType.
     */
    SensorType createSensorType (SensorTypeID sensorTypeID, TypeDescription name, UnitID unitID);
}
