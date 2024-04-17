package smart_home.domain.sensor.solar_irradiance_sensor;

import smart_home.domain.sensor.ISensor;
import smart_home.value_object.*;

import java.util.Objects;
import java.util.UUID;

public class SolarIrradianceSensor implements ISensor {
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
     * @param sensorTypeID
     * @param sensorName
     */
    public SolarIrradianceSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName) {
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
     * Constructor for SolarIrradianceSensor with SensorID
     *
     * @param deviceID
     * @param modelPath
     * @param sensorTypeID
     * @param sensorName
     * @param sensorID
     */
    public SolarIrradianceSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, SensorID sensorID) {
        validateDeviceID(deviceID);
        this._deviceID = deviceID;
        validateModelPath(modelPath);
        this._modelPath = modelPath;
        validateSensorName(sensorName);
        this._sensorName = sensorName;
        validateSensorTypeID(sensorTypeID);
        this._sensorTypeID = sensorTypeID;
        validateSensorID(sensorID);
        this._sensorID = sensorID;
    }

    /**
     * Generates sensorID
     */
    private void generateSensorID() {
        this._sensorID = new SensorID(UUID.randomUUID().toString());
    }

    /**
     * Validates the sensorID
     */
    private void validateSensorID(SensorID sensorID) {
        if (sensorID == null) {
            throw new IllegalArgumentException("SensorID cannot be null");
        }
    }

    /**
     * Validates the sensorName
     *
     * @param sensorName
     */
    private void validateSensorName(SensorName sensorName) {
        if (sensorName == null) {
            throw new IllegalArgumentException("SensorName cannot be null");
        }
    }

    /**
     * Validates the sensorTypeID
     *
     * @param sensorTypeID
     */
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        if (sensorTypeID == null) {
            throw new IllegalArgumentException("SensorTypeID cannot be null");
        }
        if (!Objects.equals(sensorTypeID.getID(), "SolarIrradiance")) {
            throw new IllegalArgumentException("SensorTypeID must be SolarIrradiance");
        }

    }

    /**
     * Validates the modelPath
     *
     * @param modelPath
     */
    private void validateModelPath(ModelPath modelPath) {
        if (modelPath == null) {
            throw new IllegalArgumentException("ModelPath cannot be null");
        }
    }

    /**
     * Validates the deviceID
     *
     * @param deviceID
     */
    private void validateDeviceID(DeviceID deviceID) {
        if (deviceID == null) {
            throw new IllegalArgumentException("DeviceID cannot be null");
        }
    }

    /**
     * Getter for sensorID
     *
     * @return
     */
    @Override
    public SensorID getID() {
        return this._sensorID;
    }

    /**
     * Getter for sensorName
     *
     * @return
     */
    @Override
    public SensorName getName() {
        return this._sensorName;
    }

    /**
     * Getter for modelPath
     *
     * @return
     */
    @Override
    public ModelPath getModelPath() {
        return this._modelPath;
    }

    /**
     * Getter for sensorTypeID
     *
     * @return
     */
    @Override
    public SensorTypeID getSensorTypeID() {
        return this._sensorTypeID;
    }

    /**
     * Getter for sensor value
     *
     * @return
     */
    @Override
    public SolarIrradianceValue getValue() {
        this._value = new SolarIrradianceValue(4500);

        return this._value;
    }

    /**
     * Getter for deviceID
     *
     * @return
     */
    @Override
    public DeviceID getDeviceID() {
        return this._deviceID;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof SolarIrradianceSensor sensor) {
            return this._sensorID.equals(sensor._sensorID);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this._sensorID.hashCode();
    }
}
