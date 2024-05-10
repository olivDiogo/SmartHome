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

  /**
   * Method to get the peak power consumption of a device in a given time period.
   *
   * @param readings
   * @param readings2
   * @param timeDelta
   * @return
   */

  public int getPeakPowerConsumption(List<Log> readings, List<Log> readings2, TimeDelta timeDelta) {

    int maxListOne = getMaximumValueFromListOfIntegers(readings);
    int maxListTwo = getMaximumValueFromListOfIntegers(readings2);
    int maxWithinTimeDelta = getMaxValueFromTwoListsWithinTimeDelta(readings, readings2, timeDelta);

    return Math.max(maxListOne, Math.max(maxListTwo, maxWithinTimeDelta));

  }

  /**
   * Method to get the maximum value from two lists of readings, when the readings are within a time
   * delta.
   *
   * @param readings1
   * @param readings2
   * @param timeDelta
   * @return
   */
  protected int getMaxValueFromTwoListsWithinTimeDelta(List<Log> readings1, List<Log> readings2,
      TimeDelta timeDelta) {
    List<Integer> sumOfReadings = new ArrayList<>();
    int timeDeltaMinutes = timeDelta.getMinutes();

    Map<Integer, Integer> positionMap = getPositionsOfReadingsWithinTimeDelta(readings1, readings2,
        timeDeltaMinutes);
    for (Map.Entry<Integer, Integer> entry : positionMap.entrySet()) {
      int sum = getSumOfTwoIntegerReadings(readings1.get(entry.getKey()),
          readings2.get(entry.getValue()));
      sumOfReadings.add(sum);
    }
    if (sumOfReadings.isEmpty()) {
      return 0;
    } else {
      return Collections.max(sumOfReadings);
    }
  }

  /**
   * Method to get the sum of two integer readings.
   *
   * @param reading1
   * @param reading2
   * @return
   */

  protected int getSumOfTwoIntegerReadings(Log reading1, Log reading2) {
    try {
      return Math.abs(Integer.parseInt(reading1.getReadingValue().getValue()) + Integer.parseInt(
          reading2.getReadingValue().getValue()));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Reading values are not integers");
    }
  }

  /**
   * Method to get the difference between two integer readings.
   *
   * @param reading1
   * @param reading2
   * @return
   */

  protected int getDifferenceBetweenReadings(Log reading1, Log reading2) {
    try {
      return Math.abs(Integer.parseInt(reading1.getReadingValue().getValue()) - Integer.parseInt(
          reading2.getReadingValue().getValue()));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Reading values are not integers");
    }
  }

  /**
   * Method to get the maximum value from a list of integer readings.
   *
   * @param readings
   * @return
   */
  protected int getMaximumValueFromListOfIntegers(List<Log> readings) {
    List<Integer> values = new ArrayList<>();
    for (Log reading : readings) {
      values.add(Integer.parseInt(reading.getReadingValue().getValue()));
    }
    return Collections.max(values);
  }

  /**
   * Method to get the positions of readings within a time delta.
   *
   * @param readings1
   * @param readings2
   * @param timeDelta
   * @return a map of the positions of readings within the time delta.
   */

  protected Map<Integer, Integer> getPositionsOfReadingsWithinTimeDelta(List<Log> readings1,
      List<Log> readings2, int timeDelta) {
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

  /**
   * Method to check if two readings are within a time delta.
   *
   * @param reading1
   * @param reading2
   * @param timeDelta
   * @return
   */
  protected boolean shouldReturnTrueWhenReadingIsWithinTimeDelta(Log reading1, Log reading2, int timeDelta) {
    int diffInMinutes = (int) ChronoUnit.MINUTES.between(reading1.getTimeStamp(),
        reading2.getTimeStamp());

    return Math.abs(diffInMinutes) < timeDelta;
  }
}

