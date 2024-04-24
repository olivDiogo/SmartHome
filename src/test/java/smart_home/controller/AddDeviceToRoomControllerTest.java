package smart_home.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import smart_home.ddd.IAggregateRoot;
import smart_home.ddd.IAssembler;
import smart_home.domain.device.IDeviceFactory;
import smart_home.domain.house.IHouseFactory;
import smart_home.domain.repository.IDeviceRepository;
import smart_home.domain.repository.IHouseRepository;
import smart_home.domain.repository.IRoomRepository;
import smart_home.domain.room.IRoomFactory;
import smart_home.domain.service.IDeviceService;
import smart_home.domain.service.IHouseService;
import smart_home.domain.service.IRoomService;
import smart_home.mapper.DeviceAssembler;
import smart_home.mapper.RoomAssembler;
import smart_home.domain.device.Device;
import smart_home.domain.device.DeviceFactoryImpl;
import smart_home.domain.house.House;
import smart_home.domain.house.HouseFactoryImpl;
import smart_home.domain.room.Room;
import smart_home.domain.room.RoomFactoryImpl;
import smart_home.dto.DeviceDTO;
import smart_home.dto.DeviceDataDTO;
import smart_home.dto.RoomDTO;
import smart_home.persistence.mem.DeviceRepository;
import smart_home.persistence.mem.HouseRepository;
import smart_home.persistence.mem.RoomRepository;
import smart_home.service.DeviceServiceImpl;
import smart_home.service.HouseServiceImpl;
import smart_home.service.RoomServiceImpl;
import smart_home.value_object.*;

import java.util.List;

class AddDeviceToRoomControllerTest {

  /** Test to verify if the AddDeviceToRoomController is being instantiated correctly. */
  @Test
  void shouldInstantiateAddDeviceToRoomController_WhenParametersAreValid() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = new HouseRepository();

    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();

    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    // Act
    AddDeviceToRoomController addDeviceToRoomController =
        new AddDeviceToRoomController(
            roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler);

