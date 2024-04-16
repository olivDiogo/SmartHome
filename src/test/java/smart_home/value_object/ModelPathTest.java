package smart_home.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelPathTest {
    @Test
    void shouldReturnModelPathWhenGivenValidPath() {
        //Arrange
        String path = "/smart_home/value_object/ModelPath.java";

        //Act
        ModelPath result = new ModelPath(path);

        //Assert
        assertNotNull(result);

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
        String path = "/smart_home/value_object/ModelPath.java";
        ModelPath modelPath = new ModelPath(path);
        //Act
        boolean result = modelPath.equals(modelPath);
        //Assert
        assertTrue(result);
    }
    @Test
    void shouldReturnTrueWhenGivenDifferentObjectWithSamePath() {
        //Arrange
        String path = "/smart_home/value_object/ModelPath.java";
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
        String path = "/smart_home/value_object/ModelPath.java";
        String path2 = "/smart_home/value_object/ModelPathTest.java";
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
        String path = "/smart_home/value_object/ModelPath.java";
        ModelPath modelPath = new ModelPath(path);
        //Act
        boolean result = modelPath.equals(null);
        //Assert
        assertFalse(result);
    }
    @Test
    void shouldReturnFalseWhenGivenDifferentClass() {
        //Arrange
        String path = "/smart_home/value_object/ModelPath.java";
        ModelPath modelPath = new ModelPath(path);
        //Act
        boolean result = modelPath.equals(path);
        //Assert
        assertFalse(result);
    }
    @Test
    void shouldReturnPathWhenToStringIsCalled() {
        //Arrange
        String path = "/smart_home/value_object/ModelPath.java";
        ModelPath modelPath = new ModelPath(path);
        //Act
        String result = modelPath.toString();
        //Assert
        assertEquals(path, result);
    }

    @Test
    void shouldReturnPathWhenGetIDIsCalled() {
        //Arrange
        String path = "/smart_home/value_object/ModelPath.java";
        ModelPath modelPath = new ModelPath(path);
        //Act
        String result = modelPath.getID();
        //Assert
        assertEquals(path, result);
    }

    @Test
    void shouldReturnExpectedHashCode_WhenCallingHashCode() {
        //Arrange
        String path = "/smart_home/value_object/ModelPath.java";
        ModelPath modelPath = new ModelPath(path);

        int expectedHashCode = path.hashCode();

        //Act
        int result = modelPath.hashCode();

        //Assert
        assertEquals(expectedHashCode, result);
    }

}