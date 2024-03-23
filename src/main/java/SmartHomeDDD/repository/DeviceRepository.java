package SmartHomeDDD.repository;

import SmartHomeDDD.ValueObject.DeviceID;
import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.Device.Device;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DeviceRepository implements Repository<DeviceID, Device> {

    /**
     * Map to store the device data.
     */
    private final Map<DeviceID, Device> _deviceData = new LinkedHashMap<>();

    /**
     * Method to save a domain entity.
     * @param device is the domain entity to be saved.
     * @return
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
}
