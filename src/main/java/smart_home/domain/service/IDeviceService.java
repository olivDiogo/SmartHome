package smart_home.domain.service;

import smart_home.ddd.IService;
import smart_home.domain.device.Device;
import smart_home.domain.device_type.DeviceType;
import smart_home.dto.DeviceDTO;
import smart_home.dto.RoomDTO;
import smart_home.value_object.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IDeviceService extends IService {

  /**
   * Adds a new device to the repository.
   *
   * @param roomID
   * @param deviceName
   * @param deviceStatus
   * @param deviceTypeID
   * @return the device that was added.
   */
  Device addDevice(RoomID roomID, DeviceName deviceName, DeviceStatus deviceStatus,
      DeviceTypeID deviceTypeID);

  /**
   * Deactivates a device by its ID.
   *
   * @param deviceID
   * @return the device that was deactivated.
   */
  Device deactivateDeviceByID(DeviceID deviceID);

  /**
   * Get all devices in the repository.
   *
   * @return a list of all devices.
   */
  List<Device> getAllDevices();

  /**
   * Get a device by its ID.
   *
   * @param deviceId
   * @return the device with the provided ID.
   */
  Optional<Device> getDeviceByID(DeviceID deviceId);

  /**
   * Get all devices in a room.
   *
   * @param roomId
   * @return a list of all devices in the room.
   */
  List<Device> getDevicesByRoomId(RoomID roomId);

  /**
   * Get devices grouped by temperature functionality from a Map.
   *
   * @param deviceMap The map of all devices grouped by functionality.
   * @return The list of devices grouped by temperature functionality.
   */
  List<DeviceDTO> getDevicesByTypeDescriptionFromMap(Map<DeviceType, List<DeviceDTO>> deviceMap, String typeDescription);

  /**
   * Filters the devices in a list by their room location.
   *
   * @param devicesDTO The list of devices to filter.
   * @param roomID The room to filter by.
   * @return The list of devices in the room.
   */
  List<DeviceDTO> getDevicesFromListByRoomId(List<DeviceDTO> devicesDTO, RoomID roomID);
}
