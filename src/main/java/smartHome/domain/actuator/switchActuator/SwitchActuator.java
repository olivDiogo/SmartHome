package smartHome.domain.actuator.switchActuator;

import smartHome.ddd.IValueObject;
import smartHome.domain.actuator.IActuator;
import smartHome.valueObject.*;

import java.util.UUID;

public class SwitchActuator implements IActuator {

    private DeviceID _deviceID;
    private ActuatorName _actuatorName;
    private ModelPath _modelPath;
    private ActuatorTypeID _actuatorTypeID;
    private ActuatorID _actuatorID;
    private SwitchActuatorValue _value;

    /**
     * Instantiates a new Switch actuator.
     *
     * @param deviceID       the device id
     * @param modelPath      the model path
     * @param actuatorTypeID the actuator type id
     * @param actuatorName   the actuator name
     */

    public SwitchActuator(DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID, ActuatorName actuatorName) {
        validateDeviceID(deviceID);
        validateActuatorName(actuatorName);
        validateModelPath(modelPath);
        validateActuatorTypeID(actuatorTypeID);
        generateActuatorID();
    }


    /**
     * generate actuator id
     */
    private void generateActuatorID() {
        this._actuatorID = new ActuatorID(UUID.randomUUID().toString());
    }

    /**
     * Validate device id.
     *
     * @param deviceID
     */
    private void validateDeviceID(DeviceID deviceID) {
        if (deviceID == null)
            throw new IllegalArgumentException("deviceID should not be null.");
        this._deviceID = deviceID;
    }

    /**
     * Validate actuator name.
     *
     * @param actuatorName
     */
    private void validateActuatorName(ActuatorName actuatorName) {
        if (actuatorName == null)
            throw new IllegalArgumentException("The value of 'actuatorName' should not be null.");
        this._actuatorName = actuatorName;
    }

    /**
     * Validate model path.
     *
     * @param modelPath
     */
    private void validateModelPath(ModelPath modelPath) {
        if (modelPath == null)
            throw new IllegalArgumentException("The value of 'modelPath' should not be null.");
        this._modelPath = modelPath;
    }

    /**
     * Validate actuator type id.
     *
     * @param actuatorTypeID
     */
    private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
        if (actuatorTypeID == null)
            throw new IllegalArgumentException("The value of 'actuatorTypeID' should not be null.");
        if (!actuatorTypeID.getID().equals("Switch")) {
            throw new IllegalArgumentException("The value of 'actuatorTypeID' should be 'Switch'.");
        } else {
            this._actuatorTypeID = actuatorTypeID;
        }
    }

    /**
     * Gets ID.
     *
     * @return the actuator id
     */
    @Override
    public ActuatorID getID() {
        return _actuatorID;
    }

    /**
     * Gets value.
     *
     * @return the actuator name
     */

    @Override
    public ActuatorName getName() {
        return _actuatorName;
    }

    /**
     * Gets value.
     *
     * @return the model path
     */

    @Override
    public ModelPath getModelPath() {
        return _modelPath;
    }

    /**
     * Gets the actuator type id.
     *
     * @return the actuator type id
     */
    @Override
    public ActuatorTypeID getActuatorTypeID() {
        return _actuatorTypeID;
    }

    /**
     * Gets the device id.
     *
     * @return the device id
     */
    @Override
    public DeviceID getDeviceID() {
        return _deviceID;
    }

    /**
     * Sets the value of the actuator.
     *
     * @param value the value object of the actuator
     * @return the value object of this Actuator
     */
    @Override
    public IValueObject setValue(IValueObject value) {
        if (value == null)
            throw new IllegalArgumentException("The value of 'value' should not be null.");
        else if (value instanceof SwitchActuatorValue) {
            this._value = (SwitchActuatorValue) value;
            return value;
        }
        return null;
    }

    /**
     * Returns the Switch Actuator in a string format.
     *
     * @return the string format of the Switch Actuator
     */
    @Override
    public String toString() {
        return "SwitchActuator: DeviceID=" + _deviceID + ", ActuatorName=" + _actuatorName + ", ModelPath=" + _modelPath + ", ActuatorTypeID=" + _actuatorTypeID + ", ActuatorID=" + _actuatorID + ", Value=" + _value;
    }

    /**
     * Compares the Switch Actuator with another object.
     * @param object is the object to be compared.
     * @return true if the object is equal to the Switch Actuator, false otherwise.
     */
    @Override
    public boolean equals (Object object) {
        if (object instanceof SwitchActuator switchActuator){
            return switchActuator._deviceID.equals(this._deviceID) && switchActuator._actuatorName.equals(this._actuatorName) && switchActuator._modelPath.equals(this._modelPath) && switchActuator._actuatorTypeID.equals(this._actuatorTypeID) && switchActuator._actuatorID.equals(this._actuatorID) && switchActuator._value.equals(this._value);
        }
        return false;
    }

    /**
     * Returns the hash code of the Switch Actuator.
     * @return the hash code of the Switch Actuator.
     */
    @Override
    public int hashCode() {
        return this._actuatorID.hashCode();
    }

}
