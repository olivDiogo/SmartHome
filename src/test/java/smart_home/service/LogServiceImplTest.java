package smart_home.service;

import org.junit.jupiter.api.Test;


import smart_home.domain.log.Log;
import smart_home.domain.repository.ILogRepository;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;
import smart_home.value_object.SensorTypeID;


import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LogServiceImplTest {

  /**
   * Test that the LogServiceImpl class can be instantiated.
   */
  @Test
  void shouldInstantiateValidLog() {
    // Arrange
    ILogRepository logRepositoryDouble = mock(ILogRepository.class);

    // Act
    LogServiceImpl result = new LogServiceImpl(logRepositoryDouble);

    // Assert
    assertNotNull(result);
  }

  /**
   * Test that the LogServiceImpl class throws an IllegalArgumentException when the LogRepository is
   * null.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenLogRepositoryIsNull() {
    // Arrange
    ILogRepository logRepository = null;
    String expectedMessage = "Log Repository is required";
    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new LogServiceImpl(logRepository));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the List of logs is returned
   */
  @Test
  void shouldReturnLogs_whenDeviceReadingsByTimePeriodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    DatePeriod period = mock(DatePeriod.class);

    Log log = mock(Log.class);
    Log log2 = mock(Log.class);

    List<Log> expectedLogs = List.of(log, log2);

    ILogRepository logRepository = mock(ILogRepository.class);
    when(logRepository.findByDeviceIDAndDatePeriodBetween(deviceID, period)).thenReturn(
        expectedLogs);

    LogServiceImpl logService = new LogServiceImpl(logRepository);

    // Act
    List<Log> actualLogs = logService.getDeviceReadingsByTimePeriod(deviceID, period);

    // Assert
    assertEquals(expectedLogs, actualLogs);
  }

  /**
   * Test if the List of logs is empty
   */
  @Test
  void shouldReturnEmptyList_whenDeviceReadingsByTimePeriodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    DatePeriod period = mock(DatePeriod.class);

    List<Log> expectedLogs = emptyList();

    ILogRepository logRepository = mock(ILogRepository.class);
    when(logRepository.findByDeviceIDAndDatePeriodBetween(deviceID, period)).thenReturn(
        expectedLogs);

    LogServiceImpl logService = new LogServiceImpl(logRepository);

    // Act
    List<Log> actualLogs = logService.getDeviceReadingsByTimePeriod(deviceID, period);

    // Assert
    assertEquals(expectedLogs, actualLogs);
  }

  @Test
  void shouldReturnLogs_whenDeviceReadingsBySensorTypeAndTimePeriodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    DatePeriod period = mock(DatePeriod.class);

    Log log = mock(Log.class);
    Log log2 = mock(Log.class);

    List<Log> expectedLogs = List.of(log, log2);

    ILogRepository logRepository = mock(ILogRepository.class);
    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriod(deviceID, sensorTypeID, period))
        .thenReturn(expectedLogs);

    LogServiceImpl logService = new LogServiceImpl(logRepository);

    // Act
    List<Log> actualLogs = logService.getDeviceReadingsBySensorTypeAndTimePeriod(deviceID,
        sensorTypeID, period);

    // Assert
    assertEquals(expectedLogs, actualLogs);
  }
}
