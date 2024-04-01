package SmartHomeDDD.service;
import SmartHomeDDD.domain.SensorModel.SensorModel;
import SmartHomeDDD.domain.SensorModel.SensorModelFactory;
import SmartHomeDDD.repository.SensorModelRepository;
import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelID;
import SmartHomeDDD.valueObject.SensorModelName;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

class SensorModelServiceTest {
    @Test
    void shouldInstantiateSensorModelServiceWhenGivenValidParameters() {
        //Arrange
        SensorModelFactory sensorModelFactory = mock(SensorModelFactory.class);
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        //Act
        new SensorModelService(sensorModelRepository, sensorModelFactory);
    }
    @Test
    void shouldThrowExceptionWhenSensorModelFactoryIsNull() {
        //Arrange
        SensorModelFactory sensorModelFactory = null;
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);

        String expectedMessage = "Please enter a valid sensor model factory.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SensorModelService(sensorModelRepository, sensorModelFactory));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenSensorModelRepositoryIsNull() {
        //Arrange
        SensorModelFactory sensorModelFactory = mock(SensorModelFactory.class);
        SensorModelRepository sensorModelRepository = null;

        String expectedMessage = "Please enter a valid sensor model repository.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SensorModelService(sensorModelRepository, sensorModelFactory));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldGetListOfSensorModel_WhenGetSensorModelsCalled() {
        //Arrange
        SensorModel sensorModel = mock(SensorModel.class);
        SensorModelFactory sensorModelFactory = mock(SensorModelFactory.class);
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        when (sensorModelRepository.findAll()).thenReturn(List.of(sensorModel));
        try (MockedConstruction<ModelPath> modelPathMockedConstruction = mockConstruction(ModelPath.class, (mock, context) -> {
        });
             MockedConstruction<SensorModelName> sensorModelMockedConstruction = mockConstruction(SensorModelName.class, (mock, context) -> {
             })) {

            // Act
            SensorModelService sensorModelService = new SensorModelService(sensorModelRepository, sensorModelFactory);
            List<SensorModel> sensorModels = sensorModelService.getAllSensorModels();

            // Assert
            assertEquals(sensorModels, List.of(sensorModel));
        }
    }
    @Test
    void shouldLoadDefaultSensorModels_WhenSensorModelServiceInstantiated() throws ConfigurationException {
        //Arrange
        SensorModelFactory sensorModelFactory = mock(SensorModelFactory.class);
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        when(sensorModelRepository.save(any())).thenReturn(mock(SensorModel.class));
        when(sensorModelFactory.createSensorModel(any(), any())).thenReturn(mock(SensorModel.class));
        when(sensorModelRepository.save(any())).thenReturn(mock(SensorModel.class));

        Configurations configs = new Configurations();
        int defaultSensorModels = configs.properties(new File("config.properties")).getStringArray("sensor").length;

        try (MockedConstruction<ModelPath> modelPathMockedConstruction = mockConstruction(ModelPath.class, (mock, context) -> {
        });
             MockedConstruction<SensorModelName> sensorModelMockedConstruction = mockConstruction(SensorModelName.class, (mock, context) -> {
             })) {

            // Act
            SensorModelService sensorModelService = new SensorModelService(sensorModelRepository, sensorModelFactory);

            verify(sensorModelRepository, times(defaultSensorModels)).save(any());
        }
    }

    @Test
    void shouldGetSensorModel_WhenGetSensorModelCalled() {
        //Arrange
        SensorModel sensorModel = mock(SensorModel.class);
        SensorModelID sensorModelId = mock(SensorModelID.class);
        when(sensorModel.getID()).thenReturn(sensorModelId);

        SensorModelFactory sensorModelFactory = mock(SensorModelFactory.class);
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        when(sensorModelRepository.ofIdentity(sensorModelId)).thenReturn(Optional.of(sensorModel));
        try (MockedConstruction<ModelPath> modelPathMockedConstruction = mockConstruction(ModelPath.class, (mock, context) -> {
        });
             MockedConstruction<SensorModelName> sensorModelMockedConstruction = mockConstruction(SensorModelName.class, (mock, context) -> {
             })) {

            // Act
            SensorModelService sensorModelService = new SensorModelService(sensorModelRepository, sensorModelFactory);

            Optional<SensorModel> actualSensor = sensorModelService.getSensorModel(sensorModelId);

            // Assert
            assertEquals(sensorModel, actualSensor.get());
        }
    }
    @Test
    void shouldCreateSensorModel_WhenGivenValidParameters() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath sensorPath = mock(ModelPath.class);
        SensorModel sensorModel = mock(SensorModel.class);
        SensorModelFactory sensorModelFactory = mock(SensorModelFactory.class);
        when(sensorModelFactory.createSensorModel(sensorModelName, sensorPath)).thenReturn(sensorModel);
        SensorModelService sensorModelService = new SensorModelService(mock(SensorModelRepository.class), sensorModelFactory);
        //Act
        SensorModel actualSensorModel = sensorModelService.createSensorModel(sensorModelName, sensorPath);
        //Assert
        assertEquals(sensorModel, actualSensorModel);
    }
    @Test
    void shouldActivateSensorModelRepository_WhenCreateSensorModelCalled() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath sensorPath = mock(ModelPath.class);
        SensorModel sensorModel = mock(SensorModel.class);
        SensorModelFactory sensorModelFactory = mock(SensorModelFactory.class);
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        when(sensorModelFactory.createSensorModel(sensorModelName, sensorPath)).thenReturn(sensorModel);

        SensorModelService sensorModelService = new SensorModelService(sensorModelRepository, sensorModelFactory);
        //Act
        sensorModelService.createSensorModel(sensorModelName, sensorPath);
        //Assert
        verify(sensorModelRepository, times(1)).save(sensorModel);
    }

}