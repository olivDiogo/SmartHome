package SmartHomeDDD.domain.Room;

import SmartHomeDDD.ValueObject.HouseID;
import SmartHomeDDD.ValueObject.RoomID;
import SmartHomeDDD.ValueObject.RoomName;
import SmartHomeDDD.ValueObject.Dimension;
import SmartHomeDDD.ValueObject.RoomFloor;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomTest {

    /**
     * Test that the Room class can be instantiated with valid parameters.
     */
    @Test
    public void shouldInstantiateRoom_WhenConstructorIsCalledWithValidParameters() {
        //Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);

        //Act & Assert
        new Room(houseID, roomName, dimension, roomFloor);
    }

    /**
     * Test that the Room class throws an IllegalArgumentException when the constructor is called with a null HouseID.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullHouseID() {
        //Arrange
        HouseID houseID = null;
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Room(houseID, roomName, dimension, roomFloor));
    }

    /**
     * Test that the Room class throws an IllegalArgumentException when the constructor is called with a null RoomName.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullRoomName() {
        //Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = null;
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Room(houseID, roomName, dimension, roomFloor));
    }

    /**
     * Test that the Room class throws an IllegalArgumentException when the constructor is called with a null Dimension.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullDimension() {
        //Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = null;
        RoomFloor roomFloor = mock(RoomFloor.class);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Room(houseID, roomName, dimension, roomFloor));
    }

    /**
     * Test that the Room class throws an IllegalArgumentException when the constructor is called with a null RoomFloor.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullRoomFloor() {
        //Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = null;

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Room(houseID, roomName, dimension, roomFloor));
    }

    /**
     * Tests if the houseID is returned correctly.
     */
    @Test
    public void shouldReturnHouseID() {
        //Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);

        Room room = new Room(houseID, roomName, dimension, roomFloor);

        //Act
        HouseID result = room.getHouseID();

        //Assert
        assertTrue(room.toString().contains(result.toString()));
    }

    /**
     * Tests if the room name is returned correctly.
     */
    @Test
    public void shouldReturnRoomName() {
        //Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);

        Room room = new Room(houseID, roomName, dimension, roomFloor);

        //Act
        RoomName result = room.getRoomName();

        //Assert
        assertTrue(room.toString().contains(result.toString()));
    }

    /**
     * Tests if the room dimension is returned correctly.
     */
    @Test
    public void shouldReturnDimension() {
        //Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);

        Room room = new Room(houseID, roomName, dimension, roomFloor);

        //Act
        Dimension result = room.getDimension();

        //Assert
        assertTrue(room.toString().contains(result.toString()));
    }

    /**
     * Tests if the room ID is returned correctly.
     */
    @Test
    public void shouldReturnRoomFloor() {
        //Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);

        Room room = new Room(houseID, roomName, dimension, roomFloor);

        //Act
        RoomFloor result = room.getRoomFloor();

        //Assert
        assertTrue(room.toString().contains(result.toString()));
    }

    /**
     * Tests if the equals method returns false when comparing a room to a null object.
     */
    @Test
    public void shouldReturnFalse_WhenRoomIsComparedToNull() {
        //Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);

        Room room = new Room(houseID, roomName, dimension, roomFloor);

        //Act
        boolean result = room.equals(null);

        //Assert
        assertFalse(result);
    }

    /**
     * Tests if the equals method returns true when comparing the same room.
     */
    @Test
    public void shouldReturnTrue_WhenTwoRoomsHaveTheSameID() {
        //Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);

        try (MockedConstruction<RoomID> mockedRoom = mockConstruction(RoomID.class, (mock, context) -> {
            when(mock.toString()).thenReturn("RoomID");
        })) {
            Room room1 = new Room(houseID, roomName, dimension, roomFloor);
            Room room2 = new Room(houseID, roomName, dimension, roomFloor);

            //Act
            boolean result = room1.equals(room2);

            //Assert
            assertTrue(result);
        }
    }

    /**
     * Tests if the equals method returns false when comparing two different rooms.
     */
    @Test
    public void shouldReturnFalse_WhenThereAreTwoDifferentHouses() {
        //Arrange
        HouseID houseID1 = mock(HouseID.class);
        HouseID houseID2 = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);

        Room room1 = new Room(houseID1, roomName, dimension, roomFloor);
        Room room2 = new Room(houseID2, roomName, dimension, roomFloor);

        //Act
        boolean result = room1.equals(room2);

        //Assert
        assertFalse(result);
    }


    /**
     * Tests if the getID method returns the correct room ID.
     */
    @Test
    public void shouldReturnRoomID() {
        //Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);

        Room room = new Room(houseID, roomName, dimension, roomFloor);
        //Act
        RoomID result = room.getID();

        //Assert
        assertNotNull(result);
    }

    /**
     * Tests if the equals method returns true when comparing the same room.
     */
    @Test
    public void shouldReturnTrue_WhenObjectIsComparedToItself() {
        //Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);

        Room room = new Room(houseID, roomName, dimension, roomFloor);

        //Act
        boolean result = room.equals(room);

        //Assert
        assertTrue(result);
    }
}