package smartHome.domain.room;

import smartHome.valueObject.HouseID;
import smartHome.valueObject.RoomID;
import smartHome.valueObject.RoomName;
import smartHome.valueObject.Dimension;
import smartHome.valueObject.RoomFloor;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomTest {

    /**
     * Test that the Room class can be instantiated with valid parameters.
     */
    @Test
    void shouldInstantiateRoom_WhenConstructorIsCalledWithValidParameters() {
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
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullHouseID() {
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
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullRoomName() {
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
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullDimension() {
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
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullRoomFloor() {
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
    void shouldReturnHouseID() {
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
    void shouldReturnRoomName() {
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
    void shouldReturnDimension() {
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
    void shouldReturnRoomFloor() {
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
    void shouldReturnFalse_WhenRoomIsComparedToNull() {
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
    void shouldReturnTrue_WhenTwoRoomsHaveTheSameID() {
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
    void shouldReturnFalse_WhenThereAreTwoDifferentHouses() {
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
    void shouldReturnRoomID() {
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
    void shouldReturnTrue_WhenObjectIsComparedToItself() {
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

    /**
     * Verifica se o método {@code hashCode} é gerado a partir do {@code RoomID}
     * em linha com a implementação sobrescrita do método {@code equals}.
     */
    @Test
    void hashCodeShouldBeGenerateFromRoomIDImLineWithOverritenEquals() {
        // Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);

        try (MockedConstruction<RoomID> roomID = mockConstruction(RoomID.class)) {
            Room room = new Room(houseID, roomName, dimension, roomFloor);
            RoomID roomIDDouble = roomID.constructed().get(0);
            int expected = roomIDDouble.hashCode();

            //Act
            int actual = room.hashCode();

            //Assert
            assertEquals(expected, actual);
        }
    }
}


