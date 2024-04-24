package smart_home.domain.repository;

import smart_home.ddd.IRepository;
import smart_home.domain.log.Log;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;
import smart_home.value_object.LogID;
import smart_home.value_object.SensorTypeID;

import java.util.List;


public interface ILogRepository extends IRepository<LogID, Log> {


    /**
     * Method to find logs by device and time period
     *
     * @param deviceID DeviceID object
     * @param period DatePeriod object
     * @return List of Log
     */
    List<Log> findByDeviceIDAndDatePeriodBetween(DeviceID deviceID, DatePeriod period);

  /**
   * Method to find logs by device and sensor type and time period
   *
   * @param deviceID DeviceID object
   * @param sensorTypeID SensorTypeID object
   * @param period DatePeriod object
   * @return List of Log
   */
  List<Log> findByDeviceIDAndSensorTypeAndDatePeriodBetween(DeviceID deviceID, SensorTypeID sensorTypeID, DatePeriod period);

}
