package smartHome.domain.repository;

import smartHome.ddd.IRepository;
import smartHome.domain.device.Device;
import smartHome.valueObject.DeviceID;
import smartHome.valueObject.RoomID;

import java.util.List;

/**
 * Represents a repository for managing Device instances.
 */
public interface IDeviceRepository extends IRepository<DeviceID, Device> {
    List<Device> getDevicesByRoomId(RoomID roomId);
}
