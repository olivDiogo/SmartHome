package smartHome.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeDescriptionTest {
    /**
     * Test the constructor
     */
    @Test
    void shouldInstantiateTypeDescriptionWhenGivenValidDescription() {
        //Arrange
        String description = "This is a valid description";
        //Act
        TypeDescription typeDescription = new TypeDescription(description);
        //Assert
        assertNotNull(typeDescription);
    }

    /**
     * Should throw IllegalArgumentException when given null.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenGivenNullDescription() {
        //Arrange
        String description = null;
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TypeDescription(description));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Should throw IllegalArgumentException when given blank.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenGivenBlankDescription() {
        //Arrange
        String description = " ";
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TypeDescription(description));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Should throw IllegalArgumentException when given empty.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenGivenEmptyDescription() {
        //Arrange
        String description = "";
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TypeDescription(description));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Should throw IllegalArgumentException when given description with more than 50 characters.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenGivenDescriptionWithMoreThan50Characters() {
        //Arrange
        String description = "This is a description with more than 50 characters. This is a description with more than 50 characters. This is a description with more than 50 characters.";
        String expectedMessage = "The description cannot have more than 50 characters.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TypeDescription(description));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test the getDescription method.
     */
    @Test
    void shouldReturnDescriptionWhenGivenValidDescription() {
        //Arrange
        String description = "This is a valid description";
        TypeDescription typeDescription = new TypeDescription(description);
        //Act
        String result = typeDescription.getDescription();
        //Assert
        assertEquals(description, result);
    }

    /**
     * Test the equals method when two objects have the same type description.
     */
    @Test
    void shouldReturnTrueWhenGivenSameTypeDescription() {
        //Arrange
        String description = "This is a valid description";
        TypeDescription typeDescription = new TypeDescription(description);
        TypeDescription sameTypeDescription = new TypeDescription(description);
        //Act
        boolean result = typeDescription.equals(sameTypeDescription);
        //Assert
        assertTrue(result);
    }

    /**
     * Tests if the TypeDescription is returned when description have special characters.
     */
    @Test
    void shouldInstantiateTypeDescriptionWhenGivenDescriptionWithNumbersAndSpecialCharacters() {
        //Arrange
        String description = "Description with numbers 123 and characters !@#";
        //Act
        TypeDescription typeDescription = new TypeDescription(description);
        //Assert
        assertNotNull(typeDescription);
    }

    /**
     * Test the getId method
     */
    @Test
    public void shouldReturnTheIdAsAsString_whenGetIDIsCalled() {
        //Arrange
        String description = "This is a valid description";
        TypeDescription typeDescription = new TypeDescription(description);

        //Act
        String result = typeDescription.getID();

        //Assert
        assertEquals(description, result);
    }

    /**
     * Test the hashCode method
     */
    @Test
    public void shouldReturnTheHashCodeOfTheDescription_whenHashCodeIsCalled() {
        //Arrange
        String description = "This is a valid description";
        TypeDescription typeDescription = new TypeDescription(description);

        //Act
        int result = typeDescription.hashCode();

        //Assert
        assertEquals(description.hashCode(), result);
    }

    /**
     * Test the toString method
     */
    @Test
     void shouldReturnTheDescription_whenToStringIsCalled() {
        //Arrange
        String description = "123";
        TypeDescription typeDescription = new TypeDescription(description);

        //Act
        String result = typeDescription.toString();

        //Assert
        assertEquals(description, result);
    }

    @Test
    void shouldReturnFalse_WhenGivenDifferentTypeDescription() {
        //Arrange
        String description = "This is a valid description";
        String differentDescription = "This is a different description";

        TypeDescription typeDescription = new TypeDescription(description);
        TypeDescription differentTypeDescription = new TypeDescription(differentDescription);

        //Act
        boolean result = typeDescription.equals(differentTypeDescription);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrue_WhenComparingTheSameObjects () {
        //Arrange
        String description = "This is a valid description";
        TypeDescription typeDescription = new TypeDescription(description);

        //Act
        boolean result = typeDescription.equals(typeDescription);

        //Assert
        assertTrue(result);
    }

}