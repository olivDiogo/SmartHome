package SmartHomeDDD.service;

import SmartHomeDDD.assembler.DeviceAssembler;
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
    public DeviceTypeService(DeviceTypeRepository deviceTypeRepository, DeviceTypeFactory deviceTypeFactory) {
        validateDeviceTypeRepository(deviceTypeRepository);
        validateDeviceTypeFactory(deviceTypeFactory);
    }

    private void validateDeviceTypeRepository(DeviceTypeRepository deviceTypeRepository) {
        if (deviceTypeRepository == null) {
            throw new IllegalArgumentException("Please enter a valid device type repository.");
        } else {
            this._deviceTypeRepository = deviceTypeRepository;
        }
    }

    private void validateDeviceTypeFactory(DeviceTypeFactory deviceTypeFactory) {
        if (deviceTypeFactory == null) {
            throw new IllegalArgumentException("Please enter a valid device type factory.");
        } else {
            this._deviceTypeFactory = deviceTypeFactory;
        }
    }


    public DeviceType createDeviceType(TypeDescription typeDescription) {
        DeviceType deviceType = _deviceTypeFactory.createDeviceType(typeDescription);
        return deviceType;
    }

    public DeviceType saveDeviceType(DeviceType deviceType) {
        if (deviceType == null) {
            throw new IllegalArgumentException("Please enter a valid device type.");
        }
        return _deviceTypeRepository.save(deviceType);
    }

    public List<DeviceType> findAllDeviceTypes() {
        return _deviceTypeRepository.findAll();
    }

    public Optional<DeviceType> getDeviceTypeByID(DeviceTypeID deviceTypeID) {
        if (deviceTypeID == null) {
            throw new IllegalArgumentException("Please enter a valid device type ID.");
        }
        return _deviceTypeRepository.ofIdentity(deviceTypeID);
    }


}
