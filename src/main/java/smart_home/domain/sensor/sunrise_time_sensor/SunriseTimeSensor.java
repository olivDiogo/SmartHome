package smart_home.domain.sensor.sunrise_time_sensor;

import org.shredzone.commons.suncalc.SunTimes;
import smart_home.domain.sensor.ISensor;
import smart_home.utils.Validator;
import smart_home.value_object.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class SunriseTimeSensor implements ISensor {

    private SunriseTimeSensorValue _sunriseTimeValue;
    private SensorTypeID _sensorTypeID;
    private SensorID _sensorID;
    private SensorName _sensorName;
    private DeviceID _deviceID;
    private ModelPath _modelPath;
    private GPS gps;

    /**
     * Creates a new SunriseTimeSensor with a given catalogue.
     *
     */
    public SunriseTimeSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, GPS gps) {
        Validator.validateNotNull(deviceID, "DeviceID");
        Validator.validateNotNull(modelPath, "ModelPath");
        Validator.validateNotNull(sensorName, "SensorName");
        validateSensorTypeID(sensorTypeID);
        Validator.validateNotNull(gps, "GPS");

        generateSensorID();
        this._deviceID = deviceID;
        this._sensorTypeID = sensorTypeID;
        this._modelPath = modelPath;
        this._sensorName = sensorName;
        this.gps = gps;
    }

    /**
     * Creates a new SunriseTimeSensor with a given catalogue.
     *
     */
    public SunriseTimeSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, GPS gps, SensorID sensorID) {
        Validator.validateNotNull(deviceID, "DeviceID");
        Validator.validateNotNull(modelPath, "ModelPath");
        Validator.validateNotNull(sensorName, "SensorName");
        validateSensorTypeID(sensorTypeID);
        Validator.validateNotNull(gps, "GPS");
        Validator.validateNotNull(sensorID, "SensorID");

        this._deviceID = deviceID;
        this._sensorTypeID = sensorTypeID;
        this._modelPath = modelPath;
        this._sensorName = sensorName;
        this.gps = gps;
        this._sensorID = sensorID;
    }


    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        Validator.validateNotNull(sensorTypeID, "SensorTypeID");

        if (!sensorTypeID.getID().equals("SunriseTime")) {
            throw new IllegalArgumentException("SensorTypeID must be 'SunriseTime'.");
        }
    }


    private void generateSensorID() {
        _sensorID = new SensorID(java.util.UUID.randomUUID().toString());
    }



    /**
     * Gets the Sunrise Time of the GPS location for a given date.
     *
     * @param date the date to be used.
     * @return the Sunrise Time of the GPS location for a given date.
     */
    private LocalTime getSunriseTime(LocalDate date) {
        SunTimes time = SunTimes.compute().on(date).at(gps.getLatitude(), gps.getLongitude()).execute();
        LocalTime sunrise = Objects.requireNonNull(time.getRise()).toLocalTime();
        return sunrise;
    }

    @Override
    public SensorID getID() {
        return _sensorID;
    }

    @Override
    public SensorName getName() {
        return _sensorName;
    }

    @Override
    public ModelPath getModelPath() {
        return _modelPath;
    }

    @Override
    public SensorTypeID getSensorTypeID() {
        return _sensorTypeID;
    }

    @Override
    public DeviceID getDeviceID() {
        return _deviceID;
    }

    /**
     * Gets the value of the SunriseTimeSensor for the current day.
     *
     * @return the value of the SunriseTimeSensor.
     */
    @Override
    public SunriseTimeSensorValue getValue() {
        LocalTime sunrise = getSunriseTime(LocalDate.now());
        this._sunriseTimeValue = new SunriseTimeSensorValue(sunrise);
        return this._sunriseTimeValue;
    }
    /**
     * Gets the GPS configuration of the sensor.
     */
    public GPS getGPS() {
        return gps;
    }

    /**
     * Gets the value of the SunriseTimeSensor for a given date.
     *
     * @param date the date to be used.
     * @return the value of the SunriseTimeSensor for a given date.
     */
    public SunriseTimeSensorValue getValue(LocalDate date) {
        LocalTime sunrise = getSunriseTime(date);
        this._sunriseTimeValue = new SunriseTimeSensorValue(sunrise);
        return this._sunriseTimeValue;
    }

    /**
     * Equals method for SunriseTimeSensor.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof SunriseTimeSensor sunriseTimeObject) {
            return _sensorID.equals(sunriseTimeObject._sensorID);
        }
        return false;
    }

    /**
     * HashCode method for SunriseTimeSensor.
     */
    @Override
    public int hashCode() {
        return _sensorID.hashCode();
    }

    /**
     * ToString method for SunriseTimeSensor.
     */
    @Override
    public String toString() {
        return "SunriseTimeSensor:" +
                ", sensorID=" + _sensorID +
                ", sensorName=" + _sensorName +
                ", modelPath=" + _modelPath +
                ", sensorTypeID=" + _sensorTypeID +
                ", deviceID=" + _deviceID +
                ", gps=" + gps;
    }
}
