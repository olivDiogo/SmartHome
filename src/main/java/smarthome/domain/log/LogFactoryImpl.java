package smarthome.domain.log;

import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.LogID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.*;

import java.time.LocalDateTime;

public class LogFactoryImpl implements ILogFactory{

    /**
     * Creates a new Log instance
     */

    @Override
    public Log createLog(
        DeviceID DeviceId, SensorID sensorId, LocalDateTime timeStamp, ReadingValue readingValue, SensorTypeID description, UnitID unit) {
        return new Log(DeviceId, sensorId, timeStamp, readingValue, description, unit);
    }

    /**
     * Creates a new Log instance with LogId
     */

    @Override
    public Log createLog(LogID logId, DeviceID DeviceId, SensorID sensorId, LocalDateTime timeStamp, ReadingValue readingValue, SensorTypeID description, UnitID unit) {
        return new Log(logId, DeviceId, sensorId, timeStamp, readingValue, description, unit);
    }
}

