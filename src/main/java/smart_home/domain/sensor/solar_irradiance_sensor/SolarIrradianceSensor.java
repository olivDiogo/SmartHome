package smart_home.domain.sensor.solar_irradiance_sensor;

import smart_home.domain.sensor.ISensor;
import smart_home.utils.Validator;
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
     * @param deviceID     is the deviceID
     * @param modelPath    is the model path
     * @param sensorTypeID is the sensor type ID
     * @param sensorName   is the sensor name
     */
    public SolarIrradianceSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName) {
        Validator.validateNotNull(deviceID);
        this._deviceID = deviceID;
        Validator.validateNotNull(modelPath);
        this._modelPath = modelPath;
        Validator.validateNotNull(sensorName);
        this._sensorName = sensorName;
        validateSensorTypeID(sensorTypeID);
        this._sensorTypeID = sensorTypeID;

        generateSensorID();
    }

    /**
     * Constructor for SolarIrradianceSensor with SensorID
     *
     * @param deviceID     is the deviceID
     * @param modelPath    is the model path
     * @param sensorTypeID is the sensor type ID
     * @param sensorName   is the sensor name
     * @param sensorID     is the sensor id
     */
    public SolarIrradianceSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, SensorID sensorID) {
        Validator.validateNotNull(deviceID);
        this._deviceID = deviceID;
        Validator.validateNotNull(modelPath);
        this._modelPath = modelPath;
        Validator.validateNotNull(sensorName);
        this._sensorName = sensorName;
        validateSensorTypeID(sensorTypeID);
        this._sensorTypeID = sensorTypeID;
        Validator.validateNotNull(sensorID);
        this._sensorID = sensorID;
    }

    /**
     * Generates sensorID
     */
    private void generateSensorID() {
        this._sensorID = new SensorID(UUID.randomUUID().toString());
    }


    /**
     * Validates the sensorTypeID
     *
     * @param sensorTypeID is the sensor type id
     */
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        Validator.validateNotNull(sensorTypeID);

        if (!Objects.equals(sensorTypeID.getID(), "SolarIrradiance")) {
            throw new IllegalArgumentException("SensorTypeID must be SolarIrradiance");
        }

    }


    /**
     * Getter for sensorID
     *
     * @return sensor id
     */
    @Override
    public SensorID getID() {
        return this._sensorID;
    }

    /**
     * Getter for sensorName
     *
     * @return sensor name
     */
    @Override
    public SensorName getName() {
        return this._sensorName;
    }

    /**
     * Getter for modelPath
     *
     * @return the model path
     */
    @Override
    public ModelPath getModelPath() {
        return this._modelPath;
    }

    /**
     * Getter for sensorTypeID
     *
     * @return sensor type id
     */
    @Override
    public SensorTypeID getSensorTypeID() {
        return this._sensorTypeID;
    }

    /**
     * Getter for sensor value
     *
     * @return sensor value
     */
    @Override
    public SolarIrradianceValue getValue() {
        this._value = new SolarIrradianceValue(4500);

        return this._value;
    }

    /**
     * Getter for deviceID
     *
     * @return device id
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
