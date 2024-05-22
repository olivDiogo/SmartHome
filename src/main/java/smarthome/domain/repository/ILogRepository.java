package smarthome.domain.repository;

import java.util.List;
import smarthome.ddd.IRepository;
import smarthome.domain.log.Log;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.LogID;
import smarthome.domain.value_object.SensorTypeID;


public interface ILogRepository extends IRepository<LogID, Log> {


  /**
   * Method to find logs by device and time period
   *
   * @param deviceID DeviceID object
   * @param period   DatePeriod object
   * @return List of Log
   */
  List<Log> findByDeviceIDAndDatePeriodBetween(DeviceID deviceID, DatePeriod period);

  /**
   * Method to find logs by device and sensor type and time period
   *
   * @param deviceID     DeviceID object
   * @param sensorTypeID SensorTypeID object
   * @param period       DatePeriod object
   * @return List of Log
   */
  List<Log> findByDeviceIDAndSensorTypeAndDatePeriodBetween(DeviceID deviceID,
      SensorTypeID sensorTypeID, DatePeriod period);

  /**
   * Method to find logs by device ID
   * @return List of Log
   */
  List<Log> findByDeviceIDAndSensorTypeID(DeviceID deviceID, SensorTypeID sensorTypeID);
}
