package smart_home.persistence.jpa.data_model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import smart_home.domain.device_type.DeviceType;
import smart_home.utils.Validator;

@Entity
@Table(name = "DeviceType")
public class DeviceTypeDataModel {
  @Id private String _deviceTypeID;

  @Column(name = "TypeDescription")
  private String _deviceTypeDescription;

  /** Default constructor */
  public DeviceTypeDataModel() {}

  /**
   * Constructor of the device type data model
   *
   * @param deviceType the device type
   */
  public DeviceTypeDataModel(DeviceType deviceType) {
    Validator.validateNotNull(deviceType, "Device Type");
    this._deviceTypeID = deviceType.getID().getID();
    this._deviceTypeDescription = deviceType.getDescription().getDescription();
  }

  /**
   * Get the device type ID
   *
   * @return the device type ID
   */
  public String getDeviceTypeID() {
    return this._deviceTypeID;
  }

  /**
   * Get the type description
   *
   * @return the type description
   */
  public String getDeviceTypeDescription() {
    return this._deviceTypeDescription;
  }
}
