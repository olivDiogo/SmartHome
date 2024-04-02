package SmartHomeDDD.repository;

import SmartHomeDDD.domain.Device.DeviceRepo;
import SmartHomeDDD.valueObject.DeviceID;
import SmartHomeDDD.valueObject.RoomID;
import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.Device.Device;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DeviceRepository implements DeviceRepo {

    /**
     * Map to store the device data.
     */
    private final Map<DeviceID, Device> _deviceData = new LinkedHashMap<>();

    /**
     * Method to save a domain entity.
     * @param device is the domain entity to be saved.
     * @return the saved domain entity.
     */
    @Override
    public Device save(Device device) {
        if (device == null) {
            throw new IllegalArgumentException("Device cannot be null.");
        } else if (containsOfIdentity(device.getID())) {
            throw new IllegalArgumentException("Device already exists.");
        } else {
            _deviceData.put(device.getID(), device);
        }
        return device;
    }

    /**
     * Method to find all domain entities.
     * @return
     */
    @Override
    public List<Device> findAll() {
        List<Device> allDevices = _deviceData.values().stream().toList();
        return allDevices;
    }

    /**
     * Method to find a domain entity by its unique identifier.
     * @param deviceID is the unique identifier of the domain entity.
     * @return
     */
    @Override
    public Optional<Device> ofIdentity(DeviceID deviceID) {
        Optional<Device> device = Optional.ofNullable(_deviceData.get(deviceID));
        return device;
    }

    /**
     * Method to check if a domain entity exists by its unique identifier.
     * @param deviceID is the unique identifier of the domain entity.
     * @return
     */
    @Override
    public boolean containsOfIdentity(DeviceID deviceID) {
        return _deviceData.containsKey(deviceID);
    }

    /**
     * Method to find all devices in a room.
     * @param roomId is the unique identifier of the room.
     * @return a list of devices in the room.
     */

    @Override
    public List<Device> getDevicesByRoomId(RoomID roomId) {
        List<Device> devices = _deviceData.values().stream().filter(device -> device.getRoomID().equals(roomId)).toList();
        return devices;
    }
}
