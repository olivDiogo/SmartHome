package smarthome.persistence.assembler;

import smarthome.domain.log.ILogFactory;
import smarthome.domain.log.Log;
import smarthome.persistence.jpa.data_model.LogDataModel;
import smarthome.utils.Validator;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.LogID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogDataModelAssembler implements IDataModelAssembler<LogDataModel, Log> {
  private ILogFactory logFactory;

  /**
   * Class constructor
   *
   * @param logFactory is the factory used to create Log instances.
   */
  public LogDataModelAssembler(ILogFactory logFactory) {
    Validator.validateNotNull(logFactory, "Log Factory");
    this.logFactory = logFactory;
  }

  /**
   * Converts a LogDataModel instance to a Log instance.
   *
   * @param logDataModel is the domain entity to be converted.
   * @return a Log instance.
   */
  @Override
  public Log toDomain(LogDataModel logDataModel) {
    Validator.validateNotNull(logDataModel, "Log Data Model");

    LogID logID = new LogID(logDataModel.getLogID());
    DeviceID deviceID = new DeviceID(logDataModel.getDeviceID());
    SensorID sensorID = new SensorID(logDataModel.getSensorID());
    LocalDateTime timeStamp = logDataModel.getTimestamp();
    ReadingValue readingValue = new ReadingValue(logDataModel.getReadingValue());
    SensorTypeID description = new SensorTypeID(logDataModel.getDescription());
    UnitID unit = new UnitID(logDataModel.getUnit());

    Log log =
        logFactory.createLog(
            logID, deviceID, sensorID, timeStamp, readingValue, description, unit);

    return log;
  }

  @Override
  public List<Log> toDomain(List<LogDataModel> logDataModels) {
    List<Log> logs = new ArrayList<>();

    for (LogDataModel logDataModel : logDataModels) {
      Log log = toDomain(logDataModel);
      logs.add(log);
    }

    return logs;
  }
}
