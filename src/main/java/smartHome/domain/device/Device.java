package smartHome.domain.device;

import smartHome.ddd.IAggregateRoot;
import smartHome.valueObject.*;

import java.util.UUID;

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
    Device(RoomID roomID, DeviceName deviceName, DeviceStatus deviceStatus, DeviceTypeID deviceTypeID) {
        validateRoomID(roomID);
        this._roomID = roomID;
        validateDeviceName(deviceName);
        this._deviceName = deviceName;
        validateDeviceState(deviceStatus);
        this._deviceStatus = deviceStatus;
        validateDeviceTypeID(deviceTypeID);
        this._deviceTypeID = deviceTypeID;
        generateDeviceID();
    }

    /**
     * Generates a new DeviceID object.
     */
    private void generateDeviceID() {
        _deviceID = new DeviceID(UUID.randomUUID().toString());
    }

    /**
     * Validates the provided RoomID object.
     *
     * @param roomID The RoomID to be validated.
     */
    private void validateRoomID(RoomID roomID) {
        if (roomID == null) {
            throw new IllegalArgumentException("RoomID is required");
        }
    }

    /**
     * Validates the provided DeviceName object.
     *
     * @param deviceName The DeviceName to be validated.
     */
    private void validateDeviceName(DeviceName deviceName) {
        if (deviceName == null) {
            throw new IllegalArgumentException("DeviceName is required");
        }
    }


    /**
     * Validates the provided DeviceStatus object.
     *
     * @param deviceState The DeviceStatus to be validated.
     */
    private void validateDeviceState(DeviceStatus deviceState) {
        if (deviceState == null) {
            throw new IllegalArgumentException("DeviceState is required");
        }
    }

    /**
     * Validates the provided DeviceTypeID object.
     *
     * @param deviceTypeID The DeviceTypeID to be validated.
     */
    private void validateDeviceTypeID(DeviceTypeID deviceTypeID) {
        if (deviceTypeID == null) {
            throw new IllegalArgumentException("DeviceTypeID is required");
        }
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
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return this._deviceID.hashCode();
    }

}
