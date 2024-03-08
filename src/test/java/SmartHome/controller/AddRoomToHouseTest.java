package SmartHome.controller;

import SmartHome.domain.House;
import SmartHome.domain.LocationFactory;
import SmartHome.domain.Room;
import SmartHome.domain.RoomFactory;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AddRoomToHouseTest {

    @Test
    void shouldInstantiateControllerWhenHouseIsValid(){
        //Arrange
        House house = new House(new LocationFactory(), new RoomFactory());

        //Act
        AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(house);

        //Assert
        assertNotNull(addRoomToHouseController);
    }
    @Test
    void shouldThrowExceptionWhenHouseIsNull(){
        //Arrange
        House house = null;

        String expected = "Invalid arguments";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AddRoomToHouseController(house));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expected));
    }
    @Test
    void shouldAddRoomToHouseSuccessfullyWhenValidRoomDetailsAreProvided() {
        //Arrange
        House house = new House(new LocationFactory(), new RoomFactory());

        String roomName= "Kitchen";
        int floor = 1;
        double width = 3;
        double length = 2;
        double height = 2;

        AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(house);

        //Act
        RoomDTO roomDTO = addRoomToHouseController.addRoomToHouse(roomName, floor, length, width, height);

        //Assert
        assertTrue(roomDTO.toString().contains(roomName));
    }
}
