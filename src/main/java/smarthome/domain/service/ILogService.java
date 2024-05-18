package smarthome.domain.service;

import java.util.List;
import smarthome.ddd.IService;
import smarthome.domain.device.Device;
import smarthome.domain.log.Log;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TimeDelta;

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
      DatePeriod period) throws Exception;


  /**
   * Method to get the difference between the reading values of two lists.
   *
   * @param readings1 is one list of readings.
   * @param readings2 is another list of readings.
   * @return the Maximum difference between readings.
   */
  int getMaxDifferenceBetweenReadings(List<Log> readings1, List<Log> readings2, TimeDelta timeDelta)
      throws Exception;

  /**
   * Method to get the peak power consumption of a device in a given time period.
   *
   * @param readings
   * @param readings2
   * @param timeDelta
   * @return
   */

  int getPeakPowerConsumption(List<Log> readings, List<Log> readings2, TimeDelta timeDelta);

  /**
   * Method to get the list of readings of a list of devices in a given time period.
   *
   * @param devices
   * @param datePeriod
   * @param sensorTypeID
   * @return
   */

  List<Log> getReadingsInTimePeriodByListOfDevicesAndSensorType(List<Device> devices,
      DatePeriod datePeriod,
      SensorTypeID sensorTypeID);
}
