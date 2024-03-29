package SmartHomeDDD.domain.Actuator.SwitchActuator;

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

    public SwitchActuator(DeviceID deviceID, ActuatorName actuatorName, ModelPath modelPath, ActuatorTypeID actuatorTypeID) {
        validateDeviceID(deviceID);
        validateActuatorName(actuatorName);
        validateModelPath(modelPath);
        validateActuatorTypeID(actuatorTypeID);
        generateActuatorID();
    }

    private void generateActuatorID() {
        this._actuatorID = new ActuatorID(UUID.randomUUID().toString());
    }
    private void validateDeviceID(DeviceID deviceID) {
        if (deviceID == null)
            throw new IllegalArgumentException("deviceID should not be null.");
        this._deviceID = deviceID;
    }

    private void validateActuatorName(ActuatorName actuatorName) {
        if (actuatorName == null)
            throw new IllegalArgumentException("The value of 'actuatorName' should not be null.");
        this._actuatorName = actuatorName;
    }

    private void validateModelPath(ModelPath modelPath) {
        if (modelPath == null)
            throw new IllegalArgumentException("The value of 'modelPath' should not be null.");
        this._modelPath = modelPath;
    }

    private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
        if (actuatorTypeID == null)
            throw new IllegalArgumentException("The value of 'actuatorTypeID' should not be null.");
        this._actuatorTypeID = actuatorTypeID;
    }

    @Override
    public ActuatorID getID() {
        return _actuatorID;
    }

    @Override
    public ActuatorName getName() {
        return _actuatorName;
    }

    @Override
    public ModelPath getModelPath() {
        return _modelPath;
    }

    @Override
    public ActuatorTypeID getActuatorTypeID() {
        return _actuatorTypeID;
    }

    @Override
    public DeviceID getDeviceID() {
        return _deviceID;
    }

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
