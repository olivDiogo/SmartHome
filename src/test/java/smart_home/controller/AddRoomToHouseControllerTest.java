package smart_home.controller;

import org.junit.jupiter.api.Test;
import smart_home.ddd.IAssembler;
import smart_home.domain.house.IHouseFactory;
import smart_home.domain.repository.IHouseRepository;
import smart_home.domain.repository.IRoomRepository;
import smart_home.domain.room.IRoomFactory;
import smart_home.domain.service.IHouseService;
import smart_home.domain.service.IRoomService;
import smart_home.mapper.RoomAssembler;
import smart_home.domain.house.House;
import smart_home.domain.house.HouseFactoryImpl;
import smart_home.domain.room.Room;
import smart_home.domain.room.RoomFactoryImpl;
import smart_home.dto.RoomDTO;
import smart_home.persistence.mem.HouseRepository;
import smart_home.persistence.mem.RoomRepository;
import smart_home.service.HouseServiceImpl;
import smart_home.service.RoomServiceImpl;
import smart_home.value_object.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for the AddRoomToHouseController class.
 */
class AddRoomToHouseControllerTest {

  /**
   * Tests the instantiation of AddRoomToHouseController with valid parameters.
   */
  @Test
  void shouldInstantiateAddRoomToHouseController_WhenParametersAreValid() {
    // Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);

    //Act
    AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(
        roomServiceImpl, roomAssembler);

    // Assert
    assertNotNull(addRoomToHouseController);

  }

  /**
   * Tests throwing an exception when house is not found.
   */
  @Test
  void shouldThrowException_WhenHouseIsNotFound() {
    // Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(
        roomServiceImpl, roomAssembler);

    // Act
    assertThrows(IllegalArgumentException.class, () -> {
      addRoomToHouseController.addRoom("1", "Living Room", 1, 10, 10, 10);
    });

  }

  /**
   * Tests returning a RoomDTO when a room is successfully added.
   */
  @Test
  void shouldReturnRoomDTO_WhenRoomIsAdded() {
    // Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IHouseFactory houseFactory = new HouseFactoryImpl();
    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

    String street = "Rua Isep";
    String doorNumber = "122A";
    String postalCode = "4000-009";
    String countryCode = "PT";

    Address newAddress = new Address(street, doorNumber, postalCode, countryCode,
        new PostalCodeFactory());

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseServiceImpl.addHouse(newAddress, newGPS);
    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));

    HouseID houseID = house.getID();

    String houseIDS = String.valueOf(houseID);
    String name = "Living Room";
    int floor = 1;
    int width = 10;
    int length = 10;
    int height = 10;

    HouseID houseID1 = new HouseID(houseIDS);
    RoomName roomName = new RoomName(name);
    RoomFloor roomFloor = new RoomFloor(floor);
    Dimension dimension = new Dimension(width, length, height);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(
        roomServiceImpl, roomAssembler);

    Room room = roomServiceImpl.addRoom(houseID1, roomName, dimension, roomFloor);
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));

    RoomDTO expectedRoomDTO = roomAssembler.domainToDTO(room);

    // Act
    RoomDTO roomDTO = addRoomToHouseController.addRoom(String.valueOf(houseID), name, floor, width,
        length, height);

    // Assert
    assertEquals(expectedRoomDTO.roomName, roomDTO.roomName);
  }

  /**
   * Tests throwing an exception when room name is not provided.
   */
  @Test
  void shouldThrowException_WhenRoomNameIsNotProvided() {
    // Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(
        roomServiceImpl, roomAssembler);

    // Act
    assertThrows(IllegalArgumentException.class, () -> {
      addRoomToHouseController.addRoom("1", null, 1, 10, 10, 10);
    });

  }

  /**
   * Tests throwing an exception when room floor is not valid.
   */
  @Test
  void shouldThrowException_WhenRoomFloorIsNotValid() {
    // Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(
        roomServiceImpl, roomAssembler);

    // Act
    assertThrows(IllegalArgumentException.class, () -> {
      addRoomToHouseController.addRoom("1", "Living Room", -1000, 10, 10, 10);
    });
  }

  /**
   * Tests throwing an exception when room width is not valid.
   */
  @Test
  void shouldThrowException_WhenRoomWidthIsNotValid() {
    // Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(
        roomServiceImpl, roomAssembler);

    // Act
    assertThrows(IllegalArgumentException.class, () -> {
      addRoomToHouseController.addRoom("1", "Living Room", 1, -1000, 10, 10);
    });
  }

  /**
   * Tests throwing an exception when room length is not valid.
   */
  @Test
  void shouldThrowException_WhenRoomLengthIsNotValid() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = new HouseRepository();

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(
        roomServiceImpl, roomAssembler);

    // Act
    assertThrows(IllegalArgumentException.class, () -> {
      addRoomToHouseController.addRoom("1", "Living Room", 1, 10, -1000, 10);
    });
  }

  /**
   * Tests throwing an exception when room height is not valid.
   */
  @Test
  void shouldThrowException_WhenRoomHeightIsNotValid() {
    // Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(
        roomServiceImpl, roomAssembler);

    // Act
    assertThrows(IllegalArgumentException.class, () -> {
      addRoomToHouseController.addRoom("1", "Living Room", 1, 10, 10, -1000);
    });
  }

  /**
   * Tests throwing an exception when room service is null.
   */
  @Test
  void shouldThrowException_WhenRoomServiceIsNull () {
    //Arrange
    IRoomService roomService = null;
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();

    String expectedMessage = "Room service is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new AddRoomToHouseController(roomService, roomAssembler);
    });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Tests throwing an exception when room assembler is null.
   */
  @Test
  void shouldThrowException_WhenRoomAssemblerIsNull () {
    //Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IRoomService roomService = new RoomServiceImpl(roomRepository, roomFactory, mock(IHouseRepository.class));
    IAssembler<Room, RoomDTO> roomAssembler = null;

    String expectedMessage = "Room assembler is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new AddRoomToHouseController(roomService, roomAssembler);
    });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }
}