package smart_home.domain.sensor.wind_sensor;

import smart_home.domain.sensor.ISensor;
import smart_home.utils.ValueSimulator;
import smart_home.utils.Validator;
import smart_home.value_object.*;
import smart_home.visitor_pattern.ISensorVisitor;

import java.util.Objects;
import java.util.UUID;

public class WindSensor implements ISensor {
    private ModelPath _modelPath;
    private SensorName _sensorName;
    private SensorID _sensorID;
    private SensorTypeID _sensorTypeID;
    private WindSensorValue _windSensorValue;
    private DeviceID _deviceID;

    public WindSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName) {
        Validator.validateNotNull(deviceID, "DeviceID");
        Validator.validateNotNull(modelPath, "ModelPath");
        Validator.validateNotNull(sensorName, "SensorName");
        validateSensorTypeID(sensorTypeID);

        generateWindID();

        this._modelPath = modelPath;
        this._sensorName = sensorName;
        this._sensorTypeID = sensorTypeID;
        this._deviceID = deviceID;
    }

    /**
     * Constructor for WindSensor.
     * @param deviceID The device ID.
     * @param modelPath The model path.
     * @param sensorTypeID The sensor type ID.
     * @param sensorName The sensor name.
     * @param sensorID The sensor ID.
     */
    public WindSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, SensorID sensorID) {
        Validator.validateNotNull(deviceID, "DeviceID");
        Validator.validateNotNull(modelPath, "ModelPath");
        Validator.validateNotNull(sensorName, "SensorName");
        validateSensorTypeID(sensorTypeID);
        Validator.validateNotNull(sensorID, "SensorID");

        this._modelPath = modelPath;
        this._sensorName = sensorName;
        this._sensorTypeID = sensorTypeID;
        this._deviceID = deviceID;
        this._sensorID = sensorID;
    }


    /**
     * Generates a new sensor id.
     */
    private void generateWindID() {
        this._sensorID = new SensorID(UUID.randomUUID().toString());
    }

    /**
     * Validates the sensor type ID.
     *
     * @param sensorTypeID The sensor type ID.
     */
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        Validator.validateNotNull(sensorTypeID, "SensorTypeID");

        if (!Objects.equals(sensorTypeID.getID(), "Wind")) {
            throw new IllegalArgumentException("SensorTypeID must be 'Wind'");

        }
    }


    /**
     * Gets the sensor ID.
     *
     * @return The sensor ID.
     */
    @Override
    public SensorID getID() {
        return this._sensorID;
    }

    /**
     * Gets the sensor name.
     *
     * @return The sensor name.
     */
    @Override
    public SensorName getName() {
        return this._sensorName;
    }

    /**
     * Gets the model path.
     *
     * @return The model path.
     */
    @Override
    public ModelPath getModelPath() {
        return this._modelPath;
    }

    /**
     * Gets the sensor type ID.
     *
     * @return The sensor type ID.
     */
    @Override
    public SensorTypeID getSensorTypeID() {
        return this._sensorTypeID;
    }

    /**
     * Gets the device ID.
     *
     * @return The device ID.
     */
    @Override
    public DeviceID getDeviceID() {
        return _deviceID;
    }

    /**
     * Method to get the value object of the sensor.
     *
     * @return the value.
     */
    @Override
    public WindSensorValue getValue() {
        int speed = ValueSimulator.generateRandomValue(0, 408);
        double direction = ValueSimulator.generateRandomValue(0.0, 2 * Math.PI);
        this._windSensorValue = new WindSensorValue(speed, direction);
        return _windSensorValue;
    }

    /**
     *Checks if the sensor is equal to another sensor.
     *
     * @param o The sensor to compare.
     * @return True if the sensors are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WindSensor sensor = (WindSensor) o;
        return _sensorID.toString().equals(sensor._sensorID.toString());
    }

    /**
     * Method to get the hash code of the sensor.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return _sensorID.hashCode();
    }

    /**
     * Method to get the string representation of the sensor.
     *
     * @return the string representation.
     */
    @Override
    public String toString() {
        return "WindSensor:" +
                " modelPath=" + _modelPath +
                ", sensorName=" + _sensorName +
                ", sensorID=" + _sensorID +
                ", sensorTypeID=" + _sensorTypeID +
                ", windSensorValue=" + _windSensorValue +
                ", deviceID=" + _deviceID;
    }

    /**
     * Accepts the visitor.
     *
     * @param visitor The visitor.
     */
    public String accept(ISensorVisitor visitor) {
         visitor.visitWindSensor(this);
         return this.toString();
    }
}
