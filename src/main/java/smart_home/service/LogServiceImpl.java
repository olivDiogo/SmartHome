package smart_home.service;

import smart_home.domain.log.Log;
import smart_home.domain.repository.ILogRepository;
import smart_home.domain.service.ILogService;
import smart_home.utils.Validator;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;
import smart_home.value_object.SensorTypeID;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LogServiceImpl implements ILogService {

  private ILogRepository logRepository;


  /**
   * Constructor of LogServiceImpl
   *
   * @param logRepository ILogRepository object
   */

  public LogServiceImpl(ILogRepository logRepository) {
    Validator.validateNotNull(logRepository, "Log Repository");
    this.logRepository = logRepository;
  }


  /**
   * Method to get device readings by time period
   *
   * @param deviceID DeviceID object
   * @param period   DatePeriod object
   * @return List of Log
   */

  @Override
  public List<Log> getDeviceReadingsByTimePeriod(DeviceID deviceID, DatePeriod period) {
    return logRepository.findByDeviceIDAndDatePeriodBetween(deviceID, period);
  }

  /**
   * Method to get device readings by sensor type and time period
   *
   * @param deviceID     DeviceID object
   * @param sensorTypeID SensorTypeID object
   * @param period       DatePeriod object
   * @return List of Log
   */
  @Override
  public List<Log> getDeviceReadingsBySensorTypeAndTimePeriod(DeviceID deviceID,
      SensorTypeID sensorTypeID, DatePeriod period) {
    List<Log> deviceReadings = logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(deviceID, sensorTypeID, period);

    if (deviceReadings.isEmpty()) {
      throw new IllegalArgumentException("No readings found for the given time period");
    }

    return deviceReadings;
  }

  /**
   * Method to get the difference between the reading values of two lists, when the readings are within an interval of 5 minutes.
   *
   * @param readings1  is one list of readings.
   * @param readings2 is another list of readings.
   * @return the list of the differences between the values, as Integers.
   */
  @Override
  public List<Integer> getDifferenceBetweenReadings(List<Log> readings1, List<Log> readings2) {
    List<Integer> valueDifferences = new ArrayList<>();

    for (int i = 0; i < readings1.size(); i++) {
      for (int j = 0; j < readings2.size(); j++) {
        int diffInMinutes = (int) ChronoUnit.MINUTES.between(readings1.get(i).getTimeStamp(), readings2.get(j).getTimeStamp());

        if (diffInMinutes < 5) {
          int temperatureDifference = Math.abs(Integer.parseInt(readings1.get(i).getReadingValue().getReadingValue()) - Integer.parseInt(readings2.get(j).getReadingValue().getReadingValue()));
          valueDifferences.add(temperatureDifference);
        }
      }
    }

    return valueDifferences;
  }

}
