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

  private ILogService logService;

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
      DeviceDataDTO insideDeviceDTO, LocalDateTime initialTime, LocalDateTime finalTime) {
    DatePeriod datePeriod = new DatePeriod(initialTime, finalTime);
    DeviceID insideDeviceID = new DeviceID(insideDeviceDTO.deviceID);
    DeviceID outsideDeviceID = new DeviceID(outsideDeviceDTO.deviceID);

    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");

    List<Log> insideReadings = logService.getDeviceReadingsBySensorTypeAndTimePeriod(insideDeviceID,
        sensorTypeID, datePeriod);
    List<Log> outsideReadings = logService.getDeviceReadingsBySensorTypeAndTimePeriod(
        outsideDeviceID, sensorTypeID,
        datePeriod);

    if (insideReadings.isEmpty() || outsideReadings.isEmpty()) {
      throw new IllegalArgumentException("No readings found for the given time period");
    }

    List<Integer> temperatureDifferences = getTemperatureDifference(insideReadings,
        outsideReadings);

    return temperatureDifferences.stream().mapToInt(Integer::intValue).max().orElse(0);
  }

  /**
   * Get the temperature difference between the inside and outside readings.
   *
   * @param insideReadings  is the list of inside readings.
   * @param outsideReadings is the list of outside readings.
   * @return the list of temperature differences.
   */
  private List<Integer> getTemperatureDifference(List<Log> insideReadings,
      List<Log> outsideReadings) {
    List<Integer> temperatureDifferences = new ArrayList<>();

    for (int i = 0; i < insideReadings.size(); i++) {
      for (int j = 0; j < outsideReadings.size(); j++) {
        int diffInMinutes = (int) ChronoUnit.MINUTES.between(insideReadings.get(i).getTimeStamp(),
            outsideReadings.get(j).getTimeStamp());

        if (diffInMinutes < 5) {
          int temperatureDifference =
              Math.abs(Integer.parseInt(insideReadings.get(i).getReadingValue().getReadingValue())
                  - Integer.parseInt(outsideReadings.get(j).getReadingValue().getReadingValue()));
          temperatureDifferences.add(temperatureDifference);
        }
      }
    }

    return temperatureDifferences;
  }

}
