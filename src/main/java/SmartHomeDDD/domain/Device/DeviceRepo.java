package SmartHomeDDD.domain.Device;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.valueObject.DeviceID;
import SmartHomeDDD.valueObject.RoomID;

import java.util.List;

public interface DeviceRepo extends Repository<DeviceID, Device> {
    List<Device> getDevicesByRoomId(RoomID roomId);
}
