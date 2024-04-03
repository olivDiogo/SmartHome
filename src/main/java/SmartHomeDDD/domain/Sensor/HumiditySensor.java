package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;

import java.util.UUID;

public class HumiditySensor implements Sensor{
    private ModelPath _modelPath;
    private SensorName _sensorName;
    private SensorID _sensorID;
    private SensorTypeID _sensorTypeID;
    private HumiditySensorValue _humidityValue;
    private DeviceID _deviceID;

    /**
     *
     * @param deviceID
     * @param modelPath
     * @param sensorTypeID
     * @param sensorName
     */
    HumiditySensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName) {
        validateModelPath(modelPath);
        validateSensorName(sensorName);
        validateSensorTypeID(sensorTypeID);
        validateDeviceID(deviceID);
        generateHumidityID();
    }

    /**
     * generates a new HumidityID
     */
     private void generateHumidityID() {
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
     * Returns the sensor ID.
     *
     * @return The sensor ID.
     */
    @Override
    public SensorID getID() {
        return _sensorID;
    }

    /**
     * Returns the sensor name.
     *
     * @return The sensor name.
     */
    @Override
    public SensorName getName() {
        return _sensorName;
    }

    /**
     * Returns the model path.
     *
     * @return The model path.
     */
    @Override
    public ModelPath getModelPath() {
        return _modelPath;
    }

    /**
     * Returns the sensor type ID.
     *
     * @return The sensor type ID.
     */
    @Override
    public SensorTypeID getSensorTypeID() {
        return _sensorTypeID;
    }

    /**
     * Returns the humidity value.
     *
     * @return The humidity value.
     */
    @Override
    public HumiditySensorValue getValue() {
        int nValue = 100;
        _humidityValue = new HumiditySensorValue(nValue);

        return _humidityValue;
    }

    /**
     * Returns the device ID.
     *
     * @return The device ID.
     */
    @Override
    public DeviceID getDeviceID() {
        return _deviceID;
    }
}
