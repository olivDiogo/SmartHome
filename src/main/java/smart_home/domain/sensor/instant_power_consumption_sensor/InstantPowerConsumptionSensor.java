package smart_home.domain.sensor.instant_power_consumption_sensor;

import smart_home.domain.sensor.ISensor;
import smart_home.utils.ValueSimulator;
import smart_home.utils.Validator;
import smart_home.value_object.*;
import smart_home.visitor_pattern.ISensorVisitor;

import java.util.Objects;
import java.util.UUID;

public class InstantPowerConsumptionSensor implements ISensor {
    private ModelPath modelPath;
    private SensorName sensorName;
    private SensorID sensorID;
    private SensorTypeID sensorTypeID;
    private InstantPowerConsumptionValue instantPowerConsumptionValue;
    private DeviceID deviceID;

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
        this.modelPath = modelPath;
        Validator.validateNotNull(sensorName, "SensorName");
        this.sensorName = sensorName;
        validateSensorTypeID(sensorTypeID);
        this.sensorTypeID = sensorTypeID;
        Validator.validateNotNull(deviceID, "DeviceID");
        this.deviceID = deviceID;

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
        this.modelPath = modelPath;
        Validator.validateNotNull(sensorName, "SensorName");
        this.sensorName = sensorName;
        validateSensorTypeID(sensorTypeID);
        this.sensorTypeID = sensorTypeID;
        Validator.validateNotNull(deviceID, "DeviceID");
        this.deviceID = deviceID;
        Validator.validateNotNull(sensorID, "SensorID");
        this.sensorID = sensorID;
    }

    /**
     * Generates a new InstantPowerConsumptionID.
     */
    private void generateInstantPowerConsumptionID() {
        this.sensorID = new SensorID(UUID.randomUUID().toString());
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
        return this.sensorID;
    }

    /**
     * Gets the sensor name.
     *
     * @return The sensor name.
     */
    @Override
    public SensorName getName() {
        return this.sensorName;
    }

    /**
     * Gets the model path.
     *
     * @return The model path.
     */
    @Override
    public ModelPath getModelPath() {
        return this.modelPath;
    }

    /**
     * Gets the sensor type ID.
     *
     * @return The sensor type ID.
     */
    @Override
    public SensorTypeID getSensorTypeID() {
        return this.sensorTypeID;
    }

    /**
     * Returns the value of the sensor.
     */
    @Override
    public InstantPowerConsumptionValue getValue() {
        double instantPowerConsumptionValue = ValueSimulator.generateRandomValue(0.0, 100);
        this.instantPowerConsumptionValue =
                new InstantPowerConsumptionValue(instantPowerConsumptionValue);

        return this.instantPowerConsumptionValue;
    }

    /**
     * Returns the device ID.
     *
     * @return The device ID.
     */
    @Override
    public DeviceID getDeviceID() {
        return this.deviceID;
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
            return this.sensorID.equals(sensor.getID());
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
        return this.sensorID.hashCode();
    }

    /**
     * toString method
     */
    @Override
    public String toString() {
        return "InstantPowerConsumptionSensor: " +
                "modelPath=" + modelPath +
                ", sensorName=" + sensorName +
                ", sensorID=" + sensorID +
                ", sensorTypeID=" + sensorTypeID +
                ", deviceID=" + deviceID;
    }
    /**
     * Accepts a visitor.
     */
    public String accept(ISensorVisitor visitor) {
        visitor.visitInstantPowerSensor(this);
        return this.toString();
    }
}




