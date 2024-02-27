package SmartHome.controller;

import SmartHome.domain.House;
import SmartHome.domain.LocationFactory;
import SmartHome.domain.RoomFactory;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AddRoomToHouseTest {

    @Test
    void successfullyAddRoomToHouse() {
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
        assertNotNull(roomDTO);
    }




}
