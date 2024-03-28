package SmartHomeDDD.service;

import SmartHomeDDD.domain.DeviceType.DeviceType;
import SmartHomeDDD.domain.DeviceType.DeviceTypeFactory;
import SmartHomeDDD.repository.DeviceTypeRepository;
import SmartHomeDDD.valueObject.DeviceTypeID;
import SmartHomeDDD.valueObject.TypeDescription;

import java.util.List;
import java.util.Optional;

public class DeviceTypeService {

    private DeviceTypeRepository _deviceTypeRepository;
    private DeviceTypeFactory _deviceTypeFactory;

    /**
     * Constructor for the DeviceTypeService class.
     * @param deviceTypeRepository The repository for the device type.
     * @param deviceTypeFactory The factory for the device type.
     */
    public DeviceTypeService(DeviceTypeRepository deviceTypeRepository, DeviceTypeFactory deviceTypeFactory) {
        validateDeviceTypeRepository(deviceTypeRepository);
        validateDeviceTypeFactory(deviceTypeFactory);
    }

    /**
     * Validates the device type repository.
     * @param deviceTypeRepository The repository for the device type.
     */
    private void validateDeviceTypeRepository(DeviceTypeRepository deviceTypeRepository) {
        if (deviceTypeRepository == null) {
            throw new IllegalArgumentException("Please enter a valid device type repository.");
        } else {
            this._deviceTypeRepository = deviceTypeRepository;
        }
    }


    /**
     * Validates the device type factory.
     * @param deviceTypeFactory The factory for the device type.
     */
    private void validateDeviceTypeFactory(DeviceTypeFactory deviceTypeFactory) {
        if (deviceTypeFactory == null) {
            throw new IllegalArgumentException("Please enter a valid device type factory.");
        } else {
            this._deviceTypeFactory = deviceTypeFactory;
        }
    }


    /**
     * Adds a device type.
     * @param deviceTypeName The name of the device type.
     * @return The device type.
     */
    public DeviceType addDeviceType(TypeDescription deviceTypeName) {
        if (deviceTypeName == null) {
            throw new IllegalArgumentException("Please enter a valid device type.");
        }
        DeviceType deviceType = _deviceTypeFactory.createDeviceType(deviceTypeName);
        _deviceTypeRepository.save(deviceType);

        return deviceType;

    }

    /**
     * Finds all device types.
     * @return A list of all device types.
     */
    public List<DeviceType> findAllDeviceTypes() {
        return _deviceTypeRepository.findAll();
    }

    /**
     * Finds a device type by its ID.
     * @param deviceTypeID The ID of the device type.
     * @return The device type.
     */
    public Optional<DeviceType> getDeviceTypeByID(DeviceTypeID deviceTypeID) {
        if (deviceTypeID == null) {
            throw new IllegalArgumentException("Please enter a valid device type ID.");
        }
        return _deviceTypeRepository.ofIdentity(deviceTypeID);
    }

}
