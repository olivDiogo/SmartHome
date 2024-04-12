package smartHome.persistence;

import smartHome.domain.actuatorModel.ActuatorModel;
import smartHome.persistence.mem.ActuatorModelRepository;
import smartHome.valueObject.ActuatorTypeID;
import smartHome.valueObject.ModelPath;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActuatorModelRepositoryTest {

    /**
     * Test of save method, of class ActuatorModelRepository, should add ActuatorModel to repository when given valid ActuatorModel.
     */

    @Test
    public void shouldAddActuatorModelToRepositoryWhenGivenValidActuatorModel() {
        //Arrange
        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        ActuatorModel actuatorModel = mock(ActuatorModel.class);
        //Act
        ActuatorModel actualActuatorModel = actuatorModelRepository.save(actuatorModel);
        //Assert
        assertEquals(actuatorModel, actualActuatorModel);
    }

    /**
     * Test of save method, of class ActuatorModelRepository, should throw an exception when given null ActuatorModel.
     */

    @Test
    public void shouldThrowExceptionWhenGivenNullActuatorModel() {
        //Arrange
        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        ActuatorModel actuatorModel = null;
        String expectedMessage = "ActuatorModel cannot be null.";
        //Act
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> actuatorModelRepository.save(actuatorModel));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test of save method, of class ActuatorModelRepository, should throw an exception when ActuatorModel already exists.
     */

    @Test
    public void shouldThrowExceptionWhenActuatorModelAlreadyExists() {
        //Arrange
        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        ActuatorModel actuatorModel = mock(ActuatorModel.class);
        actuatorModelRepository.save(actuatorModel);
        String expectedMessage = "ActuatorModel already exists.";
        //Act
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> actuatorModelRepository.save(actuatorModel));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test of findAll method, of class ActuatorModelRepository, should return list of ActuatorModels when getAllActuatorModels is called.
     */

    @Test
    public void shouldReturnListActuatorModels_WhenGetAllActuatorModelsIsCalled() {
        //Arrange
        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        ActuatorModel actuatorModel = mock(ActuatorModel.class);
        ActuatorModel secondActuatorModel = mock(ActuatorModel.class);

        ModelPath path = mock(ModelPath.class);
        when(actuatorModel.getID()).thenReturn(path);

        actuatorModelRepository.save(actuatorModel);
        actuatorModelRepository.save(secondActuatorModel);

        List<ActuatorModel> expectedActuatorModels = List.of(actuatorModel, secondActuatorModel);
        //Act
        List<ActuatorModel> actuatorModels = actuatorModelRepository.findAll();
        //Assert
        Assertions.assertEquals(expectedActuatorModels, actuatorModels);
    }

    /**
     * Test of findAll method, of class ActuatorModelRepository, should return empty list when no ActuatorModels are added.
     */

    @Test
    public void shouldReturnEmptyListWhenNoActuatorModelsAreAdded() {
        //Arrange
        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        //Act
        List<ActuatorModel> actuatorModels = actuatorModelRepository.findAll();
        //Assert
        assertTrue(actuatorModels.isEmpty());
    }

    /**
     * Test of ofIdentity method, of class ActuatorModelRepository, should return ActuatorModel when given valid ActuatorModelID.
     */

    @Test
    public void shouldReturnActuatorModelWhenGivenValidActuatorModelID() {
        //Arrange
        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        ActuatorModel actuatorModel = mock(ActuatorModel.class);
        ModelPath actuatorModelID = mock(ModelPath.class);
        when(actuatorModel.getID()).thenReturn(actuatorModelID);

        actuatorModelRepository.save(actuatorModel);
        //Act
        ActuatorModel actualActuatorModel = actuatorModelRepository.ofIdentity(actuatorModelID).get();
        //Assert
        Assertions.assertEquals(actuatorModel, actualActuatorModel);
    }

    /**
     * Test of ofIdentity method, of class ActuatorModelRepository, should return Optional.empty() when given invalid ActuatorModelID.
     */

    @Test
    public void shouldReturnOptionalEmptyWhenGivenInvalidSensorModelID() {
        //Arrange
        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        ActuatorModel actuatorModel = mock(ActuatorModel.class);
        ModelPath actuatorModelID = mock(ModelPath.class);
        ModelPath invalidActuatorModelID = mock(ModelPath.class);
        when(actuatorModel.getID()).thenReturn(actuatorModelID);

        actuatorModelRepository.save(actuatorModel);
        //Act
        boolean result = actuatorModelRepository.ofIdentity(invalidActuatorModelID).isEmpty();
        //Assert
        assertTrue(result);
    }

    /**
     * Test of containsOfIdentity method, of class ActuatorModelRepository, should return true when given valid ActuatorModelID.
     */
    @Test
    public void shouldReturnTrueWhenGivenValidSensorModelID() {
        //Arrange
        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        ActuatorModel actuatorModel = mock(ActuatorModel.class);
        ModelPath actuatorModelID = mock(ModelPath.class);
        when(actuatorModel.getID()).thenReturn(actuatorModelID);

        actuatorModelRepository.save(actuatorModel);
        //Act
        boolean result = actuatorModelRepository.containsOfIdentity(actuatorModelID);
        //Assert
        assertTrue(result);
    }

    /**
     * Test of containsOfIdentity method, of class ActuatorModelRepository, should return false when given invalid ActuatorModelID.
     */

    @Test
    public void shouldReturnFalseWhenGivenInvalidSensorModelID() {
        //Arrange
        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        ActuatorModel actuatorModel = mock(ActuatorModel.class);
        ModelPath actuatorModelID = mock(ModelPath.class);
        ModelPath invalidActuatorModelID = mock(ModelPath.class);
        when(actuatorModel.getID()).thenReturn(actuatorModelID);

        actuatorModelRepository.save(actuatorModel);
        //Act
        boolean result = actuatorModelRepository.containsOfIdentity(invalidActuatorModelID);
        //Assert
        assertFalse(result);
    }

    /**
     * Test of findByActuatorTypeId method, of class ActuatorModelRepository, should return list of ActuatorModels by ActuatorTypeID.
     */
    @Test
    public void shouldReturnListOfActuatorModelsByActuatorTypeID() {
        //Arrange
        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        ActuatorModel actuatorModel = mock(ActuatorModel.class);
        ModelPath actuatorModelID = mock(ModelPath.class);

        ActuatorTypeID actuatorModelTypeID = mock(ActuatorTypeID.class);
        when(actuatorModel.getID()).thenReturn(actuatorModelID);
        when(actuatorModel.getActuatorTypeID()).thenReturn(actuatorModelTypeID);

        actuatorModelRepository.save(actuatorModel);

        //Act
        List<ActuatorModel> actuatorModels = actuatorModelRepository.findByActuatorTypeId(actuatorModelTypeID);
        //Assert
        assertTrue(actuatorModels.contains(actuatorModel));
    }
}
