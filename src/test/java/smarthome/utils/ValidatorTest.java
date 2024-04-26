package smarthome.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void shouldNotThrowException_WhenObjectIsNotNull() {
        //Arrange
        String objectName = "Sensor Model";
        //Act
        Validator.validateNotNull(new Object(), objectName);
        //Assert
        assertTrue(true);
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenObjectIsNull() {
        //Arrange
        String objectName = "Sensor Model";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validator.validateNotNull(null, objectName));
        //Assert
        assertEquals(objectName + " is required", exception.getMessage());
    }
}
