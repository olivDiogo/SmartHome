package smart_home.domain.device;

import smart_home.value_object.*;

public class DeviceFactoryImpl implements IDeviceFactory {
    /**
     * Creates a new {@link Device} instance using the provided room ID, device name, and device state.
     *
     * @return a newly created Device instance without ID
     */
    @Override
    public Device createDevice(RoomID roomID, DeviceName deviceName, DeviceStatus deviceStatus, DeviceTypeID deviceTypeID) {
        return new Device(roomID, deviceName, deviceStatus, deviceTypeID);
    }

    /**
     * Creates and returns a new {@link Device} instance with the provided device ID, room ID, device name, device state, and device type ID.
     *
     * @return a newly created Device instance with ID
     */

    @Override
    public Device createDevice(DeviceID deviceID, RoomID roomID, DeviceName deviceName, DeviceStatus deviceStatus, DeviceTypeID deviceTypeID) {
        return new Device(deviceID, roomID, deviceName, deviceStatus, deviceTypeID);
    }
}
