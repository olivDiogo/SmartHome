package smarthome.domain.repository;

import java.util.List;
import smarthome.ddd.IRepository;
import smarthome.domain.device.Device;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.RoomID;

/**
 * Represents a repository for managing Device instances.
 */
public interface IDeviceRepository extends IRepository<DeviceID, Device> {

  List<Device> findBy_roomID(RoomID roomId);

  Device update(Device device);
}
