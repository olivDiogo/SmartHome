package smarthome.utils.dto;

import smarthome.ddd.IDTO;

public class DeviceTypeDTO implements IDTO {

  public final String deviceTypeID;
  public final String description;

  /**
   * Constructs a new DeviceTypeDTO object.
   *
   * @param deviceTypeID          The description of the device type.
   * @param description The description of the device type.
   */
  public DeviceTypeDTO(String deviceTypeID, String description) {
    this.deviceTypeID = deviceTypeID;
    this.description = description;
  }

  /**
   * Returns a string representation of the DeviceTypeDTO object.
   */
  @Override
  public String toString() {
    return deviceTypeID + " " + description;
  }
}
