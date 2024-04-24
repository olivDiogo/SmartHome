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
    private IAssembler<Log, LogDTO> _logAssembler;
    private IRoomService _roomService;
    private IAssembler<Room, RoomDTO> _roomAssembler;
    private IAssembler<Device, DeviceDTO> _deviceAssembler;

    public GetLogFromDeviceController(ILogService logService, IDeviceService deviceService,
        IAssembler<Log, LogDTO> logAssembler, IRoomService roomService, IAssembler<Room, RoomDTO> roomAssembler, IAssembler<Device, DeviceDTO> deviceAssembler) {
        Validate.notNull(logService, "Log Service is required");
        _logService = logService;
        Validate.notNull(deviceService, "Device Service is required");
        _deviceService = deviceService;
        Validate.notNull(logAssembler, "Log Assembler is required");
        _logAssembler = logAssembler;
        Validate.notNull(roomService, "Room Service is required");
        _roomService = roomService;
        Validate.notNull(roomAssembler, "Room Assembler is required");
        _roomAssembler = roomAssembler;
        Validate.notNull(deviceAssembler, "Device Assembler is required");
        _deviceAssembler = deviceAssembler;
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
        if (logs.isEmpty()) {
            LogDTO logDTO = new LogDTO("No logs found", "", "", "", "", "", "");
            return List.of(logDTO);
        }
        return _logAssembler.domainToDTO(logs);
    }
}
