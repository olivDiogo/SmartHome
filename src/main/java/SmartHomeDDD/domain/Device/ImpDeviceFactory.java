package SmartHomeDDD.domain.Device;

import SmartHomeDDD.valueObject.DeviceName;
import SmartHomeDDD.valueObject.DeviceStatus;
import SmartHomeDDD.valueObject.RoomID;
import SmartHomeDDD.valueObject.DeviceTypeID;

public class ImpDeviceFactory implements DeviceFactory {
    /**
     * Creates a new {@link Device} instance using the provided room ID, device name, and device state.
     * @param roomID      The room ID where the device is located.
     * @param deviceName  The name of the device.
     * @param deviceStatus The state of the device.
     * @return a newly created Device instance
     */
    @Override
    public Device createDevice(RoomID roomID, DeviceName deviceName, DeviceStatus deviceStatus, DeviceTypeID deviceTypeID) {
        return new Device(roomID, deviceName, deviceStatus, deviceTypeID);
    }
}
