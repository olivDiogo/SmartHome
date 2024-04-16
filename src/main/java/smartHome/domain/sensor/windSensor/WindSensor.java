package smartHome.domain.sensor.windSensor;

import smartHome.domain.sensor.ISensor;
import smartHome.valueObject.*;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class WindSensor implements ISensor {
    private ModelPath _modelPath;
    private SensorName _sensorName;
    private SensorID _sensorID;
    private SensorTypeID _sensorTypeID;
    private WindSensorValue _windSensorValue;
    private DeviceID _deviceID;

    public WindSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName) {
        validateModelPath(modelPath);
        validateSensorName(sensorName);
        validateSensorTypeID(sensorTypeID);
        validateDeviceID(deviceID);
        generateWindID();
    }

    /**
     * Generates a new sensor id.
     */
    private void generateWindID() {
        this._sensorID = new SensorID(UUID.randomUUID().toString());
    }

    /**
     * Validates the model path.
     *
     * @param modelPath The model path.
     */
    private void validateModelPath(ModelPath modelPath) {
        if (modelPath == null) {
            throw new IllegalArgumentException("ModelPath is required");
        } else {
            this._modelPath = modelPath;
        }
    }

    /**
     * Validates the sensor name.
     *
     * @param sensorName The sensor name.
     */
    private void validateSensorName(SensorName sensorName) {
        if (sensorName == null) {
            throw new IllegalArgumentException("SensorName is required");
        } else {
            this._sensorName = sensorName;
        }
    }

    /**
     * Validates the sensor type ID.
     *
     * @param sensorTypeID The sensor type ID.
     */
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        if (sensorTypeID == null) {
            throw new IllegalArgumentException("SensorTypeID is required");

        } else if (!Objects.equals(sensorTypeID.getID(), "Wind")) {
            throw new IllegalArgumentException("SensorTypeID must be 'Wind'");

        } else {
            this._sensorTypeID = sensorTypeID;
        }
    }

    /**
     * Validates the device ID.
     *
     * @param deviceID The device ID.
     */
    private void validateDeviceID(DeviceID deviceID) {
        if (deviceID == null) {
            throw new IllegalArgumentException("DeviceID is required");
        } else {
            this._deviceID = deviceID;
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
        Random rand = new Random();
        int speed = rand.nextInt(408); //wind speed world record
        double direction = rand.nextDouble() * 2 * Math.PI; // direction in radians
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
}
