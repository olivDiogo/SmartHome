package smart_home.domain.service;

import smart_home.ddd.IService;
import smart_home.domain.device.Device;
import smart_home.value_object.*;

import java.util.List;
import java.util.Optional;

public interface IDeviceService extends IService {

    /**
     * Adds a new device to the repository.
     *
     * @param roomID
     * @param deviceName
     * @param deviceStatus
     * @param deviceTypeID
     * @return the device that was added.
     */
    Device addDevice(RoomID roomID, DeviceName deviceName, DeviceStatus deviceStatus, DeviceTypeID deviceTypeID);

    /**
     * Deactivates a device by its ID.
     *
     * @param deviceID
     * @return the device that was deactivated.
     */
    Device deactivateDeviceByID(DeviceID deviceID);

    /**
     * Get all devices in the repository.
     * @return a list of all devices.
     */
    List<Device> getAllDevices();

    /**
     * Get a device by its ID.
     * @param deviceId
     * @return the device with the provided ID.
     */
    Optional<Device> getDeviceByID(DeviceID deviceId);

    /**
     * Get all devices in a room.
     * @param roomId
     * @return a list of all devices in the room.
     */
    List<Device> getDevicesByRoomId(RoomID roomId);
}
