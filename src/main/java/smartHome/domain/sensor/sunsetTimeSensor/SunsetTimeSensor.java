package smartHome.domain.sensor.sunsetTimeSensor;

import org.shredzone.commons.suncalc.SunTimes;
import smartHome.domain.sensor.ISensor;
import smartHome.valueObject.*;

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
     * @param deviceID
     * @param modelPath
     * @param sensorTypeID
     * @param sensorName
     * @param gps
     */
    public SunsetTimeSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, GPS gps) {
        validateDeviceID(deviceID);
        _deviceID = deviceID;

        validateSensorTypeID(sensorTypeID);
        _sensorTypeID = sensorTypeID;

        validateModelPath(modelPath);
        _modelPath = modelPath;

        validateSensorName(sensorName);
        _sensorName = sensorName;

        validateGpsLocation(gps);
        _gps = gps;

        generateSensorID();
    }

    /**
     * Validate DeviceID
     * @param deviceID
     */
    private void validateDeviceID(DeviceID deviceID) {
        if (deviceID == null) {
            throw new IllegalArgumentException("DeviceID cannot be null.");
        }
    }

    /**
     * Validate SensorTypeID
     * @param sensorTypeID
     */
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        if (sensorTypeID == null) {
            throw new IllegalArgumentException("SensorTypeID cannot be null.");
        } else if (!sensorTypeID.getID().equals("SunsetTime")) {
            throw new IllegalArgumentException("SensorTypeID must be 'SunsetTime'.");
        }
    }

    /**
     * Validate ModelPath
     * @param modelPath
     */
    private void validateModelPath(ModelPath modelPath) {
        if (modelPath == null) {
            throw new IllegalArgumentException("ModelPath cannot be null.");
        }
    }

    /**
     * Validate SensorName
     * @param sensorName
     */
    private void validateSensorName(SensorName sensorName) {
        if (sensorName == null) {
            throw new IllegalArgumentException("SensorName cannot be null");
        }
    }

    /**
     * Validate GPS Location
     * @param gps
     */
    private void validateGpsLocation(GPS gps) {
        if (gps == null) {
            throw new IllegalArgumentException("GPS cannot be null.");
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
}