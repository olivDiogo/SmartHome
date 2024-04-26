package smarthome.persistence.jpa.data_model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import smarthome.domain.sensor_type.SensorType;
import smarthome.utils.Validator;

@Entity
@Table(name = "SensorType")
public class SensorTypeDataModel {

  @Id
  private String _sensorTypeID;
  @Column(name = "TypeDescription")
  private String _typeDescription;
  @Column(name = "UnitID")
  private String _unitID;
  @Version
  private long version;

  /**
   * Default constructor
   */
  public SensorTypeDataModel() {
  }

  /**
   * Constructor of the sensor type data model
   *
   * @param sensorType the sensor type
   */
  public SensorTypeDataModel(SensorType sensorType) {
    Validator.validateNotNull(sensorType, "Sensor Type");
    this._sensorTypeID = sensorType.getID().getID();
    this._typeDescription = sensorType.getName().getID();
    this._unitID = sensorType.getUnit().getID();
  }


  /**
   * Get the sensor type ID
   *
   * @return the sensor type ID
   */
  public String getSensorTypeID() {
    return this._sensorTypeID;
  }

  /**
   * Get the type description
   *
   * @return the type description
   */
  public String getTypeDescription() {
    return this._typeDescription;
  }

  /**
   * Get the unit ID
   *
   * @return the unit ID
   */
  public String getUnitID() {
    return this._unitID;
  }


}
