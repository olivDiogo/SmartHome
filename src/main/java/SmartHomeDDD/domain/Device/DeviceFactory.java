package SmartHomeDDD.domain.Device;

import SmartHomeDDD.valueObject.DeviceName;
import SmartHomeDDD.valueObject.DeviceStatus;
import SmartHomeDDD.valueObject.DeviceTypeID;
import SmartHomeDDD.valueObject.RoomID;

public interface DeviceFactory {

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
