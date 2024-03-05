package SmartHome.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoomTest {

    @Test
    void testConstructWithValidArguments() {
        // Arrange
        Dimensions dimensionsDouble = mock(Dimensions.class);
        DimensionsFactory dimensionsFactory = mock(DimensionsFactory.class);
        String name = "Room";
        int floor = 1;
        double length = 10;
        double width = 10;
        double height = 10;

        when(dimensionsFactory.createDimensions(width, length, height)).thenReturn(dimensionsDouble);

        // Act
        Room room = new Room(name, floor, width, length, height, dimensionsFactory);
    }

    @Test
    void setNameShouldThrowExceptionWhenNameIsInvalid() {
        // Arrange
        Dimensions dimensionsDouble = mock(Dimensions.class);
        DimensionsFactory dimensionsFactory = mock(DimensionsFactory.class);
        String name = " ";
        int floor = 1;
        double length = 10;
        double width = 10;
        double height = 10;

        when(dimensionsFactory.createDimensions(width, length, height)).thenReturn(dimensionsDouble);

        String expectedMessage = "Please enter a valid name for the room.";

        // Act
        String exception = assertThrows(IllegalArgumentException.class, () -> new Room(name, floor, width, length, height, dimensionsFactory)).getMessage();

        // Assert
        assertTrue(exception.contains(expectedMessage));
    }

    @Test
    void getNameShouldReturnRoomName() {
        // Arrange
        Dimensions dimensionsDouble = mock(Dimensions.class);
        DimensionsFactory dimensionsFactory = mock(DimensionsFactory.class);
        String name = "Room";
        int floor = 1;
        double length = 10;
        double width = 10;
        double height = 10;

        when(dimensionsFactory.createDimensions(width, length, height)).thenReturn(dimensionsDouble);

        Room room = new Room(name, floor, width, length, height, dimensionsFactory);

        // Act
        String result = room.getName();

        // Assert
        assertEquals(name, result);
    }

    @Test
    void getDimensionsShouldReturnRoomDimensions() {
        // Arrange
        Dimensions dimensionsDouble = mock(Dimensions.class);
        DimensionsFactory dimensionsFactory = mock(DimensionsFactory.class);
        String name = "Room";
        int floor = 1;
        double length = 10;
        double width = 10;
        double height = 10;

        when(dimensionsFactory.createDimensions(width, length, height)).thenReturn(dimensionsDouble);

        Room room = new Room(name, floor, width, length, height, dimensionsFactory);

        // Act
        Dimensions result = room.getDimensions();

        // Assert
        assertEquals(dimensionsDouble, result);
    }

    @Test
    void getIdShouldReturnRoomID() {
        // Arrange
        Dimensions dimensionsDouble = mock(Dimensions.class);
        DimensionsFactory dimensionsFactory = mock(DimensionsFactory.class);
        String name = "Room";
        int floor = 1;
        double length = 10;
        double width = 10;
        double height = 10;

        when(dimensionsFactory.createDimensions(width, length, height)).thenReturn(dimensionsDouble);

        Room room = new Room(name, floor, width, length, height, dimensionsFactory);
        String expected = room.toString();

        // Act
        String result = room.getRoomId().toString();

        // Assert
        assertTrue(expected.contains(result));
    }

    @Test
    void getFloorShouldReturnCorrectFloor() {
        // Arrange
        Dimensions dimensionsDouble = mock(Dimensions.class);
        DimensionsFactory dimensionsFactory = mock(DimensionsFactory.class);
        String name = "Room";
        int floor = 1;
        double length = 10;
        double width = 10;
        double height = 10;

        when(dimensionsFactory.createDimensions(width, length, height)).thenReturn(dimensionsDouble);

        Room room = new Room(name, floor, width, length, height, dimensionsFactory);

        // Act
        int result = room.getFloor();

        // Assert
        assertEquals(floor, result);
    }

    @Test
    void shouldInstantiateDeviceSuccessfully() {
        // Arrange
        Dimensions dimensionsDouble = mock(Dimensions.class);
        DimensionsFactory dimensionsFactory = mock(DimensionsFactory.class);
        String name = "Room";
        int floor = 1;
        double length = 10;
        double width = 10;
        double height = 10;

        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        Device deviceDouble = mock(Device.class);
        String deviceName = "Device";

        when(dimensionsFactory.createDimensions(width, length, height)).thenReturn(dimensionsDouble);
        when(deviceFactory.createDevice(deviceName)).thenReturn(deviceDouble);

        Room room = new Room(name, floor, width, length, height, dimensionsFactory);

        // Act
        Device result = room.addDevice(deviceName, deviceFactory);

        // Assert
        assertEquals(deviceDouble, result);
    }

    @Test
    void shouldAddDeviceToList() {
        // Arrange
        Dimensions dimensionsDouble = mock(Dimensions.class);
        DimensionsFactory dimensionsFactory = mock(DimensionsFactory.class);
        String name = "Room";
        int floor = 1;
        double length = 10;
        double width = 10;
        double height = 10;

        Device deviceDouble = mock(Device.class);

        when(dimensionsFactory.createDimensions(width, length, height)).thenReturn(dimensionsDouble);

        Room room = new Room(name, floor, width, length, height, dimensionsFactory);

        // Act
        Device device = room.addDeviceToList(deviceDouble);

        // Assert
        List<Device> expected = room.getDevices(); // Done after the fact as returning copy of list
        assertTrue(expected.contains(device));
    }

    @Test
    void shouldReturnEmptyListOfDevices() {
        // Arrange
        Dimensions dimensionsDouble = mock(Dimensions.class);
        DimensionsFactory dimensionsFactory = mock(DimensionsFactory.class);
        String name = "Room";
        int floor = 1;
        double length = 10;
        double width = 10;
        double height = 10;

        when(dimensionsFactory.createDimensions(width, length, height)).thenReturn(dimensionsDouble);

        Room room = new Room(name, floor, width, length, height, dimensionsFactory);
        int expected = 0;

        // Act
        int result = room.getDevices().size();

        // Assert
        assertEquals(expected, result);
    }
    @Test
    void ifDimensionsNullShouldIllegalArgumentExceptionIsThrown() {
        // Arrange
        DimensionsFactory dimensionsFactory = mock(DimensionsFactory.class);
        String name = "Room";
        int floor = 1;
        double length = 10;
        double width = 10;
        double height = 10;

        when(dimensionsFactory.createDimensions(width, length, height)).thenReturn(null);

        String expectedMessage = "Invalid dimensions";

        // Act
        String exception = assertThrows(IllegalArgumentException.class, () -> new Room(name, floor, width, length, height, dimensionsFactory)).getMessage();

        // Assert
        assertTrue(exception.contains(expectedMessage));
    }


}
