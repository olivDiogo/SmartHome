package SmartHomeDDD.service;

import SmartHomeDDD.valueObject.*;
import SmartHomeDDD.assembler.RoomAssembler;
import SmartHomeDDD.domain.House.House;
import SmartHomeDDD.domain.Room.Room;
import SmartHomeDDD.domain.Room.RoomFactory;
import SmartHomeDDD.repository.HouseRepository;
import SmartHomeDDD.repository.RoomRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoomServiceTest {

    /**
     * Test the constructor of the RoomService class.
     */
    @Test
    public void shouldInstantiateRoomService_whenGivenValidParameters() {
        // Arrange
        RoomService roomService;
        RoomRepository roomRepository = mock(RoomRepository.class);
        RoomFactory roomFactory = mock(RoomFactory.class);
        RoomAssembler roomAssembler = mock(RoomAssembler.class);
        HouseRepository houseRepository = mock(HouseRepository.class);

        // Act
        roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        // Assert
        assertNotNull(roomService);
    }

    /**
     * Test the addRoom method of the RoomService class with a valid houseID, roomName, dimension and roomFloor.
     */
    @Test
    public void shouldAddARoom_WhenGivenValidParameters() {
        // Arrange
        RoomService roomService;
        RoomRepository roomRepository = mock(RoomRepository.class);
        RoomFactory roomFactory = mock(RoomFactory.class);
        RoomAssembler roomAssembler = mock(RoomAssembler.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);
        House mockHouse = mock(House.class);
        Room mockRoom = mock(Room.class);
        when(houseRepository.ofIdentity(any(HouseID.class))).thenReturn(Optional.of(mockHouse));
        when(roomFactory.createRoom(any(HouseID.class), any(RoomName.class), any(Dimension.class), any(RoomFloor.class))).thenReturn(mockRoom);

        // Act
        Room room = roomService.addRoom(houseID, roomName, dimension, roomFloor);
        // Assert
        assertNotNull(room);
    }

    /**
     * Test the addRoom method of the RoomService class with an invalid houseID.
     */
    @Test
    public void shouldThrowException_whenHouseIDIsInvalid() {
        // Arrange
        RoomService roomService;
        RoomRepository roomRepository = mock(RoomRepository.class);
        RoomFactory roomFactory = mock(RoomFactory.class);
        RoomAssembler roomAssembler = mock(RoomAssembler.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);
        when(houseRepository.ofIdentity(any(HouseID.class))).thenReturn(Optional.empty());

        // Act+Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> roomService.addRoom(houseID, roomName, dimension, roomFloor));
        assertEquals("House with ID " + houseID + " not found.", exception.getMessage());
    }
    /**
     * Test the getRooms method of the RoomService class.
     */
    @Test
    public void shouldReturnAllRooms_whenGetRoomsIsCalled() {
        // Arrange
        RoomService roomService;
        RoomRepository roomRepository = mock(RoomRepository.class);
        RoomFactory roomFactory = mock(RoomFactory.class);
        RoomAssembler roomAssembler = mock(RoomAssembler.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        Room mockRoom = mock(Room.class);
        when(roomRepository.findAll()).thenReturn(List.of(mockRoom));

        // Act
        List<Room> rooms = roomService.getRooms();

        // Assert
        assertNotNull(rooms);
        assertEquals(1, rooms.size());
    }
    /**
     * Test the getRooms method of the RoomService class when there are no rooms.
     */
    @Test
    public void shouldReturnEmptyList_whenGetRoomsIsCalledAndThereAreNoRooms() {
        // Arrange
        RoomService roomService;
        RoomRepository roomRepository = mock(RoomRepository.class);
        RoomFactory roomFactory = mock(RoomFactory.class);
        RoomAssembler roomAssembler = mock(RoomAssembler.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        when(roomRepository.findAll()).thenReturn(List.of());

        // Act
        List<Room> rooms = roomService.getRooms();

        // Assert
        assertNotNull(rooms);
        assertEquals(0, rooms.size());
    }
    /**
     * Test the getRooms method of the RoomService class when there are multiple rooms.
     */
    @Test
    public void shouldReturnAllRooms_whenGetRoomsIsCalledAndThereAreMultipleRooms() {
        // Arrange
        RoomService roomService;
        RoomRepository roomRepository = mock(RoomRepository.class);
        RoomFactory roomFactory = mock(RoomFactory.class);
        RoomAssembler roomAssembler = mock(RoomAssembler.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        Room mockRoom1 = mock(Room.class);
        Room mockRoom2 = mock(Room.class);
        when(roomRepository.findAll()).thenReturn(List.of(mockRoom1, mockRoom2));

        // Act
        List<Room> rooms = roomService.getRooms();

        // Assert
        assertNotNull(rooms);
        assertEquals(2, rooms.size());
    }

    /**
     * Test the getRoomById method of the RoomService class with a valid roomID.
     */
    @Test
    public void shouldReturnRoom_whenGetRoomByIdIsCalledWithValidRoomID() {
        // Arrange
        RoomService roomService;
        RoomRepository roomRepository = mock(RoomRepository.class);
        RoomFactory roomFactory = mock(RoomFactory.class);
        RoomAssembler roomAssembler = mock(RoomAssembler.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        RoomID roomID = mock(RoomID.class);
        Room mockRoom = mock(Room.class);
        when(roomRepository.ofIdentity(any(RoomID.class))).thenReturn(Optional.of(mockRoom));

        // Act
        Optional<Room> room = roomService.getRoomById(roomID);

        // Assert
        assertNotNull(room);
        assertTrue(room.isPresent());
        assertEquals(mockRoom, room.get());
    }

    /**
     * Test the getRoomById method of the RoomService class with an invalid roomID.
     */
    @Test
    public void shouldReturnEmptyOptional_whenGetRoomByIdIsCalledWithInvalidRoomID() {
        // Arrange
        RoomService roomService;
        RoomRepository roomRepository = mock(RoomRepository.class);
        RoomFactory roomFactory = mock(RoomFactory.class);
        RoomAssembler roomAssembler = mock(RoomAssembler.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        RoomID roomID = mock(RoomID.class);
        when(roomRepository.ofIdentity(any(RoomID.class))).thenReturn(Optional.empty());

        // Act
        Optional<Room> room = roomService.getRoomById(roomID);

        // Assert
        assertNotNull(room);
        assertTrue(room.isEmpty());
    }
}