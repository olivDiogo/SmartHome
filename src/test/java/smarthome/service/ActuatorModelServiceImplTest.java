package smarthome.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.MockedConstruction;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.actuator_model.IActuatorModelFactory;
import smarthome.persistence.mem.ActuatorModelRepository;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ActuatorModelServiceImplTest {

    /**
     * Verifies service instantiation with valid parameters.
     */
    @Test
    void shouldInstantiateActuatorModelService_WhenGivenValidParameters() {
        //Arrange
        IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
        ActuatorModelRepository actuatorModelRepository = mock(ActuatorModelRepository.class);

        //Act
        ActuatorModelServiceImpl result = new ActuatorModelServiceImpl(actuatorModelRepository, actuatorModelFactory);

        //Assert
        assertNotNull(result);
    }

    /**
     * Ensures an exception is thrown if ActuatorModelFactory is null.
     */
    @Test
    void shouldThrowException_WhenActuatorModelFactoryIsNull() {
        //Arrange
        IActuatorModelFactory actuatorModelFactory = null;
        ActuatorModelRepository actuatorModelRepository = mock(ActuatorModelRepository.class);

        String expectedMessage = "Actuator model factory is required";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorModelServiceImpl(actuatorModelRepository, actuatorModelFactory));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Ensures an exception is thrown if ActuatorModelRepository is null.
     */
    @Test
    void shouldThrowException_WhenActuatorModelRepositoryIsNull() {
        //Arrange
        IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
        ActuatorModelRepository actuatorModelRepository = null;

        String expectedMessage = "Actuator model repository is required";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorModelServiceImpl(actuatorModelRepository, actuatorModelFactory));

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
        IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
        ActuatorModelRepository actuatorModelRepository = mock(ActuatorModelRepository.class);

        when (actuatorModelRepository.findAll()).thenReturn(List.of(actuatorModel));

        try (MockedConstruction<ModelPath> modelPathMockedConstruction = mockConstruction(ModelPath.class, (mock, context) -> {
        });
             MockedConstruction<ActuatorModelName> actuatorModelMockedConstruction = mockConstruction(ActuatorModelName.class, (mock, context) -> {
             })) {

            // Act
            ActuatorModelServiceImpl actuatorModelServiceImpl = new ActuatorModelServiceImpl(actuatorModelRepository, actuatorModelFactory);
            List<ActuatorModel> actuatorModels = actuatorModelServiceImpl.getAllActuatorModels();

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
        IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
        ActuatorModelRepository actuatorModelRepository = mock(ActuatorModelRepository.class);

        when (actuatorModelRepository.ofIdentity(any())).thenReturn(Optional.of(actuatorModel));

        try (MockedConstruction<ModelPath> modelPathMockedConstruction = mockConstruction(ModelPath.class, (mock, context) -> {
        });
             MockedConstruction<ActuatorModelName> actuatorModelMockedConstruction = mockConstruction(ActuatorModelName.class, (mock, context) -> {
             })) {

            // Act
            ActuatorModelServiceImpl actuatorModelServiceImpl = new ActuatorModelServiceImpl(actuatorModelRepository, actuatorModelFactory);
            Optional<ActuatorModel> actuatorModelOptional = actuatorModelServiceImpl.getActuatorModel(mock(ModelPath.class));

            // Assert
            assertEquals(actuatorModelOptional.get(), actuatorModel);
        }
    }

    /**
     * Tests retrieval of actuator models by their type ID.
     */
    @Test
    void shouldReturnActuatorModelsByTypeID_WhenParametersAreValid () {
        //Arrange
        ActuatorModel actuatorModel = mock(ActuatorModel.class);
        IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
        ActuatorModelRepository actuatorModelRepository = mock(ActuatorModelRepository.class);

        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

        when (actuatorModelRepository.findBy_actuatorTypeID(actuatorTypeID)).thenReturn(List.of(actuatorModel));

        ActuatorModelServiceImpl actuatorModelServiceImpl = new ActuatorModelServiceImpl(actuatorModelRepository, actuatorModelFactory);

        int expected = 1;

        //Act
        List <ActuatorModel> actuatorModels = actuatorModelServiceImpl.getActuatorModelsByActuatorTypeId(actuatorTypeID);

        int result = actuatorModels.size();


        //Assert
        assertEquals(expected, result);


    }

}
