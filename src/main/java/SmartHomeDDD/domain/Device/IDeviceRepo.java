package SmartHomeDDD.domain.Device;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.valueObject.DeviceID;
import SmartHomeDDD.valueObject.RoomID;

import java.util.List;

/**
 * Represents a repository for managing Device instances.
 */
public interface IDeviceRepo extends Repository<DeviceID, Device> {
    List<Device> getDevicesByRoomId(RoomID roomId);
}
