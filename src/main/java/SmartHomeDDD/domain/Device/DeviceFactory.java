package SmartHomeDDD.domain.Device;

import SmartHomeDDD.ValueObject.DeviceID;
import SmartHomeDDD.ValueObject.DeviceName;
import SmartHomeDDD.ValueObject.DeviceStatus;
import SmartHomeDDD.ValueObject.RoomID;

public interface DeviceFactory {

    /**
     * Creates and returns a new {@link Device} instance with the provided room ID, device name, and device state.
     *
     * @param roomID      The room ID where the device is located.
     * @param deviceName  The name of the device.
     * @param deviceState The state of the device.
     * @return a newly created Device instance
     */
    public Device createDevice(RoomID roomID, DeviceName deviceName, DeviceStatus deviceState);
}
