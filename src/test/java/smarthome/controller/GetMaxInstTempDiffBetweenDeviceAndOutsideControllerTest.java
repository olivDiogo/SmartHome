package smarthome.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import smarthome.domain.log.ILogFactory;
import smarthome.domain.log.Log;
import smarthome.domain.log.LogFactoryImpl;
import smarthome.domain.repository.ILogRepository;
import smarthome.domain.service.ILogService;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;
import smarthome.persistence.mem.LogRepository;
import smarthome.service.LogServiceImpl;
import smarthome.utils.dto.DeviceDataDTO;

class GetMaxInstTempDiffBetweenDeviceAndOutsideControllerTest {

  /**
   * Test to check if the constructor of the GetMaxInstTempDiffBetweenDeviceAndOutsideController
   * class is instantiated correctly.
   */
  @Test
  void shouldInstantiateGetMaxInstTempDiffBetweenDeviceAndOutsideController() {
    //Arrange
    ILogRepository logRepository = new LogRepository();
    ILogService logService = new LogServiceImpl(logRepository);

    //Act
    GetMaxInstTempDiffBetweenDeviceAndOutsideController getMaxInstTempDiffBetweenDeviceAndOutsideController = new GetMaxInstTempDiffBetweenDeviceAndOutsideController(
        logService);

    //Assert
    assertNotNull(getMaxInstTempDiffBetweenDeviceAndOutsideController);
  }

  /**
   * Test to check if the constructor of the GetMaxInstTempDiffBetweenDeviceAndOutsideController
   * class throws an exception when the log service is null.
   */
  @Test
  void shouldThrowException_WhenLogServiceIsNull() {
    //Arrange
    ILogService logService = null;

    String expectedMessage = "Log Service is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new GetMaxInstTempDiffBetweenDeviceAndOutsideController(logService));

    String result = exception.getMessage();

    assertEquals(expectedMessage, result);
  }


