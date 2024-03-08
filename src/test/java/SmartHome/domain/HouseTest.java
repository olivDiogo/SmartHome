package SmartHome.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HouseTest {

    @Test
    void shouldInstantiateHouseWhenConstructorIsCalledWithValidParameters() {
        // Arrange
        LocationFactory locationFactoryDouble = mock(LocationFactory.class);
        RoomFactory roomFactoryDouble = mock(RoomFactory.class);
        // Act
        new House(locationFactoryDouble, roomFactoryDouble);

        // Assert
    }

    @Test
    void shouldReturnLocationWhenConfigureLocationIsCalledWithValidParameters() {
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
        Location location = house.configureLocation(street, zipCode, doorNumber, latitude, longitude);

        // Assert
        assertEquals(locationDouble, location);
    }

    @Test
    void shouldReturnRoomIWhenAddRoomIsCalledWithValidParameters() {
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
    void shouldReturnListOfRoomsContainingAddedRoomWhenAddRoomToListIsSuccessfullyExecuted() {
        // Arrange
        LocationFactory locationFactory = mock(LocationFactory.class);
        RoomFactory roomFactory = mock(RoomFactory.class);
        House house = new House(locationFactory, roomFactory);
        Room roomDouble = mock(Room.class);

        Room room = house.addRoomToList(roomDouble);

        // Act
        List<Room> rooms = house.getRooms();

        // Assert
        assertTrue(rooms.contains(room));
    }

    @Test
    void shouldReturnEmptyListOfRoomsIfNoAddedRoomWhenGetRoomsIsCalled() {
        // Arrange
        LocationFactory locationFactory = mock(LocationFactory.class);
        RoomFactory roomFactory = mock(RoomFactory.class);
        House house = new House(locationFactory, roomFactory);
        int expected = 0;

        // Act
        List<Room> rooms = house.getRooms();

        // Assert
        assertEquals(rooms.size(), expected);
    }

    @Test
    void shouldReturnListOfAllDevicesWhenGetAllDevicesIsCalled() {
        // Arrange
        LocationFactory locationFactory = mock(LocationFactory.class);
        RoomFactory roomFactory = mock(RoomFactory.class);

        House house = new House(locationFactory, roomFactory);
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
        String expected = "["+deviceDouble+", "+deviceDouble2+"]";
        // Act
        List<Device> devices = house.getAllDevices();
        // Assert
        String result = devices.toString();
        assertTrue(result.contains(expected));
    }

    @Test
    void shouldReturnEmptyListOfDevicesWhenGetAllDevicesIsCalledWithNoDevicesAddedToRooms() {
        // Arrange
        LocationFactory locationFactory = mock(LocationFactory.class);
        RoomFactory roomFactory = mock(RoomFactory.class);
        House house = new House(locationFactory, roomFactory);
        Room roomDouble = mock(Room.class);

        house.addRoomToList(roomDouble);
        int expected = 0;

        // Act
        List<Device> devices = house.getAllDevices();

        // Assert
        assertEquals(devices.size(), expected);
    }
}