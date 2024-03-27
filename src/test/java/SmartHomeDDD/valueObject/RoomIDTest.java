package SmartHomeDDD.valueObject;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

class RoomIDTest {

    /**
     * Tests the correct instantiation of a RoomID
     */
    @Test
    public void shouldGetValidObject_whenUsingValidStringInConstructor() {
        // Arrange
        String roomID = "Room12";
        // Act
        new RoomID(roomID);

    }

    /**
     * Tests if the exception is thrown with a null roomID.
     */
    @Test
    public void shouldThrowException_whenRoomIdIsNull() {
        // Arrange
        String roomID = null;
        String expectedMessage = "'roomID' must be a non-empty string.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new RoomID(roomID)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the exception is thrown with a blank roomID.
     */
    @Test
    public void shouldThrowException_whenRoomIdIsBlank() {
        // Arrange
        String roomID = " ";
        String expectedMessage = "'roomID' must be a non-empty string.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new RoomID(roomID)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the exception is thrown with an empty roomID.
     */
    @Test
    public void shouldThrowException_whenRoomIdIsEmpty() {
        // Arrange
        String roomID = "";
        String expectedMessage = "'roomID' must be a non-empty string.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new RoomID(roomID)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the RoomID is correctly returned.
     */
    @Test
    public void shouldReturnRoomID() {
        // Arrange
        String roomID = "Room12";
        RoomID roomID1 = new RoomID(roomID);

        String expected = "Room12";

        // Act
        String result = roomID1.getId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests if the RoomID is equal to itself.
     */
    @Test
    public void shouldReturnTrue_WhenRoomIdIsEqualToItself() {
        // Arrange
        String roomID = "Room12";
        RoomID roomID1 = new RoomID(roomID);

        // Act
        boolean result = roomID1.equals(roomID1);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests if the RoomID is equal to another RoomID.
     */
    @Test
    public void shouldReturnTrue_WhenRoomIdIsEqualToOtherRoomId() {
        // Arrange
        String roomID = "Room12";
        RoomID roomID1 = new RoomID(roomID);
        RoomID roomID2 = new RoomID(roomID);

        // Act
        boolean result = roomID1.equals(roomID2);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests if the RoomID is different from another RoomID.
     */
    @Test
    public void shouldReturnFalse_WhenRoomIdIsDifferentFromOtherRoomId() {
        // Arrange
        String roomID1Description = "Room12";
        String roomID2Description = "Room13";

        RoomID roomID1 = new RoomID(roomID1Description);
        RoomID roomID2 = new RoomID(roomID2Description);

        // Act
        boolean result = roomID1.equals(roomID2);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests if the object in the hashcode is the same as the RoomID.
     */
    @Test
    public void shouldReturnHashCode() {
        // Arrange
        String roomIDDescription = "Room12";
        RoomID roomID = new RoomID(roomIDDescription);

        // Act
        int result = roomID.hashCode();

        // Assert
        assertEquals(roomIDDescription.hashCode(), result);
    }

    @Test
    public void shouldReturnRoomIDInString () {
        // Arrange
        String roomIDDescription = "Room12";
        RoomID roomID = new RoomID(roomIDDescription);
        String expected = "Room12";

        // Act
        String result = roomID.toString();

        // Assert
        assertEquals(expected, result);
    }

}