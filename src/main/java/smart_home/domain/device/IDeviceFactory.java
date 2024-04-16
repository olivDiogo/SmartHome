package smart_home.domain.device;

import smart_home.value_object.*;

public interface IDeviceFactory {

    /**
     * Creates and returns a new {@link Device} instance with the provided room ID, device name, and device state.
     *
     * @param roomID      The room ID where the device is located.
     * @param deviceName  The name of the device.
     * @param deviceState The state of the device.
     * @return a newly created Device instance without ID
     */
    Device createDevice(RoomID roomID, DeviceName deviceName, DeviceStatus deviceState, DeviceTypeID deviceTypeID);

    /**
     * Creates and returns a new {@link Device} instance with the provided device ID, room ID, device name, device state, and device type ID.
     * @param deviceID
     * @param roomID
     * @param deviceName
     * @param deviceState
     * @param deviceTypeID
     * @return a newly created Device instance with ID
     */

    Device createDevice(DeviceID deviceID, RoomID roomID, DeviceName deviceName, DeviceStatus deviceState, DeviceTypeID deviceTypeID);
}