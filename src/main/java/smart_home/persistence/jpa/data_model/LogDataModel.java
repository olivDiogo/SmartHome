package smart_home.persistence.jpa.data_model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import smart_home.domain.log.Log;
import java.time.LocalDateTime;

@Entity
public class LogDataModel {
  @Id private String _logID;
  @Column private String _deviceID;
  @Column private String _sensorID;
  @Column private LocalDateTime _timestamp;
  @Column private String _readingValue;
  @Column private String _description;
  @Column private String _unit;

  /** Empty class constructor */
  public LogDataModel() {}

  /** Class constructor */
  public LogDataModel(Log log) {
    this._logID = log.getID().getID();
    this._deviceID = log.getDeviceID().getID();
    this._sensorID = log.getSensorID().getID();
    this._timestamp = log.getTimeStamp();
    this._readingValue = log.getReadingValue().getReadingValue();
    this._description = log.getDescription().getID();
    this._unit = log.getUnit().getID();
  }

  /** Method to return the log ID. */
  public String getLogID() {
    return this._logID;
  }

  /** Method to return the device ID. */
  public String getDeviceID() {
    return this._deviceID;
  }

  /** Method to return the sensor ID. */
  public String getSensorID() {
    return this._sensorID;
  }

  /** Method to return the timestamp. */
  public LocalDateTime getTimestamp() {
    return this._timestamp;
  }

  /** Method to return the reading value. */
  public String getReadingValue() {
    return this._readingValue;
  }

  /** Method to return the description. */
  public String getDescription() {
    return this._description;
  }

  /** Method to return the unit. */
  public String getUnit() {
    return this._unit;
  }
}
