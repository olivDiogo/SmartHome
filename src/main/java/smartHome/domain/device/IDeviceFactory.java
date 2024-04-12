package smartHome.domain.device;

import smartHome.valueObject.DeviceName;
import smartHome.valueObject.DeviceStatus;
import smartHome.valueObject.DeviceTypeID;
import smartHome.valueObject.RoomID;

public interface IDeviceFactory {

    /**
     * Creates and returns a new {@link Device} instance with the provided room ID, device name, and device state.
     *
     * @param roomID      The room ID where the device is located.
     * @param deviceName  The name of the device.
     * @param deviceState The state of the device.
     * @return a newly created Device instance
     */
    Device createDevice(RoomID roomID, DeviceName deviceName, DeviceStatus deviceState, DeviceTypeID deviceTypeID);
}
