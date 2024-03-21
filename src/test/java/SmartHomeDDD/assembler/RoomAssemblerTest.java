package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.HouseDTO;
import SmartHomeDDD.DTO.RoomDTO;
import SmartHomeDDD.ValueObject.Dimension;
import SmartHomeDDD.ValueObject.RoomFloor;
import SmartHomeDDD.ValueObject.RoomID;
import SmartHomeDDD.ValueObject.RoomName;
import SmartHomeDDD.domain.Room.Room;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RoomAssemblerTest {

    @Test
    void shouldInstantiateANewRoomAssembler() {
        new RoomAssembler();
    }

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

        try (MockedConstruction<RoomDTO> mocked = mockConstruction(RoomDTO.class, (mock, context) -> {
            String actualRoomName = (String) context.arguments().get(0);
            String actualDimension = (String) context.arguments().get(1);
            String actualRoomFloor = (String) context.arguments().get(2);
            String actualRoomID = (String) context.arguments().get(3);

           when(mock.getRoomName()).thenReturn(actualRoomName);
           when(mock.getDimensions()).thenReturn(actualDimension);
           when(mock.getFloor()).thenReturn(actualRoomFloor);
           when(mock.getRoomId()).thenReturn(actualRoomID);

        })) {

            // Act
            RoomDTO result = roomAssembler.domainToDTO(room);

            // Assert
            List<RoomDTO> roomDTOList = mocked.constructed();
            assertEquals(roomName, roomDTOList.get(0).getRoomName());
            assertEquals(roomName,result.getRoomName());
            assertEquals(dimension, result.getDimensions());
            assertEquals(roomfloor, result.getFloor());
            assertEquals(roomID, result.getRoomId());

        }
    }


    @Test
    void shouldReturnANewRoomDTOList_whenGivenARoomList() {
        // Arrange
        String roomName = "Test Room";
        String dimension = "Width: 10, Height: 10, Depth: 10 ";
        String roomfloor = "RoomFloor{_floor=1}";
        String roomID = "1";

        String roomName2 = "Test Room 2";
        String dimension2 = "Width: 10, Height: 20, Depth: 30 ";
        String roomfloor2 = "RoomFloor{_floor=2}";
        String roomID2 = "2";

        Room room = mock(Room.class);

        when(room.getRoomName()).thenReturn(mock(RoomName.class));
        when(room.getRoomName().toString()).thenReturn(roomName);

        when(room.getDimension()).thenReturn(mock(Dimension.class));
        when(room.getDimension().toString()).thenReturn(dimension);

        when(room.getRoomFloor()).thenReturn(mock(RoomFloor.class));
        when(room.getRoomFloor().toString()).thenReturn(roomfloor);

        when(room.getID()).thenReturn(mock(RoomID.class));
        when(room.getID().toString()).thenReturn(roomID);

        Room room2 = mock(Room.class);

        when(room2.getRoomName()).thenReturn(mock(RoomName.class));
        when(room2.getRoomName().toString()).thenReturn(roomName2);

        when(room2.getDimension()).thenReturn(mock(Dimension.class));
        when(room2.getDimension().toString()).thenReturn(dimension2);

        when(room2.getRoomFloor()).thenReturn(mock(RoomFloor.class));
        when(room2.getRoomFloor().toString()).thenReturn(roomfloor2);

        when(room2.getID()).thenReturn(mock(RoomID.class));
        when(room2.getID().toString()).thenReturn(roomID2);


        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        rooms.add(room2);

        RoomAssembler roomAssembler = new RoomAssembler();

        try (MockedConstruction<RoomDTO> mocked = mockConstruction(RoomDTO.class, (mock, context) -> {

            String actualRoomName = (String) context.arguments().get(0);
            String actualDimension = (String) context.arguments().get(1);
            String actualRoomFloor = (String) context.arguments().get(2);
            String actualRoomID = (String) context.arguments().get(3);

            when(mock.getRoomName()).thenReturn(actualRoomName);
            when(mock.getDimensions()).thenReturn(actualDimension);
            when(mock.getFloor()).thenReturn(actualRoomFloor);
            when(mock.getRoomId()).thenReturn(actualRoomID);

        })) {
            // Act
            List<RoomDTO> result = roomAssembler.domainToDTO(rooms);

            // Assert

            List<RoomDTO> roomDTOList = mocked.constructed();
            assertArrayEquals(result.toArray(), roomDTOList.toArray());
            assertEquals(result.toString(), roomDTOList.toString());
            assertTrue(result.containsAll(roomDTOList));

        }
    }
}
