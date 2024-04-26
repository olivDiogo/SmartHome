package smarthome.domain.repository;

import smarthome.ddd.IRepository;
import smarthome.domain.device.Device;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.RoomID;

import java.util.List;

/**
 * Represents a repository for managing Device instances.
 */
public interface IDeviceRepository extends IRepository<DeviceID, Device> {
    List<Device> findBy_roomID(RoomID roomId);

    Device update(Device device);
}
