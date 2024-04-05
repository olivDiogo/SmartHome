package SmartHomeDDD.service;

import SmartHomeDDD.domain.ActuatorModel.ActuatorModel;
import SmartHomeDDD.domain.ActuatorModel.ActuatorModelFactory;
import SmartHomeDDD.repository.ActuatorModelRepository;
import SmartHomeDDD.valueObject.ActuatorModelName;
import SmartHomeDDD.valueObject.ModelPath;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

class ActuatorModelServiceTest {

    /**
     * Verifies service instantiation with valid parameters.
     */
    @Test
    void shouldInstantiateActuatorModelService_WhenGivenValidParameters() {
        //Arrange
        ActuatorModelFactory actuatorModelFactory = mock(ActuatorModelFactory.class);
        ActuatorModelRepository actuatorModelRepository = mock(ActuatorModelRepository.class);

        //Act
        ActuatorModelService result = new ActuatorModelService(actuatorModelRepository, actuatorModelFactory);

        //Assert
        assertNotNull(result);
    }

    /**
     * Ensures an exception is thrown if ActuatorModelFactory is null.
     */
    @Test
    void shouldThrowException_WhenActuatorModelFactoryIsNull() {
        //Arrange
        ActuatorModelFactory actuatorModelFactory = null;
        ActuatorModelRepository actuatorModelRepository = mock(ActuatorModelRepository.class);

        String expectedMessage = "Please enter a valid actuator model factory.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorModelService(actuatorModelRepository, actuatorModelFactory));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Ensures an exception is thrown if ActuatorModelRepository is null.
     */
    @Test
    void shouldThrowException_WhenActuatorModelRepositoryIsNull() {
        //Arrange
        ActuatorModelFactory actuatorModelFactory = mock(ActuatorModelFactory.class);
        ActuatorModelRepository actuatorModelRepository = null;

        String expectedMessage = "Please enter a valid actuator model repository.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorModelService(actuatorModelRepository, actuatorModelFactory));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Tests retrieval of all actuator models.
     */
    @Test
    void shouldGetListOfActuatorModel_WhenGetActuatorModelsCalled() {
        //Arrange
        ActuatorModel actuatorModel = mock(ActuatorModel.class);
        ActuatorModelFactory actuatorModelFactory = mock(ActuatorModelFactory.class);
        ActuatorModelRepository actuatorModelRepository = mock(ActuatorModelRepository.class);

        when (actuatorModelRepository.findAll()).thenReturn(List.of(actuatorModel));

        try (MockedConstruction<ModelPath> modelPathMockedConstruction = mockConstruction(ModelPath.class, (mock, context) -> {
        });
             MockedConstruction<ActuatorModelName> actuatorModelMockedConstruction = mockConstruction(ActuatorModelName.class, (mock, context) -> {
             })) {

            // Act
            ActuatorModelService actuatorModelService = new ActuatorModelService(actuatorModelRepository, actuatorModelFactory);
            List<ActuatorModel> actuatorModels = actuatorModelService.getAllActuatorModels();

            // Assert
            assertEquals(actuatorModels, List.of(actuatorModel));
        }
    }

    /**
     * Tests retrieval of a specific actuator model by its ID.
     */
    @Test
    void shouldGetActuatorModel_WhenGetActuatorModelCalled() {
        //Arrange
        ActuatorModel actuatorModel = mock(ActuatorModel.class);
        ActuatorModelFactory actuatorModelFactory = mock(ActuatorModelFactory.class);
        ActuatorModelRepository actuatorModelRepository = mock(ActuatorModelRepository.class);

        when (actuatorModelRepository.ofIdentity(any())).thenReturn(Optional.of(actuatorModel));

        try (MockedConstruction<ModelPath> modelPathMockedConstruction = mockConstruction(ModelPath.class, (mock, context) -> {
        });
             MockedConstruction<ActuatorModelName> actuatorModelMockedConstruction = mockConstruction(ActuatorModelName.class, (mock, context) -> {
             })) {

            // Act
            ActuatorModelService actuatorModelService = new ActuatorModelService(actuatorModelRepository, actuatorModelFactory);
            Optional<ActuatorModel> actuatorModelOptional = actuatorModelService.getActuatorModel(mock(ModelPath.class));

            // Assert
            assertEquals(actuatorModelOptional.get(), actuatorModel);
        }
    }

}
