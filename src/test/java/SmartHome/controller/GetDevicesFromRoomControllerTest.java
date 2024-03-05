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

        Room room = house.addRoom(roomName2, roomFloor, roomLength, roomWidth, roomHeight);

        room.addDevice(deviceName1, new DeviceFactory());
        room.addDevice(deviceName2, new DeviceFactory());
        room.addDevice(deviceName3, new DeviceFactory());

        GetDevicesFromRoomController getDevicesFromRoomController = new GetDevicesFromRoomController(house);

        String expected = room.getDevices().toString();

        List<RoomDTO> roomDTOS = getDevicesFromRoomController.getRooms();
        RoomDTO roomDTO = roomDTOS.get(0);

        // Act
        List<DeviceDTO> deviceDTOList = getDevicesFromRoomController.getDevicesFromRoom(roomDTO);

        String resultList = deviceDTOList.toString();

        // Assert
        assertEquals(expected, resultList);
        assertTrue(resultList.contains(expected));
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

        String expected = house.getRooms().toString();
        GetDevicesFromRoomController getDevicesFromRoomController = new GetDevicesFromRoomController(house);

        //Act
        List <RoomDTO> roomDTOList = getDevicesFromRoomController.getRooms();

        String result = roomDTOList.toString();

        //Assert
        assertTrue(result.contains(expected));
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
        List <RoomDTO> roomDTOList = getDevicesFromRoomController.getRooms();
        int result = roomDTOList.size();

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