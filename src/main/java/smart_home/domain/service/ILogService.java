package smart_home.domain.service;

import smart_home.ddd.IService;
import smart_home.domain.log.Log;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;
import smart_home.value_object.SensorTypeID;

import java.util.List;

public interface ILogService extends IService {

    /**
     * Method to get device readings by time period
     *
     * @param deviceID DeviceID object
     * @param period DatePeriod object
     * @return List of Log
     */
    List<Log> getDeviceReadingsByTimePeriod(DeviceID deviceID, DatePeriod period);


    /**
     * Method to get device readings by sensor type and time period
     *
     * @param deviceID DeviceID object
     * @param sensorTypeID SensorTypeID object
     * @param period DatePeriod object
     * @return List of Log
     */
    List<Log> getDeviceReadingsBySensorTypeAndTimePeriod(DeviceID deviceID, SensorTypeID sensorTypeID, DatePeriod period);


}
