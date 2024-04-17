package smart_home.domain.actuator.blind_roller_actuator;

import smart_home.ddd.IValueObject;
import smart_home.domain.actuator.IActuator;
import smart_home.value_object.*;

import java.util.UUID;

/**
 * Represents a Blind Roller Actuator in the Smart Home Domain.
 * This actuator is responsible for controlling blind roller devices.
 */
public class BlindRollerActuator implements IActuator {

    private ActuatorID _actuatorID;
    private DeviceID _deviceID;
    private ModelPath _modelPath;
    private ActuatorTypeID _actuatorTypeID;
    private ActuatorName _actuatorName;
    private BlindRollerValue _value;

    /**
     * Constructs a new BlindRollerActuator with the specified parameters.
     *
     * @param deviceID       The ID of the device associated with this actuator.
     * @param actuatorTypeID The type ID of the actuator.
     * @param actuatorName   The name of the actuator.
     * @param modelPath      The model path of the actuator.
     */
    public BlindRollerActuator(DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID, ActuatorName actuatorName) {
        validateDeviceID(deviceID);
        validateActuatorTypeID(actuatorTypeID);
        validateActuatorName(actuatorName);
        validateModelPath(modelPath);
        generateActuatorID();
    }

    /**
     * Constructs a new BlindRollerActuator with the specified parameters.
     *
     * @param actuatorID     The ID of the actuator.
     * @param deviceID       The ID of the device associated with this actuator.
     * @param actuatorTypeID The type ID of the actuator.
     * @param actuatorName   The name of the actuator.
     * @param modelPath      The model path of the actuator.
     */
    public BlindRollerActuator(ActuatorID actuatorID, DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID, ActuatorName actuatorName) {
        validateDeviceID(deviceID);
        validateActuatorTypeID(actuatorTypeID);
        validateActuatorName(actuatorName);
        validateModelPath(modelPath);
        validateActuatorID(actuatorID);
        this._actuatorID = actuatorID;
    }

    /**
     * Validates and sets the device ID.
     *
     * @param deviceID The device ID to be validated.
     * @throws IllegalArgumentException If the deviceID is null.
     */
    private void validateDeviceID(DeviceID deviceID) {
        if (deviceID == null) {
            throw new IllegalArgumentException("DeviceID cannot be null");
        }
        this._deviceID = deviceID;
    }

    /**
     * Validates and sets the actuator type ID.
     *
     * @param actuatorTypeID The actuator type ID to be validated.
     * @throws IllegalArgumentException If the actuatorTypeID is null.
     */
    private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
        if (actuatorTypeID == null) {
            throw new IllegalArgumentException("ActuatorTypeID cannot be null");
        }
        this._actuatorTypeID = actuatorTypeID;
    }

    /**
     * Validates and sets the actuator name.
     *
     * @param actuatorName The actuator name to be validated.
     * @throws IllegalArgumentException If the actuatorName is null.
     */
    private void validateActuatorName(ActuatorName actuatorName) {
        if (actuatorName == null) {
            throw new IllegalArgumentException("ActuatorName cannot be null");
        }
        this._actuatorName = actuatorName;
    }

    /**
     * Validates and sets the model path.
     *
     * @param modelPath The model path to be validated.
     * @throws IllegalArgumentException If the modelPath is null.
     */
    private void validateModelPath(ModelPath modelPath) {
        if (modelPath == null) {
            throw new IllegalArgumentException("ModelPath cannot be null");
        }
        this._modelPath = modelPath;
    }

    /**
     * Generates a unique actuator ID.
     */
    private void generateActuatorID() {
        this._actuatorID = new ActuatorID(UUID.randomUUID().toString());
    }

    /**
     * Validates the actuator ID.
     *
     * @param actuatorID The actuator ID to be validated.
     * @throws IllegalArgumentException If the actuatorID is null.
     */
    private void validateActuatorID(ActuatorID actuatorID) {
        if (actuatorID == null) {
            throw new IllegalArgumentException("ActuatorID cannot be null");
        }
    }

    /**
     * Returns the actuator ID.
     *
     * @return The actuator ID.
     */
    @Override
    public ActuatorID getID() {
        return _actuatorID;
    }

    /**
     * Returns the actuator name.
     *
     * @return The actuator name.
     */
    @Override
    public ActuatorName getName() {
        return _actuatorName;
    }

    /**
     * Returns the model path of the actuator.
     *
     * @return The model path.
     */
    @Override
    public ModelPath getModelPath() {
        return _modelPath;
    }

    /**
     * Returns the actuator type ID.
     *
     * @return The actuator type ID.
     */
    @Override
    public ActuatorTypeID getActuatorTypeID() {
        return _actuatorTypeID;
    }

    /**
     * Returns the device ID associated with this actuator.
     *
     * @return The device ID.
     */
    @Override
    public DeviceID getDeviceID() {
        return _deviceID;
    }

    /**
     * Sets the value of the actuator if the value object is of the correct type.
     *
     * @param value The value to set.
     * @return The set value if successful, null otherwise.
     */
    @Override
    public BlindRollerValue setValue(IValueObject value) {
        if (value instanceof BlindRollerValue) {
            this._value = (BlindRollerValue) value;
            return _value;
        } else {
            return null;
        }
    }

    /**
     * Method to compare two instances
     *
     * @param object The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof BlindRollerActuator actuator) {
            return _actuatorID.equals(actuator._actuatorID);
        }
        return false;
    }

    /**
     * Overrides the hashCode method to return the hash code of the actuator ID.
     */
    @Override
    public int hashCode() {
        return _actuatorID.hashCode();
    }

    /**
     * Returns a string representation of the actuator.
     *
     * @return A string representation of the actuator.
     */
    @Override
    public String toString() {
        return _actuatorID + " " + _deviceID + " " + _modelPath + " " + _actuatorTypeID + " " + _actuatorName;
    }
}