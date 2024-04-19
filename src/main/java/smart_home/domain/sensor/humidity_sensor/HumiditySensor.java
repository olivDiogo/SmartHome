package smart_home.domain.sensor.humidity_sensor;

import smart_home.domain.sensor.ISensor;
import smart_home.utils.ValueSimulator;
import smart_home.utils.Validator;
import smart_home.value_object.*;

import java.util.Objects;
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
        Validator.validateNotNull(deviceID, "DeviceID");
        Validator.validateNotNull(modelPath, "ModelPath");
        Validator.validateNotNull(sensorName, "SensorName");
        validateSensorTypeID(sensorTypeID);
        generateHumidityID();

        this._deviceID = deviceID;
        this._sensorTypeID = sensorTypeID;
        this._modelPath = modelPath;
        this._sensorName = sensorName;
    }

    /**
     * Constructor with sensorID.
     *
     * @param deviceID     The ID of the device to which the sensor belongs.
     * @param modelPath    The path of the model associated with the sensor.
     * @param sensorTypeID The type ID of the sensor.
     * @param sensorName   The name of the sensor.
     * @param sensorID is the sensor id
     */
    public HumiditySensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, SensorID sensorID) {
        Validator.validateNotNull(deviceID, "DeviceID");
        Validator.validateNotNull(modelPath, "ModelPath");
        Validator.validateNotNull(sensorName, "SensorName");
        validateSensorTypeID(sensorTypeID);
        Validator.validateNotNull(sensorID, "SensorID");

        this._deviceID = deviceID;
        this._sensorTypeID = sensorTypeID;
        this._modelPath = modelPath;
        this._sensorName = sensorName;
        this._sensorID = sensorID;
    }

    /**
     * Generates a random SensorID for the humidity sensor.
     */
    private void generateHumidityID() {
        this._sensorID = new SensorID(UUID.randomUUID().toString());
    }

    /**
     * Validates the sensor type ID.
     *
     * @param sensorTypeID The sensor type ID to validate.
     * @throws IllegalArgumentException if the sensor type ID is null or not of type 'Humidity'.
     */
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        Validator.validateNotNull(sensorTypeID, "SensorTypeID");

        if (!Objects.equals(sensorTypeID.getID(), "Humidity")) {
            throw new IllegalArgumentException("SensorTypeID must be of type 'Humidity'");
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
        int humidityReadingReading = ValueSimulator.generateRandomValue(0, 100);
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
