package smart_home.domain.actuator.set_integer_actuator;

import smart_home.ddd.IValueObject;
import smart_home.domain.actuator.IActuator;
import smart_home.utils.Validator;
import smart_home.value_object.*;
import smart_home.visitor_pattern.IActuatorVisitor;

import java.util.UUID;

public class SetIntegerActuator implements IActuator {
    private ActuatorID _actuatorID;
    private ActuatorName _actuatorName;
    private ModelPath _modelPath;
    private ActuatorTypeID _actuatorTypeID;
    private DeviceID _deviceID;
    private SetIntegerValue _value;
    private IntegerLimits _limits;

    /**
     * Constructor for SetIntegerActuator
     *
     * @param deviceID       is the ID of the device associated with the actuator
     * @param modelPath      is the path of the model associated with the actuator
     * @param actuatorTypeID is the ID of the actuator type
     * @param actuatorName   is the name of the actuator
     */
    public SetIntegerActuator(DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID, ActuatorName actuatorName, IntegerLimits limits) {
        Validator.validateNotNull(deviceID, "DeviceID");
        Validator.validateNotNull(modelPath, "ModelPath");
        Validator.validateNotNull(actuatorName, "ActuatorName");
        validateActuatorTypeID(actuatorTypeID);
        Validator.validateNotNull(limits, "SetIntegerActuatorLimits");

        this._actuatorTypeID = actuatorTypeID;
        this._actuatorName = actuatorName;
        this._modelPath = modelPath;
        this._deviceID = deviceID;
        this._limits = limits;
        generateActuatorID();
    }

    /**
     * Constructor for SetIntegerActuator
     *
     * @param actuatorID     is the ID of the actuator
     * @param deviceID       is the ID of the device associated with the actuator
     * @param modelPath      is the path of the model associated with the actuator
     * @param actuatorTypeID is the ID of the actuator type
     * @param actuatorName   is the name of the actuator
     */
    public SetIntegerActuator(ActuatorID actuatorID, DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID, ActuatorName actuatorName, IntegerLimits limits) {
        Validator.validateNotNull(deviceID, "DeviceID");
        Validator.validateNotNull(modelPath, "ModelPath");
        Validator.validateNotNull(actuatorName, "ActuatorName");
        validateActuatorTypeID(actuatorTypeID);
        Validator.validateNotNull(limits, "SetIntegerActuatorLimits");
        Validator.validateNotNull(actuatorID, "ActuatorID");

        this._actuatorTypeID = actuatorTypeID;
        this._actuatorName = actuatorName;
        this._modelPath = modelPath;
        this._deviceID = deviceID;
        this._limits = limits;
        this._actuatorID = actuatorID;

    }

    /**
     * Generates actuatorID
     */

    private void generateActuatorID() {
        this._actuatorID = new ActuatorID(UUID.randomUUID().toString());
    }


    /**
     * Validates the actuatorTypeID
     *
     * @param actuatorTypeID
     */
    private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
        Validator.validateNotNull(actuatorTypeID, "ActuatorTypeID");

        if (!actuatorTypeID.getID().equals("SetInteger")) {
            throw new IllegalArgumentException("ActuatorTypeID must be SetInteger");
        }
    }


    /**
     * Getter for actuatorID
     *
     * @return actuatorID
     */
    @Override
    public ActuatorID getID() {
        return this._actuatorID;
    }

    /**
     * Getter for actuatorName
     *
     * @return actuatorName
     */
    @Override
    public ActuatorName getName() {
        return this._actuatorName;
    }

    /**
     * Getter for modelPath
     *
     * @return modelPath
     */
    @Override
    public ModelPath getModelPath() {
        return this._modelPath;
    }

    /**
     * Getter for actuatorTypeID
     *
     * @return actuatorTypeID
     */
    @Override
    public ActuatorTypeID getActuatorTypeID() {
        return this._actuatorTypeID;
    }

    /**
     * Getter for deviceID
     *
     * @return deviceID
     */
    @Override
    public DeviceID getDeviceID() {
        return this._deviceID;
    }

    /**
     * Getter for limits
     *
     * @return limits
     */
    public IntegerLimits getLimits() {
        return this._limits;
    }

    /**
     * Sets the value within the range
     *
     * @return SetIntegerValue
     */
    @Override
    public SetIntegerValue setValue(IValueObject value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        int nValue = Integer.parseInt(value.toString());

        if (nValue < _limits.getLowerLimit()) {
            throw new IllegalArgumentException("Value cannot be less than the lower limit.");
        } else if (nValue > _limits.getUpperLimit()) {
            throw new IllegalArgumentException("Value cannot be greater than the upper limit.");
        } else if (value instanceof SetIntegerValue) {
            this._value = (SetIntegerValue) value;
            return (SetIntegerValue) value;
        }

        return null;
    }

    /**
     * Accepts the visitor
     *
     * @param visitor The visitor.
     */
    @Override
    public String accept(IActuatorVisitor visitor) {
        visitor.visitorSetIntegerActuator(this);
        return this.toString();
    }

    /**
     * Method to compare two instances
     *
     * @param object The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */

    @Override
    public boolean equals(Object object) {
        if (object instanceof SetIntegerActuator setIntegerActuator) {
            return this._actuatorID.equals(setIntegerActuator._actuatorID);
        }
        return false;
    }

    /**
     * Method to get hash code
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return _actuatorID.hashCode();
    }

    /**
     * Method to get string representation
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return _actuatorID + " " + _actuatorName + " " + _modelPath + " " + _actuatorTypeID + " " + _deviceID + " " + _value + " " + _limits;
    }
}
