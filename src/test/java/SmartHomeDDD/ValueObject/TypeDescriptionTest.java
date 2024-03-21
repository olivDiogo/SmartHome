package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeDescriptionTest {
    @Test
    void shouldInstantiateTypeDescriptionWhenGivenValidDescription() {
        //Arrange
        String description = "This is a valid description";
        //Act
        new TypeDescription(description);
    }
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
    @Test
    void shouldInstantiateTypeDescriptionWhenGivenDescriptionWithNumbersAndSpecialCharacters() {
        //Arrange
        String description = "Description with numbers 123 and characters !@#";
        //Act
        new TypeDescription(description);
    }

    /**
     * Test the getId method
     */
    @Test
    public void shouldReturnTheIdAsAsString_whenGetIdIsCalled() {
        //Arrange
        String description = "This is a valid description";
        TypeDescription typeDescription = new TypeDescription(description);

        //Act
        String result = typeDescription.getId();

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

}