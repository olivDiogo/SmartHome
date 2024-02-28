package SmartHome.controller;

import SmartHome.domain.House;
import SmartHome.domain.LocationFactory;
import SmartHome.domain.Room;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;
import SmartHome.domain.DeviceFactory;
import SmartHome.domain.RoomFactory;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetDevicesFromRoomControllerTest {

    /**
     * Test to get the devices from a room with a house that has more than one room.
     * @throws InstantiationException if the house is null.
     */
    @Test
    void testGetDevicesFromRoom() throws InstantiationException {
        // Arrange
        String roomName1 = "bedroom";
        String roomName2 = "kitchen";
        String roomName3 = "bathroom";
        int roomFloor = 1;
        double roomLength = 18.2;
        double roomWidth = 13.2;
        double roomHeight = 13;

        String deviceName1 = "Fridge";
        String deviceName2 = "Kettle";
        String deviceName3 = "Tv";

        House house = new House(new LocationFactory(), new RoomFactory());

        GetDevicesFromRoomController getDevicesFromRoomController = new GetDevicesFromRoomController(house);

        house.addRoom(roomName1, roomFloor, roomLength, roomWidth, roomHeight);
        Room room2 = house.addRoom(roomName2, roomFloor, roomLength, roomWidth, roomHeight);
        UUID room2Id = room2.getRoomId();
        house.addRoom(roomName3, roomFloor, roomLength, roomWidth, roomHeight);

        room2.addDevice(deviceName1, new DeviceFactory());
        room2.addDevice(deviceName2, new DeviceFactory());
        room2.addDevice(deviceName3, new DeviceFactory());

        List<RoomDTO> roomDTOS = getDevicesFromRoomController.getRooms();
        RoomDTO roomDTO = roomDTOS.stream().filter(r -> r._roomId.equals(room2Id)).findFirst().get();
        int expected = 3;

        // Act
        int result = getDevicesFromRoomController.getDevicesFromRoom(roomDTO).size();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test the method getRoomsDTO with a house that has more than one room.
     * @throws InstantiationException if the house is null.
     */
    @Test
    void testGetRoomsDTO () throws InstantiationException {
        //Arrange
        String roomName1 = "bedroom";
        String roomName2 = "kitchen";
        int roomFloor = 1;
        double roomLength = 18.2;
        double roomWidth = 13.2;
        double roomHeight = 13;

        House house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom(roomName1, roomFloor, roomLength, roomWidth, roomHeight);
        house.addRoom(roomName2, roomFloor, roomLength, roomWidth, roomHeight);

        GetDevicesFromRoomController getDevicesFromRoomController = new GetDevicesFromRoomController(house);

        int expected = 2;

        //Act
        int result = getDevicesFromRoomController.getRooms().size();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test the method getRoomsDTO with a house that has no rooms.
      * @throws InstantiationException if the house is null.
     */
    @Test
    void testGetRoomsWithNoRoomsInHouse () throws InstantiationException {
        //Arrange
        House house = new House(new LocationFactory(), new RoomFactory());

        GetDevicesFromRoomController getDevicesFromRoomController = new GetDevicesFromRoomController(house);

        int expected = 0;

        //Act
        int result = getDevicesFromRoomController.getRooms().size();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test the method getDevicesFromRoom with an invalid roomDTO.
     * @throws InstantiationException if the house is null.
     */
    @Test
    void testGetDevicesFromRoomWithInvalidRoomDTO () throws InstantiationException {
        //Arrange
        String roomName1 = "bedroom";
        int roomFloor = 1;
        double roomLength = 18.2;
        double roomWidth = 13.2;
        double roomHeight = 13;

        String deviceName1 = "Fridge";

        House house = new House(new LocationFactory(), new RoomFactory());
        Room room = house.addRoom(roomName1, roomFloor, roomLength, roomWidth, roomHeight);
        room.addDevice(deviceName1, new DeviceFactory());

        GetDevicesFromRoomController getDevicesFromRoomController = new GetDevicesFromRoomController(house);

        RoomDTO roomDTO = new RoomDTO(room);

        //Act
        List<DeviceDTO> result = getDevicesFromRoomController.getDevicesFromRoom(roomDTO);

        //Assert
        assertNull(result);
    }

    /**
     * Test the controller constructor with a valid house.
     * @throws InstantiationException
     */
    @Test
    void testGetDevicesFromRoomControllerConstructor () throws InstantiationException {
        //Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        //Act
        GetDevicesFromRoomController getDevicesFromRoomController = new GetDevicesFromRoomController(house);


        //Assert
        assertNotNull(getDevicesFromRoomController);
    }

    /**
     * Test the controller constructor with a null house.
     */
    @Test
    void testGetDevicesFromRoomControllerConstructorWithNullHouse () {
        //Arrange
        House house = null;

        //Act + Assert
        Exception exception = assertThrows(InstantiationException.class, () -> new GetDevicesFromRoomController(house));
        String expectedMessage = "Invalid arguments";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }



}