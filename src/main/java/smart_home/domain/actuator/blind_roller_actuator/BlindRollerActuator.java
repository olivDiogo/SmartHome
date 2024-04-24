package smart_home.domain.actuator.blind_roller_actuator;

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

/**
 * Represents a Blind Roller Actuator in the Smart Home Domain. This actuator is responsible for
 * controlling blind roller devices.
 */
public class BlindRollerActuator implements IActuator {

  private ActuatorID _actuatorID;
  private final DeviceID _deviceID;
  private final ModelPath _modelPath;
  private final ActuatorTypeID _actuatorTypeID;
  private final ActuatorName _actuatorName;
  private BlindRollerValue _value;

  /**
   * Constructs a new BlindRollerActuator with the specified parameters.
   *
   * @param deviceID       The ID of the device associated with this actuator.
   * @param actuatorTypeID The type ID of the actuator.
   * @param actuatorName   The name of the actuator.
   * @param modelPath      The model path of the actuator.
   */
  public BlindRollerActuator(
      DeviceID deviceID,
      ModelPath modelPath,
      ActuatorTypeID actuatorTypeID,
      ActuatorName actuatorName) {
    Validator.validateNotNull(deviceID, "DeviceID");
    validateActuatorTypeID(actuatorTypeID);
    Validator.validateNotNull(actuatorName, "ActuatorName");
    Validator.validateNotNull(modelPath, "ModelPath");

    generateActuatorID();
    this._actuatorTypeID = actuatorTypeID;
    this._actuatorName = actuatorName;
    this._modelPath = modelPath;
    this._deviceID = deviceID;
  }

  /**
   * Constructs a new BlindRollerActuator with the specified parameters.
   *
   * @param deviceID       The ID of the device associated with this actuator.
   * @param modelPath      The model path of the actuator.
   * @param actuatorTypeID The type ID of the actuator.
   * @param actuatorName   The name of the actuator.
   * @param actuatorID     The ID of the actuator.
   */
  public BlindRollerActuator(
      DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID,
      ActuatorName actuatorName, ActuatorID actuatorID) {
    Validator.validateNotNull(deviceID, "DeviceID");
    validateActuatorTypeID(actuatorTypeID);
    Validator.validateNotNull(actuatorName, "ActuatorName");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(actuatorID, "ActuatorID");

    this._deviceID = deviceID;
    this._actuatorID = actuatorID;
    this._actuatorTypeID = actuatorTypeID;
    this._actuatorName = actuatorName;
    this._modelPath = modelPath;
  }

  /**
   * Validates and sets the actuator type ID.
   *
   * @param actuatorTypeID The actuator type ID to be validated.
   * @throws IllegalArgumentException If the actuatorTypeID is null.
   */
  private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
    Validator.validateNotNull(actuatorTypeID, "ActuatorTypeID");
    if (!actuatorTypeID.getID().equals("BlindRoller")) {
      throw new IllegalArgumentException("The value of 'actuatorTypeID' should be 'BlindRoller'.");
    }
  }

  /**
   * Generates a unique actuator ID.
   */
  private void generateActuatorID() {
    this._actuatorID = new ActuatorID(UUID.randomUUID().toString());
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
    if (value instanceof BlindRollerValue newValue) {
      this._value = newValue;
      return newValue;
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
    return _actuatorID
        + " "
        + _deviceID
        + " "
        + _modelPath
        + " "
        + _actuatorTypeID
        + " "
        + _actuatorName;
  }

  /**
   * Accepts the visitor.
   *
   * @param visitor The visitor to be accepted.
   * @return The string format of the {@link BlindRollerActuator}
   */
  @Override
  public String accept(IActuatorVisitor visitor) {
    visitor.visitorBlindRollerActuator(this);
    return this.toString();
  }
}
