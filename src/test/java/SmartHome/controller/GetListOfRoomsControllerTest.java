package SmartHome.controller;

import SmartHome.domain.House;
import SmartHome.domain.LocationFactory;
import SmartHome.domain.RoomFactory;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetListOfRoomsControllerTest {

    private GetListOfRoomsController _getListOfRoomsController;

    /**
     * Test the controller constructor with a valid house.
     */
    @Test
    void testGetListOfRoomsControllerConstructor () throws InstantiationException {
        //Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        //Act
        _getListOfRoomsController = new GetListOfRoomsController(house);


        //Assert
        assertNotNull(_getListOfRoomsController);
    }

    /**
     * Test the constructor of the GetListOfRoomsController with a null house
     */

    @Test
    public void testGetListOfRoomsControllerConstructorWithNullHouse(){
        //Arrange
        House house = null;

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GetListOfRoomsController(house));
        String expectedMessage = "Invalid arguments";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    /**
     * Test if the list of Rooms is returned successfully
     */

    @Test
    public void testIfSuccessfulyReturnsTheListOfRooms() throws InstantiationException {
        //Arrange
        String roomName1 = "Sala";
        int floor1 = 2;
        int lenght1 = 2;
        int width1 = 2;
        int height1 = 2;

        String roomName2 = "Cozinha";
        int floor2 = 2;
        int lenght2 = 2;
        int width2 = 2;
        int height2 = 2;

        House house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom(roomName1, floor1, lenght1, width1, height1);
        house.addRoom(roomName2, floor2, lenght2, width2, height2);

        String expected =  house.getRooms().toString();
        _getListOfRoomsController = new GetListOfRoomsController(house);

        //Act
        List<RoomDTO> roomDTOList = _getListOfRoomsController.getRooms();
        String result = roomDTOList.toString();

        //Assert
        assertTrue(result.contains(expected));
    }

    /**
     * Test if the list of Rooms is empty
     */

    @Test
    public void testIfReturnsEmptyList() throws InstantiationException {
        //Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        _getListOfRoomsController = new GetListOfRoomsController(house);

        //Act
        List<RoomDTO> result = _getListOfRoomsController.getRooms();

        //Assert
        assertEquals(0, result.size());
    }

}