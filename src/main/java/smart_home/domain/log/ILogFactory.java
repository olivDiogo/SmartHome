package smart_home.domain.log;

import smart_home.value_object.*;

import java.time.LocalDateTime;

public interface ILogFactory {

  /** Creates a new Log instance with the provided log message. */
  Log createLog(
          DeviceID DeviceId, SensorID sensorId, LocalDateTime timeStamp, ReadingValue readingValue, SensorTypeID description, UnitID unit);

  /** Creates a new Log instance with the provided log message. */
  Log createLog(
      LogID logId,
      DeviceID DeviceId,
      SensorID sensorId,
      LocalDateTime timeStamp,
      ReadingValue readingValue, SensorTypeID description, UnitID unit);
}
