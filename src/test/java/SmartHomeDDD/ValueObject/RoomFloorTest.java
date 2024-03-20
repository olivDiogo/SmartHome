package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomFloorTest {
    @Test
    void shouldInstantiateRoomFloorWhenFloorIsPositive() {
        // Arrange
        int floor = 0;
        // Act
        new RoomFloor(floor);
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenFloorIsBellowAllowedRange() {
        // Arrange
        int floor = -36;
        String expectedMessage = "Invalid floor number.";
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new RoomFloor(floor));
        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenFloorIsAboveAllowedRange() {
        // Arrange
        int floor = 163;
        String expectedMessage = "Invalid floor number.";
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new RoomFloor(floor));
        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldReturnFloorWhenGetFloorIsCalled() {
        // Arrange
        int floor = 0;
        RoomFloor roomFloor = new RoomFloor(floor);
        // Act
        int result = roomFloor.getFloor();
        // Assert
        assertEquals(floor, result);
    }
    @Test
    void shouldReturnTrueWhenComparingTwoRoomFloorsWithSameFloor() {
        // Arrange
        int floor = 0;
        RoomFloor roomFloor1 = new RoomFloor(floor);
        RoomFloor roomFloor2 = new RoomFloor(floor);
        // Act
        boolean result = roomFloor1.equals(roomFloor2);
        // Assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFloorWhenToStringIsCalled() {
        // Arrange
        int floor = 0;
        RoomFloor roomFloor = new RoomFloor(floor);
        String expected = "RoomFloor{_floor=0}";
        // Act
        String result = roomFloor.toString();
        // Assert
        assertEquals(expected, result);
    }

}