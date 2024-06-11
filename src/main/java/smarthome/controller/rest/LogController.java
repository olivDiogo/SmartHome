package smarthome.controller.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.domain.exceptions.NoLogRecordsFoundException;
import smarthome.domain.log.Log;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TimeDelta;
import smarthome.service.IDeviceService;
import smarthome.service.ILogService;
import smarthome.utils.dto.LogDTO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/logs")
public class LogController {

  private final ILogService logService;
  private static final Integer PEAK_POWER_CONSUMPTION_TIME_DELTA = 15;
  private final IAssembler<Log, LogDTO> logAssembler;
  private final IDeviceService deviceService;
  /**
   * Constructor
   */
  @Autowired
  public LogController(ILogService logService, IAssembler<Log, LogDTO> logAssembler,
      IDeviceService deviceService) {
    this.logService = logService;
    this.logAssembler = logAssembler;
    this.deviceService = deviceService;
  }

  /**
   * Method to get Device Log (Readings) by Time Period
   */
  @GetMapping
  public ResponseEntity<List<LogDTO>> getDeviceReadingsByTimePeriod(
      @RequestParam String deviceID,
      @RequestParam String timeStart,
      @RequestParam String timeEnd) throws Exception {
    DeviceID deviceIDObj = new DeviceID(deviceID);
    LocalDateTime start = LocalDateTime.parse(timeStart, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    LocalDateTime end = LocalDateTime.parse(timeEnd, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    DatePeriod period = new DatePeriod(start, end);
    List<Log> logs = logService.getDeviceReadingsByTimePeriod(deviceIDObj, period);
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
      @RequestParam int timeDelta) throws Exception {
    DeviceID insideDeviceID = new DeviceID(outsideDeviceIDStr);
    DeviceID outsideDeviceID = new DeviceID(insideDeviceIDStr);
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");
    DatePeriod datePeriod = new DatePeriod(initialTime, finalTime);

    /* Get readings for the inside and outside devices */
    List<Log> insideReadings = logService.getDeviceReadingsBySensorTypeAndTimePeriod(insideDeviceID,
        sensorTypeID, datePeriod);
    List<Log> outsideReadings = logService.getDeviceReadingsBySensorTypeAndTimePeriod(
        outsideDeviceID, sensorTypeID, datePeriod);
    TimeDelta timeDeltaObj = new TimeDelta(timeDelta);

    int maxDiff = logService.getMaxDifferenceBetweenReadingsThatAreWithinTimeDelta(insideReadings, outsideReadings,
        timeDeltaObj);
    return ResponseEntity.ok(maxDiff);
  }

  @GetMapping("/peak-power-consumption")
  public ResponseEntity<Integer> getMaxPowerConsumption(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialTime,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalTime) {
    TimeDelta timeDeltaInMinutes = new TimeDelta(PEAK_POWER_CONSUMPTION_TIME_DELTA);
    DatePeriod datePeriod = new DatePeriod(initialTime, finalTime);
    DeviceTypeID powerMeterDevices = new DeviceTypeID("PowerMeter");
    DeviceTypeID powerSourceDevices = new DeviceTypeID("PowerSource");
    SensorTypeID sensorTypeID = new SensorTypeID("InstantPowerConsumption");

    List<Device> powerMeterDevicesList = deviceService.getDevicesByDeviceTypeID(powerMeterDevices);
    List<Device> powerSourceDevicesList = deviceService.getDevicesByDeviceTypeID(
        powerSourceDevices);

    List<Log> powerMeterReadings = logService.getReadingsInTimePeriodByListOfDevicesAndSensorType(
        powerMeterDevicesList, datePeriod,
        sensorTypeID);
    List<Log> powerSourceReadings = logService.getReadingsInTimePeriodByListOfDevicesAndSensorType(
        powerSourceDevicesList, datePeriod,
        sensorTypeID);

    Integer maxPowerConsumption = logService.getPeakPowerConsumption(powerMeterReadings,
        powerSourceReadings, timeDeltaInMinutes);

    return ResponseEntity.ok(maxPowerConsumption);
  }

  @GetMapping("/get-position-blindRoller")
  public ResponseEntity<Integer> getPositionBlindRoller(
      @RequestParam String deviceID) {
    DeviceID deviceIDObj = new DeviceID(deviceID);
    SensorTypeID sensorTypeID = new SensorTypeID("PercentagePosition");
    List<Log> logRecords = logService.getDeviceReadingsByDeviceIDAndSensorTypeID(deviceIDObj,
        sensorTypeID);
    int index = logRecords.size();

    if (index == 0) {
      throw new NoLogRecordsFoundException(
          "No log records found for the specified device and sensor type.");
    }
    String value = logRecords.get(index - 1).getReadingValue().getValue();
    int currentValueInt = Integer.parseInt(value);
    return ResponseEntity.ok(currentValueInt);
  }
}