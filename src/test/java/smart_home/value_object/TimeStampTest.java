package smart_home.value_object;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeStampTest {

    /**
     * Test construct whit valid parameters.
     */
    @Test
    void shouldGetObject_whenUsingValidParameters() {
        // Arrange
        LocalDateTime localDateTime = LocalDateTime.now();
        // Act
        TimeStamp timeStamp = new TimeStamp(localDateTime);
        // Assert
        assertNotNull(timeStamp);
    }

    /**
     * Test construct whit invalid parameters.
     */
    @Test
    void shouldThrowException_whenTimeStampIsNull() {
        // Arrange
        LocalDateTime localDateTime = null;
        String expectedMessage = "timeStamp is required";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new TimeStamp(localDateTime)
        );
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test getTimeStamp method.
     */
    @Test
    void shouldReturnTimeStamp_whenCallingGetTimeStamp() {
        // Arrange
        LocalDateTime localDateTime = LocalDateTime.now();
        TimeStamp timeStamp = new TimeStamp(localDateTime);
        // Act
        LocalDateTime result = timeStamp.getTimeStamp();
        // Assert
        assertEquals(localDateTime, result);
    }

    /**
     * Test equals method with the same object.
     */
    @Test
    void shouldReturnTrue_whenComparingTheSameObject() {
        // Arrange
        LocalDateTime localDateTime = LocalDateTime.now();
        TimeStamp timeStamp = new TimeStamp(localDateTime);
        // Act
        boolean result = timeStamp.equals(timeStamp);
        // Assert
        assertTrue(result);
    }

    /**
     * Test equals method with different object.
     */
    @Test
    void shouldReturnFalse_whenComparingDifferentObject() {
        // Arrange
        LocalDateTime localDateTime = LocalDateTime.of(2020, 1, 1, 1, 1, 1);
        LocalDateTime localDateTime2 = LocalDateTime.of(2021, 1, 1, 1, 1, 1);

        TimeStamp timeStamp = new TimeStamp(localDateTime);
        TimeStamp timeStamp2 = new TimeStamp(localDateTime2);

        // Act
        boolean result = timeStamp.equals(timeStamp2);

        // Assert
        assertFalse(result);
    }

    /**
     * Equals returns false when comparing with null.
     */
    @Test
    void shouldReturnFalse_whenComparingWithNull() {
        // Arrange
        LocalDateTime localDateTime = LocalDateTime.now();
        TimeStamp timeStamp = new TimeStamp(localDateTime);
        // Act
        boolean result = timeStamp.equals(null);
        // Assert
        assertFalse(result);
    }

    /**
     * Test HashCode method.
     */
    @Test
    void shouldReturnHashCode_whenCallingHashCode() {
        // Arrange
        LocalDateTime localDateTime = LocalDateTime.now();
        TimeStamp timeStamp = new TimeStamp(localDateTime);
        // Act
        int result = timeStamp.hashCode();
        // Assert
        assertEquals(localDateTime.hashCode(), result);
    }

    /**
     * Test toString method.
     */
    @Test
    void shouldReturnTimeStampInString_whenCallingToString() {
        // Arrange
        LocalDateTime localDateTime = LocalDateTime.of(2020, 1, 1, 1, 1, 1);
        String expected = "TimeStamp: timeStamp=" + localDateTime;
        TimeStamp timeStamp = new TimeStamp(localDateTime);

        // Act
        String result = timeStamp.toString();

        // Assert
        assertEquals(expected, result);
    }


}
