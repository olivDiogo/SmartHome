package smart_home.persistence.jpa.data_model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import smart_home.domain.device_type.DeviceType;

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
    validateDeviceType(deviceType);
    this._deviceTypeID = deviceType.getID().getID();
    this._deviceTypeDescription = deviceType.getDescription().getDescription();
  }

  /**
   * Validates the device type
   *
   * @param deviceType the device type
   */
  private void validateDeviceType(DeviceType deviceType) {
    if (deviceType == null) throw new IllegalArgumentException("DeviceType cannot be null");
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
