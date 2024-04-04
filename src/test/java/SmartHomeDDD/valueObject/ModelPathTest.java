package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelPathTest {
    @Test
    void shouldReturnModelPathWhenGivenValidPath() {
        //Arrange
        String path = "/SmartHomeDDD/valueObject/ModelPath.java";
        //Act
        new ModelPath(path);

    }
    @Test
    void shouldThrowExceptionWhenPathIsNull() {
        //Arrange
        String path = null;
        String expectedMessage = "Please enter a valid path.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ModelPath(path));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenPathIsEmpty() {
        //Arrange
        String path = "";
        String expectedMessage = "Please enter a valid path.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ModelPath(path));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldReturnTrueWhenGivenSameObject() {
        //Arrange
        String path = "/SmartHomeDDD/valueObject/ModelPath.java";
        ModelPath modelPath = new ModelPath(path);
        //Act
        boolean result = modelPath.equals(modelPath);
        //Assert
        assertTrue(result);
    }
    @Test
    void shouldReturnTrueWhenGivenDifferentObjectWithSamePath() {
        //Arrange
        String path = "/SmartHomeDDD/valueObject/ModelPath.java";
        ModelPath modelPath = new ModelPath(path);
        ModelPath modelPath2 = new ModelPath(path);
        //Act
        boolean result = modelPath.equals(modelPath2);
        //Assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseWhenGivenDifferentObjectWithDifferentPath() {
        //Arrange
        String path = "/SmartHomeDDD/valueObject/ModelPath.java";
        String path2 = "/SmartHomeDDD/valueObject/ModelPathTest.java";
        ModelPath modelPath = new ModelPath(path);
        ModelPath modelPath2 = new ModelPath(path2);
        //Act
        boolean result = modelPath.equals(modelPath2);
        //Assert
        assertFalse(result);
    }
    @Test
    void shouldReturnFalseWhenGivenNull() {
        //Arrange
        String path = "/SmartHomeDDD/valueObject/ModelPath.java";
        ModelPath modelPath = new ModelPath(path);
        //Act
        boolean result = modelPath.equals(null);
        //Assert
        assertFalse(result);
    }
    @Test
    void shouldReturnFalseWhenGivenDifferentClass() {
        //Arrange
        String path = "/SmartHomeDDD/valueObject/ModelPath.java";
        ModelPath modelPath = new ModelPath(path);
        //Act
        boolean result = modelPath.equals(path);
        //Assert
        assertFalse(result);
    }
    @Test
    void shouldReturnPathWhenToStringIsCalled() {
        //Arrange
        String path = "/SmartHomeDDD/valueObject/ModelPath.java";
        ModelPath modelPath = new ModelPath(path);
        //Act
        String result = modelPath.toString();
        //Assert
        assertEquals(path, result);
    }

    @Test
    void shouldReturnPathWhenGetIdIsCalled() {
        //Arrange
        String path = "/SmartHomeDDD/valueObject/ModelPath.java";
        ModelPath modelPath = new ModelPath(path);
        //Act
        String result = modelPath.getId();
        //Assert
        assertEquals(path, result);
    }

}