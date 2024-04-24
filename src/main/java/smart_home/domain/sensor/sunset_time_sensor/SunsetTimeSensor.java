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
    private SunsetTimeSensorValue sunsetTimeSensorValue;
    private SensorTypeID sensorTypeID;
    private SensorID sensorID;
    private SensorName sensorName;
    private DeviceID deviceID;
    private ModelPath modelPath;
    private GPS gps;

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
        this.deviceID = deviceID;
        validateSensorTypeID(sensorTypeID);
        this.sensorTypeID = sensorTypeID;
        Validator.validateNotNull(modelPath, "ModelPath");
        this.modelPath = modelPath;
        Validator.validateNotNull(sensorName, "SensorName");
        this.sensorName = sensorName;
        Validator.validateNotNull(gps, "GPS");
        this.gps = gps;

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
        this.deviceID = deviceID;
        validateSensorTypeID(sensorTypeID);
        this.sensorTypeID = sensorTypeID;
        Validator.validateNotNull(modelPath, "ModelPath");
        this.modelPath = modelPath;
        Validator.validateNotNull(sensorName, "SensorName");
        this.sensorName = sensorName;
        Validator.validateNotNull(gps, "GPS");
        this.gps = gps;
        Validator.validateNotNull(sensorID, "SensorID");
        this.sensorID = sensorID;
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
        sensorID = new SensorID(UUID.randomUUID().toString());
    }

    /**
     * Get SensorID
     */
    @Override
    public SensorID getID() {
        return sensorID;
    }

    /**
     * Get SensorName
     */
    @Override
    public SensorName getName() {
        return sensorName;
    }

    /**
     * Get ModelPath
     */
    @Override
    public ModelPath getModelPath() {
        return modelPath;
    }

    /**
     * Get DeviceID
     */
    @Override
    public DeviceID getDeviceID() {
        return deviceID;
    }

    /**
     * Get SensorTypeID
     */
    @Override
    public SensorTypeID getSensorTypeID() {
        return sensorTypeID;
    }

    /**
     * Returns the sunset time value for the current date
     * @return The sunset time value
     */
    @Override
    public SunsetTimeSensorValue getValue() {
        LocalTime sunrise = getSunsetTime(LocalDate.now());
        this.sunsetTimeSensorValue = new SunsetTimeSensorValue(sunrise);
        return this.sunsetTimeSensorValue;
    }

    /**
     * Returns the sunset time value for the given date
     * @param date The date for which the sunset time is to be calculated
     * @return The sunset time value
     */
    public SunsetTimeSensorValue getValue(LocalDate date) {
        LocalTime sunrise = getSunsetTime(date);
        this.sunsetTimeSensorValue = new SunsetTimeSensorValue(sunrise);
        return this.sunsetTimeSensorValue;
    }
    /**
     * Get GPS
     */
    public GPS getGPS() {
        return gps;
    }

    /**
     * Returns the sunset time for the given date
     * @param date The date for which the sunset time is to be calculated
     * @return The sunset time
     */
    private LocalTime getSunsetTime(LocalDate date) {
        SunTimes time = SunTimes.compute().on(date).at(gps.getLatitude(), gps.getLongitude()).execute();
        LocalTime sunset = Objects.requireNonNull(time.getSet()).toLocalTime().truncatedTo(ChronoUnit.SECONDS);
        return sunset;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param object is the object to be compared.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof SunsetTimeSensor sunsetTimeSensor) {
            return this.sensorID.equals(sunsetTimeSensor.sensorID);
        }
        return false;
    }

    /**
     * Returns a hash code value for the object.
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return sensorID.hashCode();
    }

    /**
     * Returns a string representation of the SunsetTimeSensor
     * @return The string representation of the SunsetTimeSensor
     */
    @Override
    public String toString() {
        return "SunsetTimeSensor:" +
                " sunriseTimeValue=" + sunsetTimeSensorValue +
                ", sensorTypeID=" + sensorTypeID +
                ", sensorID=" + sensorID +
                ", sensorName=" + sensorName +
                ", deviceID=" + deviceID +
                ", modelPath=" + modelPath +
                ", gps=" + gps;
    }

    /**
     * Accept method for the visitor pattern
     * @param visitor The visitor
     * @return The string representation of the SunsetTimeSensor
     */
    public String accept(ISensorVisitor visitor) {
        visitor.visitSunsetTimeSensor(this);
        return this.toString();
    }
}