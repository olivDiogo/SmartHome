package SmartHomeDDD.domain.Device;

import SmartHomeDDD.ValueObject.DeviceName;
import SmartHomeDDD.ValueObject.DeviceStatus;
import SmartHomeDDD.ValueObject.RoomID;

public class ImpDeviceFactory implements DeviceFactory {
    /**
     * Creates a new {@link Device} instance using the provided room ID, device name, and device state.
     * @param roomID      The room ID where the device is located.
     * @param deviceName  The name of the device.
     * @param deviceStatus The state of the device.
     * @return a newly created Device instance
     */
    @Override
    public Device createDevice(RoomID roomID, DeviceName deviceName, DeviceStatus deviceStatus) {
        return new Device(roomID, deviceName, deviceStatus);
    }
}
