package smart_home.controller;

import smart_home.domain.device_type.DeviceType;
import smart_home.dto.DeviceDTO;
import smart_home.dto.RoomDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetDevicesByRoomAndTemperatureFunctionalityController {

  /**
   * Get devices by type description.
   *
   * @param map             The map of all devices grouped by functionality.
   * @param roomDTO         The room to get the devices from.
   * @param typeDescription The description of the device type.
   * @return The list of devices by type description.
   */
  public List<DeviceDTO> getDevicesByRoomAndTypeDescription(Map<DeviceType, List<DeviceDTO>> map,
      RoomDTO roomDTO, String typeDescription) {
    List<DeviceDTO> devicesWithTemperatureSensor = getDevicesByTypeDescription(map,
        typeDescription);

    return getDevicesFromRoom(devicesWithTemperatureSensor, roomDTO);
  }

  /**
   * Get devices grouped by  temperature functionality.
   *
   * @param map The map of all devices grouped by functionality.
   * @return The list of devices grouped by temperature functionality.
   */
  private List<DeviceDTO> getDevicesByTypeDescription(Map<DeviceType, List<DeviceDTO>> map,
      String typeDescription) {

    List<DeviceDTO> devicesByTypeDescription = new ArrayList<>();

    for (Map.Entry<DeviceType, List<DeviceDTO>> entry : map.entrySet()) {

      DeviceType deviceType = entry.getKey();
      if (deviceType.getDescription().toString().equals(typeDescription)) {
        List<DeviceDTO> deviceDTOList = entry.getValue();
        devicesByTypeDescription.addAll(deviceDTOList);
      }
    }

    return devicesByTypeDescription;
  }

  /**
   * Get temperature devices from a specific room.
   *
   * @param devicesDTO The list of temperature devices.
   * @param roomDTO    The room to get the devices from.
   * @return The list of temperature devices from the room.
   */
  private List<DeviceDTO> getDevicesFromRoom(List<DeviceDTO> devicesDTO, RoomDTO roomDTO) {

    List<DeviceDTO> devicesFromRoom = new ArrayList<>();

    for (DeviceDTO deviceDTO : devicesDTO) {
      if (deviceDTO.roomID.equals(roomDTO.roomId)) {
        devicesFromRoom.add(deviceDTO);
      }
    }
    return devicesFromRoom;
  }

}
