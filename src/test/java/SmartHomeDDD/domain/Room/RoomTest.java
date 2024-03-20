package SmartHomeDDD.domain.Room;

import SmartHomeDDD.ValueObject.HouseID;
import SmartHomeDDD.ValueObject.RoomID;
import SmartHomeDDD.ValueObject.RoomName;
import SmartHomeDDD.ValueObject.Dimension;
import SmartHomeDDD.ValueObject.RoomFloor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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

}