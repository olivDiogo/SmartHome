package smarthome.persistence.jpa.data_model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import smarthome.domain.device_type.DeviceType;
import smarthome.utils.Validator;

@Entity
@Table(name = "Device_Type")
public class DeviceTypeDataModel {

  @Id
  private String deviceTypeID;
  private String deviceTypeDescription;
  @Version
  private long version;

  /**
   * Default constructor
   */
  public DeviceTypeDataModel() {
  }

  /**
   * Constructor of the device type data model
   *
   * @param deviceType the device type
   */
  public DeviceTypeDataModel(DeviceType deviceType) {
    Validator.validateNotNull(deviceType, "Device Type");
    this.deviceTypeID = deviceType.getID().getID();
    this.deviceTypeDescription = deviceType.getDescription().getDescription();
  }

  /**
   * Get the device type ID
   *
   * @return the device type ID
   */
  public String getDeviceTypeID() {
    return this.deviceTypeID;
  }

  /**
   * Get the type description
   *
   * @return the type description
   */
  public String getDeviceTypeDescription() {
    return this.deviceTypeDescription;
  }
}
