package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

class UnitIDTest {

    /**
     * test construct
     */
    @Test
    void shouldGetValidObject_whenUsingValidStringInConstructor() {
        // Arrange
        String measurementID = "measurement1";
        // Act
        new UnitID(measurementID);
    }

    /**
     * test construct if null
     */
    @Test
    void shouldThrowException_whenMeasurementIDIsNull() {
        // Arrange
        String measurementID = null;
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new UnitID(measurementID)
        );
    }

    /**
     * test construct if blank
     */
    @Test
    void shouldThrowException_whenMeasurementIDIsBlank() {
        // Arrange
        String measurementID = " ";
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new UnitID(measurementID)
        );
    }

    /**
     * test construct if empty
     */
    @Test
    void shouldThrowException_whenMeasurementIDIsEmpty() {
        // Arrange
        String measurementID = "";
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new UnitID(measurementID)
        );
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
        UnitID unitID1 = new UnitID(measurementID);
        // Act
        boolean result = unitID1.equals(new Object());
        // Assert
        assertTrue(!result);
    }

    /**
     * test get id
     */
    @Test
    void shouldReturnMeasurementID_whenGetIdIsCalled() {
        // Arrange
        String measurementID = "measurement1";
        UnitID unitID1 = new UnitID(measurementID);
        // Act
        String result = unitID1.getId();
        // Assert
        assertTrue(result.equals(measurementID));
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
