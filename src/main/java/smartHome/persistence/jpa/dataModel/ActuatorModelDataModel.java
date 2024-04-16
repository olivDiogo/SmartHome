package smartHome.persistence.jpa.dataModel;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import smartHome.domain.actuatorModel.ActuatorModel;

@Entity
@Table(name = "ACTUATOR_MODEL")
public class ActuatorModelDataModel {

    @Id
    @Column(name = "Actuator Model ID")
    private String _actuatorModelID;

    @Column(name = "Actuator Model Name")
    private String _actuatorModelName;

    @Column(name = "Model Path")
    private String _modelPath;

    @Column(name = "Actuator Type ID")
    private String _actuatorTypeID;

    /**
     * Class constructor
     */
    public ActuatorModelDataModel() {}

    public ActuatorModelDataModel(ActuatorModel actuatorModel) {
        validateActuatorModel(actuatorModel);
        this._actuatorModelID = actuatorModel.getID().getID();
        this._actuatorModelName = actuatorModel.getActuatorModelName().getActuatorModelName();
        this._modelPath = actuatorModel.getID().getID();
        this._actuatorTypeID = actuatorModel.getActuatorTypeID().getID();
    }

    /**
     * Method to validate actuator model
     * @param actuatorModel
     */
    private void validateActuatorModel(ActuatorModel actuatorModel) {
        if (actuatorModel == null) {
            throw new IllegalArgumentException("ActuatorModel cannot be null.");
        }
    }

    /**
     * Method to return the actuator model ID.
     * @return
     */
    public String get_actuatorModelID() {
        return this._actuatorModelID;
    }

    /**
     * Method to return the actuator model name.
     * @return
     */
    public String get_actuatorModelName() {
        return this._actuatorModelName;
    }

    /**
     * Method to return the model path.
     * @return
     */
    public String get_modelPath() {
        return this._modelPath;
    }

    /**
     * Method to return the actuator type ID.
     * @return
     */
    public String get_actuatorTypeID() {
        return this._actuatorTypeID;
    }
}

