package smart_home.domain.device;

import smart_home.value_object.DeviceName;
import smart_home.value_object.DeviceStatus;
import smart_home.value_object.DeviceTypeID;
import smart_home.value_object.RoomID;

public class DeviceFactoryImpl implements IDeviceFactory {
    /**
     * Creates a new {@link Device} instance using the provided room ID, device name, and device state.
     *
     * @param roomID       The room ID where the device is located.
     * @param deviceName   The name of the device.
     * @param deviceStatus The state of the device.
     * @return a newly created Device instance
     */
    @Override
    public Device createDevice(RoomID roomID, DeviceName deviceName, DeviceStatus deviceStatus, DeviceTypeID deviceTypeID) {
        return new Device(roomID, deviceName, deviceStatus, deviceTypeID);
    }
}
