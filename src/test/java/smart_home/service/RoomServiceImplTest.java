package smart_home.service;

import org.junit.jupiter.api.Test;
import smart_home.mapper.RoomAssembler;
import smart_home.domain.house.House;
import smart_home.domain.room.IRoomFactory;
import smart_home.domain.room.Room;
import smart_home.persistence.mem.HouseRepository;
import smart_home.persistence.mem.RoomRepository;
import smart_home.value_object.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoomServiceImplTest {

    /**
     * Test the constructor of the RoomService class.
     */
    @Test
    void shouldInstantiateRoomService_whenGivenValidParameters() {
        // Arrange
        RoomServiceImpl roomServiceImpl;
        RoomRepository roomRepository = mock(RoomRepository.class);
        IRoomFactory roomFactory = mock(IRoomFactory.class);
        HouseRepository houseRepository = mock(HouseRepository.class);

        // Act
        roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

        // Assert
        assertNotNull(roomServiceImpl);
    }

    /**
     * Test the addRoom method of the RoomService class with a valid houseID, roomName, dimension and roomFloor.
     */
    @Test
    void shouldAddARoom_WhenGivenValidParameters() {
        // Arrange
        RoomServiceImpl roomServiceImpl;
        RoomRepository roomRepository = mock(RoomRepository.class);
        IRoomFactory roomFactory = mock(IRoomFactory.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);
        House mockHouse = mock(House.class);
        Room mockRoom = mock(Room.class);
        when(houseRepository.ofIdentity(any(HouseID.class))).thenReturn(Optional.of(mockHouse));
        when(roomFactory.createRoom(any(HouseID.class), any(RoomName.class), any(Dimension.class), any(RoomFloor.class))).thenReturn(mockRoom);

        // Act
        Room room = roomServiceImpl.addRoom(houseID, roomName, dimension, roomFloor);
        // Assert
        assertNotNull(room);
    }

    /**
     * Test the addRoom method of the RoomService class with an invalid houseID.
     */
    @Test
    void shouldThrowException_whenHouseIDIsInvalid() {
        // Arrange
        RoomServiceImpl roomServiceImpl;
        RoomRepository roomRepository = mock(RoomRepository.class);
        IRoomFactory roomFactory = mock(IRoomFactory.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);
        when(houseRepository.ofIdentity(any(HouseID.class))).thenReturn(Optional.empty());

        // Act+Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> roomServiceImpl.addRoom(houseID, roomName, dimension, roomFloor));
        assertEquals("House with ID " + houseID + " not found.", exception.getMessage());
    }
    /**
     * Test the getRooms method of the RoomService class.
     */
    @Test
    void shouldReturnAllRooms_whenGetRoomsIsCalled() {
        // Arrange
        RoomServiceImpl roomServiceImpl;
        RoomRepository roomRepository = mock(RoomRepository.class);
        IRoomFactory roomFactory = mock(IRoomFactory.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
        Room mockRoom = mock(Room.class);
        when(roomRepository.findAll()).thenReturn(List.of(mockRoom));

        // Act
        List<Room> rooms = roomServiceImpl.getAllRooms();

        // Assert
        assertNotNull(rooms);
        assertEquals(1, rooms.size());
    }
    /**
     * Test the getRooms method of the RoomService class when there are no rooms.
     */
    @Test
    void shouldReturnEmptyList_whenGetRoomsIsCalledAndThereAreNoRooms() {
        // Arrange
        RoomServiceImpl roomServiceImpl;
        RoomRepository roomRepository = mock(RoomRepository.class);
        IRoomFactory roomFactory = mock(IRoomFactory.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
        when(roomRepository.findAll()).thenReturn(List.of());

        // Act
        List<Room> rooms = roomServiceImpl.getAllRooms();

        // Assert
        assertNotNull(rooms);
        assertEquals(0, rooms.size());
    }
    /**
     * Test the getRooms method of the RoomService class when there are multiple rooms.
     */
    @Test
    void shouldReturnAllRooms_whenGetRoomsIsCalledAndThereAreMultipleRooms() {
        // Arrange
        RoomServiceImpl roomServiceImpl;
        RoomRepository roomRepository = mock(RoomRepository.class);
        IRoomFactory roomFactory = mock(IRoomFactory.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
        Room mockRoom1 = mock(Room.class);
        Room mockRoom2 = mock(Room.class);
        when(roomRepository.findAll()).thenReturn(List.of(mockRoom1, mockRoom2));

        // Act
        List<Room> rooms = roomServiceImpl.getAllRooms();

        // Assert
        assertNotNull(rooms);
        assertEquals(2, rooms.size());
    }

    /**
     * Test the getRoomById method of the RoomService class with a valid roomID.
     */
    @Test
    void shouldReturnRoom_whenGetRoomByIdIsCalledWithValidRoomID() {
        // Arrange
        RoomServiceImpl roomServiceImpl;
        RoomRepository roomRepository = mock(RoomRepository.class);
        IRoomFactory roomFactory = mock(IRoomFactory.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
        RoomID roomID = mock(RoomID.class);
        Room mockRoom = mock(Room.class);
        when(roomRepository.ofIdentity(any(RoomID.class))).thenReturn(Optional.of(mockRoom));

        // Act
        Optional<Room> room = roomServiceImpl.getRoomById(roomID);

        // Assert
        assertNotNull(room);
        assertTrue(room.isPresent());
        assertEquals(mockRoom, room.get());
    }

    /**
     * Test the getRoomById method of the RoomService class with an invalid roomID.
     */
    @Test
    void shouldReturnEmptyOptional_whenGetRoomByIdIsCalledWithInvalidRoomID() {
        // Arrange
        RoomServiceImpl roomServiceImpl;
        RoomRepository roomRepository = mock(RoomRepository.class);
        IRoomFactory roomFactory = mock(IRoomFactory.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
        RoomID roomID = mock(RoomID.class);
        when(roomRepository.ofIdentity(any(RoomID.class))).thenReturn(Optional.empty());

        // Act
        Optional<Room> room = roomServiceImpl.getRoomById(roomID);

        // Assert
        assertNotNull(room);
        assertTrue(room.isEmpty());
    }
}