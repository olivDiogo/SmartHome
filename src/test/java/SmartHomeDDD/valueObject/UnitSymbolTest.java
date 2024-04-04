package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitSymbolTest {

    /**
     * Test case: Instantiate UnitSymbol with null unit
     */
    @Test
    void shouldInstantiateUnitSymbol_WhenGivenValidParameters() {
        //Arrange
        String unit = "C";

        //Act
        UnitSymbol result = new UnitSymbol(unit);

        //Assert
        assertNotNull(result);
    }

    /**
     * Test case: Instantiate UnitSymbol with null unit
     */
    @Test
    void shouldThrowException_WhenUnitSymbolIsNull() {
        //Arrange
        String unit = null;

        String expectedMessage = "Invalid unit";
        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new UnitSymbol(unit);
        });

        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test case: Instantiate UnitSymbol with empty unit
     */
    @Test
    void shouldThrowException_WhenUnitSymbolIsEmpty() {
        //Arrange
        String unit = "";

        String expectedMessage = "Invalid unit";
        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new UnitSymbol(unit);
        });

        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test case: Instantiate UnitSymbol with unit length greater than 5
     */
    @Test
    void shouldThrowException_WhenUnitSymbolLengthIsGreaterThan5() {
        //Arrange
        String unit = "123456";

        String expectedMessage = "Invalid unit";
        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new UnitSymbol(unit);
        });

        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test case: Check if two UnitSymbol objects are equal
     */
    @Test
    void shouldReturnTrue_WhenTwoUnitSymbolObjectsAreEqual() {
        //Arrange
        String unit = "C";
        UnitSymbol unitSymbol1 = new UnitSymbol(unit);
        UnitSymbol unitSymbol2 = new UnitSymbol(unit);

        //Act
        boolean result = unitSymbol1.equals(unitSymbol2);

        //Assert
        assertTrue(result);
    }

    /**
     * Test case: Check if two UnitSymbol objects are not equal
     */
    @Test
    void shouldReturnFalse_WhenTwoUnitSymbolObjectsAreNotEqual() {
        //Arrange
        String unit1 = "C";
        String unit2 = "F";
        UnitSymbol unitSymbol1 = new UnitSymbol(unit1);
        UnitSymbol unitSymbol2 = new UnitSymbol(unit2);

        //Act
        boolean result = unitSymbol1.equals(unitSymbol2);

        //Assert
        assertFalse(result);
    }

    /**
     * Test case: Check if two UnitSymbol objects are equal
     */
    @Test
    void shouldReturnFalse_WhenOtherObjectIsNull () {
        //Arrange
        String unit = "C";
        UnitSymbol unitSymbol1 = new UnitSymbol(unit);
        UnitSymbol unitSymbol2 = null;

        //Act
        boolean result = unitSymbol1.equals(unitSymbol2);

        //Assert
        assertFalse(result);
    }

    /**
     * Test case: Check if two UnitSymbol objects are equal
     */
    @Test
    public void shouldReturnTrue_WhenComparingObjectWithItself(){
        //Arrange
        String description = "C";
        UnitSymbol unitSymbol = new UnitSymbol(description);

        //Act
        boolean result = unitSymbol.equals(unitSymbol);

        //Assert
        assertTrue(result);
    }

    /**
     * Should return unit symbol when getUnit method is called.
     */
    @Test
    void shouldReturnUnit_WhenGetUnitMethodIsCalled () {
        //Arrange
        String unit = "C";
        UnitSymbol unitSymbol = new UnitSymbol(unit);

        //Act
        String result = unitSymbol.getUnit();

        //Assert
        assertEquals(unit, result);
    }

    /**
     * Should return unit symbol when toString method is called.
     */
    @Test
    void shouldReturnUnit_WhenToStringMethodIsCalled () {
        //Arrange
        String unit = "C";
        UnitSymbol unitSymbol = new UnitSymbol(unit);

        //Act
        String result = unitSymbol.toString();

        //Assert
        assertTrue(result.contains(unit));
    }



}