package smart_home.domain.unit;

import org.junit.jupiter.api.Test;
import smart_home.value_object.UnitDescription;
import smart_home.value_object.UnitID;
import smart_home.value_object.UnitSymbol;

import static org.junit.jupiter.api.Assertions.*;

class UnitAggregateTest {
    /**
     * Validates construction with valid arguments.
     */
    @Test
    void shouldReturnValidUnit_WhenGivenValidParameters() {
        //Arrange
        String strUnit = "C";
        String strDescription = "Celsius";

        UnitSymbol unit = new UnitSymbol(strUnit);
        UnitDescription unitDescription = new UnitDescription(strDescription);

        //Act
        Unit result = new Unit(unitDescription, unit);

        //Assert
        assertNotNull(result);
    }


    /**
     * Tests equality on the same object instance.
     */
    @Test
    void shouldReturnTrue_WhenGivenSameObject() {
        //Arrange
        String strUnit = "C";
        String strDescription = "Celsius";

        UnitSymbol unitSymbol = new UnitSymbol(strUnit);
        UnitDescription unitDescription = new UnitDescription(strDescription);

        Unit unit = new Unit(unitDescription, unitSymbol);

        //Act
        boolean result = unit.equals(unit);

        //Assert
        assertTrue(result);
    }

    /**
     * Tests inequality on objects with different IDs.
     */
    @Test
    void shouldReturnFalse_WhenComparingTwoObjectsWithDifferentID() {
        //Arrange
        String strUnit = "C";
        String strDescription = "Celsius";
        String strAnotherDescription = "Fahrenheit";

        UnitSymbol unitSymbol = new UnitSymbol(strUnit);
        UnitDescription unitDescription = new UnitDescription(strDescription);

        UnitDescription anotherUnitDescription = new UnitDescription(strAnotherDescription);

        Unit unit = new Unit(unitDescription, unitSymbol);
        Unit unit2 = new Unit(anotherUnitDescription, unitSymbol);

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
        String strUnit = "C";
        String strDescription = "Celsius";

        UnitSymbol unitSymbol = new UnitSymbol(strUnit);
        UnitDescription unitDescription = new UnitDescription(strDescription);

        Unit unit = new Unit(unitDescription, unitSymbol);

        //Act
        boolean result = unit.equals(null);

        //Assert
        assertFalse(result);

    }

    /**
     * Tests getting Unit description.
     */
    @Test
    void shouldReturnUnitDescription_whenGetUnitDescriptionIsCalled() {
        //Arrange
        String strUnit = "C";
        String strDescription = "Celsius";

        UnitSymbol unitSymbol = new UnitSymbol(strUnit);
        UnitDescription unitDescription = new UnitDescription(strDescription);

        Unit unit = new Unit(unitDescription, unitSymbol);

        //Act
        UnitDescription result = unit.getUnitDescription();

        //Assert
        assertEquals(unitDescription, result);
    }

    /**
     * Tests getting ID.
     */
    @Test
    void shouldReturnUnitID_whenGetIDisCalled() {
        //Arrange
        String strUnit = "C";
        String strDescription = "Celsius";

        UnitSymbol unitSymbol = new UnitSymbol(strUnit);
        UnitDescription unitDescription = new UnitDescription(strDescription);

        Unit unit = new Unit(unitDescription, unitSymbol);

        //Act
        UnitID result = unit.getID();

        //Assert
        assertTrue(unit.toString().contains(result.toString()));
    }

    /**
     * Tests toString method.
     */
    @Test
    void shouldReturnString_whenToStringIsCalled() {
        //Arrange
        String strUnit = "C";
        String strDescription = "Celsius";

        UnitSymbol unitSymbol = new UnitSymbol(strUnit);
        UnitDescription unitDescription = new UnitDescription(strDescription);

        Unit unit = new Unit(unitDescription, unitSymbol);

        UnitID unitID = unit.getID();

        String expected = "Unit:" +
                "unitSymbol=" + unitSymbol +
                ", unitDescription=" + unitDescription +
                ", unitID=" + unitID;

        //Act
        String result = unit.toString();

        //Assert
        assertEquals(expected, result);

    }

    /**
     * Tests getting unit symbol.
     */
    @Test
    void shouldReturnUnitSymbol_whenGetUnitSymbolIsCalled() {
        //Arrange
        String strUnit = "C";
        String strDescription = "Celsius";

        UnitSymbol unitSymbol = new UnitSymbol(strUnit);
        UnitDescription unitDescription = new UnitDescription(strDescription);

        Unit unit = new Unit(unitDescription, unitSymbol);

        //Act
        UnitSymbol result = unit.getUnitSymbol();

        //Assert
        assertEquals(unitSymbol, result);

    }

    /**
     * Tests returning hash code.
     */
    @Test
    void shouldReturnHashCode_whenGetHashCodeIsCalled() {
        //Arrange
        String strUnit = "C";
        String strDescription = "Celsius";

        UnitSymbol unitSymbol = new UnitSymbol(strUnit);
        UnitDescription unitDescription = new UnitDescription(strDescription);

        Unit unit = new Unit(unitDescription, unitSymbol);

        int expected = unit.getID().hashCode();

        //Act
        int result = unit.hashCode();

        //Assert
        assertEquals(expected, result);
    }
}
