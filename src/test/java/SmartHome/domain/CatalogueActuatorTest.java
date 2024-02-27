package SmartHome.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatalogueActuatorTest {
    @Test
    void shouldReturnObjectWhenValidFilePathname() throws InstantiationException {
        //Arrange
        String filePathname = "config.properties";

        //Act
        new CatalogueActuator(filePathname);
    }
    @Test
    void shouldThrowExceptionWhenInvalidFilePathname() {
        //Arrange
        String filePathname = null;
        String expectedMessage = "Invalid parameters";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new CatalogueActuator(filePathname));
        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void shouldThrowExceptionWhenInvalidFilePathnameEmpty() {
        //Arrange
        String filePathname = " ";
        String expectedMessage = "Invalid parameters";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new CatalogueActuator(filePathname));
        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

}