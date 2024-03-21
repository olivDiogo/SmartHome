package SmartHomeDDD.domain.Measurement;

import SmartHomeDDD.ValueObject.MeasurementTypeUnit;
import SmartHomeDDD.ValueObject.MeasurementTypeDescription;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MeasurementTest {
    @Test
    void shouldReturnValidMeasurementWhenGivenValidMeasurementIDAndUnitAndUnitDescription() {
        //Arrange
        MeasurementTypeUnit unitDouble = mock(MeasurementTypeUnit.class);
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);
        //Act
        new Measurement( unitDescriptionDouble, unitDouble );
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenGivenNullUnit() {
        //Arrange
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);
        MeasurementTypeUnit unitDouble = null;
        String expectedMessage = "Measurement unit is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Measurement(unitDescriptionDouble,unitDouble));
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
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Measurement(unitDescriptionDouble, unitDouble));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }





}