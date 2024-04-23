package smart_home.controller;

import org.apache.commons.lang3.Validate;
import smart_home.ddd.IAssembler;
import smart_home.domain.device.Device;
import smart_home.domain.log.Log;
import smart_home.domain.repository.IDeviceRepository;
import smart_home.domain.repository.ILogRepository;
import smart_home.domain.room.Room;
import smart_home.domain.service.IDeviceService;
import smart_home.domain.service.ILogService;
import smart_home.domain.service.IRoomService;
import smart_home.dto.DeviceDTO;
import smart_home.dto.LogDTO;
import smart_home.dto.LogDataDTO;
import smart_home.dto.RoomDTO;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;
import smart_home.value_object.RoomID;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class GetLogFromDeviceController {
    private ILogService _logService;
    private IDeviceService _deviceService;
    private ILogRepository _logRepository;
    private IDeviceRepository _deviceRepository;
    private IAssembler<Log, LogDTO> _logAssembler;
    private IRoomService _roomService;
    private IAssembler<Room, RoomDTO> _roomAssembler;
    private IAssembler<Device, DeviceDTO> _deviceAssembler;

    public GetLogFromDeviceController(ILogService logService, IDeviceService deviceService, ILogRepository logRepository, IDeviceRepository deviceRepository, IAssembler<Log, LogDTO> logAssembler, IRoomService roomService, IAssembler<Room, RoomDTO> roomAssembler, IAssembler<Device, DeviceDTO> deviceAssembler) {
        Validate.notNull(logService, "Log Service is required");
        _logService = logService;
        Validate.notNull(deviceService, "Device Service is required");
        _deviceService = deviceService;
        Validate.notNull(logRepository, "Log Repository is required");
        _logRepository = logRepository;
        Validate.notNull(deviceRepository, "Device Repository is required");
        _deviceRepository = deviceRepository;
        Validate.notNull(logAssembler, "Log Assembler is required");
        _logAssembler = logAssembler;
        Validate.notNull(roomService, "Room Service is required");
        _roomService = roomService;
        Validate.notNull(roomAssembler, "Room Assembler is required");
        _roomAssembler = roomAssembler;
        Validate.notNull(deviceAssembler, "Device Assembler is required");
        _deviceAssembler = deviceAssembler;
    }

    /**
     * Gets all rooms.
     *
     * @return a list of rooms.
     */
    public List<RoomDTO> getRooms() {
        List<Room> rooms = _roomService.getAllRooms();

        List<RoomDTO> roomDTOList = _roomAssembler.domainToDTO(rooms);

        return List.copyOf(roomDTOList);
    }

    /**
     * Gets all devices from a room.
     *
     * @param roomDTO is the room to get the devices from.
     * @return a list of devices.
     */
    public List<DeviceDTO> getDevicesFromRoom(RoomDTO roomDTO) {
        RoomID roomID = new RoomID(roomDTO.roomId);

        if (!_roomService.getRoomById(roomID).isPresent()) {
            throw new IllegalArgumentException("Room with ID " + roomID + " not found.");
        }

        List<Device> devices = _deviceService.getDevicesByRoomId(roomID);

        List<DeviceDTO> deviceDTOList = _deviceAssembler.domainToDTO(devices);

        return List.copyOf(deviceDTOList);
    }

    public List<LogDTO> getLogFromDevice(LogDataDTO logDataDTO) {
        DeviceID deviceID = new DeviceID(logDataDTO.deviceID);
        LocalDateTime start = LocalDateTime.parse(logDataDTO.timeStart, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse(logDataDTO.timeEnd, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        DatePeriod period = new DatePeriod(start, end);
        Optional<Device> device = _deviceService.getDeviceByID(deviceID);
        if (!device.isPresent()) {
            LogDTO logDTO = new LogDTO("Device not found", "", "", "", "", "", "");
            return List.of(logDTO);
        }
        List<Log> logs = _logService.getDeviceReadingsByTimePeriod(deviceID, period);
        return _logAssembler.domainToDTO(logs);
    }
}