    // Assert
    assertNotNull(addDeviceToRoomController);
  }

  /** Test to verify if an exception is thrown when the RoomService is null. */
  @Test
  void shouldThrowException_WhenRoomServiceIsNull() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IRoomService roomServiceImpl = null;

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();

    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    String expectedMessage = "Please enter a valid room service.";

    // Act
    Throwable exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddDeviceToRoomController(
                    roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /** Test to verify if an exception is thrown when the RoomAssembler is null. */
  @Test
  void shouldThrowException_WhenRoomAssemblerIsNull() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, new RoomFactoryImpl(), new HouseRepository());
    IAssembler<Room, RoomDTO> roomAssembler = null;

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();

    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    String expectedMessage = "Please enter a valid room assembler.";

    // Act
    Throwable exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddDeviceToRoomController(
                    roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /** Test to verify if an exception is thrown when the DeviceService is null. */
  @Test
  void shouldThrowException_WhenDeviceServiceIsNull() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, new RoomFactoryImpl(), new HouseRepository());
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IDeviceService deviceServiceImpl = null;

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    String expectedMessage = "Please enter a valid device service.";

    // Act
    Throwable exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddDeviceToRoomController(
                    roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /** Test to verify if an exception is thrown when the DeviceAssembler is null. */
  @Test
  void shouldThrowException_WhenDeviceAssemblerIsNull() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, new RoomFactoryImpl(), new HouseRepository());
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();

    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
    IAssembler<Device, DeviceDTO> deviceAssembler = null;

    String expectedMessage = "Please enter a valid device assembler.";

    // Act
    Throwable exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddDeviceToRoomController(
                    roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /** Tests retrieving a list of RoomDTOs, checking if the returned data matches the expected. */
  @Test
  void shouldReturnListOfRoomDTOs_WhenGetAllRoomsIsCalled() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = new HouseRepository();

    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();

    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    AddDeviceToRoomController addDeviceToRoomController =
        new AddDeviceToRoomController(
            roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler);

    IHouseFactory houseFactory = new HouseFactoryImpl();
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

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

    HouseID houseID = house.getID();

    RoomName roomName = new RoomName("Living Room");

    int width = 10;
    int length = 10;
    int height = 10;

    Dimension dimension = new Dimension(width, length, height);

    int floor = 1;
    RoomFloor roomFloor = new RoomFloor(floor);

    roomServiceImpl.addRoom(houseID, roomName, dimension, roomFloor);

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

  /** Asserts that an exception is thrown when the list of rooms is empty. */
  @Test
  void shouldThrowException_WhenListOfRoomsIsEmpty() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = new HouseRepository();

    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();

    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

    IAssembler<Device,DeviceDTO> deviceAssembler= new DeviceAssembler();

    AddDeviceToRoomController addDeviceToRoomController =
        new AddDeviceToRoomController(
            roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler);

    // Act + Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          addDeviceToRoomController.getAllRooms();
        });
  }

  /** Confirms that a DeviceDTO is returned correctly when a device is added to a room. */
  @Test
  void shouldReturnDeviceDTO_WhenAddDeviceToRoomIsCalled() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = new HouseRepository();

    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IHouseFactory houseFactory = new HouseFactoryImpl();
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

    // Room
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
        new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    IAggregateRoot<HouseID> house = houseServiceImpl.addHouse(newAddress, newGPS);

    HouseID houseID = house.getID();

    RoomName roomName = new RoomName("Living Room");

    int width = 10;
    int length = 10;
    int height = 10;

    Dimension dimension = new Dimension(width, length, height);

    int floor = 1;
    RoomFloor roomFloor = new RoomFloor(floor);

    roomServiceImpl.addRoom(houseID, roomName, dimension, roomFloor);

    List<Room> rooms = roomRepository.findAll();

    RoomDTO roomDTO = roomAssembler.domainToDTO(rooms).get(0);

    // VOs
    String roomID = roomDTO.roomId;
    String deviceName = "Lamp";
    boolean deviceStatus = true;
    String deviceTypeID = "1";

    RoomID roomIdVO = new RoomID(roomID);
    DeviceName deviceNameVO = new DeviceName(deviceName);
    DeviceStatus deviceStatusVO = new DeviceStatus(deviceStatus);
    DeviceTypeID deviceTypeIDVO = new DeviceTypeID(deviceTypeID);

    // Device
    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

    Device device =
        deviceServiceImpl.addDevice(roomIdVO, deviceNameVO, deviceStatusVO, deviceTypeIDVO);

    // DeviceDTO
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();
    AddDeviceToRoomController addDeviceToRoomController =
        new AddDeviceToRoomController(
            roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler);

    DeviceDTO expectedDeviceDTO = deviceAssembler.domainToDTO(device);

    DeviceDataDTO deviceDataDTO = new DeviceDataDTO(deviceTypeID, deviceName, deviceStatus, roomID);

    // Act
    DeviceDTO deviceDTO = addDeviceToRoomController.addDeviceToRoom(deviceDataDTO);

    // Assert
    assertEquals(expectedDeviceDTO.deviceName, deviceDTO.deviceName);
  }

  /** Ensures that an exception is thrown when the specified room does not exist. */
  @Test
  void shouldThrowException_WhenRoomDoesNotExist() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = new HouseRepository();

    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();

    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

    IAssembler<Device,DeviceDTO> deviceAssembler = new DeviceAssembler();

    AddDeviceToRoomController addDeviceToRoomController =
        new AddDeviceToRoomController(
            roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler);

    DeviceDataDTO deviceDataDTO = new DeviceDataDTO("1", "Lamp", true, "1");

    // Act + Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          addDeviceToRoomController.addDeviceToRoom(deviceDataDTO);
        });
  }
}
