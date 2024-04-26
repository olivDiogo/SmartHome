package smarthome.domain.service;

import java.util.List;
import smarthome.ddd.IService;
import smarthome.domain.log.Log;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.SensorTypeID;

public interface ILogService extends IService {

  /**
   * Method to get device readings by time period
   *
   * @param deviceID DeviceID object
   * @param period   DatePeriod object
   * @return List of Log
   */
  List<Log> getDeviceReadingsByTimePeriod(DeviceID deviceID, DatePeriod period);


  /**
   * Method to get device readings by sensor type and time period
   *
   * @param deviceID     DeviceID object
   * @param sensorTypeID SensorTypeID object
   * @param period       DatePeriod object
   * @return List of Log
   */
  List<Log> getDeviceReadingsBySensorTypeAndTimePeriod(DeviceID deviceID, SensorTypeID sensorTypeID,
      DatePeriod period);


  /**
   * Method to get the difference between the reading values of two lists.
   *
   * @param readings1 is one list of readings.
   * @param readings2 is another list of readings.
   * @return the list of the differences between the values.
   */
  List<Integer> getDifferenceBetweenReadings(List<Log> readings1, List<Log> readings2);

}
