package smarthome.service;

import java.util.List;
import java.util.Optional;
import smarthome.ddd.IService;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.SensorID;

public interface ISensorService extends IService {

  /**
   * Adds a new sensor to the repository.
   *
   * @param parameters the parameters of the sensor.
   * @return the sensor that was added.
   */
  ISensor addSensor(Object... parameters);

  /**
   * Get sensors by ID
   *
   * @param sensorID is the sensorID
   * @return the sensor
   */
  Optional<ISensor> getSensorByID(SensorID sensorID);

  /**
   * Get all sensors
   *
   * @return a list of sensors
   */
  List<ISensor> getAllSensors();

  List<ISensor> getSensorsByDeviceID(DeviceID deviceID);

}
