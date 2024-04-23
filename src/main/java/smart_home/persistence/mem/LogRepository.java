package smart_home.persistence.mem;

import smart_home.domain.repository.ILogRepository;
import smart_home.utils.Validator;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;

import smart_home.value_object.LogID;

import java.util.*;

import smart_home.domain.log.Log;


public class LogRepository implements ILogRepository {

    private final Map<LogID, Log> logData = new LinkedHashMap<>();


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
            logData.put(log.getID(), log);
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
        return List.copyOf(logData.values().stream().toList());
    }


    /**
     * Method to find log by ID
     *
     * @param logID LogID object
     * @return Optional of Log
     */
    @Override
    public Optional<Log> ofIdentity(LogID logID) {
        return Optional.ofNullable(logData.get(logID));
    }


    /**
     * Method to check if log exists
     *
     * @param logID LogID object
     * @return boolean
     */
    @Override
    public boolean containsOfIdentity(LogID logID) {
        return logData.containsKey(logID);
    }


    /**
     * Method to find logs by device ID and time period
     *
     * @param deviceID DeviceID object
     * @param period   DatePeriod object
     * @return List of Log
     */
    @Override
    public List<Log> findByDeviceIDAndTimePeriod(DeviceID deviceID, DatePeriod period) {

        List<Log> logList = new ArrayList<>(logData.values());

        List<Log> logListByDeviceID = new ArrayList<>();

        for (Log log : logList) {
            if (log.getDeviceID().getID().equals(deviceID.getID()) && log.getTimeStamp().isAfter(period.getStartDate().minusMinutes(1))
                    && log.getTimeStamp().isBefore(period.getEndDate().plusMinutes(1))) {
                logListByDeviceID.add(log);
            }
        }

        return logListByDeviceID;
    }


}
