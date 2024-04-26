package smarthome.domain.device;

import java.util.UUID;
import smarthome.ddd.IAggregateRoot;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;
import smarthome.utils.Validator;

public class Device implements IAggregateRoot<DeviceID> {

  private final RoomID _roomID;
  private final DeviceName _deviceName;
  private final DeviceTypeID _deviceTypeID;
  private DeviceID _deviceID;
  private DeviceStatus _deviceStatus;


  /**
   * Constructs a new Device instance with the specified room ID, device name, and device state.
   *
   * @param roomID       The room ID where the device is located. Must not be null.
   * @param deviceName   The name of the device. Must not be null.
   * @param deviceStatus The state of the device. Must not be null.
   */
  Device(RoomID roomID, DeviceName deviceName, DeviceStatus deviceStatus,
      DeviceTypeID deviceTypeID) {
    Validator.validateNotNull(roomID, "RoomID");
    Validator.validateNotNull(deviceName, "DeviceName");
    Validator.validateNotNull(deviceStatus, "DeviceStatus");
    Validator.validateNotNull(deviceTypeID, "DeviceTypeID");
    this._roomID = roomID;
    this._deviceName = deviceName;
    this._deviceStatus = deviceStatus;
    this._deviceTypeID = deviceTypeID;
    generateDeviceID();
  }

  /**
   * Constructs a new Device instance with the specified device ID, room ID, device name, device
   * state, and device type ID.
   *
   * @param deviceID     The device ID. Must not be null.
   * @param roomID       The room ID where the device is located. Must not be null.
   * @param deviceName   The name of the device. Must not be null.
   * @param deviceStatus The state of the device. Must not be null.
   * @param deviceTypeID The device type ID. Must not be null.
   */
  Device(DeviceID deviceID, RoomID roomID, DeviceName deviceName, DeviceStatus deviceStatus,
      DeviceTypeID deviceTypeID) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(roomID, "RoomID");
    Validator.validateNotNull(deviceName, "DeviceName");
    Validator.validateNotNull(deviceStatus, "DeviceStatus");
    Validator.validateNotNull(deviceTypeID, "DeviceTypeID");

    this._deviceID = deviceID;
    this._roomID = roomID;
    this._deviceName = deviceName;
    this._deviceStatus = deviceStatus;
    this._deviceTypeID = deviceTypeID;
  }

  /**
   * Generates a new DeviceID object.
   */
  private void generateDeviceID() {
    _deviceID = new DeviceID(UUID.randomUUID().toString());
  }


  /**
   * Method to return deviceID
   *
   * @return _deviceID
   */
  public DeviceID getID() {
    return _deviceID;
  }

  /**
   * Method to return roomID
   *
   * @return _roomID
   */
  public RoomID getRoomID() {
    return _roomID;
  }


  /**
   * Method to return deviceName
   *
   * @return _deviceName
   */
  public DeviceName getDeviceName() {
    return _deviceName;
  }

  /**
   * Method to return deviceStatus
   *
   * @return _deviceStatus
   */
  public DeviceStatus getDeviceStatus() {
    return _deviceStatus;
  }

  /**
   * Method to return deviceTypeID
   *
   * @return _deviceTypeID
   */
  public DeviceTypeID getDeviceTypeID() {
    return _deviceTypeID;
  }

  /**
   * Compares this instance with another instance.
   *
   * @param object is the object to be compared.
   * @return true if the instances are equal, false otherwise.
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof Device device) {
      return _deviceID.equals(device.getID());
    }
    return false;
  }

  /**
   * Method to return the values of the object in a string.
   *
   * @return the values of the object in a string.
   */
  @Override
  public String toString() {
    return "Device:" +
        "roomID=" + _roomID +
        ", deviceID=" + _deviceID +
        ", deviceName=" + _deviceName +
        ", deviceStatus=" + _deviceStatus;
  }

  /**
   * Method to deactivate the device
   *
   * @return the status of the device
   */
  public DeviceStatus deactivateDevice() {
    _deviceStatus = new DeviceStatus(false);
    return _deviceStatus;
  }

  /**
   * Generates a hash code for the Device instance.
   *
   * @return The hash code.
   */
  @Override
  public int hashCode() {
    return this._deviceID.hashCode();
  }

}
