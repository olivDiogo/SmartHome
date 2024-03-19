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
        int floor = -6;
        String expectedMessage = "Floor must be a positive integer.";
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new RoomFloor(floor));
        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

}