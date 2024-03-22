package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

class MeasurementIDTest {

    /**
     * test construct
     */
    @Test
    void shouldGetValidObject_whenUsingValidStringInConstructor() {
        // Arrange
        String measurementID = "measurement1";
        // Act
        new MeasurementID(measurementID);
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
                new MeasurementID(measurementID)
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
                new MeasurementID(measurementID)
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
                new MeasurementID(measurementID)
        );
    }

    /**
     * test equals
     */
    @Test
    void shouldReturnTrue_whenComparingTwoEqualMeasurementIDs() {
        // Arrange
        String measurementID = "measurement1";
        MeasurementID measurementID1 = new MeasurementID(measurementID);
        MeasurementID measurementID2 = new MeasurementID(measurementID);
        // Act
        boolean result = measurementID1.equals(measurementID2);
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
        MeasurementID measurementID1 = new MeasurementID(measurementID);
        // Act
        boolean result = measurementID1.equals(measurementID1);
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
        MeasurementID measurementID1 = new MeasurementID(measurementID);
        // Act
        boolean result = measurementID1.equals(new Object());
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
        MeasurementID measurementID1 = new MeasurementID(measurementID);
        // Act
        String result = measurementID1.getId();
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
        MeasurementID measurementID1 = new MeasurementID(measurementID);
        // Act
        int result = measurementID1.hashCode();
        // Assert
        assertTrue(result == measurementID.hashCode());
    }


}
