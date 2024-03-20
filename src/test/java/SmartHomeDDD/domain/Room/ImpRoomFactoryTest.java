package SmartHomeDDD.domain.Room;

import SmartHomeDDD.ValueObject.Dimension;
import SmartHomeDDD.ValueObject.HouseID;
import SmartHomeDDD.ValueObject.RoomFloor;
import SmartHomeDDD.ValueObject.RoomName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
    void shouldCreateRoomWhenCreateRoomIsCalledWithValidParameters(){
        // Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);
        ImpRoomFactory factory = mock(ImpRoomFactory.class);

        // Act & Assert
        factory.createRoom(houseID, roomName, dimension, roomFloor);
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when {@link ImpRoomFactory#createRoom(HouseID, RoomName, Dimension, RoomFloor)}
     * is called with a null HouseID parameter. This test confirms the robustness of the factory's parameter validation.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenCreateRoomIsCalledWithNullHouseID(){
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
    void shouldThrowIllegalArgumentExceptionWhenCreateRoomIsCalledWithNullRoomName(){
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
    void shouldThrowIllegalArgumentExceptionWhenCreateRoomIsCalledWithNullDimension(){
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
    void shouldThrowIllegalArgumentExceptionWhenCreateRoomIsCalledWithNullRoomFloor(){
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