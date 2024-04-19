package smart_home.persistence.jpa.data_model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import smart_home.domain.sensor.ISensor;
import smart_home.utils.Validator;

@Entity
@Table(name = "sensor")
public class SensorDataModel {
    @Id
    private String sensorID;
    private String deviceID;
    private String modelPath;
    private String sensorTypeID;
    private String sensorName;
    @Nullable
    private String latitude;
    @Nullable
    private String longitude;
    @Nullable
    private String startDate;
    @Nullable
    private String endDate;

    public SensorDataModel() {

    }

    public SensorDataModel(ISensor sensor) {
        Validator.validateNotNull(sensor, "Sensor");
        setGenericSensor(sensor);
    }
    //Setters

    public void setGenericSensor(ISensor sensor) {
        this.sensorID = sensor.getID().getID();
        this.deviceID = sensor.getDeviceID().getID();
        this.modelPath = sensor.getModelPath().getID();
        this.sensorTypeID = sensor.getSensorTypeID().getID();
        this.sensorName = sensor.getName().getSensorName();
    }

    public void setSensorID(String sensorID) {
        this.sensorID = sensorID;
    }
    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }
    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }
    public void setSensorTypeID(String sensorTypeID) {
        this.sensorTypeID = sensorTypeID;
    }
    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    //Getters
    public String getSensorID() {
        return sensorID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public String getModelPath() {
        return modelPath;
    }

    public String getSensorTypeID() {
        return sensorTypeID;
    }

    public String getSensorName() {
        return sensorName;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "SensorDataModel{" +
                "sensorID='" + sensorID + '\'' +
                ", deviceID='" + deviceID + '\'' +
                ", modelPath='" + modelPath + '\'' +
                ", sensorTypeID='" + sensorTypeID + '\'' +
                ", sensorName='" + sensorName + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
