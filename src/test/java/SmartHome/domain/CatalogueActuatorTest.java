package SmartHome.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogueActuatorTest {

    /**
     * Tests the instantiation of the CatalogueActuator
     * when the filePathname is valid.
     *
     * @throws InstantiationException if the filePathname is null
     */

   /* @Test
    void shouldReturnObjectWhenValidFilePathname() throws InstantiationException {
        //Arrange
        String filePathname = "config.properties";

        //Act
        new CatalogueActuator(filePathname);
    }

    *//**
     * Tests the instantiation of the CatalogueActuator
     * when the filePathname is null.
     *//*

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

    *//**
     * Tests the instantiation of the CatalogueActuator
     * when the filePathname is empty.
     *//*

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

    *//**
     * Tests if return Actuator Types List
     * @throws InstantiationException
     *//*

    @Test
    void shouldReturnActuatorTypesList() throws InstantiationException {

        //Arrange
        CatalogueActuator catalogueActuator = new CatalogueActuator("config.properties");

        ActuatorType actuatorType = new ActuatorType();
        ActuatorType actuatorType2 = new ActuatorType();

        List<ActuatorType> actuatorTypes = new ArrayList<>();

        actuatorTypes.add(actuatorType);
        actuatorTypes.add(actuatorType2);

        int expected = 2;

        //Act
        catalogueActuator.getActuatorTypes();

        //Assert
        assertEquals(expected, actuatorTypes.size());

    }

    *//**
     * Tests if returns an empty Actuator Types List
     * @throws InstantiationException
     *//*

    @Test
    void shouldReturnEmptyActuatorList() throws InstantiationException {
        //Arrange
        CatalogueActuator catalogueActuator = new CatalogueActuator("config.properties");

        //Act
        List<ActuatorType> actuatorTypes = catalogueActuator.getActuatorTypes();

        //Assert
        assertTrue(actuatorTypes.isEmpty());
    }
}
*/
}