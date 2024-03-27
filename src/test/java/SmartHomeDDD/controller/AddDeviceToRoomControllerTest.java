package SmartHomeDDD.controller;

import SmartHomeDDD.DTO.RoomDTO;
import SmartHomeDDD.assembler.DeviceAssembler;
import SmartHomeDDD.assembler.RoomAssembler;
import SmartHomeDDD.domain.Device.ImpDeviceFactory;
import SmartHomeDDD.domain.House.House;
import SmartHomeDDD.domain.House.ImpHouseFactory;
import SmartHomeDDD.domain.Room.ImpRoomFactory;
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

import static org.junit.Assert.assertEquals;

class AddDeviceToRoomControllerTest {

    /**
     * Test to verify if the AddDeviceToRoomController is being instantiated correctly.
     */
    @Test
    void shouldInstantiateAddDeviceToRoomController_WhenParametersAreValid() {
        // Arrange
        RoomRepository roomRepository = new RoomRepository();
        ImpRoomFactory roomFactory = new ImpRoomFactory();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        DeviceRepository deviceRepository = new DeviceRepository();
        ImpDeviceFactory deviceFactory = new ImpDeviceFactory();

        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        DeviceAssembler deviceAssembler = new DeviceAssembler();

        // Act
        AddDeviceToRoomController addDeviceToRoomController = new AddDeviceToRoomController(roomService, roomAssembler, deviceService, deviceAssembler);
    }

    @Test
    void shouldReturnListOfRoomDTOs_WhenGetAllRoomsIsCalled() {
        // Arrange
        RoomRepository roomRepository = new RoomRepository();
        ImpRoomFactory roomFactory = new ImpRoomFactory();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        DeviceRepository deviceRepository = new DeviceRepository();
        ImpDeviceFactory deviceFactory = new ImpDeviceFactory();

        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        DeviceAssembler deviceAssembler = new DeviceAssembler();

        AddDeviceToRoomController addDeviceToRoomController = new AddDeviceToRoomController(roomService, roomAssembler, deviceService, deviceAssembler);

        ImpHouseFactory houseFactory = new ImpHouseFactory();
        HouseService houseService = new HouseService(houseFactory, houseRepository);

        String street = "Rua Do Isep";
        String doorNumber = "122A";
        Address newAddress = new Address(street, doorNumber);

        int zipCodePrefix = 4000;
        int zipCodeSuffix = 129;
        ZipCode newZipCode = new ZipCode(zipCodePrefix, zipCodeSuffix);

        double latitude = 41.178;
        double longitude = -8.608;
        GPS newGPS = new GPS(latitude, longitude);

        House house = houseService.addHouse(newAddress, newZipCode, newGPS);

        HouseID houseID = house.getID();

        RoomName roomName = new RoomName("Living Room");

        int width = 10;
        int length = 10;
        int height = 10;

        Dimension dimension = new Dimension(width, length, height);

        int floor = 1;
        RoomFloor roomFloor = new RoomFloor(floor);

        roomService.addRoom(houseID, roomName, dimension, roomFloor);

        List<Room> rooms = roomRepository.findAll();

        List<RoomDTO> expectedRoomDTOs = roomAssembler.domainToDTO(rooms);
        String expectedRoomName = expectedRoomDTOs.get(0).roomName;
        String expectedRoomID = expectedRoomDTOs.get(0).roomId;

        List<String> expectedList = List.of(expectedRoomName, expectedRoomID);

        // Act
        List<RoomDTO> roomDTOs = addDeviceToRoomController.getAllRooms();

        // Assert
        String actualRoomName = roomDTOs.get(0).roomName;
        String actualRoomID = roomDTOs.get(0).roomId;
        List<String> actualList = List.of(actualRoomName, actualRoomID);

        assertEquals(expectedList, actualList);

    }


}
