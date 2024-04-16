package smart_home.domain.actuator_model;

import smart_home.ddd.IAggregateRoot;
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
     * @param actuatorModelName
     * @param modelPath
     */
    public ActuatorModel(
            ActuatorModelName actuatorModelName, ModelPath modelPath, ActuatorTypeID actuatorTypeID) {
        validateActuatorModelName(actuatorModelName);
        validateModelPath(modelPath);
        validateActuatorTypeID(actuatorTypeID);
    }

    public ActuatorModel(
            ActuatorModelID actuatorModelID, ActuatorModelName actuatorModelName, ModelPath modelPath, ActuatorTypeID actuatorTypeID) {
        validateActuatorModelName(actuatorModelName);
        validateModelPath(modelPath);
        validateActuatorTypeID(actuatorTypeID);
        validateActuatorModelID(actuatorModelID);
    }

    /**
     * Validate actuator model ID
     *
     * @param actuatorModelID
     */
    private void validateActuatorModelID(ActuatorModelID actuatorModelID) {
        if (actuatorModelID == null) {
            throw new IllegalArgumentException("Please enter a valid actuator model ID.");
        } else {
            this._actuatorModelID = actuatorModelID;
        }
    }

    /**
     * Validate actuator type id
     *
     * @param actuatorTypeID
     */
    private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
        if (actuatorTypeID == null) {
            throw new IllegalArgumentException("Please enter a valid actuator type ID.");
        } else {
            this._actuatorTypeID = actuatorTypeID;
        }
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
