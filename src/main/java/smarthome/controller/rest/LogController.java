package smarthome.controller.rest;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.log.Log;
import smarthome.domain.service.ILogService;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.dto.LogDTO;
import smarthome.utils.dto.LogDataDTO;

@RestController
@RequestMapping("/log")
public class LogController {

  private final ILogService logService;
  private final IAssembler<Log, LogDTO> logAssembler;

  /**
   * Constructor
   */
  @Autowired
  public LogController(ILogService logService, IAssembler<Log, LogDTO> logAssembler) {
    this.logService = logService;
    this.logAssembler = logAssembler;
  }

  /**
   * Method to get Device Log (Readings) by Time Period
   */
  @GetMapping("/device")
  public ResponseEntity<List<LogDTO>> getDeviceReadingsByTimePeriod(
      @Valid @RequestBody LogDataDTO dto) {
    DeviceID deviceID = new DeviceID(dto.deviceID);
    LocalDateTime start = LocalDateTime.parse(dto.timeStart,
        DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    LocalDateTime end = LocalDateTime.parse(dto.timeEnd,
        DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    DatePeriod period = new DatePeriod(start, end);
    List<Log> logs = logService.getDeviceReadingsByTimePeriod(deviceID, period);
    if (logs.isEmpty()) {
      ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(logAssembler.domainToDTO(logs));
  }

  /**
   * Get the maximum instantaneous temperature difference between a device and the outside.
   *
   * @param outsideDeviceIDStr is the outside device id.
   * @param insideDeviceIDStr  is the inside device id.
   * @param initialTime        is the initial time.
   * @param finalTime          is the final time.
   * @return the maximum instantaneous temperature difference.
   */
  @GetMapping("/temperature-difference")
  public ResponseEntity<Integer> getMaxInstTempDiffBetweenDeviceAndOutside(
      @RequestParam String outsideDeviceIDStr,
      @RequestParam String insideDeviceIDStr,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialTime,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalTime,
      @RequestParam int timeDelta) {
    DeviceID insideDeviceID = new DeviceID(outsideDeviceIDStr);
    DeviceID outsideDeviceID = new DeviceID(insideDeviceIDStr);
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");
    DatePeriod datePeriod = new DatePeriod(initialTime, finalTime);

    /* Get readings for the inside and outside devices */
    List<Log> insideReadings = logService.getDeviceReadingsBySensorTypeAndTimePeriod(insideDeviceID,
        sensorTypeID, datePeriod);
    List<Log> outsideReadings = logService.getDeviceReadingsBySensorTypeAndTimePeriod(
        outsideDeviceID, sensorTypeID, datePeriod);

//    if (insideReadings.isEmpty() || outsideReadings.isEmpty()) {
//      return ResponseEntity.noContent().build();
//    }

    /* Get the maximum temperature difference */
    try {
      int maxDiff = logService.getMaxDifferenceBetweenReadings(insideReadings, outsideReadings,
          timeDelta);
      return ResponseEntity.ok(maxDiff);
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
  }
}