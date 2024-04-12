package smartHome.domain.sensor.sunriseTimeSensor;

import org.shredzone.commons.suncalc.SunTimes;
import smartHome.ddd.IValueObject;
import smartHome.domain.sensor.ISensor;
import smartHome.valueObject.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class SunriseTimeSensor implements ISensor {

    private SunriseSunsetTimeValue _sunriseTimeValue;
    private SensorTypeID _sensorTypeID;
    private SensorID _sensorID;
    private SensorName _sensorName;
    private DeviceID _deviceID;
    private ModelPath _modelPath;
    private GPS gps;

    /**
     * Creates a new SunriseTimeSensor with a given catalogue.
     *
     * @throws InstantiationException if the SensorType with description 'SunriseTime' does not exist.
     */
    public SunriseTimeSensor(
            DeviceID deviceID,
            ModelPath modelPath,
            SensorTypeID sensorTypeID,
            SensorName sensorName,
            GPS gps)
            throws InstantiationException {
        validateDeviceID(deviceID);
        validateSensorTypeID(sensorTypeID);
        validateModelPath(modelPath);
        validateSensorName(sensorName);
        generateSensorID();
        validateGPS(gps);
    }

    private void validateDeviceID(DeviceID deviceID) {
        if (deviceID == null) {
            throw new IllegalArgumentException("DeviceID cannot be null.");
        } else {
            _deviceID = deviceID;
        }
    }

    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        if (sensorTypeID == null) {
            throw new IllegalArgumentException("SensorTypeID cannot be null.");
        } else if (!sensorTypeID.getID().equals("SunriseTime")) {
            throw new IllegalArgumentException("SensorTypeID must be 'SunriseTime'.");
        } else {
            _sensorTypeID = sensorTypeID;
        }
    }

    private void validateModelPath(ModelPath modelPath) {
        if (modelPath == null) {
            throw new IllegalArgumentException("ModelPath cannot be null.");
        } else {
            _modelPath = modelPath;
        }
    }

    private void validateSensorName(SensorName sensorName) {
        if (sensorName == null) {
            throw new IllegalArgumentException("SensorName cannot be null");
        } else {
            _sensorName = sensorName;
        }
    }

    private void generateSensorID() {
        _sensorID = new SensorID(java.util.UUID.randomUUID().toString());
    }

    /**
     * Configures the GPS location of the SunriseTimeSensor.
     *
     * @param gps the GPS location to be used.
     * @throws IllegalArgumentException if the GPS location is null.
     */
    private void validateGPS(GPS gps) {
        if (gps == null) {
            throw new IllegalArgumentException("GPS cannot be null.");
        }
        this.gps = gps;
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
    public IValueObject getValue() {
        LocalTime sunrise = getSunriseTime(LocalDate.now());
        this._sunriseTimeValue = new SunriseSunsetTimeValue(sunrise);
        return this._sunriseTimeValue;
    }

    /**
     * Gets the value of the SunriseTimeSensor for a given date.
     *
     * @param date the date to be used.
     * @return the value of the SunriseTimeSensor for a given date.
     */
    public IValueObject getValue(LocalDate date) {
        LocalTime sunrise = getSunriseTime(date);
        this._sunriseTimeValue = new SunriseSunsetTimeValue(sunrise);
        return this._sunriseTimeValue;
    }
}
