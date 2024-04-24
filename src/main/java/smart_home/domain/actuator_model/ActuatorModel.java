package smart_home.domain.actuator_model;

import smart_home.ddd.IAggregateRoot;
import smart_home.utils.Validator;
import smart_home.value_object.ActuatorModelID;
import smart_home.value_object.ActuatorModelName;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.ModelPath;

public class ActuatorModel implements IAggregateRoot<ModelPath> {

  private ActuatorModelName _actuatorModelName;
  private ModelPath _modelPath;
  private ActuatorTypeID _actuatorTypeID;
  private ActuatorModelID _actuatorModelID;

  /**
   * ActuatorModel constructor
   *
   * @param actuatorModelName The name of the actuator model
   * @param modelPath The path to the model
   */
  public ActuatorModel(
      ActuatorModelName actuatorModelName, ModelPath modelPath, ActuatorTypeID actuatorTypeID) {
    Validator.validateNotNull(actuatorModelName, "ActuatorModelName");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(actuatorTypeID, "ActuatorTypeID");
    this._actuatorModelName = actuatorModelName;
    this._modelPath = modelPath;
    this._actuatorTypeID = actuatorTypeID;
  }

  public ActuatorModel(
      ActuatorModelID actuatorModelID, ActuatorModelName actuatorModelName, ModelPath modelPath,
      ActuatorTypeID actuatorTypeID) {
    Validator.validateNotNull(actuatorModelID, "ActuatorModelID");
    Validator.validateNotNull(actuatorModelName, "ActuatorModelName");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(actuatorTypeID, "ActuatorTypeID");
    this._actuatorModelID = actuatorModelID;
    this._actuatorModelName = actuatorModelName;
    this._modelPath = modelPath;
    this._actuatorTypeID = actuatorTypeID;

  }


  /**
   * Equals method for actuator model
   *
   * @param object Object
   * @return boolean
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    ActuatorModel actuatorModel = (ActuatorModel) object;
    return _modelPath.equals(actuatorModel._modelPath);
  }

  /**
   * Hash code method
   */
  @Override
  public int hashCode() {
    return _modelPath.hashCode();
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

  /**
   * method to get sensor type id
   */
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
    return _actuatorModelName + " " + _modelPath + " " + _actuatorTypeID;
  }
}
