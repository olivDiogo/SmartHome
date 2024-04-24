package smart_home.persistence.jpa.data_model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import smart_home.domain.actuator_model.ActuatorModel;
import smart_home.utils.Validator;

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

    @Version
    private long version;

    /**
     * Class constructor
     */
    public ActuatorModelDataModel() {}

    public ActuatorModelDataModel(ActuatorModel actuatorModel) {
        Validator.validateNotNull(actuatorModel, "Actuator Model");
        this._actuatorModelID = actuatorModel.getID().getID();
        this._actuatorModelName = actuatorModel.getActuatorModelName().getActuatorModelName();
        this._modelPath = actuatorModel.getID().getID();
        this._actuatorTypeID = actuatorModel.getActuatorTypeID().getID();
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

