package SmartHomeDDD.controller;

import SmartHomeDDD.DTO.DeviceDTO;
import SmartHomeDDD.DTO.RoomDTO;
import SmartHomeDDD.assembler.DeviceAssembler;
import SmartHomeDDD.assembler.RoomAssembler;
import SmartHomeDDD.domain.Device.Device;
import SmartHomeDDD.domain.Device.DeviceFactoryImpl;
import SmartHomeDDD.domain.House.House;
import SmartHomeDDD.domain.House.HouseFactoryImpl;
import SmartHomeDDD.domain.Room.RoomFactoryImpl;
import SmartHomeDDD.domain.Room.Room;
import SmartHomeDDD.repository.DeviceRepository;
import SmartHomeDDD.repository.HouseRepository;
import SmartHomeDDD.repository.RoomRepository;
import SmartHomeDDD.service.DeviceService;
import SmartHomeDDD.service.HouseService;
import SmartHomeDDD.service.RoomService;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetDevicesFromRoomControllerTest {

    /**
     * Test to check if the GetDevicesFromRoomController constructor instantiates the controller correctly.
     */
    @Test
    void shouldInstantiateGetDevicesFromRoomController_WhenParametersAreValid() {
        //Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        DeviceAssembler deviceAssembler = new DeviceAssembler();

        //Act
        GetDevicesFromRoomController getDevicesFromRoomController = new GetDevicesFromRoomController(roomService, deviceService, roomAssembler, deviceAssembler);

        //Assert
        assertNotNull(getDevicesFromRoomController);

    }

    /**
     * Test to check if the GetDevicesFromRoomController constructor throws an exception when the room service is null.
     */
    @Test
    void shouldThrowException_WhenRoomServiceIsNull() {
        //Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomAssembler roomAssembler = new RoomAssembler();
        RoomService roomService = null;

        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        DeviceAssembler deviceAssembler = new DeviceAssembler();

        String expectedMessage = "RoomService is required";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GetDevicesFromRoomController(roomService, deviceService, roomAssembler, deviceAssembler));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to check if the GetDevicesFromRoomController constructor throws an exception when the device service is null.
     */
    @Test
    void shouldThrowException_WhenDeviceServiceIsNull() {
        //Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        DeviceService deviceService = null;

        DeviceAssembler deviceAssembler = new DeviceAssembler();

        String expectedMessage = "DeviceService is required";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GetDevicesFromRoomController(roomService, deviceService, roomAssembler, deviceAssembler));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to check if the GetDevicesFromRoomController constructor throws an exception when the device assembler is null.
     */
    @Test
    void shouldThrowException_WhenRoomAssemblerIsNull() {
        //Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = null;
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        DeviceAssembler deviceAssembler = new DeviceAssembler();

        String expectedMessage = "RoomAssembler is required";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GetDevicesFromRoomController(roomService, deviceService, roomAssembler, deviceAssembler));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to check if the GetDevicesFromRoomController constructor throws an exception when the device assembler is null.
     */
    @Test
    void shouldThrowException_WhenDeviceAssemblerIsNull() {
        //Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        DeviceAssembler deviceAssembler = null;

        String expectedMessage = "DeviceAssembler is required";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GetDevicesFromRoomController(roomService, deviceService, roomAssembler, deviceAssembler));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }


    /**
     * Test to get rooms from a house.
     */
    @Test
    void shouldGetRoomsFromHouse_WhenGivenValidHouseID() {
        //Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();

        HouseService houseService = new HouseService(houseFactory, houseRepository);
        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();

        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();

        GetDevicesFromRoomController getDevicesFromRoomController = new GetDevicesFromRoomController(roomService, deviceService, roomAssembler, deviceAssembler);

        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";

        Address newAddress = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

        double latitude = 41.178;
        double longitude = -8.608;
        GPS newGPS = new GPS(latitude, longitude);

        House house = houseService.addHouse(newAddress, newGPS);

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
        List<RoomDTO> roomsDTOList = getDevicesFromRoomController.getRooms();

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
    void shouldThrowException_WhenRoomIDDoesNotExistInRepository() {
        // Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        HouseService houseService = new HouseService(houseFactory, houseRepository);

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();

        GetDevicesFromRoomController getDevicesFromRoomController = new GetDevicesFromRoomController(roomService, deviceService, roomAssembler, deviceAssembler);

        String roomID = "123";
        RoomID nonExistentRoomID = new RoomID(roomID);

        String roomName = "Quarto da Maria";
        String dimension = "10x10x10";
        String floor = "2";
        RoomDTO roomDTO = new RoomDTO(roomName, dimension, floor, nonExistentRoomID.toString());

        String expectedMessage = "Room with ID " + nonExistentRoomID + " not found.";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> getDevicesFromRoomController.getDevicesFromRoom(roomDTO));

        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to get devices from a room.
     */
    @Test
    void shouldGetDevicesFromRoom_WhenParametersAreValid() {
        //Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        HouseRepository houseRepository = new HouseRepository();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        HouseService houseService = new HouseService(houseFactory, houseRepository);

        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        GetDevicesFromRoomController getDevicesFromRoomController = new GetDevicesFromRoomController(roomService, deviceService, roomAssembler, deviceAssembler);

        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";

        Address newAddress = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

        double latitude = 41.178;
        double longitude = -8.608;
        GPS newGPS = new GPS(latitude, longitude);

        House house = houseService.addHouse(newAddress, newGPS);

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

        List<RoomDTO> roomsDTOList = getDevicesFromRoomController.getRooms();
        RoomDTO roomDTO = roomsDTOList.get(0);

        List<Device> devices = deviceService.getDevicesByRoomId(roomID);
        List<DeviceDTO> deviceDTOListExpected = deviceAssembler.domainToDTO(devices);

        String expectedDeviceDTOID = deviceDTOListExpected.get(0).deviceID;
        String expectedDeviceDTOName = deviceDTOListExpected.get(0).deviceName;
        String expectedDeviceDTOID2 = deviceDTOListExpected.get(1).deviceID;
        String expectedDeviceDTOName2 = deviceDTOListExpected.get(1).deviceName;

        List<String> expectedDeviceDTOList = List.of(expectedDeviceDTOID, expectedDeviceDTOName, expectedDeviceDTOID2, expectedDeviceDTOName2);

        //Act
        List<DeviceDTO> devicesDTOList = getDevicesFromRoomController.getDevicesFromRoom(roomDTO);

        String actualDeviceDTOID = devicesDTOList.get(0).deviceID;
        String actualDeviceDTOName = devicesDTOList.get(0).deviceName;
        String actualDeviceDTOID2 = devicesDTOList.get(1).deviceID;
        String actualDeviceDTOName2 = devicesDTOList.get(1).deviceName;

        List<String> actualDeviceDTOList = List.of(actualDeviceDTOID, actualDeviceDTOName, actualDeviceDTOID2, actualDeviceDTOName2);

        //Assert
        assertEquals(expectedDeviceDTOList, actualDeviceDTOList);
    }


}