package smart_home.persistence.assembler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import smart_home.domain.sensor.ISensor;
import smart_home.domain.sensor.ISensorFactory;
import smart_home.persistence.jpa.data_model.SensorDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;
import smart_home.value_object.GPS;
import smart_home.value_object.ModelPath;
import smart_home.value_object.SensorID;
import smart_home.value_object.SensorName;
import smart_home.value_object.SensorTypeID;

public class SensorDataModelAssembler {
  private ISensorFactory _sensorFactory;
  private List<Object> _parameters = new ArrayList<>();

  public SensorDataModelAssembler(ISensorFactory sensorFactory) {
    Validator.validateNotNull(sensorFactory, "Sensor factory");
    this._sensorFactory = sensorFactory;
  }

  public ISensor toDomain (SensorDataModel sensorDataModel)
       {
         Validator.validateNotNull(sensorDataModel, "Sensor data model");
    _parameters.clear();
    getDeviceID(sensorDataModel);
    getModelPath(sensorDataModel);
    getSensorTypeID(sensorDataModel);
    getSensorName(sensorDataModel);
    getGPS(sensorDataModel);
    getDatePeriod(sensorDataModel);
    getSensorID(sensorDataModel);
    return _sensorFactory.create(_parameters.toArray());
  }

  public boolean getDeviceID(SensorDataModel sensorDataModel) {
    DeviceID deviceID = new DeviceID(sensorDataModel.getDeviceID());
    _parameters.add(deviceID);
    return true;
  }
  public boolean getModelPath(SensorDataModel sensorDataModel) {
    ModelPath modelPath = new ModelPath(sensorDataModel.getModelPath());
    _parameters.add(modelPath);
    return true;
  }
  public boolean getSensorTypeID(SensorDataModel sensorDataModel) {
    SensorTypeID sensorTypeID = new SensorTypeID(sensorDataModel.getSensorTypeID());
    _parameters.add( sensorTypeID);
    return true;
  }
  public boolean getSensorName(SensorDataModel sensorDataModel) {
    SensorName sensorName = new SensorName(sensorDataModel.getSensorName());
    _parameters.add(sensorName);
    return true;
  }
  public boolean getGPS(SensorDataModel sensorDataModel) {
    if (sensorDataModel.getLatitude() != null && sensorDataModel.getLongitude() != null) {
      double latitude = Double.parseDouble(sensorDataModel.getLatitude());
      double longitude = Double.parseDouble(sensorDataModel.getLongitude());
      GPS gps = new GPS(latitude, longitude);
      _parameters.add(gps);
      return true;
    }
    return false;
  }
  public boolean getDatePeriod(SensorDataModel sensorDataModel) {
    if (sensorDataModel.getStartDate() != null && sensorDataModel.getEndDate() != null) {
      LocalDateTime startDate = LocalDateTime.parse(sensorDataModel.getStartDate());
      LocalDateTime endDate = LocalDateTime.parse(sensorDataModel.getEndDate());
      DatePeriod datePeriod = new DatePeriod(startDate, endDate);
      _parameters.add(datePeriod);
  return true;
    }
    return false;
  }
  public boolean getSensorID(SensorDataModel sensorDataModel) {
    SensorID sensorID = new SensorID(sensorDataModel.getSensorID());
    _parameters.add(sensorID);
    return true;
  }
}
