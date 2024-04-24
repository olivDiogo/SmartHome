package smart_home.domain.sensor.switch_sensor;

import smart_home.domain.sensor.ISensor;
import smart_home.utils.ValueSimulator;
import smart_home.utils.Validator;
import smart_home.value_object.*;
import smart_home.visitor_pattern.ISensorVisitor;

import java.util.Objects;
import java.util.UUID;

public class SwitchSensor implements ISensor {
    private ModelPath modelPath;
    private SensorName sensorName;
    private SensorID sensorID;
    private SensorTypeID sensorTypeID;
    private SwitchSensorValue switchSensorValue;
    private DeviceID deviceID;

    /**
     * @param deviceID     The device ID.
     * @param modelPath    The model path.
     * @param sensorTypeID The sensor type ID.
     * @param sensorName   The sensor name.
     */
    public SwitchSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName) {
        Validator.validateNotNull(deviceID, "DeviceID");
        Validator.validateNotNull(modelPath, "ModelPath");
        validateSensorTypeID(sensorTypeID);
        Validator.validateNotNull(sensorName, "SensorName");
        generateSwitchSensorID();

        switchSensorValue = new SwitchSensorValue(false);
        this.modelPath = modelPath;
        this.sensorName = sensorName;
        this.sensorTypeID = sensorTypeID;
        this.deviceID = deviceID;

    }

    /**
     * @param deviceID     The device ID.
     * @param modelPath    The model path.
     * @param sensorTypeID The sensor type ID.
     * @param sensorName   The sensor name.
     * @param sensorID   The sensor ID.
     */
    public SwitchSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, SensorID sensorID) {
        Validator.validateNotNull(deviceID, "DeviceID");
        Validator.validateNotNull(modelPath, "ModelPath");
        validateSensorTypeID(sensorTypeID);
        Validator.validateNotNull(sensorName, "SensorName");
        Validator.validateNotNull(sensorID, "SensorID");

        switchSensorValue = new SwitchSensorValue(false);
        this.modelPath = modelPath;
        this.sensorName = sensorName;
        this.sensorTypeID = sensorTypeID;
        this.deviceID = deviceID;

    }



    /**
     * generates a new HumidityID
     */
    private void generateSwitchSensorID() {
        this.sensorID = new SensorID(UUID.randomUUID().toString());
    }

    /**
     * Validates the sensor type ID.
     *
     * @param sensorTypeID The sensor type ID.
     */
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        Validator.validateNotNull(sensorTypeID, "SensorTypeID");

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
        return this.sensorID;
    }

    /**
     * Returns the sensor name.
     *
     * @return The sensor name.
     */
    @Override
    public SensorName getName() {
        return this.sensorName;
    }

    /**
     * Returns the model path.
     *
     * @return The model path.
     */
    @Override
    public ModelPath getModelPath() {
        return this.modelPath;
    }

    /**
     * Returns the sensor type ID.
     *
     * @return The sensor type ID.
     */
    @Override
    public SensorTypeID getSensorTypeID() {
        return this.sensorTypeID;
    }

    /**
     * Returns the sensor value.
     *
     * @return The sensor value.
     */
    @Override
    public SwitchSensorValue getValue() {
        boolean randomBoolean = ValueSimulator.generateRandomBoolean();
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
        if (o instanceof SwitchSensor switchSensor) {
            return this.sensorID.equals(switchSensor.getID());
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

    @Override
    public String toString() {
        return "SwitchSensor: DeviceID= " + deviceID.getID() + " ModelPath= " + modelPath.getID() + " SensorTypeID= " + sensorTypeID.getID() + " SensorName= " + sensorName.getSensorName() + " SensorID= " + sensorID.getID();
    }
    public String accept(ISensorVisitor visitor) {
         visitor.visitSwitchSensor(this);
        return this.toString();
    }
}
