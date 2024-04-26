package smart_home.controller;

import org.apache.commons.lang3.Validate;
import smart_home.ddd.IAssembler;
import smart_home.domain.device.Device;
import smart_home.domain.log.Log;
import smart_home.domain.service.IDeviceService;
import smart_home.domain.service.ILogService;
import smart_home.dto.LogDTO;
import smart_home.dto.LogDataDTO;
import smart_home.utils.Validator;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class GetLogFromDeviceController {
    private final ILogService logService;
    private final IAssembler<Log, LogDTO> logAssembler;

  /**
   * Constructor for GetLogFromDeviceController
   *
   * @param logService    is the log service
   * @param logAssembler  is the log assembler
   */
    public GetLogFromDeviceController(ILogService logService, IAssembler<Log, LogDTO> logAssembler) {
      Validator.validateNotNull(logService, "Log Service");
        this.logService = logService;
        Validator.validateNotNull(logAssembler, "Log Assembler");
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
        List<Log> logs = logService.getDeviceReadingsByTimePeriod(deviceID, period);
        if (logs.isEmpty()) {
            LogDTO logDTO = new LogDTO("No logs found", "", "", "", "", "", "");
            return List.of(logDTO);
        }
        return logAssembler.domainToDTO(logs);
    }
}
