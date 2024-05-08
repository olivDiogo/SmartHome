package smarthome.controller.cli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import smarthome.ddd.IAssembler;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.domain.log.Log;
import smarthome.domain.service.ILogService;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.utils.Validator;
import smarthome.utils.dto.LogDTO;
import smarthome.utils.dto.LogDataDTO;

public class GetLogFromDeviceController {

  private final ILogService logService;
  private final IAssembler<Log, LogDTO> logAssembler;

  /**
   * Constructor for GetLogFromDeviceController
   *
   * @param logService   is the log service
   * @param logAssembler is the log assembler
   */
  public GetLogFromDeviceController(ILogService logService, IAssembler<Log, LogDTO> logAssembler) {
    Validator.validateNotNull(logService, "Log Service");
    this.logService = logService;
    Validator.validateNotNull(logAssembler, "Log Assembler");
    this.logAssembler = logAssembler;
  }

  /**
   * Method to get log from device
   *
   * @param logDataDTO LogDataDTO object
   * @return List of LogDTO
   */
  public List<LogDTO> getLogFromDevice(LogDataDTO logDataDTO) throws EmptyReturnException {
    DeviceID deviceID = new DeviceID(logDataDTO.deviceID);
    LocalDateTime start = LocalDateTime.parse(logDataDTO.timeStart,
        DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    LocalDateTime end = LocalDateTime.parse(logDataDTO.timeEnd,
        DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    DatePeriod period = new DatePeriod(start, end);
    List<Log> logs = logService.getDeviceReadingsByTimePeriod(deviceID, period);
    if (logs.isEmpty()) {
      LogDTO logDTO = new LogDTO("No logs found", "", "", "", "", "", "");
      return List.of(logDTO);
    }
    return logAssembler.domainToDTO(logs);
  }
}
