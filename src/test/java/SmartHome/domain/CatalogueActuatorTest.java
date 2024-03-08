package SmartHome.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
     * Tests the load a configuration from a non-existing file.
     * This should trigger the ConfigurationException.
     * @throws InstantiationException if the filePathname is incorrect
     */
    @Test
    void shouldThrowExceptionWhenFileDoesNotExist() {
        // Arrange
        String filePathname = "non_existing_config.properties";
        String expectedMessage = "something went wrong in reading the configuration";

        // Act
        Exception exception = assertThrows(InstantiationException.class, () -> new CatalogueActuator(filePathname));
        String actualMessage = exception.getMessage();

        // Assert
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

        String strDescription = "LightSwitch";
        ActuatorType actuatorType = new ActuatorType(strDescription);

        String strDescription2 = "TemperatureControl";
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

        String strDescription = "LightSwitch";
        ActuatorType actuatorType = new ActuatorType(strDescription);

        String strDescription2 = "TemperatureControl";
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
     * Tests if the Actuator Type is created and added to the list
     * @throws InstantiationException if the actuator type cannot be created
     */
    @Test
    void addValidActuatorType() throws InstantiationException {
        //Arrange
        CatalogueActuator catalogueActuator = new CatalogueActuator("config.properties");
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        ActuatorTypeFactory actuatorTypeFactory = mock(ActuatorTypeFactory.class);

        String strDescription = "Switch";

        when(actuatorTypeFactory.createActuatorType(strDescription)).thenReturn(actuatorTypeDouble);
        when(actuatorTypeDouble.getDescription()).thenReturn(strDescription);

        //Act
        ActuatorType actuatorType = catalogueActuator.addActuatorType(strDescription, actuatorTypeFactory);

        //Assert
        assertEquals(actuatorType, actuatorTypeDouble);
    }

    /**
     * Tests if by trying to add an Actuator Type with an empty description an InstantiationException is thrown
     * @throws InstantiationException
     */
    @Test
    void addActuatorTypeWithEmptyDescription_thenThrowException() throws InstantiationException {
        //Arrange
        CatalogueActuator catalogueActuator = new CatalogueActuator("config.properties");
        ActuatorTypeFactory actuatorTypeFactory = mock(ActuatorTypeFactory.class);

        String strDescription = "";

        when(actuatorTypeFactory.createActuatorType(strDescription)).thenThrow(new InstantiationException());

        //Act + Assert
        assertThrows( InstantiationException.class, () -> catalogueActuator.addActuatorType(strDescription, actuatorTypeFactory));
    }

    /**
     * Tests if by trying to add an Actuator Type with a null description an InstantiationException is thrown
     * @throws InstantiationException if the actuator type cannot be created
     */
    @Test
    void addActuatorTypeWithNullDescription_thenThrowException() throws InstantiationException {
        //Arrange
        CatalogueActuator catalogueActuator = new CatalogueActuator("config.properties");
        ActuatorTypeFactory actuatorTypeFactory = mock(ActuatorTypeFactory.class);

        String strDescription = null;

        when(actuatorTypeFactory.createActuatorType(strDescription)).thenThrow(new InstantiationException());

        //Act + Assert
        assertThrows( InstantiationException.class, () -> catalogueActuator.addActuatorType(strDescription, actuatorTypeFactory));
    }

    /**
     * Tests if the Actuator is created and added to the list
     * @throws InstantiationException
     */
    @Test
    void getActuatorOfUniqueModel()  throws InstantiationException
    {
        // arrange
        CatalogueActuator catalogue = new CatalogueActuator( "config.properties" );
        Actuator actuatorDouble = mock(Actuator.class);
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);

        String strModel = "SmartHome.actuators.SwitchActuator";

        when(actuatorFactory.createActuator(strModel, catalogue)).thenReturn(actuatorDouble);

        // act
        Actuator actuator = catalogue.getActuator(strModel, actuatorFactory);

        // assert
        assertEquals(actuator, actuatorDouble);
    }

    /**
     * Tests if the Actuator is created and added to the list if no actuator models exist
     * @throws InstantiationException if the actuator cannot be created
     */
    @Test
    void getNullSensorOfEmptyListOfModels() throws InstantiationException {
        // Arrange
        CatalogueActuator catalogue = new CatalogueActuator( "config.properties" );
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);

        String strModel = "";

        // Act
        Actuator actuator = catalogue.getActuator(strModel, actuatorFactory);

        // Assert
        assertNull(actuator);
    }

    /**
     * Tests if the Actuator is created and added to the list if the actuator model does not exist
     * @throws InstantiationException if the actuator cannot be created
     */
    @Test
    void getNullSensorOfNonExistingModel() throws InstantiationException {
        // Arrange
        CatalogueActuator catalogue = new CatalogueActuator( "config.properties" );
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);

        String strModel = "SmartHome.actuators.ActuatorXYJ4563";

        // Act
        Actuator actuator = catalogue.getActuator(strModel, actuatorFactory);

        // Assert
        assertNull(actuator);
    }

    /**
     * Tests if the Actuator Type is returned
     * @throws InstantiationException if the actuator type cannot be created
     */
    @Test
    void getActuatorType() throws InstantiationException {
        // Arrange
        CatalogueActuator catalogue = new CatalogueActuator( "config.properties" );
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        String strDescription = "LightSwitch";

        catalogue.addActuatorTypeToList(actuatorTypeDouble);

        when(actuatorTypeDouble.getDescription()).thenReturn(strDescription);

        // Act
        ActuatorType actuatorType = catalogue.getActuatorType(strDescription);

        // Assert
        assertEquals(actuatorType, actuatorTypeDouble);
    }

    /**
     * Tests if the Actuator Type is returned when the description does not exist
     * @throws InstantiationException
     */
    @Test
    void getNonExistingActuatorType_thenReturnNull() throws InstantiationException {
        // Arrange
        CatalogueActuator catalogue = new CatalogueActuator( "config.properties" );

        String strDescription = "LightSwitch";

        // Act
        ActuatorType actuatorType = catalogue.getActuatorType(strDescription);

        // Assert
        assertNull(actuatorType);
    }

    /**
     * Test when adding an actuator type to the list is null, then return null.
     * @throws InstantiationException
     */
    @Test
    void addActuatorTypeWithNullDescription_thenReturnNull() throws InstantiationException {
        // Arrange
        CatalogueActuator catalogue = new CatalogueActuator("config.properties");
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(actuatorTypeDouble.getDescription()).thenReturn(null);

        // Act
        ActuatorType actuatorType = catalogue.addActuatorTypeToList(actuatorTypeDouble);

        // Assert
        assertNotNull(actuatorType);
    }

    /**
     * Test when adding an actuator type to the list, verify if the list is not empty and contains the actuator type.
     */
    @Test
    void addActuatorTypeToList() throws InstantiationException {
        // Arrange
        CatalogueActuator catalogue = new CatalogueActuator("config.properties");
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        String strDescription = "LightSwitch";

        when(actuatorTypeDouble.getDescription()).thenReturn(strDescription);

        catalogue.addActuatorTypeToList(actuatorTypeDouble);

        // Act
        List<ActuatorType> actuatorTypes = catalogue.getActuatorTypes();

        // Assert
        assertTrue(actuatorTypes.contains(actuatorTypeDouble));
    }

    /**
     * Test to verify two different actuator types are added to the list.
     */
    @Test
    void testToAddTwoDifferentActuatorTypes() throws InstantiationException {
        // Arrange
        CatalogueActuator catalogue = new CatalogueActuator("config.properties");
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        ActuatorType actuatorTypeDouble2 = mock(ActuatorType.class);

        String strDescription = "LightSwitch";
        String strDescription2 = "TemperatureControl";

        when(actuatorTypeDouble.getDescription()).thenReturn(strDescription);
        when(actuatorTypeDouble2.getDescription()).thenReturn(strDescription2);

        // Act
        ActuatorType actuatorType = catalogue.addActuatorTypeToList(actuatorTypeDouble);
        ActuatorType actuatorType2 = catalogue.addActuatorTypeToList(actuatorTypeDouble2);

        // Assert
        assertEquals(actuatorType, catalogue.getActuatorType(strDescription));
        assertEquals(actuatorType2, catalogue.getActuatorType(strDescription2));
    }

    /**
     *Tests that the {@link CatalogueActuator#getActuator(String, ActuatorFactory)} method returns {@code null} when
     *provided with a {@code null} description
     *@throws InstantiationException
     */
    @Test
    void testGetActuatorWithNonExistingModel() throws InstantiationException {
        // Arrange
        CatalogueActuator catalogue = new CatalogueActuator("config.properties");
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        String nullDescription = null;

        when(actuatorFactory.createActuator(nullDescription, catalogue)).thenThrow(new InstantiationException());

        // Act
        Actuator actuator = catalogue.getActuator(nullDescription, actuatorFactory);

        // Assert
        assertNull(actuator);
    }


}