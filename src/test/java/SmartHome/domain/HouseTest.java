package SmartHome.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HouseTest {

    @Test
    void seeIfConstructorWorks() {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        // Act
        // Assert
        assertNotNull(house);
    }

    @Test
    void shouldReturnLocation() {
        // Arrange
        LocationFactory locationFactory = mock(LocationFactory.class);
        RoomFactory roomFactory = mock(RoomFactory.class);
        House house = new House(locationFactory, roomFactory);
        Location locationDouble = mock(Location.class);


        String street = "Rua de Teste";
        String zipCode = "4000-007";
        int doorNumber = 123;
        double latitude = 41.14961;
        double longitude = -8.61099;

        when(locationFactory.createLocation(street, zipCode, doorNumber, latitude, longitude))
                .thenReturn(locationDouble);

        // Act
        Location location = house.defineLocation(street, zipCode, doorNumber, latitude, longitude);

        // Assert
        assertEquals(locationDouble, location);
    }

    @Test
    void shouldReturnRoom() {
        // Arrange
        LocationFactory locationFactory = mock(LocationFactory.class);
        RoomFactory roomFactory = mock(RoomFactory.class);
        House house = new House(locationFactory, roomFactory);
        Room roomDouble = mock(Room.class);

        String name = "Room";
        int floor = 1;
        double length = 10;
        double width = 10;
        double height = 10;

        when(roomFactory.createRoom(name, floor, length, width, height))
                .thenReturn(roomDouble);

        // Act
        Room room = house.addRoom(name, floor, length, width, height);

        // Assert
        assertEquals(roomDouble, room);
    }

    @Test
    void shouldReturnListOfRooms() {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        Room roomDouble = mock(Room.class);

        house.addRoomToList(roomDouble);
        int expected = 1;

        // Act
        List<Room> rooms = house.getRooms();

        // Assert
        assertEquals(rooms.size(), expected);
    }

    @Test
    void shouldReturnEmptyListOfRooms() {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        int expected = 0;

        // Act
        List<Room> rooms = house.getRooms();

        // Assert
        assertEquals(rooms.size(), expected);
    }

    @Test
    void shouldReturnListOfDevices() {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        Room roomDouble = mock(Room.class);
        Room roomDouble2 = mock(Room.class);

        Device deviceDouble = mock(Device.class);
        Device deviceDouble2 = mock(Device.class);

        when(roomDouble.getDevices())
                    .thenReturn(List.of(deviceDouble));
        when(roomDouble2.getDevices())
                .thenReturn(List.of(deviceDouble2));

        house.addRoomToList(roomDouble);
        house.addRoomToList(roomDouble2);

        int expected = 2;

        // Act
        List<Device> devices = house.getAllDevices();

        // Assert
        assertEquals(devices.size(), expected);
    }

    @Test
    void shouldReturnEmptyListOfDevices() {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        Room roomDouble = mock(Room.class);

        house.addRoomToList(roomDouble);
        int expected = 0;

        // Act
        List<Device> devices = house.getAllDevices();

        // Assert
        assertEquals(devices.size(), expected);
    }
}