package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;

import java.util.UUID;

public class SolarIrradianceSensor implements Sensor{
    private SensorID _sensorID;
    private SensorName _sensorName;
    private ModelPath _modelPath;
    private SensorTypeID _sensorTypeID;
    private DeviceID _deviceID;
    private SolarIrradianceValue _value;

    /**
     * Constructor for SolarIrradianceSensor
     *
     * @param deviceID
     * @param modelPath
     * @param sensorName
     * @param sensorTypeID
     */
    public SolarIrradianceSensor(DeviceID deviceID, ModelPath modelPath, SensorName sensorName, SensorTypeID sensorTypeID) {
        validateDeviceID(deviceID);
        this._deviceID = deviceID;

        validateModelPath(modelPath);
        this._modelPath = modelPath;

        validateSensorName(sensorName);
        this._sensorName = sensorName;

        validateSensorTypeID(sensorTypeID);
        this._sensorTypeID = sensorTypeID;

        generateSensorID();
    }

    /**
     * Generates sensorID
     */
    private void generateSensorID() {
        this._sensorID = new SensorID(UUID.randomUUID().toString());
    }

    /**
     * Validates the sensorName
     * @param sensorName
     */
    private void validateSensorName(SensorName sensorName) {
        if (sensorName == null) {
            throw new IllegalArgumentException("SensorName cannot be null");
        }
    }

    /**
     * Validates the sensorTypeID
     * @param sensorTypeID
     */
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        if (sensorTypeID == null) {
            throw new IllegalArgumentException("SensorTypeID cannot be null");
        }
    }

    /**
     * Validates the modelPath
     * @param modelPath
     */
    private void validateModelPath(ModelPath modelPath) {
        if (modelPath == null) {
            throw new IllegalArgumentException("ModelPath cannot be null");
        }
    }

    /**
     * Validates the deviceID
     * @param deviceID
     */
    private void validateDeviceID(DeviceID deviceID) {
        if (deviceID == null) {
            throw new IllegalArgumentException("DeviceID cannot be null");
        }
    }

    /**
     * Getter for sensorID
     * @return
     */
    @Override
    public SensorID getID() {
        return this._sensorID;
    }

    /**
     * Getter for sensorName
     * @return
     */
    @Override
    public SensorName getName() {
        return this._sensorName;
    }

    /**
     * Getter for modelPath
     * @return
     */
    @Override
    public ModelPath getModelPath() {
        return this._modelPath;
    }

    /**
     * Getter for sensorTypeID
     * @return
     */
    @Override
    public SensorTypeID getSensorTypeID() {
        return this._sensorTypeID;
    }

    /**
     * Getter for sensor value
     * @return
     */
    @Override
    public ValueObject getValue() {
        this._value = new SolarIrradianceValue(4500);

        return this._value;
    }

    /**
     * Getter for deviceID
     * @return
     */
    @Override
    public DeviceID getDeviceID() {
        return this._deviceID;
    }
}
