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

    @Test
    void shouldReturnObjectWhenValidFilePathname() throws InstantiationException {
        //Arrange
        String filePathname = "config.properties";

        //Act
        new CatalogueActuator(filePathname);
    }

    /**
     * Tests the instantiation of the CatalogueActuator
     * when the filePathname is null.
     */

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

    /**
     * Tests the instantiation of the CatalogueActuator
     * when the filePathname is empty.
     */

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

    /**
     * Tests if return Actuator Types List
     * @throws InstantiationException
     */

    @Test
    void shouldReturnActuatorTypesList() throws InstantiationException {

        //Arrange
        CatalogueActuator catalogueActuator = new CatalogueActuator("config.properties");

        String strDescription = "Temperature";
        ActuatorType actuatorType = new ActuatorType(strDescription);

        String strDescription2 = "Humidity";
        ActuatorType actuatorType2 = new ActuatorType(strDescription2);

        List<ActuatorType> actuatorTypesList = new ArrayList<>();

        actuatorTypesList.add(actuatorType);
        actuatorTypesList.add(actuatorType2);

        int expected = 2;

        //Act
        catalogueActuator.getActuatorTypes();

        //Assert
        assertEquals(expected, actuatorTypesList.size());

    }

    /**
     * Tests if returns an empty Actuator Types List
     * @throws InstantiationException
     */

    @Test
    void shouldReturnEmptyActuatorList() throws InstantiationException {
        //Arrange
        CatalogueActuator catalogueActuator = new CatalogueActuator("config.properties");

        int expected = 0;

        //Act
        List<ActuatorType> actuatorTypesList = catalogueActuator.getActuatorTypes();

        //Assert
        assertEquals(expected, actuatorTypesList.size());
    }

    /**
     * Tests if return Actuator Models List
     * @throws InstantiationException
     */
    @Test
    void shouldReturnActuatorModelsList() throws InstantiationException {
        //Arrange
        CatalogueActuator catalogueActuator = new CatalogueActuator("config.properties");

        String strDescription = "Temperature";
        ActuatorType actuatorType = new ActuatorType(strDescription);

        String strDescription2 = "Humidity";
        ActuatorType actuatorType2 = new ActuatorType(strDescription2);

        List<ActuatorType> actuatorModelsList = new ArrayList<>();

        actuatorModelsList.add(actuatorType);
        actuatorModelsList.add(actuatorType2);

        int expected = 2;

        //Act
        catalogueActuator.getActuatorModels();

        //Assert
        assertEquals(expected, actuatorModelsList.size());
    }

    /**
     * Tests if returns an empty Actuator Models List
     * @throws InstantiationException
     */
    @Test
    void shouldReturnEmptyActuatorModelsList() throws InstantiationException {
        //Arrange
        CatalogueActuator catalogueActuator = new CatalogueActuator("config.properties");

        int expected = 0;

        //Act
        List<String> actuatorModelsList = catalogueActuator.getActuatorModels();

        //Assert
        assertEquals(expected, actuatorModelsList.size());
    }
}