package smart_home.persistence.jpa.data_model;

import org.junit.jupiter.api.Test;
import smart_home.domain.room.Room;
import smart_home.value_object.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoomDataModelTest {
    /**
     * Test for the empty constructor of the RoomDataModel class.
     */
    @Test
    void shouldInstantiateRoomDataModel_whenEmptyConstructorIsCalled(){
        // Arrange
        RoomDataModel roomDataModel = new RoomDataModel();

        // Act
        assertNotNull(roomDataModel);
    }


    /**
     * Test for the constructor of the RoomDataModel class.
     */
    @Test
    void shouldInstantiateRoomDataModel_whenRoomIsValid(){
        // Arrange
        Dimension dimension = mock(Dimension.class);
        when(dimension.getDepth()).thenReturn(1);
        when(dimension.getHeight()).thenReturn(1);
        when(dimension.getWidth()).thenReturn(1);

        Room roomDouble = mock(Room.class);
        when(roomDouble.getID()).thenReturn(mock(RoomID.class));
        when(roomDouble.getRoomName()).thenReturn(mock(RoomName.class));
        when(roomDouble.getRoomFloor()).thenReturn(mock(RoomFloor.class));
        when(roomDouble.getHouseID()).thenReturn(mock(HouseID.class));
        when(roomDouble.getDimension()).thenReturn(dimension);

        // Act
        RoomDataModel roomDataModel = new RoomDataModel(roomDouble);

        // Assert
        assertNotNull(roomDataModel);
    }

    /**
     * Test for the constructor of the RoomDataModel class when room is null.
     */
    @Test
    void shouldThrowException_whenRoomIsNull(){
        // Arrange
        Room roomDouble = null;

        String expectedMessage = "Room cannot be null.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new RoomDataModel(roomDouble)
        );

        // Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test for the getRoomID method of the RoomDataModel class.
     */
    @Test
    void shouldReturnRoomID_whenGetRoomIDIsCalled(){
        // Arrange
        Dimension dimension = mock(Dimension.class);
        when(dimension.getDepth()).thenReturn(1);
        when(dimension.getHeight()).thenReturn(1);
        when(dimension.getWidth()).thenReturn(1);

        RoomID roomID = mock(RoomID.class);
        when(roomID.getID()).thenReturn("1");

        Room roomDouble = mock(Room.class);
        when(roomDouble.getID()).thenReturn(roomID);
        when(roomDouble.getRoomName()).thenReturn(mock(RoomName.class));
        when(roomDouble.getRoomFloor()).thenReturn(mock(RoomFloor.class));
        when(roomDouble.getHouseID()).thenReturn(mock(HouseID.class));
        when(roomDouble.getDimension()).thenReturn(dimension);

        RoomDataModel roomDataModel = new RoomDataModel(roomDouble);

        // Act
        String actualRoomID = roomDataModel.getRoomID();

        // Assert
        assertEquals(roomID.getID(), actualRoomID);
    }

    /**
     * Test for the getRoomName method of the RoomDataModel class.
     */
    @Test
    void shouldReturnRoomName_whenGetRoomNameIsCalled(){
        // Arrange
        Dimension dimension = mock(Dimension.class);
        when(dimension.getDepth()).thenReturn(1);
        when(dimension.getHeight()).thenReturn(1);
        when(dimension.getWidth()).thenReturn(1);

        RoomName roomName = mock(RoomName.class);
        when(roomName.getRoomName()).thenReturn("Room");

        Room roomDouble = mock(Room.class);
        when(roomDouble.getID()).thenReturn(mock(RoomID.class));
        when(roomDouble.getRoomName()).thenReturn(roomName);
        when(roomDouble.getRoomFloor()).thenReturn(mock(RoomFloor.class));
        when(roomDouble.getHouseID()).thenReturn(mock(HouseID.class));
        when(roomDouble.getDimension()).thenReturn(dimension);

        RoomDataModel roomDataModel = new RoomDataModel(roomDouble);

        // Act
        String actualRoomName = roomDataModel.getRoomName();

        // Assert
        assertEquals(roomName.getRoomName(), actualRoomName);
    }

    /**
     * Test for the getWidth method of the RoomDataModel class.
     */
    @Test
    void shouldReturnWidth_whenGetWidthIsCalled(){
        // Arrange
        Dimension dimension = mock(Dimension.class);
        when(dimension.getDepth()).thenReturn(1);
        when(dimension.getHeight()).thenReturn(1);
        when(dimension.getWidth()).thenReturn(1);

        Room roomDouble = mock(Room.class);
        when(roomDouble.getID()).thenReturn(mock(RoomID.class));
        when(roomDouble.getRoomName()).thenReturn(mock(RoomName.class));
        when(roomDouble.getRoomFloor()).thenReturn(mock(RoomFloor.class));
        when(roomDouble.getHouseID()).thenReturn(mock(HouseID.class));
        when(roomDouble.getDimension()).thenReturn(dimension);

        RoomDataModel roomDataModel = new RoomDataModel(roomDouble);

        // Act
        int actualWidth = roomDataModel.getWidth();

        // Assert
        assertEquals(dimension.getWidth(), actualWidth);
    }

    /**
     * Test for the getDepth method of the RoomDataModel class.
     */
    @Test
    void shouldReturnDepth_whenGetDepthIsCalled(){
        // Arrange
        Dimension dimension = mock(Dimension.class);
        when(dimension.getDepth()).thenReturn(1);
        when(dimension.getHeight()).thenReturn(1);
        when(dimension.getWidth()).thenReturn(1);

        Room roomDouble = mock(Room.class);
        when(roomDouble.getID()).thenReturn(mock(RoomID.class));
        when(roomDouble.getRoomName()).thenReturn(mock(RoomName.class));
        when(roomDouble.getRoomFloor()).thenReturn(mock(RoomFloor.class));
        when(roomDouble.getHouseID()).thenReturn(mock(HouseID.class));
        when(roomDouble.getDimension()).thenReturn(dimension);

        RoomDataModel roomDataModel = new RoomDataModel(roomDouble);

        // Act
        int actualDepth = roomDataModel.getDepth();

        // Assert
        assertEquals(dimension.getDepth(), actualDepth);
    }

    /**
     * Test for the getHeight method of the RoomDataModel class.
     */
    @Test
    void shouldReturnHeight_whenGetHeightIsCalled(){
        // Arrange
        Dimension dimension = mock(Dimension.class);
        when(dimension.getDepth()).thenReturn(1);
        when(dimension.getHeight()).thenReturn(1);
        when(dimension.getWidth()).thenReturn(1);

        Room roomDouble = mock(Room.class);
        when(roomDouble.getID()).thenReturn(mock(RoomID.class));
        when(roomDouble.getRoomName()).thenReturn(mock(RoomName.class));
        when(roomDouble.getRoomFloor()).thenReturn(mock(RoomFloor.class));
        when(roomDouble.getHouseID()).thenReturn(mock(HouseID.class));
        when(roomDouble.getDimension()).thenReturn(dimension);

        RoomDataModel roomDataModel = new RoomDataModel(roomDouble);

        // Act
        int actualHeight = roomDataModel.getHeight();

        // Assert
        assertEquals(dimension.getHeight(), actualHeight);
    }

    /**
     * Test for the getFloor method of the RoomDataModel class.
     */
    @Test
    void shouldReturnFloor_whenGetFloorIsCalled(){
        // Arrange
        Dimension dimension = mock(Dimension.class);
        when(dimension.getDepth()).thenReturn(1);
        when(dimension.getHeight()).thenReturn(1);
        when(dimension.getWidth()).thenReturn(1);

        RoomFloor roomFloor = mock(RoomFloor.class);
        when(roomFloor.getFloor()).thenReturn(1);

        Room roomDouble = mock(Room.class);
        when(roomDouble.getID()).thenReturn(mock(RoomID.class));
        when(roomDouble.getRoomName()).thenReturn(mock(RoomName.class));
        when(roomDouble.getRoomFloor()).thenReturn(roomFloor);
        when(roomDouble.getHouseID()).thenReturn(mock(HouseID.class));
        when(roomDouble.getDimension()).thenReturn(dimension);

        RoomDataModel roomDataModel = new RoomDataModel(roomDouble);

        // Act
        int actualFloor = roomDataModel.getFloor();

        // Assert
        assertEquals(roomFloor.getFloor(), actualFloor);
    }

    /**
     * Test for the getHouseID method of the RoomDataModel class.
     */
    @Test
    void shouldReturnHouseID_whenGetHouseIDIsCalled(){
        // Arrange
        Dimension dimension = mock(Dimension.class);
        when(dimension.getDepth()).thenReturn(1);
        when(dimension.getHeight()).thenReturn(1);
        when(dimension.getWidth()).thenReturn(1);

        HouseID houseID = mock(HouseID.class);
        when(houseID.getID()).thenReturn("1");

        Room roomDouble = mock(Room.class);
        when(roomDouble.getID()).thenReturn(mock(RoomID.class));
        when(roomDouble.getRoomName()).thenReturn(mock(RoomName.class));
        when(roomDouble.getRoomFloor()).thenReturn(mock(RoomFloor.class));
        when(roomDouble.getHouseID()).thenReturn(houseID);
        when(roomDouble.getDimension()).thenReturn(dimension);

        RoomDataModel roomDataModel = new RoomDataModel(roomDouble);

        // Act
        String actualHouseID = roomDataModel.getHouseID();

        // Assert
        assertEquals(houseID.getID(), actualHouseID);
    }


}
