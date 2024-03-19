package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class RoomNameTest {
    /**
     * Tests the RoomName constructor with a valid room name.
     */
    @Test
    public void shouldGetValidObject_whenUsingValidRoomName(){
        // Arrange
        String validRoomName = "Living Room 2";

        // Act
        new RoomName(validRoomName);

        // Assert
    }

    /**
     * Tests the RoomName constructor with a null room name.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenRoomNameNull(){
        // Arrange
        String nullRoomName = null;
        String expectedMessage = "The room name cannot be null, blank, or empty.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new RoomName(nullRoomName)
        );

        // Assert
        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Tests the RoomName constructor with a blank room name.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenRoomNameBlank(){
        // Arrange
        String blankRoomName = " ";
        String expectedMessage = "The room name cannot be null, blank, or empty.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new RoomName(blankRoomName)
        );

        // Assert
        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Tests the RoomName constructor with an empty room name.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenRoomNameEmpty(){
        // Arrange
        String emptyRoomName = "";
        String expectedMessage = "The room name cannot be null, blank, or empty.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new RoomName(emptyRoomName)
        );

        // Assert
        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Tests the getRoomName method.
     */
    @Test
    public void shouldGetRoomName(){
        // Arrange
        String roomName = "Kitchen 1";
        RoomName roomNameObject = new RoomName(roomName);

        // Act
        String actualRoomName = roomNameObject.getRoomName();

        // Assert
        assertTrue(actualRoomName.equals(roomName));
    }

}
