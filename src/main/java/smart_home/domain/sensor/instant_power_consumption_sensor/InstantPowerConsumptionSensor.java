package smart_home.domain.sensor.instant_power_consumption_sensor;

import smart_home.domain.sensor.ISensor;
import smart_home.utils.ValueSimulator;
import smart_home.utils.Validator;
import smart_home.value_object.*;
import smart_home.visitor_pattern.ISensorVisitor;

import java.util.Objects;
import java.util.UUID;

public class InstantPowerConsumptionSensor implements ISensor {
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
    public InstantPowerConsumptionSensor(
            DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName) {
        Validator.validateNotNull(modelPath, "ModelPath");
        this._modelPath = modelPath;
        Validator.validateNotNull(sensorName, "SensorName");
        this._sensorName = sensorName;
        validateSensorTypeID(sensorTypeID);
        this._sensorTypeID = sensorTypeID;
        Validator.validateNotNull(deviceID, "DeviceID");
        this._deviceID = deviceID;

        generateInstantPowerConsumptionID();
    }

    /**
     * Constructor of the class, including the sensor ID.
     *
     * @param deviceID     is the device id
     * @param modelPath    is the model path
     * @param sensorTypeID is the sensor type id
     * @param sensorName   is the sensor name
     * @param sensorID     is the sensor id
     */
    public InstantPowerConsumptionSensor(
            DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, SensorID sensorID) {

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
     * Generates a new InstantPowerConsumptionID.
     */
    private void generateInstantPowerConsumptionID() {
        this._sensorID = new SensorID(UUID.randomUUID().toString());
    }


    /**
     * Validates the sensor type ID.
     *
     * @param sensorTypeID The sensor type ID.
     */
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        Validator.validateNotNull(sensorTypeID, "SensorTypeID");

        if (!Objects.equals(sensorTypeID.getID(), "InstantPowerConsumption")) {
            throw new IllegalArgumentException("SensorTypeID must be of type 'InstantPowerConsumption'");
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
        double instantPowerConsumptionValue = ValueSimulator.generateRandomValue(0.0, 100);
        this._instantPowerConsumptionValue =
                new InstantPowerConsumptionValue(instantPowerConsumptionValue);

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

    /**
     * Compares this instance with another instance.
     *
     * @param o is the object to be compared.
     * @return true if the instances are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof InstantPowerConsumptionSensor sensor) {
            return this._sensorID.equals(sensor.getID());
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

    /**
     * toString method
     */
    @Override
    public String toString() {
        return "InstantPowerConsumptionSensor: " +
                "modelPath=" + _modelPath +
                ", sensorName=" + _sensorName +
                ", sensorID=" + _sensorID +
                ", sensorTypeID=" + _sensorTypeID +
                ", deviceID=" + _deviceID;
    }
    /**
     * Accepts a visitor.
     */
    public String accept(ISensorVisitor visitor) {
        visitor.visitInstantPowerSensor(this);
        return this.toString();
    }
}




