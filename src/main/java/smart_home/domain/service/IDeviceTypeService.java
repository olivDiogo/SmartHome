package smart_home.domain.service;

import smart_home.ddd.IService;
import smart_home.domain.device_type.DeviceType;
import smart_home.value_object.DeviceTypeID;
import smart_home.value_object.TypeDescription;

import java.util.List;
import java.util.Optional;

public interface IDeviceTypeService extends IService {

    /**
     * Adds a new device type to the repository.
     *
     * @param deviceTypeName the name of the device type.
     * @return the device type that was added.
     */
    DeviceType addDeviceType(TypeDescription deviceTypeName);

    /**
     * Gets all device types in the repository.
     * @return a list of all device types.
     */
    List<DeviceType> getAllDeviceTypes();

    /**
     * Gets a device type by its ID.
     * @param deviceTypeID the ID of the device type.
     * @return the device type with the provided ID.
     */
    Optional<DeviceType> getDeviceTypeByID(DeviceTypeID deviceTypeID);
}
