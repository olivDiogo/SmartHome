package SmartHomeDDD.domain.Actuator.SetDecimalActuator;

import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;

import java.util.UUID;

/**
 * Represents a decimal actuator used to set decimal values within specified limits.
 */
public class SetDecimalActuator {

    private ActuatorID _actuatorID;

    private ActuatorName _actuatorName;

    private ModelPath _modelPath;

    private ActuatorTypeID _actuatorTypeID;

    private DeviceID _deviceID;

    private SetDecimalValue _value;

    private SetDecimalActuatorLimits _limits;

    /**
     * Constructs a SetDecimalActuator object with the provided parameters.
     *
     * @param deviceID       The ID of the device associated with the actuator.
     * @param modelPath      The path of the model associated with the actuator.
     * @param actuatorTypeID The ID of the actuator type.
     * @param actuatorName   The name of the actuator.
     * @param limits         The limits within which the actuator can set values.
     */
    public SetDecimalActuator(DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID, ActuatorName actuatorName, SetDecimalActuatorLimits limits) {
        validateDeviceID(deviceID);
        validateModelPath(modelPath);
        validateActuatorName(actuatorName);
        validateActuatorTypeID(actuatorTypeID);
        validateLimits(limits);
        generateActuatorID();
    }

    /**
     * Generates a new actuator ID.
     */
    private void generateActuatorID() {
        this._actuatorID = new ActuatorID(UUID.randomUUID().toString());
    }

    /**
     * Validates the provided actuator name.
     *
     * @param actuatorName The name of the actuator.
     */
    private void validateActuatorName(ActuatorName actuatorName) {
        if (actuatorName == null) {
            throw new IllegalArgumentException("ActuatorName cannot be null");
        }
        this._actuatorName = actuatorName;
    }

    /**
     * Validates the provided model path.
     *
     * @param modelPath The path of the model associated with the actuator.
     */
    private void validateModelPath(ModelPath modelPath) {
        if (modelPath == null) {
            throw new IllegalArgumentException("ModelPath cannot be null");
        }
        this._modelPath = modelPath;
    }

    /**
     * Validates the provided device ID.
     *
     * @param deviceID The ID of the device associated with the actuator.
     */
    private void validateDeviceID(DeviceID deviceID) {
        if (deviceID == null) {
            throw new IllegalArgumentException("DeviceID cannot be null");
        }
        this._deviceID = deviceID;
    }

    /**
     * Validates the provided actuator type ID.
     *
     * @param actuatorTypeID The ID of the actuator type.
     */
    private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
        if (actuatorTypeID == null) {
            throw new IllegalArgumentException("ActuatorTypeID cannot be null");
        }
        this._actuatorTypeID = actuatorTypeID;
    }

    /**
     * Validates the provided limits.
     *
     * @param limits The limits within which the actuator can set values.
     */
    private void validateLimits(SetDecimalActuatorLimits limits) {
        if (limits == null) {
            throw new IllegalArgumentException("Limits cannot be null");
        }
        this._limits = limits;
    }

    /**
     * Gets the ID of the actuator.
     *
     * @return The ActuatorID object representing the ID of the actuator.
     */
    public ActuatorID getActuatorID() {
        return _actuatorID;
    }

    /**
     * Gets the name of the actuator.
     *
     * @return The ActuatorName object representing the name of the actuator.
     */
    public ActuatorName getActuatorName() {
        return _actuatorName;
    }

    /**
     * Gets the model path associated with the actuator.
     *
     * @return The ModelPath object representing the model path associated with the actuator.
     */
    public ModelPath getModelPath() {
        return _modelPath;
    }

    /**
     * Gets the ID of the actuator type.
     *
     * @return The ActuatorTypeID object representing the ID of the actuator type.
     */
    public ActuatorTypeID getActuatorTypeID() {
        return _actuatorTypeID;
    }

    /**
     * Gets the ID of the device associated with the actuator.
     *
     * @return The DeviceID object representing the ID of the device associated with the actuator.
     */
    public DeviceID getDeviceID() {
        return _deviceID;
    }

    /**
     * Gets the limits within which the actuator can set values.
     *
     * @return The SetDecimalActuatorLimits object representing the limits of the actuator.
     */
    public SetDecimalActuatorLimits getLimits() {
        return _limits;
    }

    /**
     * Sets the value of the actuator within the specified limits.
     *
     * @param value The value to be set for the actuator.
     * @return The SetDecimalValue object representing the set value of the actuator.
     * @throws IllegalArgumentException if the provided value is null or outside the specified limits.
     */
    public ValueObject setValue(ValueObject value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        double nValue = Double.parseDouble(value.toString());

        if (nValue < _limits.getLowerLimit()) {
            throw new IllegalArgumentException("Value cannot be less than the lower limit.");
        } else if (nValue > _limits.getUpperLimit()) {
            throw new IllegalArgumentException("Value cannot be greater than the upper limit.");
        } else if (value instanceof SetDecimalValue) {
            this._value = (SetDecimalValue) value;
            return this._value;
        }
        return null;
    }
}

