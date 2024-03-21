package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class MeasurementIDTest {

    /**
     * Tests the correct instantiation of a MeasurementID
     */
    @Test
    void shouldReturnExpectedMeasurement_whenGivenValidParameters(){
        //Arrange
        String unitID = "Unit1";

        //Act
        MeasurementID unit = new MeasurementID(unitID);

    }
    /**
     * Tests if the exception is thrown with a null measurementID
     */
    @Test
    void shouldThrowException_whenGivenNullMeasurementID(){
        //Arrange
        String unitID = null;
        String expectedMessage = "Invalid unit ID.";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new MeasurementID(unitID)
        );
        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the exception is thrown with a blank measurementID
     */
    @Test
    void shouldThrowException_whenGivenBlankMeasurementID(){
        //Arrange
        String unitID = " ";
        String expectedMessage = "Invalid unit ID.";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new MeasurementID(unitID)
        );

        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the exception is thrown with an empty measurementID
     */
    @Test
    void shouldThrowException_whenGivenEmptyMeasurementID(){
        //Arrange
        String unitID = "";
        String expectedMessage = "Invalid unit ID.";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new MeasurementID(unitID)
        );
        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the equals method returns true when given the same MeasurementID
     */
    @Test
    void shouldReturnTrue_whenGivenSameMeasurementID(){
        //Arrange
        String unitID = "Unit1";
        MeasurementID unit1 = new MeasurementID(unitID);
        MeasurementID unit2 = new MeasurementID(unitID);

        //Act
        boolean result = unit1.equals(unit2);

        //Assert
        assertTrue(result);
    }

    /**
     * Tests if the equals method returns true when given the same MeasurementID instance
     */
    @Test
    void shouldReturnTrue_whenGivenSameMeasurementIDInstance(){
        //Arrange
        String unitID = "Unit1";
        MeasurementID unit1 = new MeasurementID(unitID);

        //Act
        MeasurementID unit2 = unit1;

        //Assert
        assertEquals(unit1, unit2);
    }

    /**
     * Tests if the equals method returns false when given different MeasurementID
     */
    @Test
    void shouldReturnFalse_whenGivenDifferentMeasurementID(){
        //Arrange
        String unitID1 = "Unit1";
        String unitID2 = "Unit2";
        MeasurementID unit1 = new MeasurementID(unitID1);
        MeasurementID unit2 = new MeasurementID(unitID2);

        //Act
        boolean result = unit1.equals(unit2);

        //Assert
        assertFalse(result);
    }

    /**
     * Tests if the hashCode method returns the same value for the same MeasurementID
     */
    @Test
    void shouldReturnExpectedHashCode_whenGivenValidParameters(){
        //Arrange
        String unitID = "Unit1";
        MeasurementID unit = new MeasurementID(unitID);

        int expectedHashCode = unitID.hashCode();

        //Act
        int result = unit.hashCode();

        //Assert
        assertEquals(expectedHashCode, result);
    }

    /**
     * Tests if the toString method returns the expected string
     */
    @Test
    void shouldReturnExpectedString_whenGivenValidParameters(){
        //Arrange
        String unitID = "Unit1";
        MeasurementID unit = new MeasurementID(unitID);
        String expectedString = "Measurement{" +
                "_measurementID='" + unitID + '\'' +
                '}';

        //Act
        String result = unit.toString();

        //Assert
        assertEquals(expectedString, result);
    }

    /**
     * Tests if the getId method returns the expected ID
     */
    @Test
    void shouldReturnID_whenGivenValidParameters(){
        //Arrange
        String unitID = "Unit1";
        MeasurementID unit = new MeasurementID(unitID);

        //Act
        String result = unit.getId();

        //Assert
        assertEquals(unitID, result);
    }

    /**
     * Tests if the equals method returns false when given a null object
     */
    @Test
    void shouldReturnFalse_whenGivenNullObject() {
        //Arrange
        String unitID = "Unit1";
        MeasurementID unit = new MeasurementID(unitID);

        //Act
        boolean result = unit.equals(null);

        //Assert
        assertFalse(result);
    }



}
