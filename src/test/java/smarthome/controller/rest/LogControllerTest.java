package smarthome.controller.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.domain.log.ILogFactory;
import smarthome.domain.log.Log;
import smarthome.domain.log.LogFactoryImpl;
import smarthome.domain.repository.ILogRepository;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;
import smarthome.utils.dto.data_dto.LogDataDTO;

@SpringBootTest
@AutoConfigureMockMvc
class LogControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ILogFactory logFactory;

  @MockBean
  private ILogRepository logRepository;


  Log setupLog() {
    ILogFactory logFactory = new LogFactoryImpl();
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 0);
    ReadingValue readingValue = new ReadingValue("20");
    SensorID sensorID = new SensorID("1");
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");
    UnitID unitID = new UnitID("C");
    DeviceID deviceID2 = new DeviceID("2");
    return logFactory.createLog(deviceID2, sensorID, timeStamp, readingValue, sensorTypeID, unitID);
  }

  Log setupLogWithTimeAndValue(LocalDateTime timeStamp, String value) {
    ReadingValue readingValue = new ReadingValue(value);
    SensorID sensorID = new SensorID("1");
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");
    UnitID unitID = new UnitID("C");
    DeviceID deviceID2 = new DeviceID("2");
    return logFactory.createLog(deviceID2, sensorID, timeStamp, readingValue, sensorTypeID, unitID);
  }


  /**
   * Test method to get Device Log (Readings) by Time Period
   */
  @Test
  void shouldGetLogFromDevice_WhenParametersAreValid() throws Exception {
    // Arrange
    String deviceIDStr = "123";
    String timeStart = "2020-03-01T13:45:30";
    String timeEnd = "2022-03-01T13:50:30";
    Log log = setupLog();
    List<Log> logs = List.of(log);

    when(logRepository.findByDeviceIDAndDatePeriodBetween(any(DeviceID.class),
        any(DatePeriod.class)))
        .thenReturn(logs);

    // Act & Assert
    mockMvc.perform(get("/logs/")
            .param("deviceID", deviceIDStr)
            .param("timeStart", timeStart)
            .param("timeEnd", timeEnd))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1))); // Assuming the response is a list of logs
  }

  /**
   * Test method to get Device Log (Readings) by Time Period when no logs are found
   */
  @Test
  void shouldReturnNotFound_WhenNoLogsFound() throws Exception {
    // Arrange
    String deviceIDStr = "123";
    String timeStart = "2020-03-01T13:45:30";
    String timeEnd = "2022-03-01T13:50:30";
    List<Log> logs = new ArrayList<>();

    when(logRepository.findByDeviceIDAndDatePeriodBetween(any(DeviceID.class),
        any(DatePeriod.class)))
        .thenReturn(logs);

    // Act & Assert
    mockMvc.perform(get("/logs/")
            .param("deviceID", deviceIDStr)
            .param("timeStart", timeStart)
            .param("timeEnd", timeEnd))
        .andExpect(
            status().isNoContent());
  }

  /**
   * Test method to get the maximum instantaneous temperature difference between a device and the
   * outside.
   */
  @Test
  void shouldReturnMaxTempDifference_WhenParametersAreValid() throws Exception {
    // Arrange
    // Create LogDataDTO
    String outsideDeviceIDStr = "123";
    String insideDeviceIDStr = "456";
    LocalDateTime initialTime = LocalDateTime.of(2021, 5, 1, 12, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 5, 1, 13, 0);
    int timeDelta = 5;

    //Set up a single log for outside
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 5);
    Log log1 = setupLogWithTimeAndValue(timeStamp, "20");
    List<Log> outsideDeviceLogs = List.of(log1);

    //Set up a single log for inside
    LocalDateTime timeStamp2 = LocalDateTime.of(2021, 5, 1, 12, 5);
    Log log2 = setupLogWithTimeAndValue(timeStamp2, "25");
    List<Log> insideDeviceLogs = List.of(log2);

    String expectedTemperatureDifference = "5";

    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(any(DeviceID.class),
        any(SensorTypeID.class), any(DatePeriod.class)))
        .thenReturn(outsideDeviceLogs)
        .thenReturn(insideDeviceLogs);

    // Act & Assert
    mockMvc.perform(get("/logs/temperature-difference")
            .contentType(MediaType.APPLICATION_JSON)
            .param("outsideDeviceIDStr", outsideDeviceIDStr)
            .param("insideDeviceIDStr", insideDeviceIDStr)
            .param("initialTime", initialTime.toString())
            .param("finalTime", finalTime.toString())
            .param("timeDelta", String.valueOf(timeDelta)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(expectedTemperatureDifference));
  }

  /**
   * Test method to get the maximum instantaneous temperature difference between a device and the
   * outside when readings exist but are not within the time delta
   */
  @Test
  void shouldNotReturnMaxTempDifference_WhenReadingsNotWithinTimeDelta() throws Exception {
    // Arrange
    // Create LogDataDTO
    String outsideDeviceIDStr = "123";
    String insideDeviceIDStr = "456";
    LocalDateTime initialTime = LocalDateTime.of(2021, 5, 1, 12, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 5, 1, 13, 0);
    int timeDelta = 5;

    //Set up a single log for outside
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 5);
    Log log1 = setupLogWithTimeAndValue(timeStamp, "20");
    List<Log> outsideDeviceLogs = List.of(log1);

    //Set up a single log for inside
    LocalDateTime timeStamp2 = LocalDateTime.of(2021, 5, 1, 13, 0);
    Log log2 = setupLogWithTimeAndValue(timeStamp2, "25");
    List<Log> insideDeviceLogs = List.of(log2);

    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(any(DeviceID.class),
        any(SensorTypeID.class), any(DatePeriod.class)))
        .thenReturn(outsideDeviceLogs)
        .thenReturn(insideDeviceLogs);

    // Act & Assert
    mockMvc.perform(get("/logs/temperature-difference")
            .contentType(MediaType.APPLICATION_JSON)
            .param("outsideDeviceIDStr", outsideDeviceIDStr)
            .param("insideDeviceIDStr", insideDeviceIDStr)
            .param("initialTime", initialTime.toString())
            .param("finalTime", finalTime.toString())
            .param("timeDelta", String.valueOf(timeDelta)))
        .andExpect(status().isNoContent());
  }

  /**
   * Test method to get the maximum instantaneous temperature difference between a device and the
   * outside when no logs are found for outside
   */
  @Test
  void shouldNotReturnMaxTempDifference_WhenNoOutsideReadings() throws Exception {
    // Arrange
    // Create LogDataDTO
    String outsideDeviceIDStr = "123";
    String insideDeviceIDStr = "456";
    LocalDateTime initialTime = LocalDateTime.of(2021, 5, 1, 12, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 5, 1, 13, 0);
    int timeDelta = 5;

    //Set up a single log for outside - empty
    List<Log> outsideDeviceLogs = new ArrayList<>();

    //Set up a single log for inside
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 5);
    Log log = setupLogWithTimeAndValue(timeStamp, "25");
    List<Log> insideDeviceLogs = List.of(log);

    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(any(DeviceID.class),
        any(SensorTypeID.class), any(DatePeriod.class)))
        .thenReturn(outsideDeviceLogs)
        .thenReturn(insideDeviceLogs);

    // Act & Assert
    mockMvc.perform(get("/logs/temperature-difference")
            .contentType(MediaType.APPLICATION_JSON)
            .param("outsideDeviceIDStr", outsideDeviceIDStr)
            .param("insideDeviceIDStr", insideDeviceIDStr)
            .param("initialTime", initialTime.toString())
            .param("finalTime", finalTime.toString())
            .param("timeDelta", String.valueOf(timeDelta)))
        .andExpect(status().isNoContent());
  }

  /**
   * Test method to get the maximum instantaneous temperature difference between a device and the
   * outside when no logs are found for inside
   */
  @Test
  void shouldNotReturnMaxTempDifference_WhenNoInsideReadings() throws Exception {
    // Arrange
    // Create LogDataDTO
    String outsideDeviceIDStr = "123";
    String insideDeviceIDStr = "456";
    LocalDateTime initialTime = LocalDateTime.of(2021, 5, 1, 12, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 5, 1, 13, 0);
    int timeDelta = 5;

    //Set up a single log for outside - empty
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 5);
    Log log = setupLogWithTimeAndValue(timeStamp, "25");
    List<Log> outsideDeviceLogs = List.of(log);

    //Set up a single log for inside
    List<Log> insideDeviceLogs = new ArrayList<>();

    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(any(DeviceID.class),
        any(SensorTypeID.class), any(DatePeriod.class)))
        .thenReturn(outsideDeviceLogs)
        .thenReturn(insideDeviceLogs);

    // Act & Assert
    mockMvc.perform(get("/logs/temperature-difference")
            .contentType(MediaType.APPLICATION_JSON)
            .param("outsideDeviceIDStr", outsideDeviceIDStr)
            .param("insideDeviceIDStr", insideDeviceIDStr)
            .param("initialTime", initialTime.toString())
            .param("finalTime", finalTime.toString())
            .param("timeDelta", String.valueOf(timeDelta)))
        .andExpect(status().isNoContent());
  }
}