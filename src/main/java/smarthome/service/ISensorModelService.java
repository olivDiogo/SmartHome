package smarthome.service;

import java.util.List;
import java.util.Optional;
import smarthome.ddd.IService;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.SensorTypeID;

public interface ISensorModelService extends IService {

  /**
   * Create a new sensor model.
   *
   * @param sensorModelName the name of the sensor model.
   * @param sensorPath      the path of the sensor model.
   * @param sensorTypeID    the type of the sensor model.
   * @return the sensor model that was created.
   */
  SensorModel createSensorModel(SensorModelName sensorModelName, ModelPath sensorPath,
      SensorTypeID sensorTypeID);

  /**
   * Gets all sensor models in the repository.
   *
   * @return a list of all sensor models.
   */
  List<SensorModel> getAllSensorModels();

  /**
   * Gets a sensor model by its path.
   *
   * @param modelPath the path of the sensor model.
   * @return the sensor model with the provided path.
   */
  Optional<SensorModel> getSensorModel(ModelPath modelPath);

  /**
   * Gets all sensor models by their sensor type ID.
   *
   * @param sensorTypeID the ID of the sensor type.
   * @return a list of all sensor models with the provided sensor type ID.
   */
  List<SensorModel> getSensorModelsBySensorTypeId(SensorTypeID sensorTypeID);
}
