package smart_home.service;

import smart_home.ddd.IRepository;
import smart_home.domain.device_type.DeviceType;
import smart_home.domain.device_type.IDeviceTypeFactory;
import smart_home.domain.service.IDeviceTypeService;
import smart_home.utils.Validator;
import smart_home.value_object.DeviceTypeID;
import smart_home.value_object.TypeDescription;

import java.util.List;
import java.util.Optional;

public class DeviceTypeServiceImpl implements IDeviceTypeService {

  private IRepository<DeviceTypeID, DeviceType> deviceTypeRepository;
  private IDeviceTypeFactory deviceTypeFactory;

  /**
   * Constructor for the DeviceTypeService class.
   *
   * @param deviceTypeRepository The repository for the device type.
   * @param deviceTypeFactory    The factory for the device type.
   */
  public DeviceTypeServiceImpl(IRepository<DeviceTypeID, DeviceType> deviceTypeRepository,
      IDeviceTypeFactory deviceTypeFactory) {
    Validator.validateNotNull(deviceTypeRepository, "Device type repository");
    this.deviceTypeFactory = deviceTypeFactory;
    Validator.validateNotNull(deviceTypeFactory, "Device type factory");
    this.deviceTypeRepository = deviceTypeRepository;
  }

  /**
   * Adds a device type.
   *
   * @param deviceTypeName The name of the device type.
   * @return The device type.
   */
  @Override
  public DeviceType addDeviceType(TypeDescription deviceTypeName) {
    Validator.validateNotNull(deviceTypeName, "Device type");

    DeviceType deviceType = deviceTypeFactory.createDeviceType(deviceTypeName);
    deviceTypeRepository.save(deviceType);

    return deviceType;

  }

  /**
   * Finds all device types.
   *
   * @return A list of all device types.
   */
  @Override
  public List<DeviceType> getAllDeviceTypes() {
    return deviceTypeRepository.findAll();
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
    return deviceTypeRepository.ofIdentity(deviceTypeID);
  }
}
