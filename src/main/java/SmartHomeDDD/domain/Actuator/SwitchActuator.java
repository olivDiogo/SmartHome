package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.domain.Actuator.Actuator;
import SmartHomeDDD.domain.ActuatorType.ActuatorType;
import SmartHomeDDD.valueObject.*;

import java.util.UUID;

public class SwitchActuator implements Actuator {

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
     * @param actuatorName   the actuator name
     * @param modelPath      the model path
     * @param actuatorTypeID the actuator type id
     */

    public SwitchActuator(DeviceID deviceID, ActuatorName actuatorName, ModelPath modelPath, ActuatorTypeID actuatorTypeID) {
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
     * @param deviceID
     */
    private void validateDeviceID(DeviceID deviceID) {
        if (deviceID == null)
            throw new IllegalArgumentException("deviceID should not be null.");
        this._deviceID = deviceID;
    }

    /**
     * Validate actuator name.
     * @param actuatorName
     */
    private void validateActuatorName(ActuatorName actuatorName) {
        if (actuatorName == null)
            throw new IllegalArgumentException("The value of 'actuatorName' should not be null.");
        this._actuatorName = actuatorName;
    }

    /**
     * Validate model path.
     * @param modelPath
     */
    private void validateModelPath(ModelPath modelPath) {
        if (modelPath == null)
            throw new IllegalArgumentException("The value of 'modelPath' should not be null.");
        this._modelPath = modelPath;
    }

    /**
     * Validate actuator type id.
     * @param actuatorTypeID
     */
    private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
        if (actuatorTypeID == null)
            throw new IllegalArgumentException("The value of 'actuatorTypeID' should not be null.");
        this._actuatorTypeID = actuatorTypeID;
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
     * @return the actuator type id
     */
    @Override
    public ActuatorTypeID getActuatorTypeID() {
        return _actuatorTypeID;
    }

    /**
     * Gets the device id.
     * @return the device id
     */
    @Override
    public DeviceID getDeviceID() {
        return _deviceID;
    }

    /**
     * Sets the value of the actuator.
     * @param value
     * @return the value object of this Actuator
     */
    @Override
    public ValueObject setValue(ValueObject value) {
        if (value == null)
            throw new IllegalArgumentException("The value of 'value' should not be null.");
        else if (value instanceof SwitchActuatorValue) {
            this._value = (SwitchActuatorValue) value;
            return value;
        }
        return null;
    }

}
