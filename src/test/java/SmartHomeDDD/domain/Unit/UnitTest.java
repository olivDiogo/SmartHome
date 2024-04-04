package SmartHomeDDD.domain.Unit;

import SmartHomeDDD.valueObject.UnitDescription;
import SmartHomeDDD.valueObject.UnitID;
import SmartHomeDDD.valueObject.UnitSymbol;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;

class UnitTest {

    /**
     * Validates construction with valid arguments.
     */
    @Test
    void shouldReturnValidMeasurement_WhenGivenValidParameters() {
        //Arrange
        String unit = "C";
        String description = "Celsius";

        UnitSymbol unitDouble = new UnitSymbol(unit);
        UnitDescription unitDescriptionDouble = new UnitDescription(description);

        //Act
        Unit result = new Unit(unitDescriptionDouble, unitDouble);

        //Assert
        assertNotNull(result);

    }

    /**
     * Expects IllegalArgumentException for null measurement unit.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullUnit() {
        //Arrange
        String description = "Celsius";
        UnitSymbol unitDouble = null;

        UnitDescription unitDescriptionDouble = new UnitDescription(description);

        String expectedMessage = "Measurement unit is required";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Unit(unitDescriptionDouble, unitDouble));

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Expects IllegalArgumentException for null unit description.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullUnitDescription() {
        //Arrange
        String unit = "C";

        UnitSymbol unitDouble = new UnitSymbol(unit);
        UnitDescription unitDescriptionDouble = null;

        String expectedMessage = "Unit description is required";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Unit(unitDescriptionDouble, unitDouble));

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Tests equality on the same object instance.
     */
    @Test
    void shouldReturnTrue_WhenGivenSameObject() {
        //Arrange
        String unitSymbol = "C";
        String description = "Celsius";

        UnitSymbol unitDouble = new UnitSymbol(unitSymbol);
        UnitDescription unitDescriptionDouble = new UnitDescription(description);

        try (MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {

            Unit unit = new Unit(unitDescriptionDouble, unitDouble);
            //Act
            boolean result = unit.equals(unit);
            //Assert
            assertTrue(result);
        }
    }

    /**
     * Tests inequality on objects with different IDs.
     */
    @Test
    void shouldReturnFalse_WhenComparingTwoObjectsWithDifferentID() throws Exception {
        //Arrange
        String unitSymbol = "C";
        String description = "Celsius";
        String anotherDescription = "Fahrenheit";

        UnitSymbol unitDouble = new UnitSymbol(unitSymbol);
        UnitDescription unitDescriptionDouble = new UnitDescription(description);
        UnitDescription anotherUnitDescriptionDouble = new UnitDescription(anotherDescription);

        try (MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {
            Unit unit = new Unit(unitDescriptionDouble, unitDouble);
            Unit unit2 = new Unit(anotherUnitDescriptionDouble, unitDouble);
            //Act
            boolean result = unit.equals(unit2);
            //Assert
            assertFalse(result);
        }
    }

    /**
     * Tests inequality with null.
     */
    @Test
    void shouldReturnFalse_WhenComparingObjectWithNull() {
        //Arrange
        String unitSymbol = "C";
        String description = "Celsius";

        UnitSymbol unitDouble = new UnitSymbol(unitSymbol);
        UnitDescription unitDescriptionDouble = new UnitDescription(description);

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
    void shouldReturnMeasurementDescription_whenGetUnitDescriptionIsCalled() {
        //Arrange
        String unitSymbol = "C";
        String description = "Celsius";

        UnitSymbol unitDouble = new UnitSymbol(unitSymbol);
        UnitDescription unitDescriptionDouble = new UnitDescription(description);


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
    void shouldReturnMeasurementID_whenGetIDisCalled() {
        //Arrange
        String unitSymbol = "C";
        String description = "Celsius";

        UnitSymbol unitDouble = new UnitSymbol(unitSymbol);
        UnitDescription unitDescriptionDouble = new UnitDescription(description);

        try (MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {
            Unit unit = new Unit(unitDescriptionDouble, unitDouble);

            //Act
            UnitID result = unit.getID();

            //Assert
            assertTrue(unit.toString().contains(result.toString()));
        }
    }

    /**
     * Tests toString method.
     */
    @Test
    void shouldReturnString_whenToStringIsCalled() {
        //Arrange
        String unitSymbol = "C";
        String description = "Celsius";

        UnitSymbol unitDouble = new UnitSymbol(unitSymbol);
        UnitDescription unitDescriptionDouble = new UnitDescription(description);

        try (MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {
            Unit unit = new Unit(unitDescriptionDouble, unitDouble);

            UnitID unitIDDouble = mockedUnitID.constructed().get(0);
            String expected = "Unit{" +
                    "_unitSymbol=" + unitDouble +
                    ", _unitDescription=" + unitDescriptionDouble +
                    ", _unitID=" + unitIDDouble +
                    '}';

            //Act
            String result = unit.toString();

            //Assert
            assertTrue(result.contains(expected));
        }
    }

    @Test
    void shouldReturnUnitSymbol_whenGetUnitSymbolIsCalled() {
        //Arrange
        String unitSymbol = "C";
        String description = "Celsius";

        UnitSymbol unitDouble = new UnitSymbol(unitSymbol);
        UnitDescription unitDescriptionDouble = new UnitDescription(description);

        Unit unit = new Unit(unitDescriptionDouble, unitDouble);
        //Act
        UnitSymbol result = unit.getUnitSymbol();
        //Assert
        assertEquals(unitDouble, result);

    }

}