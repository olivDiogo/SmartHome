package SmartHomeDDD.domain.ActuatorModel;

import SmartHomeDDD.ddd.AggregateRoot;
import SmartHomeDDD.valueObject.ActuatorModelID;
import SmartHomeDDD.valueObject.ActuatorModelName;
import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.valueObject.ModelPath;

public class ActuatorModel implements AggregateRoot<ModelPath> {
  private ActuatorModelName _actuatorModelName;
  private ModelPath _modelPath;
  private ActuatorTypeID _actuatorTypeID;

  /**
   * ActuatorModel constructor
   *
   * @param actuatorModelName
   * @param modelPath
   */
  public ActuatorModel(
      ActuatorModelName actuatorModelName, ModelPath modelPath, ActuatorTypeID actuatorTypeID) {
    validateActuatorModelName(actuatorModelName);
    validateModelPath(modelPath);
    _actuatorTypeID = actuatorTypeID;
  }

  /**
   * Validate actuator model name
   *
   * @param actuatorModelName
   */
  private void validateActuatorModelName(ActuatorModelName actuatorModelName) {
    if (actuatorModelName == null) {
      throw new IllegalArgumentException("Please enter a valid actuator model name.");
    } else {
      this._actuatorModelName = actuatorModelName;
    }
  }

  /**
   * Validate model path
   *
   * @param modelPath
   */
  private void validateModelPath(ModelPath modelPath) {
    if (modelPath == null) {
      throw new IllegalArgumentException("Please enter a valid model path.");
    } else {
      this._modelPath = modelPath;
    }
  }

  /**
   * Equals method for actuator model
   *
   * @param object
   * @return boolean
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    ActuatorModel actuatorModel = (ActuatorModel) object;
    return _modelPath.equals(actuatorModel._modelPath);
  }

  /**
   * Get actuator model ID
   *
   * @return ActuatorModelID
   */
  @Override
  public ModelPath getID() {
    return _modelPath;
  }

  /**
   * Get actuator model name
   *
   * @return ActuatorModelName
   */
  public ActuatorModelName getActuatorModelName() {
    return _actuatorModelName;
  }

  /** method to get sensor type id */
  public ActuatorTypeID getActuatorTypeID() {
    return _actuatorTypeID;
  }

  /**
   * To string method for actuator model
   *
   * @return String
   */
  @Override
  public String toString() {
    return "ActuatorModel{"
        + "_actuatorModelID="
        + _modelPath
        + ", _actuatorModelName="
        + _actuatorModelName
        + ", _modelPath="
        + _modelPath
        + '}';
  }
}
