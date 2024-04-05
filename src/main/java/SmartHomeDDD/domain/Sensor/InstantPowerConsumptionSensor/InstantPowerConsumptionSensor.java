package SmartHomeDDD.domain.Sensor.InstantPowerConsumptionSensor;

import SmartHomeDDD.domain.Sensor.Sensor;
import SmartHomeDDD.valueObject.*;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class InstantPowerConsumptionSensor implements Sensor {
    private ModelPath _modelPath;
    private SensorName _sensorName;
    private SensorID _sensorID;
    private SensorTypeID _sensorTypeID;
    private InstantPowerConsumptionValue _instantPowerConsumptionValue;
    private DeviceID _deviceID;

    /**
     * Constructor of the class.
     *
     * @param deviceID     The device ID.
     * @param modelPath    The model path.
     * @param sensorName   The sensor name.
     * @param sensorTypeID The sensor type ID.
     */
    public InstantPowerConsumptionSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName) {
        validateModelPath(modelPath);
        validateSensorName(sensorName);
        validateSensorTypeID(sensorTypeID);
        validateDeviceID(deviceID);
        generateInstantPowerConsumptionID();
    }

    /**
     * Generates a new InstantPowerConsumptionID.
     */
    private void generateInstantPowerConsumptionID() {
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
        } else if (!Objects.equals(modelPath.toString(), "InstantPowerConsumption")) {
            this._modelPath = modelPath;
        }
    }

    /**
     * Validates the sensor name.
     *
     * @return
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

        } else if (!Objects.equals(sensorTypeID.getId(), "InstantPowerConsumption")) {
            throw new IllegalArgumentException("SensorTypeID must be of type 'InstantPowerConsumption'");
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
     * Returns the value of the sensor.
     */
    @Override
    public InstantPowerConsumptionValue getValue() {
        Random rand = new Random();
        double instantPowerConsumptionValue = rand.nextDouble() * 100;

        this._instantPowerConsumptionValue = new InstantPowerConsumptionValue(instantPowerConsumptionValue);

        return _instantPowerConsumptionValue;
    }

    /**
     * Returns the device ID.
     *
     * @return The device ID.
     */
    @Override
    public DeviceID getDeviceID() {
        return this._deviceID;
    }
}
