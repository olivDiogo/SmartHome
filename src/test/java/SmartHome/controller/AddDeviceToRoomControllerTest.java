package SmartHome.controller;

import SmartHome.domain.*;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


public class AddDeviceToRoomControllerTest {
    private House house;
    private AddDeviceToRoomController controller;
    private Map<RoomDTO, Room> _roomsDTOAndRooms;
    private Room room;

    @Test
    public void testForConstruct() throws InstantiationException {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        // Act
        controller = new AddDeviceToRoomController(house);
        // Assert
        assertNotNull(controller);
    }

    @Test
    void whenHouseIsNull_thenThrowsInstantiationException() throws InstantiationException {
        // Arrange
        House house = null;
        String expected = "Invalid arguments";

        // Act & Assert
        Exception exception = assertThrows(InstantiationException.class, () -> new AddDeviceToRoomController(house));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expected));
    }

    @Test
    public void getRoomListSuccessfully() throws InstantiationException {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        controller = new AddDeviceToRoomController(house);
        // Act
        List<RoomDTO> result = controller.getRoomList();
        // Assert
        assertEquals(0, result.size());
    }
    @Test
    public void addDeviceToRoomSuccessfully() throws InstantiationException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());

        String roomName = "Room";
        int floor = 1;
        double width = 1.0;
        double length = 1.0;
        double height = 1.0;

        String deviceName = "Device";

        house.addRoom(roomName, floor, width, length, height);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        List<RoomDTO> roomList = controller.getRoomList();
        RoomDTO roomDTO = roomList.get(0);

        // Act
        Optional<DeviceDTO> result = controller.addDeviceToRoom(roomDTO, deviceName);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    public void addDeviceToRoomWithInvalidDeviceNameShouldFail() throws InstantiationException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());

        String roomName = "Room";
        int floor = 1;
        double width = 1.0;
        double length = 1.0;
        double height = 1.0;

        house.addRoom(roomName, floor, width, length, height);

        String deviceName = " ";

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);

        List<RoomDTO> roomList = controller.getRoomList();
        RoomDTO roomDTO = roomList.get(0);

        // Act
        Optional<DeviceDTO> result = controller.addDeviceToRoom(roomDTO, deviceName);

        // Assert
        assertFalse(result.isPresent());
    }




}


