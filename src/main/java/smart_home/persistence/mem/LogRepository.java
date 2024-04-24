package smart_home.persistence.mem;

import smart_home.domain.repository.ILogRepository;
import smart_home.utils.Validator;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;

import smart_home.value_object.LogID;

import java.util.*;

import smart_home.domain.log.Log;
import smart_home.value_object.SensorTypeID;


public class LogRepository implements ILogRepository {

    private final Map<LogID, Log> DATA = new LinkedHashMap<>();


    /**
     * Method to save log
     *
     * @param log Log object
     * @return Log object
     */
    @Override
    public Log save(Log log) {
        Validator.validateNotNull(log, "Log");

        if (containsOfIdentity(log.getID())) {
            throw new IllegalArgumentException("Log already exists.");
        } else {
            DATA.put(log.getID(), log);
        }
        return log;
    }


    /**
     * Method to find all logs
     *
     * @return List of Log
     */
    @Override
    public List<Log> findAll() {
        return List.copyOf(DATA.values().stream().toList());
    }


    /**
     * Method to find log by ID
     *
     * @param logID LogID object
     * @return Optional of Log
     */
    @Override
    public Optional<Log> ofIdentity(LogID logID) {
        return Optional.ofNullable(DATA.get(logID));
    }


    /**
     * Method to check if log exists
     *
     * @param logID LogID object
     * @return boolean
     */
    @Override
    public boolean containsOfIdentity(LogID logID) {
        return DATA.containsKey(logID);
    }


    /**
     * Method to find logs by device ID and time period
     *
     * @param deviceID DeviceID object
     * @param period   DatePeriod object
     * @return List of Log
     */
    @Override
    public List<Log> findByDeviceIDAndDatePeriodBetween(DeviceID deviceID, DatePeriod period) {
      return DATA.values().stream()
          .filter(log -> log.getDeviceID().getID().equals(deviceID.getID()))
          .filter(log -> log.getTimeStamp().isAfter(period.getStartDate()))
          .filter(log -> log.getTimeStamp().isBefore(period.getEndDate()))
          .toList();
    }

  /**
   * Method to find logs by device ID, sensor type and time period
   *
   * @param deviceID DeviceID object
   * @param sensorTypeID SensorTypeID object
   * @param period DatePeriod object
   * @return List of Log
   */
  @Override
    public List<Log> findByDeviceIDAndSensorTypeAndDatePeriodBetween(DeviceID deviceID, SensorTypeID sensorTypeID, DatePeriod period) {
      return DATA.values().stream()
          .filter(log -> log.getDeviceID().getID().equals(deviceID.getID()))
          .filter(log -> log.getDescription().getID().equals(sensorTypeID.getID()))
          .filter(log -> log.getTimeStamp().isAfter(period.getStartDate().minusSeconds(1)))
          .filter(log -> log.getTimeStamp().isBefore(period.getEndDate().plusSeconds(1)))
          .toList();
    }
}
