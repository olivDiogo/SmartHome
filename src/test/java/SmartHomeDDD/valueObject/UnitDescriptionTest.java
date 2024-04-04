package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitDescriptionTest {

    /**
     * Validates construction with valid arguments.
     */
    @Test
    public void shouldInstantiateUnitDescriptionWhenGivenValidDescription() {
        //Arrange
        String description = "This is a valid description";

        //Act
        UnitDescription unitDescription = new UnitDescription(description);

        //Assert
        assertNotNull(unitDescription);
    }

    /**
     * Expects IllegalArgumentException for null description.
     */
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenNullDescription() {
        //Arrange
        String description = null;
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";
        //Act

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UnitDescription(description));

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Expects IllegalArgumentException for blank description.
     */
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenBlankDescription() {
        //Arrange
        String description = " ";
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UnitDescription(description));

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Expects IllegalArgumentException for empty description.
     */
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenEmptyDescription() {
        //Arrange
        String description = "";
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UnitDescription(description));

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Expects IllegalArgumentException for description with more than 50 characters.
     */
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenDescriptionWithMoreThan50Characters() {
        //Arrange
        String description = "This is a description with more than 50 characters. This is a description with more than 50 characters. This is a description with more than 50 characters.";
        String expectedMessage = "The description cannot have more than 50 characters.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UnitDescription(description));

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Should return true when two objects have the same description.
     */
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

    /**
     * Should return false when two objects have different description.
     */
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

    /**
     * Should return true when comparing object with itself.
     */
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

    /**
     * Description with same description should have same hash code.
     */
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

    /**
     * Description with different description should have different hash code.
     */
    @Test
    void shouldReturnDescription_WhenToStringIsCalled() {
        //Arrange
        String description = "This is a valid description";
        UnitDescription unitDescription = new UnitDescription(description);

        //Act
        String result = unitDescription.toString();

        //Assert
        assertEquals(description, result);
    }

    @Test
    void shouldReturnDescription_WhenMethodGetDescriptionIsCalled () {
        //Arrange
        String description = "This is a valid description";
        UnitDescription unitDescription = new UnitDescription(description);

        //Act
        String result = unitDescription.getDescription();

        //Assert
        assertEquals(description, result);
    }
}