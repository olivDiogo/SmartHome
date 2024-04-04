package SmartHomeDDD.service;

import SmartHomeDDD.domain.SensorModel.SensorModel;
import SmartHomeDDD.domain.SensorModel.SensorModelFactory;
import SmartHomeDDD.domain.Unit.Unit;
import SmartHomeDDD.domain.Unit.UnitFactory;
import SmartHomeDDD.repository.SensorModelRepository;
import SmartHomeDDD.repository.UnitRepository;
import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelName;
import SmartHomeDDD.valueObject.UnitDescription;
import SmartHomeDDD.valueObject.UnitSymbol;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ConfigurationServiceTest {
    @Test
    void shouldThrowIllegalArgumentException_WhenSensorModelRepositoryIsNull() {
        //Arrange
        SensorModelRepository sensorModelRepository = null;
        UnitRepository unitRepository = mock(UnitRepository.class);
        SensorModelFactory sensorModelFactory = mock(SensorModelFactory.class);
        UnitFactory unitFactory = mock(UnitFactory.class);
        String expectedMessage = "Please enter a valid sensor model repository.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ConfigurationService(sensorModelRepository, unitRepository, sensorModelFactory, unitFactory));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentException_WhenMeasurementTypeRepositoryIsNull() {
        //Arrange
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        UnitRepository unitRepository = null;
        SensorModelFactory sensorModelFactory = mock(SensorModelFactory.class);
        UnitFactory unitFactory = mock(UnitFactory.class);
        String expectedMessage = "Please enter a valid measurement type repository.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ConfigurationService(sensorModelRepository, unitRepository, sensorModelFactory, unitFactory));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentException_WhenSensorModelFactoryIsNull() {
        //Arrange
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        UnitRepository unitRepository = mock(UnitRepository.class);
        SensorModelFactory sensorModelFactory = null;
        UnitFactory unitFactory = mock(UnitFactory.class);
        String expectedMessage = "Please enter a valid sensor model factory.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ConfigurationService(sensorModelRepository, unitRepository, sensorModelFactory, unitFactory));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentException_WhenUnitFactoryIsNull() {
        //Arrange
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        UnitRepository unitRepository = mock(UnitRepository.class);
        SensorModelFactory sensorModelFactory = mock(SensorModelFactory.class);
        UnitFactory unitFactory = null;
        String expectedMessage = "Please enter a valid unit factory.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ConfigurationService(sensorModelRepository, unitRepository, sensorModelFactory, unitFactory));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }


    @Test
    void shouldLoadDefaultSensorModels_WhenConfigurationServiceInstantiated() throws ConfigurationException {
        //Arrange
        SensorModelFactory sensorModelFactory = mock(SensorModelFactory.class);
        when(sensorModelFactory.createSensorModel(any(), any(), any())).thenReturn(mock(SensorModel.class));
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        UnitRepository unitRepository = mock(UnitRepository.class);
        UnitFactory unitFactory = mock(UnitFactory.class);

        Configurations configs = new Configurations();
        int defaultSensorModels = configs.properties(new File("configDDD.properties")).getStringArray("sensor").length;

        try (MockedConstruction<ModelPath> modelPathMockedConstruction = mockConstruction(ModelPath.class, (mock, context) -> {
        });
             MockedConstruction<SensorModelName> sensorModelMockedConstruction = mockConstruction(SensorModelName.class, (mock, context) -> {
             })) {

            // Act
            ConfigurationService configurationService = new ConfigurationService(sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
            verify(sensorModelFactory, times(defaultSensorModels)).createSensorModel(any(), any(), any());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void shouldLoadDefaultMeasurementTypes_WhenConfigurationServiceInstantiated() throws ConfigurationException {
        //Arrange
        SensorModelFactory sensorModelFactory = mock(SensorModelFactory.class);
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        UnitRepository unitRepository = mock(UnitRepository.class);
        UnitFactory unitFactory = mock(UnitFactory.class);
        when(unitFactory.createMeasurement(any(), any())).thenReturn(mock(Unit.class));
        Configurations configs = new Configurations();
        int defaultSensorModels = configs.properties(new File("configDDD.properties")).getStringArray("measurement").length;

        try (MockedConstruction<UnitDescription> measurementDescriptionConstruction = mockConstruction(UnitDescription.class, (mock, context) -> {
        });
             MockedConstruction<UnitSymbol> measurementTUnitConstruction = mockConstruction(UnitSymbol.class, (mock, context) -> {
             })) {

            // Act
            ConfigurationService configurationService = new ConfigurationService(sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
            verify(unitFactory, times(defaultSensorModels)).createMeasurement(any(), any());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}