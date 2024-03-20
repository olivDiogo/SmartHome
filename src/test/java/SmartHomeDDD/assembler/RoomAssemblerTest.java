package SmartHomeDDD.assembler;

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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
        int width = 10;
        int height = 10;
        int depth = 10;
        int floor = 1;
        String roomID = "1";

        Room room = mock(Room.class);
        when(room.getRoomName()).thenReturn(new RoomName(roomName));
        when(room.getDimension()).thenReturn(new Dimension(width, height, depth));
        when(room.getRoomFloor()).thenReturn(new RoomFloor(floor));
        when(room.getID()).thenReturn(new RoomID(roomID));

        int expected = 1;

        RoomAssembler roomAssembler = new RoomAssembler();

        try (MockedConstruction<RoomDTO> mocked = mockConstruction(RoomDTO.class, (mock, context) -> {
            when(mock.toString()).thenReturn(context.arguments().get(0).toString());
        })) {
            // Act
            RoomDTO result = roomAssembler.domainToDTO(room);

            // Assert
            List<RoomDTO> roomDTOList = mocked.constructed();
            assertEquals(result.toString(), roomDTOList.get(0).toString());
            assertEquals(expected, roomDTOList.size());
        }
    }


    @Test
    void shouldReturnANewRoomDTOList_whenGivenARoomList() {
        // Arrange
        String roomName = "Test Room";
        int width = 10;
        int height = 10;
        int depth = 10;
        int floor = 1;
       String roomID = "1";

        String roomName2 = "Test Room 2";
        int width2 = 20;
        int height2 = 30;
        int depth2 = 40;
        int floor2 = 2;
        String roomID2 = "2";

        Room room = mock(Room.class);
        when(room.getRoomName()).thenReturn(new RoomName(roomName));
        when(room.getDimension()).thenReturn(new Dimension(width, height, depth));
        when(room.getRoomFloor()).thenReturn(new RoomFloor(floor));
        when(room.getID()).thenReturn(new RoomID(roomID));

        Room room2 = mock(Room.class);
        when(room2.getRoomName()).thenReturn(new RoomName(roomName2));
        when(room2.getDimension()).thenReturn(new Dimension(width2, height2, depth2));
        when(room2.getRoomFloor()).thenReturn(new RoomFloor(floor2));
        when(room2.getID()).thenReturn(new RoomID(roomID2));

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        rooms.add(room2);

        RoomAssembler roomAssembler = new RoomAssembler();

        try (MockedConstruction<RoomDTO> mocked = mockConstruction(RoomDTO.class, (mock, context) -> {
            when(mock.toString()).thenReturn(context.arguments().get(0).toString());
        })) {
            // Act
            List<RoomDTO> result = roomAssembler.domainToDTO(rooms);

            // Assert
            List<RoomDTO> roomDTOList = mocked.constructed();
            assertEquals(rooms.size(), roomDTOList.size());


        }
    }
}
