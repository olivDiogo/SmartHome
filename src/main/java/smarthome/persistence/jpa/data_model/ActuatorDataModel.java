package smarthome.persistence.jpa.data_model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import smarthome.domain.actuator.IActuator;
import smarthome.utils.Validator;

@Entity
@Table(name = "Actuator")
public class ActuatorDataModel {

  @Id
  private String actuatorID;
  @Column(name = "deviceID")
  private String deviceid;
  @Column(name = "modelPath")
  private String modelPath;
  @Column(name = "actuatorTypeID")
  private String actuatorTypeID;
  @Column(name = "actuatorName")
  private String actuatorName;
  @Column(name = "integerLowerBond")
  private String integerLowerBond;
  @Column(name = "integerUpperBond")
  private String integerUpperBond;
  @Column(name = "decimalLowerBond")
  private String decimalLowerBond;
  @Column(name = "decimalUpperBond")
  private String decimalUpperBond;
  @Version
  private long version;


  public ActuatorDataModel() {
  }

  /**
   * Class constructor
   */
  public ActuatorDataModel(IActuator actuator) {
    Validator.validateNotNull(actuator, "Actuator");
    setGenericActuatorParameters(actuator);

  }

  /**
   * Set the generic actuator parameters to the data model
   *
   * @param actuator
   */
  //Setters
  public void setGenericActuatorParameters(IActuator actuator) {
    this.actuatorID = actuator.getID().getID();
    this.deviceid = actuator.getDeviceID().getID();
    this.modelPath = actuator.getModelPath().getID();
    this.actuatorTypeID = actuator.getActuatorTypeID().getID();
    this.actuatorName = actuator.getName().getActuatorName();
  }

  /**
   * Method to return the integer lower bond
   */
  public String getIntegerLowerBond() {
    return this.integerLowerBond;
  }

  /**
   * Set the integer lower bond from actuators that have this specification
   *
   * @param integerLowerBond
   */

  public void setIntegerLowerBond(int integerLowerBond) {
    this.integerLowerBond = String.valueOf(integerLowerBond);
  }

  /**
   * Method to return the integer upper bond
   */
  public String getIntegerUpperBond() {
    return this.integerUpperBond;
  }

  /**
   * Set the integer upper bond from actuators that have this specification
   *
   * @param integerUpperBond
   */
  public void setIntegerUpperBond(int integerUpperBond) {
    this.integerUpperBond = String.valueOf(integerUpperBond);
  }
  // This section is for getter methods

  /**
   * Method to return the decimal lower bond
   */
  public String getDecimalLowerBond() {
    return this.decimalLowerBond;
  }

  /**
   * set de lower decimal bond from actuators that have this specification
   *
   * @param decimalLowerBond
   */
  public void setDecimalLowerBond(double decimalLowerBond) {
    this.decimalLowerBond = String.valueOf(decimalLowerBond);
  }

  /**
   * Method to return the decimal upper bond
   */
  public String getDecimalUpperBond() {
    return this.decimalUpperBond;
  }

  /**
   * Set the upper decimal bond from actuators that have this specification
   *
   * @param decimalUpperBond
   */
  public void setDecimalUpperBond(double decimalUpperBond) {
    this.decimalUpperBond = String.valueOf(decimalUpperBond);
  }

  /**
   * Method to return the actuator ID.
   *
   * @return the actuator ID
   */
  public String getActuatorID() {
    return this.actuatorID;
  }

  /**
   * Method to return the device ID.
   *
   * @return the device ID
   */
  public String getDeviceID() {
    return this.deviceid;
  }

  /**
   * Method to return the model path.
   *
   * @return the model path
   */
  public String getModelPath() {
    return this.modelPath;
  }

  /**
   * Method to return the actuator type ID.
   *
   * @return the actuator type ID
   */
  public String getActuatorTypeID() {
    return this.actuatorTypeID;
  }

  /**
   * Method to return the actuator name.
   *
   * @return the actuator name
   */
  public String getActuatorName() {
    return this.actuatorName;
  }

  @Override
  public String toString() {
    return "ActuatorDataModel{" +
        "actuatorID='" + actuatorID + '\'' +
        ", deviceID='" + deviceid + '\'' +
        ", modelPath='" + modelPath + '\'' +
        ", actuatorTypeID='" + actuatorTypeID + '\'' +
        ", actuatorName='" + actuatorName + '\'' +
        ", integerLowerBond='" + integerLowerBond + '\'' +
        ", integerUpperBond='" + integerUpperBond + '\'' +
        ", decimalLowerBond='" + decimalLowerBond + '\'' +
        ", decimalUpperBond='" + decimalUpperBond + '\'' +
        '}';
  }

}
