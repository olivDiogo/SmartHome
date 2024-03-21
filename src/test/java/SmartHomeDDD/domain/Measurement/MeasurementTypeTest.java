package SmartHomeDDD.domain.Measurement;

import SmartHomeDDD.ValueObject.MeasurementTypeUnit;
import SmartHomeDDD.ValueObject.MeasurementTypeDescription;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MeasurementTypeTest {
    @Test
    void shouldReturnValidMeasurementWhenGivenValidMeasurementIDAndUnitAndUnitDescription() {
        //Arrange
        MeasurementTypeUnit unitDouble = mock(MeasurementTypeUnit.class);
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);
        //Act
        new MeasurementType( unitDescriptionDouble, unitDouble );
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenGivenNullUnit() {
        //Arrange
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);
        MeasurementTypeUnit unitDouble = null;
        String expectedMessage = "Measurement unit is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new MeasurementType(unitDescriptionDouble,unitDouble));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenGivenNullUnitDescription() {
        //Arrange
        MeasurementTypeUnit unitDouble = mock(MeasurementTypeUnit.class);
        MeasurementTypeDescription unitDescriptionDouble = null;
        String expectedMessage = "Unit description is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new MeasurementType(unitDescriptionDouble, unitDouble));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldReturnUnitDescriptionWhenGetIDIsCalled() {
        //Arrange
        MeasurementTypeUnit unitDouble = mock(MeasurementTypeUnit.class);
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);
        MeasurementType measurementType = new MeasurementType(unitDescriptionDouble, unitDouble);
        //Act
        MeasurementTypeDescription result = measurementType.getID();
        //Assert
        assertEquals(unitDescriptionDouble, result);
    }
    @Test
    void shouldReturnExpectedStringWhenToStringIsCalled() {
        //Arrange
        MeasurementTypeUnit unitDouble = mock(MeasurementTypeUnit.class);
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);
        MeasurementType measurementType = new MeasurementType(unitDescriptionDouble, unitDouble);
        String expectedString = "MeasurementType{_measurementUnit=" + unitDouble + ", _unitDescription=" + unitDescriptionDouble + '}';
        //Act
        String result = measurementType.toString();
        //Assert
        assertEquals(expectedString, result);
    }
    @Test
    void shouldReturnTrueWhenGivenSameObject() {
        //Arrange
        MeasurementTypeUnit unitDouble = mock(MeasurementTypeUnit.class);
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);
        MeasurementType measurementType = new MeasurementType(unitDescriptionDouble, unitDouble);
        //Act
        boolean result = measurementType.equals(measurementType);
        //Assert
        assertTrue(result);
    }
    @Test
    void shouldReturnTrueWhenComparingTwoObjectsWithSameID () {
        //Arrange
        MeasurementTypeUnit unitDouble = mock(MeasurementTypeUnit.class);
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);
        MeasurementType measurementType = new MeasurementType(unitDescriptionDouble, unitDouble);
        MeasurementType measurementType2 = new MeasurementType(unitDescriptionDouble, unitDouble);
        //Act
        boolean result = measurementType.equals(measurementType2);
        //Assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseWhenComparingTwoObjectsWithDifferentID () {
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
    @Test
    void shouldReturnFalseWhenComparingObjectWithNull() {
        //Arrange
        MeasurementTypeUnit unitDouble = mock(MeasurementTypeUnit.class);
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);
        MeasurementType measurementType = new MeasurementType(unitDescriptionDouble, unitDouble);
        //Act
        boolean result = measurementType.equals(null);
        //Assert
        assertFalse(result);
    }





}