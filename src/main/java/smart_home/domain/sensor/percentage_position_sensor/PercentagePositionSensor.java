package smart_home.domain.sensor.percentage_position_sensor;

import smart_home.domain.sensor.ISensor;
import smart_home.utils.Validator;
import smart_home.value_object.*;

import java.util.UUID;

/**
 * Represents a percentage position sensor.
 * This sensor measures the percentage position of an object.
 */
public class PercentagePositionSensor implements ISensor {

    private ModelPath _modelPath;
    private SensorName _sensorName;
    private SensorID _sensorID;
    private SensorTypeID _sensorTypeID;
    private PercentagePositionSensorValue _percentagePositionSensorValue;
    private DeviceID _deviceID;

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
        Validator.validateNotNull(modelPath, "ModelPath");
        Validator.validateNotNull(sensorName, "SensorName");
        validateSensorTypeID(sensorTypeID);
        Validator.validateNotNull(deviceID, "DeviceID");
        generatePercentageID();
        this._modelPath = modelPath;
        this._sensorName = sensorName;
        this._sensorTypeID = sensorTypeID;
        this._deviceID = deviceID;
    }

    /**
     * Constructs a PercentagePositionSensor with the given parameters.
     *
     * @param deviceID     The ID of the device.
     * @param modelPath    The model path of the sensor.
     * @param sensorTypeID The type ID of the sensor.
     * @param sensorName   The name of the sensor.
     * @param sensorID     The ID of the sensor.
     * @throws IllegalArgumentException if any of the parameters are null.
     */
    public PercentagePositionSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, SensorID sensorID) {
        Validator.validateNotNull(modelPath, "ModelPath");
        Validator.validateNotNull(sensorName, "SensorName");
        validateSensorTypeID(sensorTypeID);
        Validator.validateNotNull(deviceID, "DeviceID");
        Validator.validateNotNull(sensorID, "SensorID");

        this._modelPath = modelPath;
        this._sensorName = sensorName;
        this._sensorTypeID = sensorTypeID;
        this._deviceID = deviceID;
        this._sensorID = sensorID;
    }


    /**
     * Generates a unique ID for the sensor.
     */
    private void generatePercentageID() {
        this._sensorID = new SensorID(UUID.randomUUID().toString());
    }

    /**
     * Validates the sensor type ID.
     *
     * @param sensorTypeID The sensor type ID to validate.
     * @throws IllegalArgumentException if the sensor type ID is null.
     */
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        Validator.validateNotNull(sensorTypeID, "SensorTypeID");

        if (!sensorTypeID.getID().equals("PercentagePosition")) {
            throw new IllegalArgumentException("SensorTypeID must be 'PercentagePosition'");
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

    /**
     * Method to compare this object with another object for equality.
     *
     * @param o The object to be compared.
     * @return true if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof PercentagePositionSensor percentagePositionSensorObject) {
            return _sensorID.equals(percentagePositionSensorObject._sensorID);
        }
        return false;
    }

    /**
     * Returns a hash code value for this object.
     *
     * @return The hash code value for this object.
     */
    @Override
    public int hashCode() {
        return _sensorID.hashCode();
    }

    /**
     * Returns a string representation of this object.
     *
     * @return The string representation of this object.
     */
    @Override
    public String toString() {
        return "PercentagePositionSensor: DeviceID= " + _deviceID.getID() + " ModelPath= " + _modelPath.getID() + " SensorTypeID= " + _sensorTypeID.getID() + " SensorName= " + _sensorName.getSensorName() + " SensorID= " + _sensorID.getID();
    }

}
