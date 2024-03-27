package SmartHomeDDD.domain.ActuatorModel;

import SmartHomeDDD.ddd.AggregateRoot;
import SmartHomeDDD.valueObject.ActuatorModelID;
import SmartHomeDDD.valueObject.ActuatorModelName;
import SmartHomeDDD.valueObject.ModelPath;

import java.util.UUID;

public class ActuatorModel implements AggregateRoot<ActuatorModelID> {

    private ActuatorModelID _actuatorModelID;
    private ActuatorModelName _actuatorModelName;
    private ModelPath _modelPath;

    /**
     * ActuatorModel constructor
     * @param actuatorModelName
     * @param modelPath
     */

    public ActuatorModel(ActuatorModelName actuatorModelName, ModelPath modelPath) {
        validateActuatorModelName(actuatorModelName);
        validateModelPath(modelPath);
        _actuatorModelID = new ActuatorModelID("" + modelPath.toString().hashCode());
    }

    /**
     * Validate actuator model name
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
     * @param object
     * @return boolean
     */

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ActuatorModel actuatorModel = (ActuatorModel) object;
        return _actuatorModelID.equals(actuatorModel._actuatorModelID);
    }

    /**
     * Get actuator model ID
     * @return ActuatorModelID
     */
    @Override
    public ActuatorModelID getID() {
        return _actuatorModelID;
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
     * Get model path
     * @ return ModelPath
     */
    public ModelPath getModelPath() {
        return _modelPath;
    }

    /**
     * To string method for actuator model
     * @return String
     */
    @Override
    public String toString() {
        return "ActuatorModel{" +
                "_actuatorModelID=" + _actuatorModelID +
                ", _actuatorModelName=" + _actuatorModelName +
                ", _modelPath=" + _modelPath +
                '}';
    }
}
