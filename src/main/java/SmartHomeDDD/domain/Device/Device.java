package SmartHomeDDD.domain.Device;

import SmartHomeDDD.valueObject.*;
import SmartHomeDDD.ddd.AggregateRoot;

import java.util.UUID;

public class Device implements AggregateRoot<DeviceID> {

    private RoomID _roomID;

    private DeviceID _deviceID;

    private DeviceName _deviceName;

    private DeviceStatus _deviceStatus;

    private DeviceTypeID _deviceTypeID;


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
     * Checks if this Device instance is equal to another object.
     *
     * @param object The object to compare.
     * @return true if the objects are equal, false if they are different.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof Device) {
            Device objectDevice = (Device) object;
            return _deviceID.toString().equals(objectDevice._deviceID.toString());
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
        return "Device{" +
                "_roomID=" + _roomID +
                ", _deviceID=" + _deviceID +
                ", _deviceName=" + _deviceName +
                ", _deviceStatus=" + _deviceStatus +
                '}';
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
}
