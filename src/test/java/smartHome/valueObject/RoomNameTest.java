package smartHome.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class RoomNameTest {
    /**
     * Tests the RoomName constructor with a valid room name.
     */
    @Test
    public void shouldGetValidObject_whenUsingValidRoomName(){
        // Arrange
        String validRoomName = "Living Room 2";

        // Act
        RoomName roomName = new RoomName(validRoomName);

        // Assert
        assertNotNull(roomName);
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
     * Tests the RoomName constructor with a room name containing special characters.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenRoomNameContainsSpecialCharacters(){
        // Arrange
        String roomNameWithSpecialCharacters = "Living Room 2!";
        String expectedMessage = "The room name can only contain letters and numbers.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new RoomName(roomNameWithSpecialCharacters)
        );

        // Assert
        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Tests the getRoomName method.
     */
    @Test
    public void shouldGetRoomName_whenCallingGetRoomName(){
        // Arrange
        String roomName = "Kitchen 1";
        RoomName roomNameObject = new RoomName(roomName);

        // Act
        String actualRoomName = roomNameObject.getRoomName();

        // Assert
        assertTrue(actualRoomName.equals(roomName));
    }

    /**
     * Tests the equals method with the same object.
     */
    @Test
    public void shouldReturnTrue_whenComparingSameObject(){
        // Arrange
        String roomName = "Kitchen 1";
        RoomName roomNameObject = new RoomName(roomName);

        // Act
        boolean result = roomNameObject.equals(roomNameObject);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests the equals method with a different object with the same name.
     */
    @Test
    public void shouldReturnTrue_whenComparingDifferentObjectWithSameName() {
        // Arrange
        String roomName = "Kitchen 1";
        RoomName roomNameObject = new RoomName(roomName);
        RoomName roomNameObject2 = new RoomName(roomName);

        // Act
        boolean result = roomNameObject.equals(roomNameObject2);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests the equals method with a different object.
     */
    @Test
    public void shouldReturnFalse_whenComparingDifferentObject(){
        // Arrange
        String roomName = "Kitchen 1";
        RoomName roomNameObject = new RoomName(roomName);
        RoomName roomNameObject2 = new RoomName("Living Room 2");

        // Act
        boolean result = roomNameObject.equals(roomNameObject2);

        // Assert
        assertTrue(!result);
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void shouldReturnRoomName_whenCallingToString(){
        // Arrange
        String roomName = "Kitchen 1";
        RoomName roomNameObject = new RoomName(roomName);

        // Act
        String actualRoomName = roomNameObject.toString();

        // Assert
        assertTrue(actualRoomName.equals(roomName));
    }

    /**
     * Tests the hashCode method.
     */

    @Test
    public void shouldReturnHashCode_whenCallingHashCode(){
        // Arrange
        String roomName = "Kitchen 1";
        RoomName roomNameObject = new RoomName(roomName);

        int expectedHashCode = roomName.hashCode();

        // Act
        int actualHashCode = roomNameObject.hashCode();

        // Assert
        assertEquals(expectedHashCode, actualHashCode);
    }
}
