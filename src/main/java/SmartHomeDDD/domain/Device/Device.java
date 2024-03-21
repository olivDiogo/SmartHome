package SmartHomeDDD.domain.Device;

import SmartHomeDDD.ValueObject.DeviceID;
import SmartHomeDDD.ValueObject.DeviceName;
import SmartHomeDDD.ValueObject.DeviceStatus;
import SmartHomeDDD.ValueObject.RoomID;
import SmartHomeDDD.ddd.AggregateRoot;

import java.util.UUID;

public class Device implements AggregateRoot<DeviceID> {

    private RoomID _roomID;

    private DeviceID _deviceID;

    private DeviceName _deviceName;

    private DeviceStatus _deviceStatus;


    /**
     * Constructs a new Device instance with the specified room ID, device name, and device state.
     *
     * @param roomID       The room ID where the device is located. Must not be null.
     * @param deviceName   The name of the device. Must not be null.
     * @param deviceStatus The state of the device. Must not be null.
     */
    public Device(RoomID roomID, DeviceName deviceName, DeviceStatus deviceStatus) {
        validateRoomID(roomID);
        validateDeviceName(deviceName);
        validateDeviceState(deviceStatus);
        generateDeviceID();
    }

    private void generateDeviceID() {
        _deviceID = new DeviceID(UUID.randomUUID().toString());
    }

    private void validateRoomID(RoomID roomID) {
        if (roomID == null) {
            throw new IllegalArgumentException("RoomID is required");
        } else {
            _roomID = roomID;
        }
    }

    private void validateDeviceName(DeviceName deviceName) {
        if (deviceName == null) {
            throw new IllegalArgumentException("DeviceName is required");
        } else {
            _deviceName = deviceName;
        }
    }

    private void validateDeviceState(DeviceStatus deviceState) {
        if (deviceState == null) {
            throw new IllegalArgumentException("DeviceState is required");
        } else {
            _deviceStatus = deviceState;
        }
    }

    public DeviceID getID() {
        return _deviceID;
    }

    public RoomID getRoomID() {
        return _roomID;
    }

    public DeviceName getDeviceName() {
        return _deviceName;
    }

    public DeviceStatus getDeviceStatus() {
        return _deviceStatus;
    }

    public void changeDeviceStatus(DeviceStatus deviceStatus) {
        validateDeviceState(deviceStatus);
        _deviceStatus = deviceStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o instanceof Device) {
            Device objectDevice = (Device) o;

            if (this._deviceID.equals(objectDevice._deviceID))
                return true;
        }
        return false;
    }

   @Override
   public String toString() {
        return "Device{" +
                "_roomID=" + _roomID +
                ", _deviceID=" + _deviceID +
                ", _deviceName=" + _deviceName +
                ", _deviceStatus=" + _deviceStatus +
                '}';
    }
}
