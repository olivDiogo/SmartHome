package smart_home.domain.device_type;

import smart_home.ddd.IAggregateRoot;
import smart_home.utils.Validator;
import smart_home.value_object.DeviceTypeID;
import smart_home.value_object.TypeDescription;

import java.util.UUID;

public class DeviceType implements IAggregateRoot<DeviceTypeID> {
  private final TypeDescription _deviceTypeDescription;
  private DeviceTypeID _deviceTypeID;

  /**
   * Creates a new instance of the {@link DeviceType} class.
   *
   * @param deviceTypeDescription The description of the device type.
   */
  DeviceType(TypeDescription deviceTypeDescription) {
    generateDeviceTypeID();
    Validator.validateNotNull(deviceTypeDescription, "DeviceTypeDescription");
    this._deviceTypeDescription = deviceTypeDescription;
  }

  /**
   * Creates a new instance of the {@link DeviceType} class, with the specified ID and description.
   */
  DeviceType(DeviceTypeID id, TypeDescription description) {
    _deviceTypeID = id;
    _deviceTypeDescription = description;
  }


  /** Generates a new device type ID. */
  private void generateDeviceTypeID() {
    this._deviceTypeID = new DeviceTypeID(UUID.randomUUID().toString());
  }

  /**
   * Gets the device type ID.
   *
   * @return The device type ID.
   */
  @Override
  public DeviceTypeID getID() {
    return _deviceTypeID;
  }

  /**
   * Gets the description of the device type.
   *
   * @return The description.
   */
  public TypeDescription getDescription() {
    return _deviceTypeDescription;
  }

  /**
   * Compares this instance with another instance.
   *
   * @param object is the object to be compared.
   * @return true if the instances are equal, false otherwise.
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof DeviceType deviceType) {
      return this._deviceTypeID.equals(deviceType.getID());
    }
    return false;
  }

  /**
   * Generates a hash code for the device type.
   *
   * @return The hash code.
   */
  @Override
  public int hashCode() {
    return this._deviceTypeID.hashCode();
  }

  /**
   * Returns a string representation of the device type.
   *
   * @return The string representation.
   */
  public String toString() {
    return "Device Type:  Device Description= "
        + _deviceTypeDescription.getDescription()
        + " ID= "
        + _deviceTypeID.getID();
  }
}
