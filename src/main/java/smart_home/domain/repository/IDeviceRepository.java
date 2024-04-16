package smart_home.domain.repository;

import smart_home.ddd.IRepository;
import smart_home.domain.device.Device;
import smart_home.value_object.DeviceID;
import smart_home.value_object.RoomID;

import java.util.List;

/**
 * Represents a repository for managing Device instances.
 */
public interface IDeviceRepository extends IRepository<DeviceID, Device> {
    List<Device> getDevicesByRoomId(RoomID roomId);
}
