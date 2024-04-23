package smart_home.domain.log;

import smart_home.value_object.*;

import java.time.LocalDateTime;

public class LogFactoryImpl implements ILogFactory{

    /**
     * Creates a new Log instance
     */

    @Override
    public Log createLog(DeviceID DeviceId, SensorID sensorId, LocalDateTime timeStamp, ReadingValue readingValue, SensorTypeID description, UnitID unit) {
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

