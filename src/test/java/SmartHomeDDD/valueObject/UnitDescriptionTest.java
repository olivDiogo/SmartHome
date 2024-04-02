package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitDescriptionTest {

    @Test
    public void shouldInstantiateUnitDescriptionWhenGivenValidDescription() {
        //Arrange
        String description = "This is a valid description";
        //Act
        new UnitDescription(description);
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenNullDescription() {
        //Arrange
        String description = null;
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UnitDescription(description));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenBlankDescription() {
        //Arrange
        String description = " ";
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UnitDescription(description));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenEmptyDescription() {
        //Arrange
        String description = "";
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UnitDescription(description));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenDescriptionWithMoreThan50Characters() {
        //Arrange
        String description = "This is a description with more than 50 characters. This is a description with more than 50 characters. This is a description with more than 50 characters.";
        String expectedMessage = "The description cannot have more than 50 characters.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UnitDescription(description));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void shouldReturnTrue_WhenTwoObjectsHaveSameDescription(){
        //Arrange
        String description = "This is a valid description";
        UnitDescription unitDescription1 = new UnitDescription(description);
        UnitDescription unitDescription2 = new UnitDescription(description);
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
        UnitDescription unitDescription1 = new UnitDescription(description1);
        UnitDescription unitDescription2 = new UnitDescription(description2);
        //Act
        boolean result = unitDescription1.equals(unitDescription2);
        //Assert
        assertFalse(result);
    }
    @Test
    public void shouldReturnTrue_WhenComparingObjectWithItself(){
        //Arrange
        String description = "This is a valid description";
        UnitDescription unitDescription = new UnitDescription(description);
        //Act
        boolean result = unitDescription.equals(unitDescription);
        //Assert
        assertTrue(result);
    }
    @Test
    public void descriptionWithSameDescriptionShouldHaveSameHashCode(){
        //Arrange
        String description = "This is a valid description";
        UnitDescription unitDescription1 = new UnitDescription(description);
        UnitDescription unitDescription2 = new UnitDescription(description);
        //Act
        int hashCode1 = unitDescription1.hashCode();
        int hashCode2 = unitDescription2.hashCode();
        //Assert
        assertEquals(hashCode1, hashCode2);
    }
}