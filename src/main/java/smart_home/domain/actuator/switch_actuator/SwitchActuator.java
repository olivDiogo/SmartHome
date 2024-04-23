package smart_home.domain.actuator.switch_actuator;

import java.util.UUID;
import smart_home.ddd.IValueObject;
import smart_home.domain.actuator.IActuator;
import smart_home.utils.Validator;
import smart_home.value_object.ActuatorID;
import smart_home.value_object.ActuatorName;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.DeviceID;
import smart_home.value_object.ModelPath;
import smart_home.visitor_pattern.IActuatorVisitor;

public class SwitchActuator implements IActuator {

  private final DeviceID _deviceID;
  private final ActuatorName _actuatorName;
  private final ModelPath _modelPath;
  private final ActuatorTypeID _actuatorTypeID;
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
        Validator.validateNotNull(deviceID, "DeviceID");
        Validator.validateNotNull(modelPath, "ModelPath");
        Validator.validateNotNull(actuatorName, "ActuatorName");
        validateActuatorTypeID(actuatorTypeID);

        generateActuatorID();
        this._actuatorTypeID = actuatorTypeID;
        this._actuatorName = actuatorName;
        this._modelPath = modelPath;
        this._deviceID = deviceID;
    }

    /**
     * Instantiates a new Switch actuator.
     *
     * @param deviceID       the device id
     * @param modelPath      the model path
     * @param actuatorTypeID the actuator type id
     * @param actuatorName   the actuator name
     * @param actuatorID     the actuator id
     */
    public SwitchActuator(DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID,
        ActuatorName actuatorName, ActuatorID actuatorID) {
        Validator.validateNotNull(deviceID, "DeviceID");
        Validator.validateNotNull(modelPath, "ModelPath");
        Validator.validateNotNull(actuatorName, "ActuatorName");
        validateActuatorTypeID(actuatorTypeID);
        Validator.validateNotNull(actuatorID, "ActuatorID");

        this._actuatorTypeID = actuatorTypeID;
        this._actuatorName = actuatorName;
        this._modelPath = modelPath;
        this._deviceID = deviceID;
        this._actuatorID = actuatorID;
    }

    /**
     * generate actuator id
     */
    private void generateActuatorID() {
        this._actuatorID = new ActuatorID(UUID.randomUUID().toString());
    }


    /**
     * Validate actuator type id.
     *
     * @param actuatorTypeID the actuator type id
     */
    private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
        Validator.validateNotNull(actuatorTypeID, "ActuatorTypeID");

        if (!actuatorTypeID.getID().equals("Switch")) {
            throw new IllegalArgumentException("The value of 'actuatorTypeID' should be 'Switch'.");
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
     *
     * @param object is the object to be compared.
     * @return true if the object is equal to the Switch Actuator, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof SwitchActuator switchActuator) {
          return switchActuator._deviceID.equals(this._deviceID);
        }
        return false;
    }

    /**
     * Returns the hash code of the Switch Actuator.
     *
     * @return the hash code of the Switch Actuator.
     */
    @Override
    public int hashCode() {
        return this._actuatorID.hashCode();
    }

    /**
     * Accepts the visitor.
     *
     * @param visitor is the visitor to be accepted.
     * @return the string format of the Switch Actuator.
     */
    @Override
    public String accept(IActuatorVisitor visitor) {
        visitor.visitorSwitchActuator(this);
        return this.toString();
    }
}
