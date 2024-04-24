package smart_home.domain.sensor.humidity_sensor;

import smart_home.domain.sensor.ISensor;
import smart_home.utils.ValueSimulator;
import smart_home.utils.Validator;
import smart_home.value_object.*;
import smart_home.visitor_pattern.ISensorVisitor;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a humidity sensor.
 */
public class HumiditySensor implements ISensor {
    private ModelPath modelPath;
    private SensorName sensorName;
    private SensorID sensorID;
    private final SensorTypeID sensorTypeID;
    private HumiditySensorValue humiditySensorValue;
    private final DeviceID deviceID;

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

        this.deviceID = deviceID;
        this.sensorTypeID = sensorTypeID;
        this.modelPath = modelPath;
        this.sensorName = sensorName;
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

        this.deviceID = deviceID;
        this.sensorTypeID = sensorTypeID;
        this.modelPath = modelPath;
        this.sensorName = sensorName;
        this.sensorID = sensorID;
    }

    /**
     * Generates a random SensorID for the humidity sensor.
     */
    private void generateHumidityID() {
        this.sensorID = new SensorID(UUID.randomUUID().toString());
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
        return sensorID;
    }

    /**
     * Returns the sensor name.
     *
     * @return The sensor name.
     */
    @Override
    public SensorName getName() {
        return sensorName;
    }

    /**
     * Returns the model path.
     *
     * @return The model path.
     */
    @Override
    public ModelPath getModelPath() {
        return modelPath;
    }

    /**
     * Returns the sensor type ID.
     *
     * @return The sensor type ID.
     */
    @Override
    public SensorTypeID getSensorTypeID() {
        return sensorTypeID;
    }

    /**
     * Returns the humidity sensor value.
     *
     * @return The humidity sensor value.
     */
    @Override
    public HumiditySensorValue getValue() {
        int humidityReadingReading = ValueSimulator.generateRandomValue(0, 100);
        humiditySensorValue = new HumiditySensorValue(humidityReadingReading);
        return humiditySensorValue;
    }

    /**
     * Returns the device ID.
     *
     * @return The device ID.
     */
    @Override
    public DeviceID getDeviceID() {
        return deviceID;
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
            return this.sensorID.equals(humiditySensor.getID());
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
        return this.sensorID.hashCode();
    }
    /**
     * Should accept a visitor.
     */
    public String accept(ISensorVisitor visitor) {
        visitor.visitHumiditySensor(this);
        return this.toString();
    }

    @Override
    public String toString() {
        return "HumiditySensor{" +
                "_modelPath=" + modelPath +
                ", _sensorName=" + sensorName +
                ", _sensorID=" + sensorID +
                ", _sensorTypeID=" + sensorTypeID +
                ", _deviceID=" + deviceID +
                '}';
    }
}
