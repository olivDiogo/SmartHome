package smart_home.domain.service;

import smart_home.ddd.IService;
import smart_home.domain.sensor_type.SensorType;
import smart_home.value_object.SensorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

import java.util.List;
import java.util.Optional;

public interface ISensorTypeService extends IService {

    /**
     * Creates a new SensorType.
     *
     * @param name   is the name of the SensorType.
     * @param unitID is the ID of the unit.
     * @return the created SensorType.
     */
    SensorType createSensorType(TypeDescription name, UnitID unitID);

    /**
     * Adds a SensorType to the repository.
     *
     * @param sensorType is the SensorType to add.
     * @return the added SensorType.
     */
    SensorType addSensorType(SensorType sensorType);

    /**
     * Gets a SensorType by its ID.
     *
     * @param sensorTypeID is the ID of the SensorType.
     * @return the SensorType.
     */
    Optional<SensorType> getSensorTypeByID(SensorTypeID sensorTypeID);

    /**
     * Gets all SensorTypes.
     *
     * @return a list of all SensorTypes.
     */
    List<SensorType> getAllSensorTypes();
}
