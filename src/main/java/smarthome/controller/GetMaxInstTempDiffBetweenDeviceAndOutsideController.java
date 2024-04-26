package smarthome.controller;

import smarthome.domain.log.Log;
import smarthome.domain.service.ILogService;
import smarthome.utils.dto.DeviceDataDTO;
import smarthome.utils.Validator;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.SensorTypeID;

import java.time.LocalDateTime;
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
