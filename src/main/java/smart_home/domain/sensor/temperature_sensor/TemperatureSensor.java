package smart_home.domain.sensor.temperature_sensor;

import smart_home.domain.sensor.ISensor;
import smart_home.utils.ValueSimulator;
import smart_home.utils.Validator;
import smart_home.value_object.*;
import smart_home.visitor_pattern.ISensorVisitor;

import java.util.Objects;
import java.util.UUID;

public class TemperatureSensor implements ISensor {

    private ModelPath _modelPath;
    private SensorName _sensorName;
    private SensorID _sensorID;
    private SensorTypeID _sensorTypeID;
    private TemperatureSensorValue _temperatureValue;
    private DeviceID _deviceID;

    /**
     * Constructor of the class.
     *
     * @param deviceID     The device ID.
     * @param modelPath    The model path.
     * @param sensorName   The sensor name.
     * @param sensorTypeID The sensor type ID.
     */
    public TemperatureSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName) {
        Validator.validateNotNull(modelPath, "ModelPath");
        this._modelPath = modelPath;
        Validator.validateNotNull(sensorName, "SensorName");
        this._sensorName = sensorName;
        validateSensorTypeID(sensorTypeID);
        this._sensorTypeID = sensorTypeID;
        Validator.validateNotNull(deviceID, "DeviceID");
        this._deviceID = deviceID;

        generateTemperatureID();
    }

    /**
     * Constructor of the class.
     *
     * @param deviceID     The device ID.
     * @param modelPath    The model path.
     * @param sensorName   The sensor name.
     * @param sensorTypeID The sensor type ID.
     * @param sensorID     The sensor ID.
     */
    public TemperatureSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, SensorID sensorID) {
        Validator.validateNotNull(modelPath, "ModelPath");
        this._modelPath = modelPath;
        Validator.validateNotNull(sensorName, "SensorName");
        this._sensorName = sensorName;
        validateSensorTypeID(sensorTypeID);
        this._sensorTypeID = sensorTypeID;
        Validator.validateNotNull(deviceID, "DeviceID");
        this._deviceID = deviceID;
        Validator.validateNotNull(sensorID, "SensorID");
        this._sensorID = sensorID;
    }

    /**
     * Generates a new TemperatureID.
     */
    private void generateTemperatureID() {
        this._sensorID = new SensorID(UUID.randomUUID().toString());
    }


    /**
     * Validates the sensor type ID.
     *
     * @param sensorTypeID The sensor type ID.
     */
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        Validator.validateNotNull(sensorTypeID, "SensorTypeID");

        if (!Objects.equals(sensorTypeID.getID(), "Temperature")) {
            throw new IllegalArgumentException("SensorTypeID must be of type 'Temperature'");
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
     * Returns the value of the sensor.
     *
     * @return The value of the sensor.
     */
    @Override
    public TemperatureSensorValue getValue() {
        // Generate a random temperature as a simulation of hardware behavior
        double temperatureReading = ValueSimulator.generateRandomValue(-50.0, 50.0);
        this._temperatureValue = new TemperatureSensorValue(temperatureReading);

        return _temperatureValue;
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
     * Checks if the object is equal to the current instance.
     *
     * @param o is the object to be compared.
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TemperatureSensor)) return false;
        TemperatureSensor that = (TemperatureSensor) o;
        return Objects.equals(_sensorID, that._sensorID);
    }

    /**
     * Returns the hash code of the sensor ID.
     *
     * @return
     */
    @Override
    public int hashCode() {
        return _sensorID.hashCode();
    }

    /**
     * Returns the string representation of the sensor.
     *
     * @return
     */
    @Override
    public String toString() {
        return "TemperatureSensor:" +
                " modelPath=" + _modelPath +
                ", sensorName=" + _sensorName +
                ", sensorID=" + _sensorID +
                ", sensorTypeID=" + _sensorTypeID +
                ", temperatureValue=" + _temperatureValue +
                ", deviceID=" + _deviceID;
    }
    public String accept(ISensorVisitor visitor) {
        visitor.visitTemperatureSensor(this);
        return this.toString();
    }
}
