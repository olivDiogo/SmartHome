package SmartHomeDDD.domain.Unit;

import SmartHomeDDD.valueObject.UnitID;
import SmartHomeDDD.valueObject.UnitSymbol;
import SmartHomeDDD.valueObject.UnitDescription;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UnitTest {

    /**
     * Validates construction with valid arguments.
     */
    @Test
    void shouldReturnValidMeasurement_WhenGivenValidParameters() {
        //Arrange
        UnitSymbol unitDouble = mock(UnitSymbol.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
        //Act
        new Unit( unitDescriptionDouble, unitDouble );
    }
    /**
     * Expects IllegalArgumentException for null measurement unit.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullUnit() {
        //Arrange
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
        UnitSymbol unitDouble = null;
        String expectedMessage = "Measurement unit is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Unit(unitDescriptionDouble,unitDouble));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    /**
     * Expects IllegalArgumentException for null unit description.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullUnitDescription() {
        //Arrange
        UnitSymbol unitDouble = mock(UnitSymbol.class);
        UnitDescription unitDescriptionDouble = null;
        String expectedMessage = "Unit description is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Unit(unitDescriptionDouble, unitDouble));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    /**
     * Tests equality on the same object instance.
     */
    @Test
    void shouldReturnTrue_WhenGivenSameObject() {
        //Arrange
        UnitSymbol unitDouble = mock(UnitSymbol.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
        Unit unit = new Unit(unitDescriptionDouble, unitDouble);
        //Act
        boolean result = unit.equals(unit);
        //Assert
        assertTrue(result);
    }

    /**
     * Tests inequality on objects with different IDs.
     */
    @Test
    void shouldReturnFalse_WhenComparingTwoObjectsWithDifferentID () {
        //Arrange
        UnitSymbol unitDouble = mock(UnitSymbol.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
        UnitDescription unitDescriptionDouble2 = mock(UnitDescription.class);
        Unit unit = new Unit(unitDescriptionDouble, unitDouble);
        Unit unit2 = new Unit(unitDescriptionDouble2, unitDouble);
        //Act
        boolean result = unit.equals(unit2);
        //Assert
        assertFalse(result);
    }
    /**
     * Tests inequality with null.
     */
    @Test
    void shouldReturnFalse_WhenComparingObjectWithNull() {
        //Arrange
        UnitSymbol unitDouble = mock(UnitSymbol.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
        Unit unit = new Unit(unitDescriptionDouble, unitDouble);
        //Act
        boolean result = unit.equals(null);
        //Assert
        assertFalse(result);
    }
    /**
     * Tests getting MeasurementDescription.
     */
    @Test
    void shouldReturnMeasurementDescription_whenGetUnitDescriptionIsCalled(){
        //Arrange
        UnitSymbol unitDouble = mock(UnitSymbol.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

        Unit unit = new Unit(unitDescriptionDouble, unitDouble);

        //Act
        UnitDescription result = unit.getUnitDescription();

        //Assert
        assertEquals(unitDescriptionDouble, result);
    }
    /**
     * Tests getting ID.
     */
    @Test
    void shouldReturnMeasurementID_whenGetIDisCalled(){
        //Arrange
        UnitSymbol unitDouble = mock(UnitSymbol.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

        Unit unit = new Unit(unitDescriptionDouble, unitDouble);

        //Act
        UnitID result = unit.getID();

        //Assert
        assertTrue(unit.toString().contains(result.toString()));
    }
    /**
     * Tests toString method.
     */
    @Test
    void shouldReturnString_whenToStringIsCalled(){
        //Arrange
        UnitSymbol unitDouble = mock(UnitSymbol.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

        Unit unit = new Unit(unitDescriptionDouble, unitDouble);

        //Act
        String result = unit.toString();

        //Assert
        assertTrue(result.contains(unit.getID().toString()));
    }

}