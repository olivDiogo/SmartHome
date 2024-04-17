package smart_home.domain.sensor.switch_sensor;

import smart_home.domain.sensor.ISensor;
import smart_home.utils.Validator;
import smart_home.value_object.*;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class SwitchSensor implements ISensor {
    private ModelPath _modelPath;
    private SensorName _sensorName;
    private SensorID _sensorID;
    private SensorTypeID _sensorTypeID;
    private SwitchSensorValue switchSensorValue;
    private DeviceID _deviceID;

    /**
     * @param deviceID     The device ID.
     * @param modelPath    The model path.
     * @param sensorTypeID The sensor type ID.
     * @param sensorName   The sensor name.
     */
    public SwitchSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName) {
        Validator.validateNotNull(deviceID);
        Validator.validateNotNull(modelPath);
        validateSensorTypeID(sensorTypeID);
        Validator.validateNotNull(sensorName);
        generateSwitchSensorID();

        switchSensorValue = new SwitchSensorValue(false);
        this._modelPath = modelPath;
        this._sensorName = sensorName;
        this._sensorTypeID = sensorTypeID;
        this._deviceID = deviceID;

    }

    /**
     * @param deviceID     The device ID.
     * @param modelPath    The model path.
     * @param sensorTypeID The sensor type ID.
     * @param sensorName   The sensor name.
     * @param sensorID   The sensor ID.
     */
    public SwitchSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, SensorID sensorID) {
        Validator.validateNotNull(deviceID);
        Validator.validateNotNull(modelPath);
        validateSensorTypeID(sensorTypeID);
        Validator.validateNotNull(sensorName);
        Validator.validateNotNull(sensorID);

        switchSensorValue = new SwitchSensorValue(false);
        this._modelPath = modelPath;
        this._sensorName = sensorName;
        this._sensorTypeID = sensorTypeID;
        this._deviceID = deviceID;

    }



    /**
     * generates a new HumidityID
     */
    private void generateSwitchSensorID() {
        this._sensorID = new SensorID(UUID.randomUUID().toString());
    }

    /**
     * Validates the sensor type ID.
     *
     * @param sensorTypeID The sensor type ID.
     */
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        Validator.validateNotNull(sensorTypeID);

        if (!Objects.equals(sensorTypeID.getID(), "Switch")) {
            throw new IllegalArgumentException("SensorTypeID must be of type 'Switch'");
        }
    }

    /**
     * Returns the sensor ID.
     *
     * @return The sensor ID.
     */
    @Override
    public SensorID getID() {
        return this._sensorID;
    }

    /**
     * Returns the sensor name.
     *
     * @return The sensor name.
     */
    @Override
    public SensorName getName() {
        return this._sensorName;
    }

    /**
     * Returns the model path.
     *
     * @return The model path.
     */
    @Override
    public ModelPath getModelPath() {
        return this._modelPath;
    }

    /**
     * Returns the sensor type ID.
     *
     * @return The sensor type ID.
     */
    @Override
    public SensorTypeID getSensorTypeID() {
        return this._sensorTypeID;
    }

    /**
     * Returns the sensor value.
     *
     * @return The sensor value.
     */
    @Override
    public SwitchSensorValue getValue() {
        Random random = new Random();
        // Randomly choose true or false
        boolean randomBoolean = random.nextBoolean();

        this.switchSensorValue = new SwitchSensorValue(randomBoolean);

        return this.switchSensorValue;
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

    /**
     * Compares this instance with another instance.
     *
     * @param o is the object to be compared.
     * @return true if the instances are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof SwitchSensor switchSensor) {
            return this._sensorID.equals(switchSensor.getID());
        }
        return false;
    }

    /**
     * Generates a hash code for the sensor.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return this._sensorID.hashCode();
    }

    @Override
    public String toString() {
        return "SwitchSensor: DeviceID= " + _deviceID.getID() + " ModelPath= " + _modelPath.getID() + " SensorTypeID= " + _sensorTypeID.getID() + " SensorName= " + _sensorName.getSensorName() + " SensorID= " + _sensorID.getID();
    }
}
