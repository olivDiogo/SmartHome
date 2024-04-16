package smart_home.service;

import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smart_home.domain.sensor_model.ISensorModelFactory;
import smart_home.domain.sensor_model.SensorModel;
import smart_home.domain.unit.IUnitFactory;
import smart_home.domain.unit.Unit;
import smart_home.persistence.mem.SensorModelRepository;
import smart_home.persistence.mem.UnitRepository;
import smart_home.value_object.ModelPath;
import smart_home.value_object.SensorModelName;
import smart_home.value_object.UnitDescription;
import smart_home.value_object.UnitSymbol;

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
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        IUnitFactory unitFactory = mock(IUnitFactory.class);
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
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        IUnitFactory unitFactory = mock(IUnitFactory.class);
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
        ISensorModelFactory sensorModelFactory = null;
        IUnitFactory unitFactory = mock(IUnitFactory.class);
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
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        IUnitFactory unitFactory = null;
        String expectedMessage = "Please enter a valid unit factory.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ConfigurationService(sensorModelRepository, unitRepository, sensorModelFactory, unitFactory));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }


    @Test
    void shouldLoadDefaultSensorModels_WhenConfigurationServiceInstantiated() throws ConfigurationException {
        //Arrange
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        when(sensorModelFactory.createSensorModel(any(), any(), any())).thenReturn(mock(SensorModel.class));
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        UnitRepository unitRepository = mock(UnitRepository.class);
        IUnitFactory unitFactory = mock(IUnitFactory.class);

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
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        UnitRepository unitRepository = mock(UnitRepository.class);
        IUnitFactory unitFactory = mock(IUnitFactory.class);
        when(unitFactory.createUnit(any(), any())).thenReturn(mock(Unit.class));
        Configurations configs = new Configurations();
        int defaultSensorModels = configs.properties(new File("configDDD.properties")).getStringArray("measurement").length;

        try (MockedConstruction<UnitDescription> measurementDescriptionConstruction = mockConstruction(UnitDescription.class, (mock, context) -> {
        });
             MockedConstruction<UnitSymbol> measurementTUnitConstruction = mockConstruction(UnitSymbol.class, (mock, context) -> {
             })) {

            // Act
            ConfigurationService configurationService = new ConfigurationService(sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
            verify(unitFactory, times(defaultSensorModels)).createUnit(any(), any());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}