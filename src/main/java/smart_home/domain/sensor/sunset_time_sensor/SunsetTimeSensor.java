package smart_home.domain.sensor.sunset_time_sensor;

import org.shredzone.commons.suncalc.SunTimes;
import smart_home.domain.sensor.ISensor;
import smart_home.utils.Validator;
import smart_home.value_object.*;
import smart_home.visitor_pattern.ISensorVisitor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

public class SunsetTimeSensor implements ISensor {
    private SunsetTimeSensorValue _sunsetTimeValue;
    private SensorTypeID _sensorTypeID;
    private SensorID _sensorID;
    private SensorName _sensorName;
    private DeviceID _deviceID;
    private ModelPath _modelPath;
    private GPS _gps;

    /**
     * Constructor for SunsetTimeSensor
     * @param deviceID is the device id
     * @param modelPath is the model path
     * @param sensorTypeID is the sensor type id
     * @param sensorName is the sensor name
     * @param gps is the gps
     */
    public SunsetTimeSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, GPS gps) {
        Validator.validateNotNull(deviceID, "DeviceID");
        _deviceID = deviceID;
        validateSensorTypeID(sensorTypeID);
        _sensorTypeID = sensorTypeID;
        Validator.validateNotNull(modelPath, "ModelPath");
        _modelPath = modelPath;
        Validator.validateNotNull(sensorName, "SensorName");
        _sensorName = sensorName;
        Validator.validateNotNull(gps, "GPS");
        _gps = gps;

        generateSensorID();
    }

    /**
     * Constructor for SunsetTimeSensor
     *
     * @param deviceID is the device id
     * @param modelPath is the model path
     * @param sensorTypeID is the sensor type id
     * @param sensorName is the sensor name
     * @param gps is the gps
     * @param sensorID is the sensor id
     */
    public SunsetTimeSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, GPS gps, SensorID sensorID) {
        Validator.validateNotNull(deviceID, "DeviceID");
        _deviceID = deviceID;
        validateSensorTypeID(sensorTypeID);
        _sensorTypeID = sensorTypeID;
        Validator.validateNotNull(modelPath, "ModelPath");
        _modelPath = modelPath;
        Validator.validateNotNull(sensorName, "SensorName");
        _sensorName = sensorName;
        Validator.validateNotNull(gps, "GPS");
        _gps = gps;
        Validator.validateNotNull(sensorID, "SensorID");
        _sensorID = sensorID;
    }

    /**
     * Validate SensorTypeID
     * @param sensorTypeID is the sensor type id
     */
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        Validator.validateNotNull(sensorTypeID, "SensorTypeID");

        if (!sensorTypeID.getID().equals("SunsetTime")) {
            throw new IllegalArgumentException("SensorTypeID must be 'SunsetTime'.");
        }
    }


    /**
     * Generate SensorID
     */
    private void generateSensorID() {
        _sensorID = new SensorID(UUID.randomUUID().toString());
    }

    /**
     * Get SensorID
     * @return
     */
    @Override
    public SensorID getID() {
        return _sensorID;
    }

    /**
     * Get SensorName
     * @return
     */
    @Override
    public SensorName getName() {
        return _sensorName;
    }

    /**
     * Get ModelPath
     * @return
     */
    @Override
    public ModelPath getModelPath() {
        return _modelPath;
    }

    /**
     * Get DeviceID
     * @return
     */
    @Override
    public DeviceID getDeviceID() {
        return _deviceID;
    }

    /**
     * Get SensorTypeID
     * @return
     */
    @Override
    public SensorTypeID getSensorTypeID() {
        return _sensorTypeID;
    }

    /**
     * Returns the sunset time value for the current date
     * @return The sunset time value
     */
    @Override
    public SunsetTimeSensorValue getValue() {
        LocalTime sunrise = getSunsetTime(LocalDate.now());
        this._sunsetTimeValue = new SunsetTimeSensorValue(sunrise);
        return this._sunsetTimeValue;
    }

    /**
     * Returns the sunset time value for the given date
     * @param date The date for which the sunset time is to be calculated
     * @return The sunset time value
     */
    public SunsetTimeSensorValue getValue(LocalDate date) {
        LocalTime sunrise = getSunsetTime(date);
        this._sunsetTimeValue = new SunsetTimeSensorValue(sunrise);
        return this._sunsetTimeValue;
    }
    /**
     * Get GPS
     */
    public GPS getGPS() {
        return _gps;
    }

    /**
     * Returns the sunset time for the given date
     * @param date
     * @return
     */
    private LocalTime getSunsetTime(LocalDate date) {
        SunTimes time = SunTimes.compute().on(date).at(_gps.getLatitude(), _gps.getLongitude()).execute();
        LocalTime sunset = Objects.requireNonNull(time.getSet()).toLocalTime().truncatedTo(ChronoUnit.SECONDS);
        return sunset;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param object is the object to be compared.
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof SunsetTimeSensor sunsetTimeSensor) {
            return this._sensorID.equals(sunsetTimeSensor._sensorID);
        }
        return false;
    }

    /**
     * Returns a hash code value for the object.
     * @return
     */
    @Override
    public int hashCode() {
        return _sensorID.hashCode();
    }

    /**
     * Returns a string representation of the SunsetTimeSensor
     * @return
     */
    @Override
    public String toString() {
        return "SunsetTimeSensor:" +
                " sunriseTimeValue=" + _sunsetTimeValue +
                ", sensorTypeID=" + _sensorTypeID +
                ", sensorID=" + _sensorID +
                ", sensorName=" + _sensorName +
                ", deviceID=" + _deviceID +
                ", modelPath=" + _modelPath +
                ", gps=" + _gps;
    }
    public String accept(ISensorVisitor visitor) {
        visitor.visitSunsetTimeSensor(this);
        return this.toString();
    }
}