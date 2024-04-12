package smartHome.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class UnitIDTest {

    /**
     * test construct
     */
    @Test
    void shouldGetValidObject_whenUsingValidStringInConstructor() {
        // Arrange
        String measurementID = "measurement1";
        // Act
        UnitID unitID = new UnitID(measurementID);
        // Assert
        assertNotNull(unitID);
    }

    /**
     * test construct if null
     */
    @Test
    void shouldThrowException_whenMeasurementIDIsNull() {
        // Arrange
        String measurementID = null;

        String expectedMessage = "The value of 'measurementID' should not null, blank, or empty.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new UnitID(measurementID));

        String actualMessage = exception.getMessage();

        //assert
        assertEquals(expectedMessage, actualMessage);

    }

    /**
     * test construct if blank
     */
    @Test
    void shouldThrowException_whenMeasurementIDIsBlank() {
        // Arrange
        String measurementID = " ";

        String expectedMessage = "The value of 'measurementID' should not null, blank, or empty.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new UnitID(measurementID));

        String actualMessage = exception.getMessage();

        //assert
        assertEquals(expectedMessage, actualMessage);

    }

    /**
     * test construct if empty
     */
    @Test
    void shouldThrowException_whenMeasurementIDIsEmpty() {
        // Arrange
        String measurementID = "";

        String expectedMessage = "The value of 'measurementID' should not null, blank, or empty.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new UnitID(measurementID));

        String actualMessage = exception.getMessage();

        //assert
        assertEquals(expectedMessage, actualMessage);

    }

    /**
     * test equals
     */
    @Test
    void shouldReturnTrue_whenComparingTwoEqualMeasurementIDs() {
        // Arrange
        String measurementID = "measurement1";
        UnitID unitID1 = new UnitID(measurementID);
        UnitID unitID2 = new UnitID(measurementID);

        // Act
        boolean result = unitID1.equals(unitID2);
        // Assert
        assertTrue(result);
    }

    /**
     * test equals if same object
     */
    @Test
    void shouldReturnTrue_whenComparingMeasurementIDToItself() {
        // Arrange
        String measurementID = "measurement1";
        UnitID unitID1 = new UnitID(measurementID);
        // Act
        boolean result = unitID1.equals(unitID1);
        // Assert
        assertTrue(result);
    }

    /**
     * test equals if different object
     */
    @Test
    void shouldReturnFalse_whenComparingMeasurementIDToDifferentObject() {
        // Arrange
        String measurementID = "measurement1";
        String measurementID2 = "measurement2";

        UnitID unitID1 = new UnitID(measurementID);
        UnitID unitID2 = new UnitID(measurementID2);

        // Act
        boolean result = unitID1.equals(unitID2);
        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalse_whenComparingMeasurementIDToNull() {
        // Arrange
        String measurementID = "measurement1";
        UnitID unitID1 = new UnitID(measurementID);
        // Act
        boolean result = unitID1.equals(null);
        // Assert
        assertFalse(result);
    }

    /**
     * test get id
     */
    @Test
    void shouldReturnMeasurementID_whenGetIDIsCalled() {
        // Arrange
        String measurementID = "measurement1";
        UnitID unitID1 = new UnitID(measurementID);

        // Act
        String result = unitID1.getID();
        // Assert
        assertEquals(measurementID, result);
    }

    /**
     * test hash code
     */
    @Test
    void shouldReturnHashCode_whenHashCodeIsCalled() {
        // Arrange
        String measurementID = "measurement1";
        UnitID unitID1 = new UnitID(measurementID);
        // Act
        int result = unitID1.hashCode();
        // Assert
        assertTrue(result == measurementID.hashCode());
    }


}
