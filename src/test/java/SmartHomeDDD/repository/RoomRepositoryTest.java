package SmartHomeDDD.repository;

import SmartHomeDDD.valueObject.RoomID;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import SmartHomeDDD.domain.Room.Room;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class RoomRepositoryTest {
    /**
     * Test for the RoomRepository constructor
     */
    @Test
    public void shouldGetValidObject() {
        // Arrange
        // Act
        new RoomRepository();
        // Assert
    }

    /**
     * Test for the save room method when room is valid
     */
    @Test
    public void shouldReturnRoomAfterSavingIt_whenRoomIsValid(){
        // Arrange
        Room roomDouble = mock(Room.class);
        RoomRepository roomRepository = new RoomRepository();

        // Act
        Room room = roomRepository.save(roomDouble);

        // Assert
        assertEquals(roomDouble, room);
    }

    /**
     * Test for the save room method when room is null
     */
    @Test
    public void shouldThrowException_whenRoomIsNull(){
        // Arrange
        Room roomDouble = null;
        RoomRepository roomRepository = new RoomRepository();

        String expectedMessage = "Room cannot be null";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                roomRepository.save(roomDouble)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test for the save room method when room is null
     */
    @Test
    public void shouldThrowException_whenRoomAlreadyExists(){
        // Arrange
        Room roomDouble = mock(Room.class);
        RoomRepository roomRepository = new RoomRepository();

        roomRepository.save(roomDouble);

        String expectedMessage = "Room already exists";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                roomRepository.save(roomDouble)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test for the find all rooms method when there are rooms
     */
    @Test
    public void shouldReturnAllRooms(){
        // Arrange
        RoomID roomID1 = new RoomID("1");
        Room roomDouble1 = mock(Room.class);
        when(roomDouble1.getID()).thenReturn(roomID1);

        RoomID roomID2 = new RoomID("2");
        Room roomDouble2 = mock(Room.class);
        when(roomDouble2.getID()).thenReturn(roomID2);

        RoomRepository roomRepository = new RoomRepository();
        roomRepository.save(roomDouble1);
        roomRepository.save(roomDouble2);

        // Act
        List<Room> rooms = roomRepository.findAll();

        // Assert
        assertEquals(roomDouble2, rooms.get(1));
    }

    /**
     * Test for the find all rooms method when no room exist
     */
    @Test
    public void shouldReturnEmptyList_whenNoRoomsExist(){
        // Arrange
        RoomRepository roomRepository = new RoomRepository();

        // Act
        List<Room> rooms = roomRepository.findAll();

        // Assert
        assertTrue(rooms.isEmpty());
    }

    /**
     * Test for the ofIdentity method when room exists
     */
    @Test
    public void shouldReturnRoomById_whenRoomExists(){
        // Arrange
        RoomID roomID = new RoomID("1");
        Room roomDouble = mock(Room.class);
        when(roomDouble.getID()).thenReturn(roomID);

        RoomRepository roomRepository = new RoomRepository();
        roomRepository.save(roomDouble);

        // Act
        Room room = roomRepository.ofIdentity(roomID).get();

        // Assert
        assertEquals(roomDouble, room);
    }

    /**
     * Test for the ofIdentity method when room does not exist
     */
    @Test
    public void shouldReturnException_whenRoomDoesNotExist(){
        // Arrange
        RoomID roomID1 = new RoomID("1");
        Room roomDouble = mock(Room.class);
        when(roomDouble.getID()).thenReturn(roomID1);

        RoomRepository roomRepository = new RoomRepository();
        roomRepository.save(roomDouble);

        RoomID roomID2 = new RoomID("2");

        // Act
        Optional<Room> room = roomRepository.ofIdentity(roomID2);

        // Assert
        assertEquals(Optional.empty(), room);
    }

    /**
     * Test for the containsOfIdentity method when room exists
     */
    @Test
    public void shouldReturnTrue_whenRoomIdExists(){
        // Arrange
        RoomID roomID = new RoomID("1");
        Room roomDouble = mock(Room.class);
        when(roomDouble.getID()).thenReturn(roomID);

        RoomRepository roomRepository = new RoomRepository();
        roomRepository.save(roomDouble);

        // Act
        boolean result = roomRepository.containsOfIdentity(roomID);

        // Assert
        assertTrue(result);
    }

    /**
     * Test for the containsOfIdentity method when room does not exist
     */
    @Test
    public void shouldReturnFalse_whenRoomIdDoesNotExists(){
        // Arrange
        RoomID roomID = new RoomID("1");
        Room roomDouble = mock(Room.class);
        when(roomDouble.getID()).thenReturn(roomID);

        RoomRepository roomRepository = new RoomRepository();
        roomRepository.save(roomDouble);

        RoomID roomID2 = new RoomID("2");
        // Act
        boolean result = roomRepository.containsOfIdentity(roomID2);

        // Assert
        assertFalse(result);
    }
}
