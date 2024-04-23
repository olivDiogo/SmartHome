package smart_home.controller;
//
import smart_home.domain.device_type.DeviceType;
import smart_home.dto.DeviceDTO;
import smart_home.dto.RoomDTO;
import smart_home.persistence.mem.LogRepository;
import smart_home.service.LogServiceImpl;
import smart_home.utils.Validator;
import smart_home.value_object.DeviceID;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetMaxInstTempDiffBetweenDeviceAndOutsideController {

    private LogServiceImpl _logService;
    private LogRepository _logRepository;

    /**
     * Constructor for GetMaxInstTempDiffBetweenDeviceAndOutsideController.
     *
     * @param logService The log service.
     * @param logRepository The log repository.
     */
    public GetMaxInstTempDiffBetweenDeviceAndOutsideController(LogServiceImpl logService, LogRepository logRepository) {
        Validator.validateNotNull(logService, "Log Service");
        Validator.validateNotNull(logRepository, "Log Repository");

        this._logService = logService;
        this._logRepository = logRepository;
    }

    public List<DeviceDTO> getDevicesByTypeDescription (Map<DeviceType, List<DeviceDTO>> map, RoomDTO roomDTO){
        List<DeviceDTO> devicesWithTemperatureSensor = getDevicesByTypeDescription(map);

        return getDevicesFromRoom(devicesWithTemperatureSensor, roomDTO);

    }

    /**
     * Get devices grouped by  temperature functionality.
     * @param map The map of all devices grouped by functionality.
     * @return The list of devices grouped by temperature functionality.
     */
    private List<DeviceDTO> getDevicesByTypeDescription(Map<DeviceType, List<DeviceDTO>> map) {

        List<DeviceDTO> devicesByTypeDescription = new ArrayList<>();

        for (Map.Entry<DeviceType, List<DeviceDTO>> entry : map.entrySet()) {

            DeviceType deviceType = entry.getKey();
            if (deviceType.getDescription().toString().equals("Temperature")) {
                List<DeviceDTO> deviceDTOList = entry.getValue();
                devicesByTypeDescription.addAll(deviceDTOList);
            }
        }
        return devicesByTypeDescription;
    }

    /**
     * Get temperature devices from a specific room.
     * @param temperatureDevicesDTO The list of temperature devices.
     * @param roomDTO The room to get the devices from.
     * @return The list of temperature devices from the room.
     */
    private List<DeviceDTO> getDevicesFromRoom(List<DeviceDTO> temperatureDevicesDTO, RoomDTO roomDTO) {

        List<DeviceDTO> devicesFromRoom = new ArrayList<>();

        for (DeviceDTO deviceDTO : temperatureDevicesDTO) {
            if (deviceDTO.roomID.equals(roomDTO.roomId)) {
                devicesFromRoom.add(deviceDTO);
            }
        }
        return devicesFromRoom;
    }


}
