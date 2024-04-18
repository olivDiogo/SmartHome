package smart_home.service;

import smart_home.ddd.IRepository;
import smart_home.domain.device_type.DeviceType;
import smart_home.domain.device_type.IDeviceTypeFactory;
import smart_home.domain.service.IDeviceTypeService;
import smart_home.persistence.mem.DeviceTypeRepository;
import smart_home.value_object.DeviceTypeID;
import smart_home.value_object.TypeDescription;

import java.util.List;
import java.util.Optional;

public class DeviceTypeServiceImpl implements IDeviceTypeService {

    private IRepository<DeviceTypeID, DeviceType> _deviceTypeRepository;
    private IDeviceTypeFactory _deviceTypeFactory;

    /**
     * Constructor for the DeviceTypeService class.
     *
     * @param deviceTypeRepository The repository for the device type.
     * @param deviceTypeFactory    The factory for the device type.
     */
    public DeviceTypeServiceImpl(IRepository<DeviceTypeID, DeviceType> deviceTypeRepository, IDeviceTypeFactory deviceTypeFactory) {
        validateDeviceTypeRepository(deviceTypeRepository);
        validateDeviceTypeFactory(deviceTypeFactory);
    }

    /**
     * Validates the device type repository.
     *
     * @param deviceTypeRepository The repository for the device type.
     */
    private void validateDeviceTypeRepository(IRepository<DeviceTypeID, DeviceType> deviceTypeRepository) {
        if (deviceTypeRepository == null) {
            throw new IllegalArgumentException("Please enter a valid device type repository.");
        } else {
            this._deviceTypeRepository = deviceTypeRepository;
        }
    }


    /**
     * Validates the device type factory.
     *
     * @param deviceTypeFactory The factory for the device type.
     */
    private void validateDeviceTypeFactory(IDeviceTypeFactory deviceTypeFactory) {
        if (deviceTypeFactory == null) {
            throw new IllegalArgumentException("Please enter a valid device type factory.");
        } else {
            this._deviceTypeFactory = deviceTypeFactory;
        }
    }


    /**
     * Adds a device type.
     *
     * @param deviceTypeName The name of the device type.
     * @return The device type.
     */
    @Override
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
     *
     * @return A list of all device types.
     */
    @Override
    public List<DeviceType> getAllDeviceTypes() {
        return _deviceTypeRepository.findAll();
    }

    /**
     * Finds a device type by its ID.
     *
     * @param deviceTypeID The ID of the device type.
     * @return The device type.
     */
    @Override
    public Optional<DeviceType> getDeviceTypeByID(DeviceTypeID deviceTypeID) {
        if (deviceTypeID == null) {
            throw new IllegalArgumentException("Please enter a valid device type ID.");
        }
        return _deviceTypeRepository.ofIdentity(deviceTypeID);
    }
}