  /**
   * Test to check if the method getMaxInstTempDiffBetweenDeviceAndOutside returns the correct
   * value.
   */
  @Test
  void shouldReturnCorrectValue_whenGetMaxInstTempDiffBetweenDeviceAndOutsideIsCalled() {
    //Arrange
    ILogRepository logRepository = new LogRepository();
    ILogService logService = new LogServiceImpl(logRepository);

    ILogFactory logFactory = new LogFactoryImpl();

    GetMaxInstTempDiffBetweenDeviceAndOutsideController getMaxInstTempDiffBetweenDeviceAndOutsideController = new GetMaxInstTempDiffBetweenDeviceAndOutsideController(
        logService);

    /* Create DevicesDataDTO */
    String strDeviceName1 = "Outside Device";
    String strDeviceName2 = "Inside Device";
    String strDeviceType = "Temperature";
    String strDeviceID1 = "1";
    String strDeviceID2 = "2";
    String strRoomID1 = "1";
    String strRoomID2 = "Out";
    boolean deviceStatus = true;

    DeviceDataDTO outsideDeviceDTO = new DeviceDataDTO(strDeviceType, strDeviceName1, deviceStatus,
        strRoomID1, strDeviceID1);
    DeviceDataDTO insideDeviceDTO = new DeviceDataDTO(strDeviceType, strDeviceName2, deviceStatus,
        strRoomID2, strDeviceID2);

    LocalDateTime initialTime = LocalDateTime.of(2021, 1, 1, 0, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 1, 1, 1, 0);

    /* Create and save log data for outside device */
    DeviceID deviceID1 = new DeviceID(strDeviceID1);
    SensorID sensorID1 = new SensorID("1");
    LocalDateTime timeStamp1 = LocalDateTime.of(2021, 1, 1, 0, 10);
    LocalDateTime timeStamp2 = LocalDateTime.of(2021, 1, 1, 0, 15);
    ReadingValue readingValue1 = new ReadingValue("10");
    ReadingValue readingValue2 = new ReadingValue("15");
    SensorTypeID description = new SensorTypeID("Temperature");
    UnitID unit = new UnitID("Celsius");

    Log log1 = logFactory.createLog(deviceID1, sensorID1, timeStamp1, readingValue1, description,
        unit);
    logRepository.save(log1);
    Log log2 = logFactory.createLog(deviceID1, sensorID1, timeStamp2, readingValue2, description,
        unit);
    logRepository.save(log2);

    /* Create and save log data for inside device */
    DeviceID deviceID2 = new DeviceID(strDeviceID2);
    SensorID sensorID2 = new SensorID("2");
    LocalDateTime timeStamp3 = LocalDateTime.of(2021, 1, 1, 0, 4);
    LocalDateTime timeStamp4 = LocalDateTime.of(2021, 1, 1, 0, 6);
    ReadingValue readingValue3 = new ReadingValue("5");
    ReadingValue readingValue4 = new ReadingValue("1");

    Log log3 = logFactory.createLog(deviceID2, sensorID2, timeStamp3, readingValue3, description,
        unit);
    logRepository.save(log3);
    Log log4 = logFactory.createLog(deviceID2, sensorID2, timeStamp4, readingValue4, description,
        unit);
    logRepository.save(log4);

    int timeDelta = 5;

    int expected = 9;

    // Act
    int result = getMaxInstTempDiffBetweenDeviceAndOutsideController.getMaxInstTempDiffBetweenDeviceAndOutside(
        outsideDeviceDTO, insideDeviceDTO, initialTime, finalTime, timeDelta);

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Test to check if the method getMaxInstTempDiffBetweenDeviceAndOutside returns the correct value
   * when the inside device has no readings.
   */
  @Test
  void shouldThrowException_whenInsideDeviceHasNoReadings() {
    //Arrange
    ILogRepository logRepository = new LogRepository();
    ILogService logService = new LogServiceImpl(logRepository);

    ILogFactory logFactory = new LogFactoryImpl();

    GetMaxInstTempDiffBetweenDeviceAndOutsideController getMaxInstTempDiffBetweenDeviceAndOutsideController = new GetMaxInstTempDiffBetweenDeviceAndOutsideController(
        logService);

    /* Create DevicesDataDTO */
    String strDeviceName1 = "Outside Device";
    String strDeviceName2 = "Inside Device";
    String strDeviceType = "Temperature";
    String strDeviceID1 = "1";
    String strDeviceID2 = "2";
    String strRoomID1 = "1";
    String strRoomID2 = "Out";
    boolean deviceStatus = true;

    DeviceDataDTO outsideDeviceDTO = new DeviceDataDTO(strDeviceType, strDeviceName1,
        deviceStatus, strRoomID1, strDeviceID1);
    DeviceDataDTO insideDeviceDTO = new DeviceDataDTO(strDeviceType, strDeviceName2, deviceStatus,
        strRoomID2, strDeviceID2);

    LocalDateTime initialTime = LocalDateTime.of(2021, 1, 1, 0, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 1, 1, 1, 0);

    /* Create and save log data for outside device */
    DeviceID deviceID1 = new DeviceID(strDeviceID1);
    SensorID sensorID1 = new SensorID("1");
    LocalDateTime timeStamp1 = LocalDateTime.of(2021, 1, 1, 0, 10);
    LocalDateTime timeStamp2 = LocalDateTime.of(2021, 1, 1, 0, 15);
    ReadingValue readingValue1 = new ReadingValue("10");
    ReadingValue readingValue2 = new ReadingValue("15");
    SensorTypeID description = new SensorTypeID("Temperature");
    UnitID unit = new UnitID("Celsius");

    Log log1 = logFactory.createLog(deviceID1, sensorID1, timeStamp1, readingValue1, description,
        unit);
    logRepository.save(log1);
    Log log2 = logFactory.createLog(deviceID1, sensorID1, timeStamp2, readingValue2, description,
        unit);
    logRepository.save(log2);

    int timeDelta = 5;

    String expected = "No readings found for the given time period";

    // Act
    Exception result = assertThrows(IllegalArgumentException.class,
        () -> getMaxInstTempDiffBetweenDeviceAndOutsideController.getMaxInstTempDiffBetweenDeviceAndOutside(
            outsideDeviceDTO, insideDeviceDTO, initialTime, finalTime, timeDelta));

    // Assert
    String resultMessage = result.getMessage();
    assertEquals(expected, resultMessage);
  }

  /**
   * Test to check if the method getMaxInstTempDiffBetweenDeviceAndOutside returns the correct value
   * when the outside device has no readings.
   */
  @Test
  void shouldThrowException_whenOutsideDeviceHasNoReadings() {
    //Arrange
    ILogRepository logRepository = new LogRepository();
    ILogService logService = new LogServiceImpl(logRepository);

    ILogFactory logFactory = new LogFactoryImpl();

    GetMaxInstTempDiffBetweenDeviceAndOutsideController getMaxInstTempDiffBetweenDeviceAndOutsideController = new GetMaxInstTempDiffBetweenDeviceAndOutsideController(
        logService);

    /* Create DevicesDataDTO */
    String strDeviceName1 = "Outside Device";
    String strDeviceName2 = "Inside Device";
    String strDeviceType = "Temperature";
    String strDeviceID1 = "1";
    String strDeviceID2 = "2";
    String strRoomID1 = "1";
    String strRoomID2 = "Out";
    boolean deviceStatus = true;

    DeviceDataDTO outsideDeviceDTO = new DeviceDataDTO(strDeviceType, strDeviceName1,
        deviceStatus, strRoomID1, strDeviceID1);
    DeviceDataDTO insideDeviceDTO = new DeviceDataDTO(strDeviceType, strDeviceName2, deviceStatus,
        strRoomID2, strDeviceID2);

    LocalDateTime initialTime = LocalDateTime.of(2021, 1, 1, 0, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 1, 1, 1, 0);

    /* Create and save log data for inside device */
    DeviceID deviceID2 = new DeviceID(strDeviceID2);
    SensorID sensorID2 = new SensorID("2");
    LocalDateTime timeStamp3 = LocalDateTime.of(2021, 1, 1, 0, 4);
    LocalDateTime timeStamp4 = LocalDateTime.of(2021, 1, 1, 0, 6);
    ReadingValue readingValue3 = new ReadingValue("5");
    ReadingValue readingValue4 = new ReadingValue("1");

    Log log3 = logFactory.createLog(deviceID2, sensorID2, timeStamp3, readingValue3,
        new SensorTypeID("Temperature"),
        new UnitID("Celsius"));
    logRepository.save(log3);
    Log log4 = logFactory.createLog(deviceID2, sensorID2, timeStamp4, readingValue4,
        new SensorTypeID("Temperature"),
        new UnitID("Celsius"));
    logRepository.save(log4);

    int timeDelta = 5;

    String expected = "No readings found for the given time period";

    // Act
    Exception result = assertThrows(IllegalArgumentException.class,
        () -> getMaxInstTempDiffBetweenDeviceAndOutsideController.getMaxInstTempDiffBetweenDeviceAndOutside(
            outsideDeviceDTO, insideDeviceDTO, initialTime, finalTime, timeDelta));

    // Assert
    String resultMessage = result.getMessage();
    assertEquals(expected, resultMessage);
  }
}