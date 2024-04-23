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
import smart_home.dto.DeviceDTO;
import smart_home.dto.LogDTO;
import smart_home.dto.LogDataDTO;
import smart_home.dto.RoomDTO;
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

    /**
     * Test for the getRooms method.
     */
    @Test
    void shouldGetRoomsFromHouse_WhenGivenValidHouseID() {
        // Arrange
        LogRepository logRepository = new LogRepository();
        LogServiceImpl logService = new LogServiceImpl(logRepository);
        LogAssembler logAssembler = new LogAssembler();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
        RoomServiceImpl roomService = new RoomServiceImpl(roomRepository, roomFactory, roomAssembler, houseRepository);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
        GetLogFromDeviceController getLogFromDeviceController = new GetLogFromDeviceController(logService, deviceService, logRepository, deviceRepository, logAssembler, roomService, roomAssembler, deviceAssembler);

        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";

        Address newAddress = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

        double latitude = 41.178;
        double longitude = -8.608;
        GPS newGPS = new GPS(latitude, longitude);

        House house = houseServiceImpl.addHouse(newAddress, newGPS);

        HouseID houseID = house.getID();
        String name2 = "Quarto da Maria";
        RoomName roomName2 = new RoomName(name2);
        String name1 = "Quarto do Joao";
        RoomName roomName1 = new RoomName(name1);

        int width = 10;
        int length = 10;
        int height = 10;
        Dimension dimension = new Dimension(width, length, height);

        int floor = 2;
        RoomFloor roomFloor = new RoomFloor(floor);

        roomService.addRoom(houseID, roomName1, dimension, roomFloor);
        roomService.addRoom(houseID, roomName2, dimension, roomFloor);

        List<Room> rooms = roomRepository.findAll();

        List<RoomDTO> expectedRoomsDTOList = roomAssembler.domainToDTO(rooms);
        String expectedRoomDTOName1 = expectedRoomsDTOList.get(0).roomName;
        String expectedRoomDTOID1 = expectedRoomsDTOList.get(0).roomId;
        String expectedRoomDTOName2 = expectedRoomsDTOList.get(1).roomName;
        String expectedRoomDTOID2 = expectedRoomsDTOList.get(1).roomId;

        List<String> expectedList = List.of(expectedRoomDTOName1, expectedRoomDTOID1, expectedRoomDTOName2, expectedRoomDTOID2);

        //Act
        List<RoomDTO> roomsDTOList = getLogFromDeviceController.getRooms();

        String actualRoomDTOName1 = roomsDTOList.get(0).roomName;
        String actualRoomDTOID1 = roomsDTOList.get(0).roomId;
        String actualRoomDTOName2 = roomsDTOList.get(1).roomName;
        String actualRoomDTOID2 = roomsDTOList.get(1).roomId;

        List<String> actualList = List.of(actualRoomDTOName1, actualRoomDTOID1, actualRoomDTOName2, actualRoomDTOID2);

        //Assert
        assertEquals(expectedList, actualList);
    }

    /**
     * Test to throw an exception when the room ID does not exist in the repository.
     */
    @Test
    void shouldThrowException_WhenRoomIDDoesNotExistInRepository(){
        // Arrange
        LogRepository logRepository = new LogRepository();
        LogServiceImpl logService = new LogServiceImpl(logRepository);
        LogAssembler logAssembler = new LogAssembler();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
        RoomServiceImpl roomService = new RoomServiceImpl(roomRepository, roomFactory, roomAssembler, houseRepository);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
        GetLogFromDeviceController getLogFromDeviceController = new GetLogFromDeviceController(logService, deviceService, logRepository, deviceRepository, logAssembler, roomService, roomAssembler, deviceAssembler);

        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";

        Address newAddress = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

        double latitude = 41.178;
        double longitude = -8.608;
        GPS newGPS = new GPS(latitude, longitude);

        House house = houseServiceImpl.addHouse(newAddress, newGPS);

        HouseID houseID = house.getID();
        String name2 = "Quarto da Maria";
        RoomName roomName2 = new RoomName(name2);
        String name1 = "Quarto do Joao";
        RoomName roomName1 = new RoomName(name1);

        int width = 10;
        int length = 10;
        int height = 10;
        Dimension dimension = new Dimension(width, length, height);

        int floor = 2;
        RoomFloor roomFloor = new RoomFloor(floor);

        RoomDTO roomDTO = new RoomDTO(houseID.toString(), roomName1.toString(), dimension.toString(), roomFloor.toString());

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> getLogFromDeviceController.getDevicesFromRoom(roomDTO));
        assertEquals("Room with ID " + new RoomID(roomDTO.roomId) + " not found.", exception.getMessage());
    }

    /**
     * Test for the getDevicesFromRoom method.
     */
    @Test
    void shouldGetDevicesFromRoom_WhenParametersAreValid() {
        //Arrange
        LogRepository logRepository = new LogRepository();
        LogServiceImpl logService = new LogServiceImpl(logRepository);
        LogAssembler logAssembler = new LogAssembler();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
        RoomServiceImpl roomService = new RoomServiceImpl(roomRepository, roomFactory, roomAssembler, houseRepository);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
        GetLogFromDeviceController getLogFromDeviceController = new GetLogFromDeviceController(logService, deviceService, logRepository, deviceRepository, logAssembler, roomService, roomAssembler, deviceAssembler);

        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";

        Address newAddress = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

        double latitude = 41.178;
        double longitude = -8.608;
        GPS newGPS = new GPS(latitude, longitude);

        House house = houseServiceImpl.addHouse(newAddress, newGPS);

        HouseID houseID = house.getID();
        String name2 = "Quarto da Maria";
        int width = 10;
        int length = 10;
        int height = 10;
        int floor = 2;

        RoomName roomName2 = new RoomName(name2);
        RoomFloor roomFloor = new RoomFloor(floor);
        Dimension dimension = new Dimension(width, length, height);

        Room room2 = roomService.addRoom(houseID, roomName2, dimension, roomFloor);

        RoomID roomID = room2.getID();
        String name1 = "Lampada";
        String nameDevice = "Ar Condicionado";
        DeviceName deviceName = new DeviceName(name1);
        DeviceName deviceName2 = new DeviceName(nameDevice);
        DeviceStatus deviceStatus = new DeviceStatus(true);
        DeviceTypeID deviceTypeID = new DeviceTypeID("1");

        deviceService.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);
        deviceService.addDevice(roomID, deviceName2, deviceStatus, deviceTypeID);

        List<RoomDTO> roomsDTOList = getLogFromDeviceController.getRooms();
        RoomDTO roomDTO = roomsDTOList.get(0);

        List<Device> devices = deviceService.getDevicesByRoomId(roomID);
        List<DeviceDTO> deviceDTOListExpected = deviceAssembler.domainToDTO(devices);

        String expectedDeviceDTOID = deviceDTOListExpected.get(0).deviceID;
        String expectedDeviceDTOName = deviceDTOListExpected.get(0).deviceName;
        String expectedDeviceDTOID2 = deviceDTOListExpected.get(1).deviceID;
        String expectedDeviceDTOName2 = deviceDTOListExpected.get(1).deviceName;

        List<String> expectedDeviceDTOList = List.of(expectedDeviceDTOID, expectedDeviceDTOName, expectedDeviceDTOID2, expectedDeviceDTOName2);

        //Act
        List<DeviceDTO> devicesDTOList = getLogFromDeviceController.getDevicesFromRoom(roomDTO);

        String actualDeviceDTOID = devicesDTOList.get(0).deviceID;
        String actualDeviceDTOName = devicesDTOList.get(0).deviceName;
        String actualDeviceDTOID2 = devicesDTOList.get(1).deviceID;
        String actualDeviceDTOName2 = devicesDTOList.get(1).deviceName;

        List<String> actualDeviceDTOList = List.of(actualDeviceDTOID, actualDeviceDTOName, actualDeviceDTOID2, actualDeviceDTOName2);

        //Assert
        assertEquals(expectedDeviceDTOList, actualDeviceDTOList);
    }

    /**
     * Test getLogFromDevice method.
     */
    @Test
    void shouldGetLogFromDevice_WhenParametersAreValid() {
        // Arrange
        LogRepository logRepository = new LogRepository();
        LogServiceImpl logService = new LogServiceImpl(logRepository);
        LogAssembler logAssembler = new LogAssembler();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        RoomServiceImpl roomService = new RoomServiceImpl(roomRepository, roomFactory, roomAssembler, houseRepository);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
        GetLogFromDeviceController getLogFromDeviceController = new GetLogFromDeviceController(logService, deviceService, logRepository, deviceRepository, logAssembler, roomService, roomAssembler, deviceAssembler);

        // Add a house
        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";
        Address address = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
        GPS gps = new GPS(41.5514, -8.4221);
        House house = houseFactory.createHouse(address, gps);
        houseRepository.save(house);


        // Add a room
        HouseID houseID = house.getID();
        RoomName roomName = new RoomName("Living Room");
        Dimension dimension = new Dimension(10, 10, 10);
        RoomFloor roomFloor = new RoomFloor(1);
        Room room = roomService.addRoom(houseID, roomName, dimension, roomFloor);

        // Add a device
        DeviceName deviceName = new DeviceName("Light bulb");
        DeviceStatus deviceStatus = new DeviceStatus(false);
        DeviceTypeID deviceTypeID = new DeviceTypeID("1");
        Device device = deviceService.addDevice(room.getID(), deviceName, deviceStatus, deviceTypeID);
        DeviceID deviceID = device.getID();

        // Add a log
        LogFactoryImpl logFactory = new LogFactoryImpl();
        LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 0);
        ReadingValue readingValue = new ReadingValue("20");
        SensorID sensorID = new SensorID("1");
        SensorTypeID sensorTypeID = new SensorTypeID("Temperature");
        UnitID unitID = new UnitID("C");
        Log log = logFactory.createLog(device.getID(), sensorID, timeStamp, readingValue, sensorTypeID, unitID);
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

    /**
     * Test getLogFromDevice method when the device does not exist.
     */
    @Test
    void shouldReturnDeviceNotFound_WhenDeviceDoesNotExist() {
        // Arrange
        LogRepository logRepository = new LogRepository();
        LogServiceImpl logService = new LogServiceImpl(logRepository);
        LogAssembler logAssembler = new LogAssembler();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        RoomServiceImpl roomService = new RoomServiceImpl(roomRepository, roomFactory, roomAssembler, houseRepository);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
        GetLogFromDeviceController getLogFromDeviceController = new GetLogFromDeviceController(logService, deviceService, logRepository, deviceRepository, logAssembler, roomService, roomAssembler, deviceAssembler);

        // Add a house
        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";
        Address address = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
        GPS gps = new GPS(41.5514, -8.4221);
        House house = houseFactory.createHouse(address, gps);
        houseRepository.save(house);


        // Add a room
        HouseID houseID = house.getID();
        RoomName roomName = new RoomName("Living Room");
        Dimension dimension = new Dimension(10, 10, 10);
        RoomFloor roomFloor = new RoomFloor(1);
        Room room = roomService.addRoom(houseID, roomName, dimension, roomFloor);

        // Add a device
        /*DeviceName deviceName = new DeviceName("Light bulb");
        DeviceStatus deviceStatus = new DeviceStatus(false);
        DeviceTypeID deviceTypeID = new DeviceTypeID("1");
        Device device = deviceService.addDevice(room.getID(), deviceName, deviceStatus, deviceTypeID);
        DeviceID deviceID = device.getID();
        DeviceID deviceID2 = new DeviceID("2");*/

        // Add a log
        LogFactoryImpl logFactory = new LogFactoryImpl();
        LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 0);
        ReadingValue readingValue = new ReadingValue("20");
        SensorID sensorID = new SensorID("1");
        SensorTypeID sensorTypeID = new SensorTypeID("Temperature");
        UnitID unitID = new UnitID("C");
        DeviceID deviceID2 = new DeviceID("2");
        Log log = logFactory.createLog(deviceID2, sensorID, timeStamp, readingValue, sensorTypeID, unitID);
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
}