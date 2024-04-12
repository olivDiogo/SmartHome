package smartHome.domain.sensor.percentagePositionSensor;

import smartHome.domain.sensor.ISensor;
import smartHome.valueObject.*;

import java.util.UUID;

/**
 * Represents a percentage position sensor.
 * This sensor measures the percentage position of an object.
 */
public class PercentagePositionSensor implements ISensor {

    private ModelPath _modelPath; // The model path of the sensor.
    private SensorName _sensorName; // The name of the sensor.
    private SensorID _sensorID; // The ID of the sensor.
    private SensorTypeID _sensorTypeID; // The type ID of the sensor.
    private PercentagePositionSensorValue _percentagePositionSensorValue; // The value of the sensor.
    private DeviceID _deviceID; // The ID of the device associated with the sensor.

    /**
     * Constructs a PercentagePositionSensor with the given parameters.
     *
     * @param deviceID     The ID of the device.
     * @param modelPath    The model path of the sensor.
     * @param sensorTypeID The type ID of the sensor.
     * @param sensorName   The name of the sensor.
     * @throws IllegalArgumentException if any of the parameters are null.
     */
    public PercentagePositionSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName) {
        validateModelPath(modelPath);
        validateSensorName(sensorName);
        validateSensorTypeID(sensorTypeID);
        validateDeviceID(deviceID);
        generateHumidityID();
    }

    /**
     * Generates a unique ID for the sensor.
     */
    private void generateHumidityID() {
        this._sensorID = new SensorID(UUID.randomUUID().toString());
    }

    /**
     * Validates the model path.
     *
     * @param modelPath The model path to validate.
     * @throws IllegalArgumentException if the model path is null.
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
     * @param sensorName The sensor name to validate.
     * @throws IllegalArgumentException if the sensor name is null.
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
     * @param sensorTypeID The sensor type ID to validate.
     * @throws IllegalArgumentException if the sensor type ID is null.
     */
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        if (sensorTypeID == null) {
            throw new IllegalArgumentException("SensorTypeID is required");
        } else {
            this._sensorTypeID = sensorTypeID;
        }
    }

    /**
     * Validates the device ID.
     *
     * @param deviceID The device ID to validate.
     * @throws IllegalArgumentException if the device ID is null.
     */
    private void validateDeviceID(DeviceID deviceID) {
        if (deviceID == null) {
            throw new IllegalArgumentException("DeviceID is required");
        } else {
            this._deviceID = deviceID;
        }
    }

    /**
     * Gets the ID of the sensor.
     *
     * @return The ID of the sensor.
     */
    @Override
    public SensorID getID() {
        return _sensorID;
    }

    /**
     * Gets the name of the sensor.
     *
     * @return The name of the sensor.
     */
    @Override
    public SensorName getName() {
        return _sensorName;
    }

    /**
     * Gets the model path of the sensor.
     *
     * @return The model path of the sensor.
     */
    @Override
    public ModelPath getModelPath() {
        return _modelPath;
    }

    /**
     * Gets the type ID of the sensor.
     *
     * @return The type ID of the sensor.
     */
    @Override
    public SensorTypeID getSensorTypeID() {
        return _sensorTypeID;
    }

    /**
     * Gets the device ID associated with the sensor.
     *
     * @return The device ID associated with the sensor.
     */
    @Override
    public DeviceID getDeviceID() {
        return _deviceID;
    }

    /**
     * Gets the value of the sensor.
     *
     * @return The value of the sensor.
     */
    @Override
    public PercentagePositionSensorValue getValue() {
        int randomInt = 14;

        this._percentagePositionSensorValue = new PercentagePositionSensorValue(randomInt);

        return this._percentagePositionSensorValue;

    }
}
