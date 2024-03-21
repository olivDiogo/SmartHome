package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class MeasurementTypeTypeUnitTest {

    /**
     * Test the constructor of the class Unit.
     */
    @Test
    void shouldReturnExpectedUnit_whenGivenValidParameters() {
        // Arrange
        String unit = "ºC";

        // Act
        MeasurementTypeUnit unitObject = new MeasurementTypeUnit(unit);
    }

    /**
     * Test the constructor of the class Unit with an empty unit.
     */

    @Test
    void shouldThrowException_whenUnitIsEmpty() {
        // Arrange
        String unit = " ";

        // Act+Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new MeasurementTypeUnit(unit));
        assertEquals("Invalid unit", exception.getMessage());
    }

    /**
     * Test the constructor of the class Unit with a null unit.
     */
    @Test
    void shouldThrowException_whenUnitIsNull() {
        // Arrange
        String unit = null;

        // Act+Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new MeasurementTypeUnit(unit));
        assertEquals("Invalid unit", exception.getMessage());
    }

    /**
     * Test the constructor of the class Unit with a unit that has more than 5 characters.
     */
    @Test
    void shouldThrowException_whenUnitAsMoreThan5Characters() {
        // Arrange
        String unit = "km/h  ";

        // Act+Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new MeasurementTypeUnit(unit));
        assertEquals("Invalid unit", exception.getMessage());
    }

    /**
     * Test the equals method of the class Unit.
     */
    @Test
    void shouldReturnTrue_whenComparingTwoEqualUnit() {
        // Arrange
        String unit = "ºC";
        MeasurementTypeUnit unitObject = new MeasurementTypeUnit(unit);
        MeasurementTypeUnit unitObject2 = new MeasurementTypeUnit(unit);

        // Act
        boolean result = unitObject.equals(unitObject2);

        // Assert
        assertTrue(result);
    }

    /**
     * Test the equals method of the class Unit with different units.
     */
    @Test
    void shouldReturnFalse_whenComparingTwoDifferentUnit() {
        // Arrange
        String unit = "ºC";
        String unit2 = "ºF";
        MeasurementTypeUnit unitObject = new MeasurementTypeUnit(unit);
        MeasurementTypeUnit unitObject2 = new MeasurementTypeUnit(unit2);

        // Act
        boolean result = unitObject.equals(unitObject2);

        // Assert
        assertFalse(result);
    }

    /**
     * Test the equals method of the class Unit with the same unit.
     */
    @Test
    void shouldReturnTrue_whenComparingTheSameUnit() {
        // Arrange
        String unit = "ºC";
        MeasurementTypeUnit unitObject = new MeasurementTypeUnit(unit);

        // Act
        boolean result = unitObject.equals(unitObject);

        // Assert
        assertTrue(result);
    }

    /**
     * Test the getUnit method of the class Unit.
     */
    @Test
    void shouldReturnExpectedUnit_whenGetUnit() {
        // Arrange
        String unit = "ºC";
        MeasurementTypeUnit unitObject = new MeasurementTypeUnit(unit);

        // Act
        String result = unitObject.getUnit();

        // Assert
        assertEquals(unit, result);
    }

    /**
     * Test the toString method of the class Unit.
     */
    @Test
    void shouldReturnExpectedString_whenToString() {
        // Arrange
        String unit = "ºC";
        MeasurementTypeUnit unitObject = new MeasurementTypeUnit(unit);

        String expected = "Unit{_unit='ºC}";

        // Act
        String result = unitObject.toString();

        // Assert
        assertEquals(expected, result);
    }
}
