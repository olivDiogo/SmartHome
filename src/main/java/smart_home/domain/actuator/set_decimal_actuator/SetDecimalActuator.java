package smart_home.domain.actuator.set_decimal_actuator;

import smart_home.ddd.IValueObject;
import smart_home.utils.Validator;
import smart_home.value_object.*;

import java.util.UUID;

/** Represents a decimal actuator used to set decimal values within specified limits. */
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
   * @param deviceID The ID of the device associated with the actuator.
   * @param modelPath The path of the model associated with the actuator.
   * @param actuatorTypeID The ID of the actuator type.
   * @param actuatorName The name of the actuator.
   * @param limits The limits within which the actuator can set values.
   */
  public SetDecimalActuator(
      DeviceID deviceID,
      ModelPath modelPath,
      ActuatorTypeID actuatorTypeID,
      ActuatorName actuatorName,
      SetDecimalActuatorLimits limits) {
    Validator.validateNotNull(deviceID);
    Validator.validateNotNull(modelPath);
    Validator.validateNotNull(actuatorName);
    validateActuatorTypeID(actuatorTypeID);
    Validator.validateNotNull(limits);

    generateActuatorID();
    this._actuatorName = actuatorName;
    this._modelPath = modelPath;
    this._deviceID = deviceID;
    this._actuatorTypeID = actuatorTypeID;
    this._limits = limits;
  }

  /**
   * Constructs a SetDecimalActuator object with the provided parameters.
   *
   * @param actuatorID The ID of the actuator.
   * @param deviceID The ID of the device associated with the actuator.
   * @param modelPath The path of the model associated with the actuator.
   * @param actuatorTypeID The ID of the actuator type.
   * @param actuatorName The name of the actuator.
   * @param limits The limits within which the actuator can set values.
   */
  public SetDecimalActuator(
      ActuatorID actuatorID,
      DeviceID deviceID,
      ModelPath modelPath,
      ActuatorTypeID actuatorTypeID,
      ActuatorName actuatorName,
      SetDecimalActuatorLimits limits) {
    Validator.validateNotNull(deviceID);
    Validator.validateNotNull(modelPath);
    Validator.validateNotNull(actuatorName);
    validateActuatorTypeID(actuatorTypeID);
    Validator.validateNotNull(limits);
    Validator.validateNotNull(actuatorID);

    this._actuatorName = actuatorName;
    this._modelPath = modelPath;
    this._deviceID = deviceID;
    this._actuatorTypeID = actuatorTypeID;
    this._limits = limits;
    this._actuatorID = actuatorID;
  }

  /** Generates a new actuator ID. */
  private void generateActuatorID() {
    this._actuatorID = new ActuatorID(UUID.randomUUID().toString());
  }

  /**
   * Validates the provided actuator type ID.
   *
   * @param actuatorTypeID The ID of the actuator type.
   */
  private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
    Validator.validateNotNull(actuatorTypeID);

    if (!actuatorTypeID.getID().equals("SetDecimal")) {
      throw new IllegalArgumentException("The value of 'actuatorTypeID' should be 'SetDecimal'.");
    }
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
  public IValueObject setValue(IValueObject value) {
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

  /**
   * Method to compare two instances
   *
   * @param object The object to compare with.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof SetDecimalActuator setDecimalActuator) {
      return _actuatorID.equals(setDecimalActuator._actuatorID);
    }
    return false;
  }

  /** Overrides the hashCode method to return the hash code of the actuator ID. */
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
    return "ActuatorID: "
        + _actuatorID
        + ", ActuatorName: "
        + _actuatorName
        + ", ModelPath: "
        + _modelPath
        + ", ActuatorTypeID: "
        + _actuatorTypeID
        + ", DeviceID: "
        + _deviceID
        + ", Limits: "
        + _limits;
  }
}
