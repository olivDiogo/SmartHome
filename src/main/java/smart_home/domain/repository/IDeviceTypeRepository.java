package smart_home.domain.repository;

import smart_home.ddd.IRepository;
import smart_home.domain.device_type.DeviceType;
import smart_home.value_object.DeviceTypeID;

public interface IDeviceTypeRepository extends IRepository<DeviceTypeID, DeviceType> {
}
