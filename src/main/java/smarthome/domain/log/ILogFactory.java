package smarthome.domain.log;

import java.time.LocalDateTime;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.LogID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;

public interface ILogFactory {

  /**
   * Creates a new Log instance with the provided log message.
   */
  Log createLog(
      DeviceID DeviceId, SensorID sensorId, LocalDateTime timeStamp, ReadingValue readingValue,
      SensorTypeID description, UnitID unit);

  /**
   * Creates a new Log instance with the provided log message.
   */
  Log createLog(
      LogID logId,
      DeviceID DeviceId,
      SensorID sensorId,
      LocalDateTime timeStamp,
      ReadingValue readingValue, SensorTypeID description, UnitID unit);
}
