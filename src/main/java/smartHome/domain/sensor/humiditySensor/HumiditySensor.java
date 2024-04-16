package smartHome.domain.sensor.humiditySensor;

import smartHome.domain.sensor.ISensor;
import smartHome.valueObject.*;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * Represents a humidity sensor.
 */
public class HumiditySensor implements ISensor {
    private ModelPath _modelPath;
    private SensorName _sensorName;
    private SensorID _sensorID;
    private final SensorTypeID _sensorTypeID;
    private HumiditySensorValue _humiditySensorValue;
    private final DeviceID _deviceID;

    /**
     * Constructs a new HumiditySensor.
     *
     * @param deviceID     The ID of the device to which the sensor belongs.
     * @param modelPath    The path of the model associated with the sensor.
     * @param sensorTypeID The type ID of the sensor.
     * @param sensorName   The name of the sensor.
     */
    public HumiditySensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName) {
        validateModelPath(modelPath);
        validateSensorName(sensorName);
        validateSensorTypeID(sensorTypeID);
        validateDeviceID(deviceID);
        generateHumidityID();
        this._deviceID = deviceID;
        this._sensorTypeID = sensorTypeID;
    }

    /**
     * Generates a random SensorID for the humidity sensor.
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
     * @throws IllegalArgumentException if the sensor type ID is null or not of type 'Humidity'.
     */
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        if (sensorTypeID == null) {
            throw new IllegalArgumentException("SensorTypeID is required");
        } else if (!Objects.equals(sensorTypeID.getID(), "Humidity")) {
            throw new IllegalArgumentException("SensorTypeID must be of type 'Humidity'");
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
     * Returns the humidity sensor value.
     *
     * @return The humidity sensor value.
     */
    @Override
    public HumiditySensorValue getValue() {
        Random random = new Random();
        int humidityReadingReading = random.nextInt(101);
        _humiditySensorValue = new HumiditySensorValue(humidityReadingReading);
        return _humiditySensorValue;
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

    /**
     * Checks if this humidity sensor is equal to another object.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof HumiditySensor humiditySensor) {
            return this._sensorID.equals(humiditySensor.getID());
        }
        return false;
    }

    /**
     * Returns the hash code of this humidity sensor.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return this._sensorID.hashCode();
    }
}
