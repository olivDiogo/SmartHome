package smarthome.domain.service;

import java.util.List;
import java.util.Optional;
import smarthome.ddd.IService;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;

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
