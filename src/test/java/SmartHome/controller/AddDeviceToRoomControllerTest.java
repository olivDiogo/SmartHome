package SmartHome.controller;

import SmartHome.domain.House;
import SmartHome.domain.LocationFactory;
import SmartHome.domain.Room;
import SmartHome.domain.RoomFactory;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class AddDeviceToRoomControllerTest {
    private House house;
    private AddDeviceToRoomController controller;
    private Map<RoomDTO, Room> _roomsDTOAndRooms;
    private Room room;

    @Test
    public void testForConstruct() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        // Act
        controller = new AddDeviceToRoomController(house);
        // Assert
        assertNotNull(controller);
    }

    @Test
    public void getRoomListSuccessfully() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        controller = new AddDeviceToRoomController(house);
        // Act
        List<RoomDTO> result = controller.getRoomList();
        // Assert
        assertEquals(0, result.size());
    }
    @Test
    public void addDeviceToRoomSuccessfully() {
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
        RoomDTO roomDTO = roomList.stream().filter(r -> r.getName().equals(roomName)).findFirst().orElseThrow(() -> new IllegalArgumentException("Room not found"));

        // Act
        Optional<DeviceDTO> result = controller.addDeviceToRoom(roomDTO, deviceName);

        // Assert
        assertTrue(result.isPresent());
    }
    @Test
    public void addDeviceToRoomWithInvalidRoomNameShouldFail() {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());

        String roomName = "Room";
        int floor = 1;
        double width = 1.0;
        double length = 1.0;
        double height = 1.0;

        String deviceName = "Device";

        String roomNotExist = "Kitchen";

        house.addRoom(roomName, floor, width, length, height);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        List<RoomDTO> roomList = controller.getRoomList();
        RoomDTO roomDTO = roomList.stream().filter(r -> r.getName().equals(roomNotExist)).findFirst().orElse(null);

        // Act
        Optional<DeviceDTO> result = controller.addDeviceToRoom(roomDTO, deviceName);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void addDeviceToRoomWithInvalidDeviceNameShouldFail() {
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
        RoomDTO roomDTO = roomList.stream().filter(r -> r.getName().equals(roomName)).findFirst().orElseThrow(() -> new IllegalArgumentException("Room not found"));

        // Act
        Optional<DeviceDTO> result = controller.addDeviceToRoom(roomDTO, deviceName);

        // Assert
        assertFalse(result.isPresent());
    }




}


