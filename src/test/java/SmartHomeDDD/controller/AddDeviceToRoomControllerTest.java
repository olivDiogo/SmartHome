package SmartHomeDDD.controller;

import SmartHomeDDD.DTO.DeviceDTO;
import SmartHomeDDD.DTO.RoomDTO;
import SmartHomeDDD.assembler.DeviceAssembler;
import SmartHomeDDD.assembler.RoomAssembler;
import SmartHomeDDD.domain.Device.Device;
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

import static org.junit.Assert.*;

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

        // Assert
        assertNotNull(addDeviceToRoomController);
    }

    /**
     * Tests retrieving a list of RoomDTOs, checking if the returned data matches the expected.
     */
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
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
        HouseService houseService = new HouseService(houseFactory, houseRepository);

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

    /**
     * Asserts that an exception is thrown when the list of rooms is empty.
     */
    @Test
    void shouldThrowException_WhenListOfRoomsIsEmpty(){
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

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {addDeviceToRoomController.getAllRooms();
        });

    }
    /**
     * Confirms that a DeviceDTO is returned correctly when a device is added to a room.
     */
    @Test
    void shouldReturnDeviceDTO_WhenAddDeviceToRoomIsCalled() {
        // Arrange
        RoomRepository roomRepository = new RoomRepository();
        ImpRoomFactory roomFactory = new ImpRoomFactory();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        ImpHouseFactory houseFactory = new ImpHouseFactory();
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
        HouseService houseService = new HouseService(houseFactory, houseRepository);

        //Room
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

        RoomName roomName = new RoomName("Living Room");

        int width = 10;
        int length = 10;
        int height = 10;

        Dimension dimension = new Dimension(width, length, height);

        int floor = 1;
        RoomFloor roomFloor = new RoomFloor(floor);

        roomService.addRoom(houseID, roomName, dimension, roomFloor);

        List<Room> rooms = roomRepository.findAll();

        RoomDTO roomDTO = roomAssembler.domainToDTO(rooms).get(0);

                               //VOs
        String roomID = roomDTO.roomId;
        String deviceName = "Lamp";
        boolean deviceStatus = true;
        String deviceTypeID = "1";

        RoomID roomIdVO = new RoomID(roomID);
        DeviceName deviceNameVO = new DeviceName(deviceName);
        DeviceStatus deviceStatusVO = new DeviceStatus(deviceStatus);
        DeviceTypeID deviceTypeIDVO = new DeviceTypeID(deviceTypeID);

                               //Device
        DeviceRepository deviceRepository = new DeviceRepository();
        ImpDeviceFactory deviceFactory = new ImpDeviceFactory();
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        Device device = deviceService.addDevice(roomIdVO, deviceNameVO, deviceStatusVO, deviceTypeIDVO);

                               //DeviceDTO
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        AddDeviceToRoomController addDeviceToRoomController = new AddDeviceToRoomController(roomService, roomAssembler, deviceService, deviceAssembler);

        DeviceDTO expectedDeviceDTO = deviceAssembler.domainToDTO(device);

        // Act
        DeviceDTO deviceDTO = addDeviceToRoomController.addDeviceToRoom(roomID, deviceName, deviceStatus, deviceTypeID);

        // Assert
        assertEquals(expectedDeviceDTO.deviceName, deviceDTO.deviceName);
    }
    /**
     * Ensures that an exception is thrown when the specified room does not exist.
     */
    @Test
    void shouldThrowException_WhenRoomDoesNotExist(){
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

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {addDeviceToRoomController.addDeviceToRoom("1", "Lamp", true, "1");
        });
    }


}
