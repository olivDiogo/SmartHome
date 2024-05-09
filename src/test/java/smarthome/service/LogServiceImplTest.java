package smarthome.service;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.domain.log.Log;
import smarthome.domain.repository.ILogRepository;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TimeDelta;

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

  /**
   * Test if the List of logs is returned not empty
   */
  @Test
  void shouldReturnLogs_whenDeviceReadingsBySensorTypeAndTimePeriodIsCalled()
      throws EmptyReturnException {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    DatePeriod period = mock(DatePeriod.class);

    Log log = mock(Log.class);
    Log log2 = mock(Log.class);

    List<Log> expectedLogs = List.of(log, log2);

    ILogRepository logRepository = mock(ILogRepository.class);
    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(deviceID, sensorTypeID,
        period))
        .thenReturn(expectedLogs);

    LogServiceImpl logService = new LogServiceImpl(logRepository);

    // Act
    List<Log> actualLogs = logService.getDeviceReadingsBySensorTypeAndTimePeriod(deviceID,
        sensorTypeID, period);

    // Assert
    assertEquals(expectedLogs, actualLogs);
  }

  /**
   * Test if the List of logs is empty
   */
  @Test
  void shouldThrowException_whenDeviceReadingsBySensorTypeAndTimePeriodIsCalledAndGetsEmptyList() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    DatePeriod period = mock(DatePeriod.class);

    List<Log> emptyLogs = emptyList();

    ILogRepository logRepository = mock(ILogRepository.class);
    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(deviceID, sensorTypeID,
        period))
        .thenReturn(emptyLogs);

    LogServiceImpl logService = new LogServiceImpl(logRepository);

    String expectedMessage = "No readings found for the given time period";

    // Act
    EmptyReturnException exception = assertThrows(EmptyReturnException.class,
        () -> logService.getDeviceReadingsBySensorTypeAndTimePeriod(deviceID, sensorTypeID,
            period));

    // Assert
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for method getDifferenceBetweenReadings when single readings are within an interval of 5 minutes.
   */
  @Test
  void shouldReturnMaxDifferenceBetweenReadings_whenGetMaxDifferenceBetweenReadingsIsCalledAndReadingsAreWithin5MinutesInterval()
      throws EmptyReturnException {
    // Arrange
    /* Mocking one log object and adding it to a list*/
    ReadingValue readingValue1 = mock(ReadingValue.class);
    when(readingValue1.getValue()).thenReturn("5");
    Log log1 = mock(Log.class);
    when(log1.getReadingValue()).thenReturn(readingValue1);
    when(log1.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 1));

    List<Log> list1 = List.of(log1);

    /* Mocking another log object and adding it to another list*/
    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getValue()).thenReturn("14");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);
    when(log2.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 5));

    List<Log> list2 = List.of(log2);

    ILogRepository logRepository = mock(ILogRepository.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository);

    TimeDelta timeDelta = mock(TimeDelta.class);
    when(timeDelta.getMinutes()).thenReturn(5);

    int expectedDifference = 9;

    // Act
    int actualDifference = logService.getMaxDifferenceBetweenReadings(list1, list2, timeDelta);

    // Assert
    assertEquals(expectedDifference, actualDifference);
  }

  /**
   * Test for method getDifferenceBetweenReadings when multiple readings are within an interval of 5 minutes.
   */
  @Test
  void shouldReturnMaxDifferenceBetweenReadings_whenGetMaxDifferenceBetweenReadingsIsCalledAndMultipleReadingsAreWithin5MinutesInterval()
      throws EmptyReturnException {
    // Arrange
    /* Mocking one log object and adding it to a list*/
    ReadingValue readingValue1 = mock(ReadingValue.class);
    when(readingValue1.getValue()).thenReturn("5");
    Log log1 = mock(Log.class);
    when(log1.getReadingValue()).thenReturn(readingValue1);
    when(log1.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 1));

    /* Mocking another log object and adding it to the second list*/
    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getValue()).thenReturn("14");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);
    when(log2.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 2));

    /* Mocking another log object and adding it to the second list*/
    ReadingValue readingValue3 = mock(ReadingValue.class);
    when(readingValue3.getValue()).thenReturn("20");
    Log log3 = mock(Log.class);
    when(log3.getReadingValue()).thenReturn(readingValue3);
    when(log3.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 3));

    List<Log> list1 = List.of(log1);

    List<Log> list2 = List.of(log2, log3);

    ILogRepository logRepository = mock(ILogRepository.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository);

    TimeDelta timeDelta = mock(TimeDelta.class);
    when(timeDelta.getMinutes()).thenReturn(5);

    int expectedDifference = 15;

    // Act
    int actualDifference = logService.getMaxDifferenceBetweenReadings(list1, list2, timeDelta);

    // Assert
    assertEquals(expectedDifference, actualDifference);
  }


  /**
   * Test for method getDifferenceBetweenReadings when the readings are not within an interval of 5
   * minutes.
   */
  @Test
  void shouldThrowException_whenGetMaxDifferenceBetweenReadingsIsCalledAndReadingsAreNotWithin5MinutesInterval() {
    // Arrange
    /* Mocking one log object and adding it to a list*/
    ReadingValue readingValue1 = mock(ReadingValue.class);
    when(readingValue1.getValue()).thenReturn("5");
    Log log1 = mock(Log.class);
    when(log1.getReadingValue()).thenReturn(readingValue1);
    when(log1.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 1));

    List<Log> list1 = List.of(log1);

    /* Mocking another log object and adding it to another list*/
    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getValue()).thenReturn("14");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);
    when(log2.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 6));

    List<Log> list2 = List.of(log2);

    ILogRepository logRepository = mock(ILogRepository.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository);

    TimeDelta timeDelta = mock(TimeDelta.class);
    when(timeDelta.getMinutes()).thenReturn(5);

    String expectedMessage = "No readings found within the given time interval";

    // Act
    Exception exception = assertThrows(Exception.class,
        () -> logService.getMaxDifferenceBetweenReadings(list1, list2, timeDelta));

    // Assert
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  void shouldReturnSumOfTwoReadingContainingInteger_WhenGetSumOfTwoIntegerReadingsCalled() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository);
    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("5");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);

    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue);

    int expected = 10;
    // Act
    int result = logService.getSumOfTwoIntegerReadings(log, log2);
    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldThrowExeption_WhenGetSumOfTwoIntegerReadingsCalledOnNonIntengerReading() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository);
    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("testes");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);

    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue);

    String expected = "Reading values are not integers";

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> logService.getSumOfTwoIntegerReadings(log, log2));
    // Assert
    String actual = exception.getMessage();
    assertEquals(expected, actual);
  }

  @Test
  void shouldReturnDifferenceOfTwoReadingContainingInteger_WhenGetDifferenceBetweenReadingsCalled() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository);
    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("5");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);

    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue);

    int expected = 0;
    // Act
    int result = logService.getDifferenceBetweenReadings(log, log2);
    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldThrowExeption_WWhenGetDifferenceBetweenReadingsCalledWithNonIntengerReading() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository);
    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("testes");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);

    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue);

    String expected = "Reading values are not integers";

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> logService.getDifferenceBetweenReadings(log, log2));
    // Assert
    String actual = exception.getMessage();
    assertEquals(expected, actual);
  }


}
