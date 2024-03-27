package SmartHomeDDD.repository;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.DeviceType.DeviceType;
import SmartHomeDDD.valueObject.DeviceTypeID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.*;

/**
 * This class represents a repository for storing and managing device types.
 * It implements the Repository interface for handling DeviceTypeID and DeviceType objects.
 */
public class DeviceTypeRepository implements Repository<DeviceTypeID, DeviceType> {

    // Data structure to store device types
    private final Map<DeviceTypeID, DeviceType> _deviceTypeData = new LinkedHashMap<>();

    /**
     * Saves a device type to the repository.
     *
     * @param deviceType The device type to save.
     * @return The saved device type.
     * @throws IllegalArgumentException If the deviceType is null or already exists in the repository.
     */
    @Override
    public DeviceType save(DeviceType deviceType) {
        if (deviceType == null) {
            throw new IllegalArgumentException("DeviceType cannot be null.");
        } else if (containsOfIdentity(deviceType.getID())) {
            throw new IllegalArgumentException("DeviceType already exists.");
        } else {
            _deviceTypeData.put(deviceType.getID(), deviceType);
        }
        return deviceType;
    }

    /**
     * Retrieves all device types from the repository.
     *
     * @return A list containing all device types stored in the repository.
     */
    @Override
    public List<DeviceType> findAll() {
        List<DeviceType> allDeviceTypes = new ArrayList<>(_deviceTypeData.values());
        return allDeviceTypes;
    }

    /**
     * Retrieves a device type from the repository based on its identity.
     *
     * @param deviceTypeID The identity of the device type to retrieve.
     * @return An Optional containing the retrieved device type, or empty if not found.
     */
    @Override
    public Optional<DeviceType> ofIdentity(DeviceTypeID deviceTypeID) {
        return Optional.ofNullable(_deviceTypeData.get(deviceTypeID));
    }

    /**
     * Checks if a device type with the given identity exists in the repository.
     *
     * @param deviceTypeID The identity of the device type to check.
     * @return true if the device type exists, false otherwise.
     */
    @Override
    public boolean containsOfIdentity(DeviceTypeID deviceTypeID) {
        return _deviceTypeData.containsKey(deviceTypeID);
    }
}


