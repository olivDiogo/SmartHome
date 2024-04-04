package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.RoomDTO;
import SmartHomeDDD.valueObject.Dimension;
import SmartHomeDDD.valueObject.RoomFloor;
import SmartHomeDDD.valueObject.RoomID;
import SmartHomeDDD.valueObject.RoomName;
import SmartHomeDDD.domain.Room.Room;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RoomAssemblerTest {

    /**
     * Test if the constructor of the RoomAssembler class can be called.
     */
    @Test
    void shouldInstantiateANewRoomAssembler() {
        // Arrange
        RoomAssembler roomAssembler = new RoomAssembler();

        // Act + Assert
        assertNotNull(roomAssembler);
    }

    /**
     * Test if the domainToDTO method returns a RoomDTO object when the room is valid.
     */
    @Test
    void shouldReturnARoomDTO_whenGivenARoom() {
        // Arrange
        String roomName = "Test Room";
        String dimension = "Width: 10, Height: 10, Depth: 10 ";
        String roomfloor = "RoomFloor{_floor=1}";
        String roomID = "1";

        Room room = mock(Room.class);

        when(room.getRoomName()).thenReturn(mock(RoomName.class));
        when(room.getRoomName().toString()).thenReturn(roomName);

        when(room.getDimension()).thenReturn(mock(Dimension.class));
        when(room.getDimension().toString()).thenReturn(dimension);

        when(room.getRoomFloor()).thenReturn(mock(RoomFloor.class));
        when(room.getRoomFloor().toString()).thenReturn(roomfloor);

        when(room.getID()).thenReturn(mock(RoomID.class));
        when(room.getID().toString()).thenReturn(roomID);

        RoomAssembler roomAssembler = new RoomAssembler();

        RoomDTO expectedRoom = new RoomDTO(roomName, dimension, roomfloor, roomID);

        // Act
        RoomDTO roomDTO = roomAssembler.domainToDTO(room);

        // Assert
        assertEquals(expectedRoom.roomId, roomDTO.roomId);

    }

    /**
     * Test if the domainToDTO method throws an IllegalArgumentException when the Room is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenRoomIsNull() {
        // Arrange
        Room room = null;
        RoomAssembler roomAssembler = new RoomAssembler();

        String expected = "The Room cannot be null.";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> roomAssembler.domainToDTO(room));

        // Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }


//    @Test
//    void shouldReturnANewRoomDTOList_whenGivenARoomList() {
//        // Arrange
//        String roomName = "Test Room";
//        String dimension = "Width: 10, Height: 10, Depth: 10 ";
//        String roomfloor = "RoomFloor{_floor=1}";
//        String roomID = "1";
//
//        String roomName2 = "Test Room 2";
//        String dimension2 = "Width: 10, Height: 20, Depth: 30 ";
//        String roomfloor2 = "RoomFloor{_floor=2}";
//        String roomID2 = "2";
//
//        Room room = mock(Room.class);
//
//        when(room.getRoomName()).thenReturn(mock(RoomName.class));
//        when(room.getRoomName().toString()).thenReturn(roomName);
//
//        when(room.getDimension()).thenReturn(mock(Dimension.class));
//        when(room.getDimension().toString()).thenReturn(dimension);
//
//        when(room.getRoomFloor()).thenReturn(mock(RoomFloor.class));
//        when(room.getRoomFloor().toString()).thenReturn(roomfloor);
//
//        when(room.getID()).thenReturn(mock(RoomID.class));
//        when(room.getID().toString()).thenReturn(roomID);
//
//        Room room2 = mock(Room.class);
//
//        when(room2.getRoomName()).thenReturn(mock(RoomName.class));
//        when(room2.getRoomName().toString()).thenReturn(roomName2);
//
//        when(room2.getDimension()).thenReturn(mock(Dimension.class));
//        when(room2.getDimension().toString()).thenReturn(dimension2);
//
//        when(room2.getRoomFloor()).thenReturn(mock(RoomFloor.class));
//        when(room2.getRoomFloor().toString()).thenReturn(roomfloor2);
//
//        when(room2.getID()).thenReturn(mock(RoomID.class));
//        when(room2.getID().toString()).thenReturn(roomID2);
//
//
//        List<Room> rooms = new ArrayList<>();
//        rooms.add(room);
//        rooms.add(room2);
//
//        RoomAssembler roomAssembler = new RoomAssembler();
//
//        RoomDTO roomDTO = new RoomDTO(roomName, dimension, roomfloor, roomID);
//        RoomDTO roomDTO2 = new RoomDTO(roomName2, dimension2, roomfloor2, roomID2);
//
//        List<RoomDTO> expected = new ArrayList<>();
//        expected.add(roomDTO);
//        expected.add(roomDTO2);
//
//        // Act
//        List<RoomDTO> result = roomAssembler.domainToDTO(rooms);
//
//        // Assert
//        assertEquals(expected.get(0).roomName, result.get(0).roomName);
//        assertEquals(expected.get(0).dimensions, result.get(0).dimensions);
//        assertEquals(expected.get(0).floor, result.get(0).floor);
//        assertEquals(expected.get(0).roomId, result.get(0).roomId);
//        assertEquals(expected.get(1).roomName, result.get(1).roomName);
//        assertEquals(expected.get(1).dimensions, result.get(1).dimensions);
//        assertEquals(expected.get(1).floor, result.get(1).floor);
//        assertEquals(expected.get(1).roomId, result.get(1).roomId);
//
//    }

    /**
     * Test if the domainToDTO method throws an IllegalArgumentException when the list of Rooms is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenRoomListIsNull() {
        // Arrange
        List<Room> rooms = null;
        RoomAssembler roomAssembler = new RoomAssembler();

        String expected = "The list of Rooms cannot be null.";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> roomAssembler.domainToDTO(rooms));

        // Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * Test if the domainToDTO method throws an IllegalArgumentException when the list of Rooms is empty.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenRoomListIsEmpty() {
        // Arrange
        List<Room> rooms = new ArrayList<>();
        RoomAssembler roomAssembler = new RoomAssembler();

        String expected = "The list of Rooms cannot be null.";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> roomAssembler.domainToDTO(rooms));

        // Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * Test if the domainToDTO method throws an IllegalArgumentException when the list of Rooms contains null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenRoomListContainsNull() {
        // Arrange
        Room room = mock(Room.class);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        rooms.add(null);
        RoomAssembler roomAssembler = new RoomAssembler();

        String expected = "The list of Rooms cannot be null.";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> roomAssembler.domainToDTO(rooms));

        // Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

}
