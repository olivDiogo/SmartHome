package smartHome.domain.repository;

import smartHome.ddd.IRepository;
import smartHome.domain.deviceType.DeviceType;
import smartHome.valueObject.DeviceTypeID;

public interface IDeviceTypeRepository extends IRepository<DeviceTypeID, DeviceType>{
}
