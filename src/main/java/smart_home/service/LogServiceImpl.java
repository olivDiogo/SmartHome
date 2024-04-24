package smart_home.service;

import smart_home.domain.log.Log;
import smart_home.domain.repository.ILogRepository;
import smart_home.domain.service.ILogService;
import smart_home.utils.Validator;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;
import smart_home.value_object.SensorTypeID;

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
    return logRepository.findByDeviceIDAndSensorTypeAndDatePeriod(deviceID, sensorTypeID, period);
  }

}
