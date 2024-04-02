package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;

import java.util.UUID;

public class SetIntegerActuator implements Actuator{
    private ActuatorID _actuatorID;
    private ActuatorName _actuatorName;
    private ModelPath _modelPath;
    private ActuatorTypeID _actuatorTypeID;
    private DeviceID _deviceID;
    private SetIntegerValue _value;
    private SetIntegerActuatorLimits _limits;

    /**
     * Constructor for SetIntegerActuator
     *
     * @param deviceID
     * @param modelPath
     * @param actuatorName
     * @param actuatorTypeID
     */
    protected SetIntegerActuator(DeviceID deviceID, ModelPath modelPath, ActuatorName actuatorName, ActuatorTypeID actuatorTypeID, SetIntegerActuatorLimits limits) {
        validateDeviceID(deviceID);
        this._deviceID = deviceID;

        validateModelPath(modelPath);
        this._modelPath = modelPath;

        validateActuatorName(actuatorName);
        this._actuatorName = actuatorName;

        validateActuatorTypeID(actuatorTypeID);
        this._actuatorTypeID = actuatorTypeID;

        validateLimits(limits);
        this._limits = limits;

        generateActuatorID();
    }

    /**
     * Generates actuatorID
     */

    private void generateActuatorID() {
        this._actuatorID = new ActuatorID(UUID.randomUUID().toString());
    }

    /**
     * Validates the actuatorName
     * @param actuatorName
     */
    private void validateActuatorName(ActuatorName actuatorName) {
        if (actuatorName == null) {
            throw new IllegalArgumentException("ActuatorName cannot be null");
        }
    }

    /**
     * Validates the modelPath
     * @param modelPath
     */
    private void validateModelPath(ModelPath modelPath) {
        if (modelPath == null) {
            throw new IllegalArgumentException("ModelPath cannot be null");
        }
    }

    /**
     * Validates the actuatorTypeID
     * @param actuatorTypeID
     */
    private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
        if (actuatorTypeID == null) {
            throw new IllegalArgumentException("ActuatorTypeID cannot be null");
        }
    }

    /**
     * Validates the deviceID
     * @param deviceID
     */
    private void validateDeviceID(DeviceID deviceID) {
        if (deviceID == null) {
            throw new IllegalArgumentException("DeviceID cannot be null");
        }
    }

    /**
     * Validates the limits
     * @param limits
     */
    private void validateLimits(SetIntegerActuatorLimits limits) {
        if (limits == null) {
            throw new IllegalArgumentException("SetIntegerActuatorLimits cannot be null");
        }
    }

    /**
     * Getter for actuatorID
     * @return
     */
    @Override
    public ActuatorID getID() {
        return this._actuatorID;
    }

    /**
     * Getter for actuatorName
     * @return
     */
    @Override
    public ActuatorName getName() {
        return this._actuatorName;
    }

    /**
     * Getter for modelPath
     * @return
     */
    @Override
    public ModelPath getModelPath() {
        return this._modelPath;
    }

    /**
     * Getter for actuatorTypeID
     * @return
     */
    @Override
    public ActuatorTypeID getActuatorTypeID() {
        return this._actuatorTypeID;
    }

    /**
     * Getter for deviceID
     * @return
     */
    @Override
    public DeviceID getDeviceID() {
        return this._deviceID;
    }

    /**
     * Getter for limits
     * @return
     */
    public SetIntegerActuatorLimits getLimits() {
        return this._limits;
    }

    /**
     * Sets the value within the range
     * @return
     */
    @Override
    public ValueObject setValue(ValueObject value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        int nValue = Integer.parseInt(value.toString());

        if(nValue < _limits.getLowerLimit()) {
            throw new IllegalArgumentException("Value cannot be less than the lower limit.");
        } else if (nValue > _limits.getUpperLimit()) {
            throw new IllegalArgumentException("Value cannot be greater than the upper limit.");
        } else if (value instanceof SetIntegerValue) {
            this._value = (SetIntegerValue) value;
            return value;
        }

        return null;
    }
}
