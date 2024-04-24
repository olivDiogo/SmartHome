package smart_home.controller;

import org.junit.jupiter.api.Test;
import smart_home.domain.device.Device;
import smart_home.domain.device.DeviceFactoryImpl;
import smart_home.domain.house.House;
import smart_home.domain.house.HouseFactoryImpl;
import smart_home.domain.log.Log;
import smart_home.domain.log.LogFactoryImpl;
import smart_home.domain.room.Room;
import smart_home.domain.room.RoomFactoryImpl;
import smart_home.dto.LogDTO;
import smart_home.dto.LogDataDTO;
import smart_home.mapper.DeviceAssembler;
import smart_home.mapper.LogAssembler;
import smart_home.mapper.RoomAssembler;
import smart_home.persistence.mem.*;
import smart_home.service.*;
import smart_home.value_object.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetLogFromDeviceControllerTest {
  LogRepository logRepository = new LogRepository();
  LogServiceImpl logService = new LogServiceImpl(logRepository);
  LogAssembler logAssembler = new LogAssembler();
  DeviceRepository deviceRepository = new DeviceRepository();
  DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
  RoomRepository roomRepository = new RoomRepository();
  RoomFactoryImpl roomFactory = new RoomFactoryImpl();
  HouseRepository houseRepository = new HouseRepository();
  HouseFactoryImpl houseFactory = new HouseFactoryImpl();
  HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
  RoomServiceImpl roomService =
      new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
  DeviceServiceImpl deviceService =
      new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
  PostalCodeFactory postalCodeFactory = new PostalCodeFactory();

  private House createHouse() {
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
        new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseServiceImpl.addHouse(newAddress, newGPS);
    return house;
  }

  private Room createRoom(HouseID id) {
    String name1 = "Quarto do Joao";
    RoomName roomName1 = new RoomName(name1);

    int width = 10;
    int length = 10;
    int height = 10;
    Dimension dimension = new Dimension(width, length, height);

    int floor = 2;
    RoomFloor roomFloor = new RoomFloor(floor);

    Room room = roomService.addRoom(id, roomName1, dimension, roomFloor);
    return room;
  }

  private Device createDevice(RoomID id) {
    String name = "Lampada";
    DeviceName deviceName = new DeviceName(name);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");

    Device device = deviceService.addDevice(id, deviceName, deviceStatus, deviceTypeID);
    return device;
  }

  /** Test getLogFromDevice method. */
  @Test
  void shouldGetLogFromDevice_WhenParametersAreValid() {
    // Arrange
    GetLogFromDeviceController getLogFromDeviceController =
        new GetLogFromDeviceController(
            logService, deviceService, logAssembler);

    // Add a house
    House house = createHouse();

    // Add a room
    Room room = createRoom(house.getID());

    // Add a device
    Device device = createDevice(room.getID());
    DeviceID deviceID = device.getID();

    // Add a log
    LogFactoryImpl logFactory = new LogFactoryImpl();
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 0);
    ReadingValue readingValue = new ReadingValue("20");
    SensorID sensorID = new SensorID("1");
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");
    UnitID unitID = new UnitID("C");
    Log log =
        logFactory.createLog(
            device.getID(), sensorID, timeStamp, readingValue, sensorTypeID, unitID);
    logRepository.save(log);

    // Create LogDataDTO
    String timeStart = "2020-03-01T13:45:30";
    String timeEnd = "2022-03-01T13:50:30";
    LogDataDTO logDataDTO = new LogDataDTO(deviceID.toString(), timeStart, timeEnd);

    // Act
    List<LogDTO> logs = getLogFromDeviceController.getLogFromDevice(logDataDTO);

    // Assert
    assertNotNull(logs);
  }

  /** Test getLogFromDevice method when the device does not exist. */
  @Test
  void shouldReturnDeviceNotFound_WhenDeviceDoesNotExist() {
    // Arrange
    GetLogFromDeviceController getLogFromDeviceController =
        new GetLogFromDeviceController(
            logService, deviceService, logAssembler);

    // Add a house
    House house = createHouse();

    // Add a room
    Room room = createRoom(house.getID());

    // Add a log
    LogFactoryImpl logFactory = new LogFactoryImpl();
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 0);
    ReadingValue readingValue = new ReadingValue("20");
    SensorID sensorID = new SensorID("1");
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");
    UnitID unitID = new UnitID("C");
    DeviceID deviceID2 = new DeviceID("2");
    Log log =
        logFactory.createLog(deviceID2, sensorID, timeStamp, readingValue, sensorTypeID, unitID);
    logRepository.save(log);

    // Create LogDataDTO
    String timeStart = "2020-03-01T13:45:30";
    String timeEnd = "2022-03-01T13:50:30";
    LogDataDTO logDataDTO = new LogDataDTO(deviceID2.toString(), timeStart, timeEnd);

    LogDTO logDTO = new LogDTO("Device not found", "", "", "", "", "", "");

    // Act
    List<LogDTO> logs = getLogFromDeviceController.getLogFromDevice(logDataDTO);

    // Assert
    assertEquals(logs.get(0).toString(), logDTO.toString());
  }

  /** Test getLogFromDevice method when timeStart is after timeEnd. */
  @Test
  void shouldReturnInvalidTimePeriod_WhenTimeStartIsAfterTimeEnd() {
    // Arrange
    GetLogFromDeviceController getLogFromDeviceController =
        new GetLogFromDeviceController(
            logService, deviceService, logAssembler);

    // Add a house
    House house = createHouse();

    // Add a room
    Room room = createRoom(house.getID());

    // Add a log
    LogFactoryImpl logFactory = new LogFactoryImpl();
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 0);
    ReadingValue readingValue = new ReadingValue("20");
    SensorID sensorID = new SensorID("1");
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");
    UnitID unitID = new UnitID("C");
    DeviceID deviceID = new DeviceID("2");
    Log log =
        logFactory.createLog(deviceID, sensorID, timeStamp, readingValue, sensorTypeID, unitID);
    logRepository.save(log);

    // Create LogDataDTO
    String timeEnd = "2020-03-01T13:45:30";
    String timeStart = "2022-03-01T13:50:30";
    LogDataDTO logDataDTO = new LogDataDTO(deviceID.toString(), timeStart, timeEnd);

    String expected = "Start date cannot be after end date.";

    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> getLogFromDeviceController.getLogFromDevice(logDataDTO));
    assertEquals(expected, exception.getMessage());
  }

  /** Test when no measurements are available for the given period. */
  @Test
  void shouldReturnNoMeasurementsAvailable_WhenNoMeasurementsForGivenPeriod() {
    // Arrange
    GetLogFromDeviceController getLogFromDeviceController =
        new GetLogFromDeviceController(
            logService, deviceService, logAssembler);

    // Add a house
    House house = createHouse();

    // Add a room
    Room room = createRoom(house.getID());

    // Add a device
    Device device = createDevice(room.getID());
    DeviceID deviceID = device.getID();

    // Add a log
    LogFactoryImpl logFactory = new LogFactoryImpl();
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 0);
    ReadingValue readingValue = new ReadingValue("20");
    SensorID sensorID = new SensorID("1");
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");
    UnitID unitID = new UnitID("C");
    DeviceID deviceID2 = new DeviceID("2");
    Log log =
        logFactory.createLog(deviceID2, sensorID, timeStamp, readingValue, sensorTypeID, unitID);
    logRepository.save(log);

    // Create LogDataDTO
    String timeStart = "2020-03-01T13:45:30";
    String timeEnd = "2022-03-01T13:50:30";
    LogDataDTO logDataDTO = new LogDataDTO(deviceID.toString(), timeStart, timeEnd);

    LogDTO logDTO = new LogDTO("No logs found", "", "", "", "", "", "");

    // Act
    List<LogDTO> logs = getLogFromDeviceController.getLogFromDevice(logDataDTO);

    // Assert
    assertEquals(logs.get(0).toString(), logDTO.toString());
  }

  /**
   * Test when 2 devices are created, one log is created for Device 1, one logs fo Device 2
   * Controller calls for Device 2 Get logs should return a list with only one element.
   */
  @Test
  void shouldReturnLogFromCorrectDeviceOnly_WhenThereAreMultipleDevicesInRoom() {
    // Arrange
    GetLogFromDeviceController getLogFromDeviceController =
        new GetLogFromDeviceController(
            logService, deviceService, logAssembler);

    // Add a house
    House house = createHouse();

    // Add a room
    Room room = createRoom(house.getID());

    // Add a device
    Device device = createDevice(room.getID());
    DeviceID deviceID = device.getID();
    Device deviceTwo = createDevice(room.getID());
    DeviceID deviceIDTwo = deviceTwo.getID();

    // Add a log
    LogFactoryImpl logFactory = new LogFactoryImpl();
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 0);
    ReadingValue readingValue = new ReadingValue("20");
    SensorID sensorID = new SensorID("1");
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");
    UnitID unitID = new UnitID("C");
    Log log =
        logFactory.createLog(deviceID, sensorID, timeStamp, readingValue, sensorTypeID, unitID);
    logRepository.save(log);

    // Add a log to the second device
    Log logTwo = logFactory.createLog(deviceIDTwo, sensorID, timeStamp, readingValue, sensorTypeID, unitID);
    logRepository.save(logTwo);

    // Create LogDataDTO
    String timeStart = "2020-03-01T13:45:30";
    String timeEnd = "2022-03-01T13:50:30";
    LogDataDTO logDataDTO = new LogDataDTO(deviceID.toString(), timeStart, timeEnd);

    LogDTO logDTO = new LogDTO("No logs found", "", "", "", "", "", "");

    // Act
    List<LogDTO> logs = getLogFromDeviceController.getLogFromDevice(logDataDTO);

    // Assert
    assertEquals(1, logs.size());
  }
}