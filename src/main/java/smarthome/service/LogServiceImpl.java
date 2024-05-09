package smarthome.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.domain.log.Log;
import smarthome.domain.repository.ILogRepository;
import smarthome.domain.service.ILogService;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TimeDelta;
import smarthome.utils.Validator;

@Service
public class LogServiceImpl implements ILogService {

  private final ILogRepository logRepository;


  /**
   * Constructor of LogServiceImpl
   *
   * @param logRepository ILogRepository object
   */

  @Autowired
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
      SensorTypeID sensorTypeID, DatePeriod period) throws EmptyReturnException {
    List<Log> deviceReadings = logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(
        deviceID, sensorTypeID, period);

    if (deviceReadings.isEmpty()) {
      throw new EmptyReturnException("No readings found for the given time period");
    }

    return deviceReadings;
  }

  /**
   * Method to get the difference between the reading values of two lists, when the readings are
   * within an interval of 5 minutes.
   *
   * @param readings1 is one list of readings.
   * @param readings2 is another list of readings.
   * @return the list of the differences between the values, as Integers.
   */
  @Override
  public int getMaxDifferenceBetweenReadings(List<Log> readings1, List<Log> readings2, TimeDelta timeDelta)
      throws EmptyReturnException {
    List<Integer> valueDifferences = new ArrayList<>();
    int timeDeltaMinutes = timeDelta.getMinutes();

    Map<Integer, Integer> positionMap = getPositionsOfReadingsWithinTimeDelta(readings1, readings2, timeDeltaMinutes);
    for (Map.Entry<Integer, Integer> entry : positionMap.entrySet()) {
      int difference = getDifferenceBetweenReadings(readings1.get(entry.getKey()), readings2.get(entry.getValue()));
      valueDifferences.add(difference);
    }
    if (valueDifferences.isEmpty()) {
      throw new EmptyReturnException("No readings found within the given time interval");
    }else return Collections.max(valueDifferences);
  }

  public int getSumOfTwoIntegerReadings(Log reading1, Log reading2) {
    try {
      return Math.abs(Integer.parseInt(reading1.getReadingValue().getValue()) + Integer.parseInt(
          reading2.getReadingValue().getValue()));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Reading values are not integers");
    }
  }

  private Map<Integer, Integer> getPositionsOfReadingsWithinTimeDelta (List<Log> readings1, List<Log> readings2, int timeDelta) {
    Map<Integer, Integer> positionMap = new HashMap<>();
    for (int i = 0; i < readings1.size(); i++) {
      for (int j = 0; j < readings2.size(); j++) {
        boolean isWithinTimeDelta = shouldReturnTrueWhenReadingIsWithinTimeDelta(readings1.get(i), readings2.get(j), timeDelta);
        if (isWithinTimeDelta) {
          positionMap.put(i, j);
        }
      }
    }
    return positionMap;
  }

  public int getDifferenceBetweenReadings(Log reading1, Log reading2) {
    try {
      return Math.abs(Integer.parseInt(reading1.getReadingValue().getValue()) - Integer.parseInt(
          reading2.getReadingValue().getValue()));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Reading values are not integers");
    }
  }

  private boolean shouldReturnTrueWhenReadingIsWithinTimeDelta(Log reading1, Log reading2, int timeDelta) {
    int diffInMinutes = (int) ChronoUnit.MINUTES.between(reading1.getTimeStamp(), reading2.getTimeStamp());
    return diffInMinutes < timeDelta;
  }
}

