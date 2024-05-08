package smarthome.controller.cli;

import java.time.LocalDateTime;
import java.util.List;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.domain.log.Log;
import smarthome.domain.service.ILogService;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TimeDelta;
import smarthome.utils.Validator;
import smarthome.utils.dto.DeviceDataDTO;

public class GetMaxInstTempDiffBetweenDeviceAndOutsideController {

  private final ILogService logService;

  /**
   * Constructor for GetMaxInstTempDiffBetweenDeviceAndOutsideController.
   *
   * @param logService The log service.
   */
  public GetMaxInstTempDiffBetweenDeviceAndOutsideController(ILogService logService) {
    Validator.validateNotNull(logService, "Log Service");
    this.logService = logService;
  }

  /**
   * Get the maximum instantaneous temperature difference between a device and the outside.
   *
   * @param outsideDeviceDTO is the outside device.
   * @param insideDeviceDTO  is the inside device.
   * @param initialTime      is the initial time.
   * @param finalTime        is the final time.
   * @return the maximum instantaneous temperature difference.
   */
  public int getMaxInstTempDiffBetweenDeviceAndOutside(DeviceDataDTO outsideDeviceDTO,
      DeviceDataDTO insideDeviceDTO, LocalDateTime initialTime, LocalDateTime finalTime, int timeDelta)
      throws EmptyReturnException {
    DatePeriod datePeriod = new DatePeriod(initialTime, finalTime);
    DeviceID insideDeviceID = new DeviceID(insideDeviceDTO.deviceID);
    DeviceID outsideDeviceID = new DeviceID(outsideDeviceDTO.deviceID);
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");

    /* Get readings for the inside and outside devices */
    List<Log> insideReadings = logService.getDeviceReadingsBySensorTypeAndTimePeriod(insideDeviceID,
        sensorTypeID, datePeriod);
    List<Log> outsideReadings = logService.getDeviceReadingsBySensorTypeAndTimePeriod(
        outsideDeviceID, sensorTypeID, datePeriod);

    TimeDelta timeDeltaObj = new TimeDelta(timeDelta);
    /* Get the maximum temperature difference */
    return logService.getMaxDifferenceBetweenReadings(insideReadings,
        outsideReadings, timeDeltaObj);
  }
}
