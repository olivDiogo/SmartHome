package smart_home.controller;

import org.apache.commons.lang3.Validate;
import smart_home.ddd.IAssembler;
import smart_home.domain.device.Device;
import smart_home.domain.log.Log;
import smart_home.domain.service.IDeviceService;
import smart_home.domain.service.ILogService;
import smart_home.dto.LogDTO;
import smart_home.dto.LogDataDTO;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class GetLogFromDeviceController {
    private ILogService logService;
    private IDeviceService deviceservice;
    private IAssembler<Log, LogDTO> logAssembler;

  /**
   * Constructor for GetLogFromDeviceController
   *
   * @param logService    is the log service
   * @param deviceService is the device service
   * @param logAssembler  is the log assembler
   */
    public GetLogFromDeviceController(ILogService logService, IDeviceService deviceService,
        IAssembler<Log, LogDTO> logAssembler) {
        Validate.notNull(logService, "Log Service is required");
        this.logService = logService;
        Validate.notNull(deviceService, "Device Service is required");
        deviceservice = deviceService;
        Validate.notNull(logAssembler, "Log Assembler is required");
        this.logAssembler = logAssembler;
    }

    /**
     * Method to get log from device
     * @param logDataDTO LogDataDTO object
     * @return List of LogDTO
     */
    public List<LogDTO> getLogFromDevice(LogDataDTO logDataDTO) {
        DeviceID deviceID = new DeviceID(logDataDTO.deviceID);
        LocalDateTime start = LocalDateTime.parse(logDataDTO.timeStart, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse(logDataDTO.timeEnd, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        DatePeriod period = new DatePeriod(start, end);
        Optional<Device> device = deviceservice.getDeviceByID(deviceID);
        if (device.isEmpty()) {
            LogDTO logDTO = new LogDTO("Device not found", "", "", "", "", "", "");
            return List.of(logDTO);
        }
        List<Log> logs = logService.getDeviceReadingsByTimePeriod(deviceID, period);
        if (logs.isEmpty()) {
            LogDTO logDTO = new LogDTO("No logs found", "", "", "", "", "", "");
            return List.of(logDTO);
        }
        return logAssembler.domainToDTO(logs);
    }
}
