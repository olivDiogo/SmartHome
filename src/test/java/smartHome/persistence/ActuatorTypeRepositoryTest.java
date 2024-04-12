package smartHome.persistence;

import smartHome.domain.actuatorType.ActuatorType;
import smartHome.persistence.mem.ActuatorTypeRepository;
import smartHome.valueObject.ActuatorTypeID;
import smartHome.valueObject.TypeDescription;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActuatorTypeRepositoryTest {

    /**
     * Test the save method with valid ActuatorType.
     */
    @Test
    public void shouldSaveActuatorType_whenGivenValidActuatorType() {
        //Arrange
        ActuatorType actuatorType = mock(ActuatorType.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

        when(actuatorType.getID()).thenReturn(actuatorTypeID);
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();

        //Act
        ActuatorType savedActuatorType = actuatorTypeRepository.save(actuatorType);

        //Assert
        assertEquals(actuatorType, savedActuatorType);
    }

    /**
     * Test the save method with null ActuatorType.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenGivenNullActuatorType() {
        //Arrange
        ActuatorType actuatorType = null;
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();

        String expectedMessage = "ActuatorType cannot be null.";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            //Act
            actuatorTypeRepository.save(actuatorType);
        });

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test the save method with an ActuatorType that already exists.
     */
    @Test
    public void shouldThrowException_WhenSActuatorTypeAlreadyExists() {
        //Arrange
        ActuatorType actuatorType = mock(ActuatorType.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorType.getID()).thenReturn(actuatorTypeID);

        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();

        actuatorTypeRepository.save(actuatorType);

        String expectedMessage = "ActuatorType already exists.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            actuatorTypeRepository.save(actuatorType);
        });

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Tests if all the actuators type are returned correctly.
     */
    @Test
    public void shouldReturnAllActuatorTypes() {
        //Arrange
        ActuatorType actuatorType = mock(ActuatorType.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorType.getID()).thenReturn(actuatorTypeID);

        ActuatorType secondActuatorType = mock(ActuatorType.class);
        ActuatorTypeID secondActuatorTypeID = mock(ActuatorTypeID.class);
        when(secondActuatorType.getID()).thenReturn(secondActuatorTypeID);

        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();

        actuatorTypeRepository.save(actuatorType);
        actuatorTypeRepository.save(secondActuatorType);
        List<ActuatorType> expectedList = List.of(actuatorType, secondActuatorType);

        //Act
        List<ActuatorType> allActuatorTypes = actuatorTypeRepository.findAll();


        //Assert
        assertEquals(expectedList, allActuatorTypes);
    }

    /**
     * Test the findAll method when there are no ActuatorTypes saved.
     */
    @Test
    public void shouldReturnEmptyList_whenNoActuatorTypesSaved() {
        //Arrange
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();

        //Act
        List<ActuatorType> allActuatorTypes = actuatorTypeRepository.findAll();

        //Assert
        assertTrue(allActuatorTypes.isEmpty());
    }

    /**
     * Test the ofIdentity method with an invalid ActuatorTypeID.
     */
    @Test
    public void shouldReturnOptionalEmpty_whenGivenInvalidActuatorTypeID() {
        //Arrange
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();

        ActuatorType actuatorType = mock(ActuatorType.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorType.getID()).thenReturn(actuatorTypeID);

        actuatorTypeRepository.save(actuatorType);

        ActuatorTypeID invalidActuatorTypeID = mock(ActuatorTypeID.class);

        //Act
        Optional<ActuatorType> returnedActuatorType = actuatorTypeRepository.ofIdentity(invalidActuatorTypeID);

        //Assert
        assertTrue(returnedActuatorType.isEmpty());
    }

    /**
     * Test the ofIdentity method with a valid ActuatorTypeID.
     */
    @Test
    public void shouldReturnTrue_WhenGivenValidActuatorTypeID() {
        //Arrange
        ActuatorType actuatorType = mock(ActuatorType.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorType.getID()).thenReturn(actuatorTypeID);

        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        actuatorTypeRepository.save(actuatorType);

        //Act
        boolean containsActuatorType = actuatorTypeRepository.containsOfIdentity(actuatorTypeID);

        //Assert
        assertTrue(containsActuatorType);
    }

    /**
     * Test the ofIdentity method with invalid ActuatorTypeID.
     */
    @Test
    public void shouldReturnFalse_whenGivenInvalidActuatorTypeID() {
        //Arrange
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();

        ActuatorType actuatorType = mock(ActuatorType.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorType.getID()).thenReturn(actuatorTypeID);

        actuatorTypeRepository.save(actuatorType);

        ActuatorTypeID invalidActuatorTypeID = mock(ActuatorTypeID.class);

        //Act
        boolean containsActuatorType = actuatorTypeRepository.containsOfIdentity(invalidActuatorTypeID);

        //Assert
        assertFalse(containsActuatorType);
    }

    /**
     * Test the existsOfName method with a valid ActuatorTypeName.
     */
    @Test
    public void shouldReturnTrue_whenGivenValidActuatorTypeName() {
        //Arrange
        ActuatorType actuatorType = mock(ActuatorType.class);

        TypeDescription actuatorTypeNameMock = mock(TypeDescription.class);
        when(actuatorType.getActuatorTypeName()).thenReturn(actuatorTypeNameMock);

        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        actuatorTypeRepository.save(actuatorType);

        //Act
        boolean actuatorTypeExists = actuatorTypeRepository.existsOfName(actuatorTypeNameMock);

        //Assert
        assertTrue(actuatorTypeExists);
    }

    /**
     * Test the existsOfName method with a null ActuatorTypeName.
     */
    @Test
    public void shouldReturnFalse_whenGivenNullActuatorTypeName() {
        //Arrange
        ActuatorType actuatorType = mock(ActuatorType.class);

        TypeDescription actuatorTypeNameMock = mock(TypeDescription.class);
        when(actuatorType.getActuatorTypeName()).thenReturn(actuatorTypeNameMock);

        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        actuatorTypeRepository.save(actuatorType);

        //Act
        boolean actuatorTypeExists = actuatorTypeRepository.existsOfName(null);

        //Assert
        assertFalse(actuatorTypeExists);
    }


}