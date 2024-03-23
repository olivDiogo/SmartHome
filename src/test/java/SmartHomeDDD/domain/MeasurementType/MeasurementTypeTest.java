package SmartHomeDDD.domain.MeasurementType;

import SmartHomeDDD.ValueObject.MeasurementID;
import SmartHomeDDD.ValueObject.MeasurementTypeUnit;
import SmartHomeDDD.ValueObject.MeasurementTypeDescription;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MeasurementTypeTest {

    /**
     * Validates construction with valid arguments.
     */
    @Test
    void shouldReturnValidMeasurement_WhenGivenValidParameters() {
        //Arrange
        MeasurementTypeUnit unitDouble = mock(MeasurementTypeUnit.class);
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);
        //Act
        new MeasurementType( unitDescriptionDouble, unitDouble );
    }
    /**
     * Expects IllegalArgumentException for null measurement unit.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullUnit() {
        //Arrange
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);
        MeasurementTypeUnit unitDouble = null;
        String expectedMessage = "Measurement unit is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new MeasurementType(unitDescriptionDouble,unitDouble));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    /**
     * Expects IllegalArgumentException for null unit description.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullUnitDescription() {
        //Arrange
        MeasurementTypeUnit unitDouble = mock(MeasurementTypeUnit.class);
        MeasurementTypeDescription unitDescriptionDouble = null;
        String expectedMessage = "Unit description is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new MeasurementType(unitDescriptionDouble, unitDouble));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    /**
     * Tests equality on the same object instance.
     */
    @Test
    void shouldReturnTrue_WhenGivenSameObject() {
        //Arrange
        MeasurementTypeUnit unitDouble = mock(MeasurementTypeUnit.class);
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);
        MeasurementType measurementType = new MeasurementType(unitDescriptionDouble, unitDouble);
        //Act
        boolean result = measurementType.equals(measurementType);
        //Assert
        assertTrue(result);
    }
    /**
     * Tests inequality on objects with different IDs.
     */
    @Test
    void shouldReturnFalse_WhenComparingTwoObjectsWithDifferentID () {
        //Arrange
        MeasurementTypeUnit unitDouble = mock(MeasurementTypeUnit.class);
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);
        MeasurementTypeDescription unitDescriptionDouble2 = mock(MeasurementTypeDescription.class);
        MeasurementType measurementType = new MeasurementType(unitDescriptionDouble, unitDouble);
        MeasurementType measurementType2 = new MeasurementType(unitDescriptionDouble2, unitDouble);
        //Act
        boolean result = measurementType.equals(measurementType2);
        //Assert
        assertFalse(result);
    }
    /**
     * Tests inequality with null.
     */
    @Test
    void shouldReturnFalse_WhenComparingObjectWithNull() {
        //Arrange
        MeasurementTypeUnit unitDouble = mock(MeasurementTypeUnit.class);
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);
        MeasurementType measurementType = new MeasurementType(unitDescriptionDouble, unitDouble);
        //Act
        boolean result = measurementType.equals(null);
        //Assert
        assertFalse(result);
    }
    /**
     * Tests getting ID.
     */
    @Test
    void shouldReturnMeasurementID_whenGetIDisCalled(){
        //Arrange
        MeasurementTypeUnit unitDouble = mock(MeasurementTypeUnit.class);
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);

        MeasurementType measurementType = new MeasurementType(unitDescriptionDouble, unitDouble);

        //Act
        MeasurementID result = measurementType.getID();

        //Assert
        assertTrue(measurementType.toString().contains(result.toString()));
    }
    /**
     * Tests toString method.
     */
    @Test
    void shouldReturnString_whenToStringIsCalled(){
        //Arrange
        MeasurementTypeUnit unitDouble = mock(MeasurementTypeUnit.class);
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);

        MeasurementType measurementType = new MeasurementType(unitDescriptionDouble, unitDouble);

        //Act
        String result = measurementType.toString();

        //Assert
        assertTrue(result.contains(measurementType.getID().toString()));
    }

}