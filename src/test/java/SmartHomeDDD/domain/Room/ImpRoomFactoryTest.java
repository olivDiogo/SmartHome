package SmartHomeDDD.domain.Room;

import SmartHomeDDD.valueObject.Dimension;
import SmartHomeDDD.valueObject.HouseID;
import SmartHomeDDD.valueObject.RoomFloor;
import SmartHomeDDD.valueObject.RoomName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for the {@link ImpRoomFactory} class, ensuring that rooms are created correctly under various conditions
 * and that appropriate exceptions are thrown when invalid parameters are provided.
 */
class ImpRoomFactoryTest {

    /**
     * Test to ensure that a Room can be created successfully when {@link ImpRoomFactory#createRoom(HouseID, RoomName, Dimension, RoomFloor)}
     * is called with valid parameters. This test verifies that no exceptions are thrown during the creation process.
     */
    @Test
    void shouldCreateRoom_WhenCreateRoomIsCalledWithValidParameters(){
        // Arrange
        try(MockedConstruction<Room> roomDouble = mockConstruction(Room.class,(mock, context) -> {
            when(mock.getHouseID()).thenReturn(mock(HouseID.class));
            when(mock.getRoomName()).thenReturn(mock(RoomName.class));
            when(mock.getRoomFloor()).thenReturn(mock(RoomFloor.class));
            when(mock.getDimension()).thenReturn(mock(Dimension.class));
        })){
            ImpRoomFactory factory = new ImpRoomFactory();

            // Act
            Room room = factory.createRoom(mock(HouseID.class), mock(RoomName.class), mock(Dimension.class), mock(RoomFloor.class));

            // Assert
            List<Room> rooms = roomDouble.constructed();
            assertEquals(1, rooms.size());
            assertEquals(rooms.get(0), room);
        }
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when {@link ImpRoomFactory#createRoom(HouseID, RoomName, Dimension, RoomFloor)}
     * is called with a null HouseID parameter. This test confirms the robustness of the factory's parameter validation.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenCreateRoomIsCalledWithNullHouseID(){
        // Arrange
        HouseID houseID = null;
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);
        ImpRoomFactory factory = new ImpRoomFactory();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createRoom(houseID, roomName, dimension, roomFloor), "Factory should throw IllegalArgumentException for null HouseID.");
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when {@link ImpRoomFactory#createRoom(HouseID, RoomName, Dimension, RoomFloor)}
     * is called with a null RoomName parameter. This verifies that the factory properly checks for null values in its arguments.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenCreateRoomIsCalledWithNullRoomName(){
        // Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = null;
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);
        ImpRoomFactory factory = new ImpRoomFactory();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createRoom(houseID, roomName, dimension, roomFloor), "Factory should throw IllegalArgumentException for null RoomName.");
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when {@link ImpRoomFactory#createRoom(HouseID, RoomName, Dimension, RoomFloor)}
     * is called with a null Dimension parameter. This test checks that all critical parameters are validated before room creation.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenCreateRoomIsCalledWithNullDimension(){
        // Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = null;
        RoomFloor roomFloor = mock(RoomFloor.class);
        ImpRoomFactory factory = new ImpRoomFactory();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createRoom(houseID, roomName, dimension, roomFloor), "Factory should throw IllegalArgumentException for null Dimension.");
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when {@link ImpRoomFactory#createRoom(HouseID, RoomName, Dimension, RoomFloor)}
     * is called with a null RoomFloor parameter. This test confirms that the factory checks for null values in its arguments.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenCreateRoomIsCalledWithNullRoomFloor(){
        // Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = null;
        ImpRoomFactory factory = new ImpRoomFactory();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createRoom(houseID, roomName, dimension, roomFloor), "Factory should throw IllegalArgumentException for null RoomFloor.");
    }
}