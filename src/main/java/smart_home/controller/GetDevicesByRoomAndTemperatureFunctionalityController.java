package smart_home.controller;

import smart_home.domain.device_type.DeviceType;
import smart_home.domain.service.IDeviceService;
import smart_home.dto.DeviceDTO;
import smart_home.dto.RoomDTO;
import smart_home.utils.Validator;
import smart_home.value_object.RoomID;
import java.util.List;
import java.util.Map;

public class GetDevicesByRoomAndTemperatureFunctionalityController {
  private IDeviceService deviceService;

  /**
   * Constructor for GetDevicesByRoomAndTemperatureFunctionalityController.
   *
   * @param deviceService The device service.
   */
  public GetDevicesByRoomAndTemperatureFunctionalityController(IDeviceService deviceService) {
    Validator.validateNotNull(deviceService, "Device service");
    this.deviceService = deviceService;
  }

  /**
   * Get devices by type description.
   *
   * @param map The map of all devices grouped by functionality.
   * @param roomDTO The room to get the devices from.
   * @return The list of devices by type description.
   */
  public List<DeviceDTO> getDevicesByRoomAndTemperatureFunctionality(Map<DeviceType, List<DeviceDTO>> map, RoomDTO roomDTO) {
    Validator.validateNotNull(map, "A Map");
    Validator.validateNotNull(roomDTO, "Room DTO");

    List<DeviceDTO> temperatureDevicesDTO = deviceService.getDevicesByTypeDescriptionFromMap(map, "Temperature");
    List<DeviceDTO> temperatureDevicesInRoomDTO =  deviceService.getDevicesFromListByRoomId(temperatureDevicesDTO, new RoomID(roomDTO.roomId));

    return temperatureDevicesInRoomDTO;
  }
}
