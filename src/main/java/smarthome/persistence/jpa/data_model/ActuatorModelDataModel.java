package smarthome.persistence.jpa.data_model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.utils.Validator;

@Entity
@Table(name = "ACTUATOR_MODEL")
public class ActuatorModelDataModel {

  @Id
  @Column(name = "Actuator Model ID")
  private String actuatorModelID;

  @Column(name = "Actuator Model Name")
  private String actuatorModelName;

  @Column(name = "Model Path")
  private String modelPath;

  @Column(name = "Actuator Type ID")
  private String actuatorTypeID;

  @Version
  private long version;

  /**
   * Class constructor
   */
  public ActuatorModelDataModel() {
  }

  public ActuatorModelDataModel(ActuatorModel actuatorModel) {
    Validator.validateNotNull(actuatorModel, "Actuator Model");
    this.actuatorModelID = actuatorModel.getID().getID();
    this.actuatorModelName = actuatorModel.getActuatorModelName().getActuatorModelName();
    this.modelPath = actuatorModel.getID().getID();
    this.actuatorTypeID = actuatorModel.getActuatorTypeID().getID();
  }

  /**
   * Method to return the actuator model ID.
   *
   * @return
   */
  public String getActuatorModelID() {
    return this.actuatorModelID;
  }

  /**
   * Method to return the actuator model name.
   *
   * @return actuatorModelName
   */
  public String getActuatorModelName() {
    return this.actuatorModelName;
  }

  /**
   * Method to return the model path.
   *
   * @return modelPath
   */
  public String getModelPath() {
    return this.modelPath;
  }

  /**
   * Method to return the actuator type ID.
   *
   * @return actuatorTypeID
   */
  public String getActuatorTypeID() {
    return this.actuatorTypeID;
  }
}

