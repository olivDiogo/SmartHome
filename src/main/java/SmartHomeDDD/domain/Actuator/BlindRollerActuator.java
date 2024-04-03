package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;

import java.util.UUID;

public class BlindRollerActuator implements Actuator{

    private ActuatorID _actuatorID;
    private DeviceID _deviceID;
    private ActuatorTypeID _actuatorTypeID;
    private ActuatorName _actuatorName;
    private ModelPath _modelPath;
    private BlindRollerValue _value;

    BlindRollerActuator(DeviceID deviceID, ActuatorTypeID actuatorTypeID, ActuatorName actuatorName, ModelPath modelPath){
        validateDeviceID(deviceID);
        validateActuatorTypeID(actuatorTypeID);
        validateActuatorName(actuatorName);
        validateModelPath(modelPath);

        generateActuatorID();
    }

        private void validateDeviceID(DeviceID deviceID) {
        if (deviceID == null) {
            throw new IllegalArgumentException("DeviceID cannot be null");
        }
        this._deviceID = deviceID;
    }
    private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
        if (actuatorTypeID == null) {
            throw new IllegalArgumentException("ActuatorTypeID cannot be null");
        }
        this._actuatorTypeID = actuatorTypeID;
    }
    private void validateActuatorName(ActuatorName actuatorName) {
        if (actuatorName == null) {
            throw new IllegalArgumentException("ActuatorName cannot be null");
        }
        this._actuatorName = actuatorName;
    }
    private void validateModelPath(ModelPath modelPath) {
        if (modelPath == null) {
            throw new IllegalArgumentException("ModelPath cannot be null");
        }
        this._modelPath = modelPath;
    }

    private void generateActuatorID() {

        this._actuatorID = new ActuatorID(UUID.randomUUID().toString());
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
    public BlindRollerValue setValue(ValueObject value) {
        if(value instanceof BlindRollerValue){
            this._value = (BlindRollerValue) value;
            return _value;
        }
        else {
            return null;
        }
    }
}
