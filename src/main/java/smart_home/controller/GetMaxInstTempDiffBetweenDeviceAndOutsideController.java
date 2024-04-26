package smart_home.controller;

import smart_home.domain.log.Log;
import smart_home.domain.service.ILogService;
import smart_home.dto.DeviceDataDTO;
import smart_home.utils.Validator;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;
import smart_home.value_object.SensorTypeID;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
  public int getMaxInstTempDiffBetweenDeviceAndOutside(DeviceDataDTO outsideDeviceDTO, DeviceDataDTO insideDeviceDTO, LocalDateTime initialTime, LocalDateTime finalTime) {
    DatePeriod datePeriod = new DatePeriod(initialTime, finalTime);
    DeviceID insideDeviceID = new DeviceID(insideDeviceDTO.deviceID);
    DeviceID outsideDeviceID = new DeviceID(outsideDeviceDTO.deviceID);
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");

    /* Get readings for the inside and outside devices */
    List<Log> insideReadings = logService.getDeviceReadingsBySensorTypeAndTimePeriod(insideDeviceID, sensorTypeID, datePeriod);
    List<Log> outsideReadings = logService.getDeviceReadingsBySensorTypeAndTimePeriod(outsideDeviceID, sensorTypeID, datePeriod);

    /* Get the temperature differences between the inside and outside readings */
    List<Integer> temperatureDifferences = logService.getDifferenceBetweenReadings(insideReadings, outsideReadings);

    /* Get the maximum temperature difference from list */
    return temperatureDifferences.stream().mapToInt(Integer::intValue).max().orElse(0);
  }

}
