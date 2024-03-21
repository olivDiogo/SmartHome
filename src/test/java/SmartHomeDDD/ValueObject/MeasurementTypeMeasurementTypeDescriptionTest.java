package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeasurementTypeMeasurementTypeDescriptionTest {

    @Test
    public void shouldInstantiateUnitDescriptionWhenGivenValidDescription() {
        //Arrange
        String description = "This is a valid description";
        //Act
        new MeasurementTypeDescription(description);
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenNullDescription() {
        //Arrange
        String description = null;
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new MeasurementTypeDescription(description));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenBlankDescription() {
        //Arrange
        String description = " ";
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new MeasurementTypeDescription(description));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenEmptyDescription() {
        //Arrange
        String description = "";
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new MeasurementTypeDescription(description));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenDescriptionWithMoreThan50Characters() {
        //Arrange
        String description = "This is a description with more than 50 characters. This is a description with more than 50 characters. This is a description with more than 50 characters.";
        String expectedMessage = "The description cannot have more than 50 characters.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new MeasurementTypeDescription(description));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void shouldReturnTrue_WhenTwoObjectsHaveSameDescription(){
        //Arrange
        String description = "This is a valid description";
        MeasurementTypeDescription unitDescription1 = new MeasurementTypeDescription(description);
        MeasurementTypeDescription unitDescription2 = new MeasurementTypeDescription(description);
        //Act
        boolean result = unitDescription1.equals(unitDescription2);
        //Assert
        assertTrue(result);
    }
    @Test
    public void shouldReturnFalse_WhenTwoObjectsHaveDifferentDescription(){
        //Arrange
        String description1 = "This is a valid description";
        String description2 = "This is another valid description";
        MeasurementTypeDescription unitDescription1 = new MeasurementTypeDescription(description1);
        MeasurementTypeDescription unitDescription2 = new MeasurementTypeDescription(description2);
        //Act
        boolean result = unitDescription1.equals(unitDescription2);
        //Assert
        assertFalse(result);
    }
    @Test
    public void shouldReturnCorrectIDWhenGetIdIsCalled(){
        //Arrange
        String description = "This is a valid description";
        MeasurementTypeDescription unitDescription = new MeasurementTypeDescription(description);
        //Act
        String result = unitDescription.getId();
        //Assert
        assertEquals(description, result);
    }
    @Test
    public void shouldReturnTrue_WhenComparingObjectWithItself(){
        //Arrange
        String description = "This is a valid description";
        MeasurementTypeDescription unitDescription = new MeasurementTypeDescription(description);
        //Act
        boolean result = unitDescription.equals(unitDescription);
        //Assert
        assertTrue(result);
    }
    @Test
    public void descriptionWithSameDescriptionShouldHaveSameHashCode(){
        //Arrange
        String description = "This is a valid description";
        MeasurementTypeDescription unitDescription1 = new MeasurementTypeDescription(description);
        MeasurementTypeDescription unitDescription2 = new MeasurementTypeDescription(description);
        //Act
        int hashCode1 = unitDescription1.hashCode();
        int hashCode2 = unitDescription2.hashCode();
        //Assert
        assertEquals(hashCode1, hashCode2);
    }
}