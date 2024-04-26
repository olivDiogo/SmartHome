package smarthome.domain.repository;

import smarthome.ddd.IRepository;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.value_object.DeviceTypeID;

public interface IDeviceTypeRepository extends IRepository<DeviceTypeID, DeviceType> {
}
