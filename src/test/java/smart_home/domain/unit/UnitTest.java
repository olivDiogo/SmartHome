package smart_home.domain.unit;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smart_home.value_object.UnitDescription;
import smart_home.value_object.UnitID;
import smart_home.value_object.UnitSymbol;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnitTest {

    /**
     * Validates construction with valid arguments.
     */
    @Test
    void shouldReturnValidUnit_WhenGivenValidParameters() {
        //Arrange
        UnitSymbol unitDouble = mock(UnitSymbol.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

        //Act
        Unit result = new Unit(unitDescriptionDouble, unitDouble);

        //Assert
        assertNotNull(result);
    }

    /**
     * Expects IllegalArgumentException for null measurement unit.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullUnitSymbol() {
        //Arrange
        UnitSymbol unitDouble = null;
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

        String expectedMessage = "Unit symbol is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Unit(unitDescriptionDouble, unitDouble));

        //Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
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

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Unit(unitDescriptionDouble, unitDouble));

        //Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Tests equality on the same object instance.
     */
    @Test
    void shouldReturnTrue_WhenGivenSameObject() {
        //Arrange
        UnitSymbol unitDouble = mock(UnitSymbol.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

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
    void shouldReturnFalse_WhenComparingTwoObjectsWithDifferentID() {
        //Arrange
        UnitSymbol unitDouble = mock(UnitSymbol.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
        UnitDescription anotherUnitDescriptionDouble = mock(UnitDescription.class);

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
        UnitSymbol unitDouble = mock(UnitSymbol.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

        try (MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {
            Unit unit = new Unit(unitDescriptionDouble, unitDouble);

            //Act
            boolean result = unit.equals(null);

            //Assert
            assertFalse(result);
        }
    }

    /**
     * Tests getting Unit description.
     */
    @Test
    void shouldReturnUnitDescription_whenGetUnitDescriptionIsCalled() {
        //Arrange
        String description = "Celsius";

        UnitSymbol unitDouble = mock(UnitSymbol.class);

        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
        when(unitDescriptionDouble.toString()).thenReturn(description);

        try(MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {
            Unit unit = new Unit(unitDescriptionDouble, unitDouble);

            //Act
            UnitDescription result = unit.getUnitDescription();

            //Assert
            assertEquals(unitDescriptionDouble, result);
        }
    }

    /**
     * Tests getting ID.
     */
    @Test
    void shouldReturnUnitID_whenGetIDisCalled() {
        //Arrange
        UnitSymbol unitDouble = mock(UnitSymbol.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

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

        UnitSymbol unitDouble = mock(UnitSymbol.class);
        when(unitDouble.toString()).thenReturn(unitSymbol);

        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
        when(unitDescriptionDouble.toString()).thenReturn(description);

        try (MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {
            Unit unit = new Unit(unitDescriptionDouble, unitDouble);

            UnitID unitIDDouble = mockedUnitID.constructed().get(0);

            String expected = "Unit:" +
                    "unitSymbol=" + unitDouble +
                    ", unitDescription=" + unitDescriptionDouble +
                    ", unitID=" + unitIDDouble;

            //Act
            String result = unit.toString();

            //Assert
            assertEquals(expected, result);
        }
    }

    /**
     * Tests getting unit symbol.
     */
    @Test
    void shouldReturnUnitSymbol_whenGetUnitSymbolIsCalled() {
        //Arrange
        String unitSymbol = "C";

        UnitSymbol unitDouble = mock(UnitSymbol.class);
        when(unitDouble.toString()).thenReturn(unitSymbol);

        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

        try (MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {
            Unit unit = new Unit(unitDescriptionDouble, unitDouble);

            //Act
            UnitSymbol result = unit.getUnitSymbol();

            //Assert
            assertEquals(unitDouble, result);
        }
    }

    /**
     * Tests returning hash code.
     */
    @Test
    void shouldReturnHashCode_whenGetHashCodeIsCalled(){
        //Arrange
        UnitSymbol unitDouble = mock(UnitSymbol.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

        try (MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {
            Unit unit = new Unit(unitDescriptionDouble, unitDouble);
            int expected = unit.getID().hashCode();

            //Act
            int result = unit.hashCode();

            //Assert
            assertEquals(expected, result);
        }
    }
}