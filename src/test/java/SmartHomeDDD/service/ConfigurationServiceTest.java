package SmartHomeDDD.service;

import SmartHomeDDD.domain.MeasurementType.MeasurementType;
import SmartHomeDDD.domain.SensorModel.SensorModel;
import SmartHomeDDD.domain.SensorModel.SensorModelFactory;
import SmartHomeDDD.repository.SensorModelRepository;
import SmartHomeDDD.valueObject.MeasurementTypeDescription;
import SmartHomeDDD.valueObject.MeasurementTypeUnit;
import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelName;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ConfigurationServiceTest {
    @Test
    void shouldCreateConfigurationService_WhenGivenValidParameters() {
        // Arrange
        SensorModelService sensorModelService = mock(SensorModelService.class);
        MeasurementTypeService measurementTypeService = mock(MeasurementTypeService.class);
        SensorTypeService sensorTypeService = mock(SensorTypeService.class);
        //Act
        new ConfigurationService(sensorModelService, measurementTypeService, sensorTypeService);
    }
    @Test
    void shouldThrowIllegalArgumentException_WhenSensorModelServiceIsNull() {
        // Arrange
        SensorModelService sensorModelService = null;
        MeasurementTypeService measurementTypeService = mock(MeasurementTypeService.class);
        SensorTypeService sensorTypeService = mock(SensorTypeService.class);
        String expectedMessage = "Please enter a valid sensor model service.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ConfigurationService(sensorModelService, measurementTypeService, sensorTypeService));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
}
    @Test
    void shouldThrowIllegalArgumentException_WhenMeasurementTypeServiceIsNull() {
        // Arrange
        SensorModelService sensorModelService = mock(SensorModelService.class);
        MeasurementTypeService measurementTypeService = null;
        SensorTypeService sensorTypeService = mock(SensorTypeService.class);
        String expectedMessage = "Please enter a valid measurement type service.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ConfigurationService(sensorModelService, measurementTypeService, sensorTypeService));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentException_WhenSensorTypeServiceIsNull() {
        // Arrange
        SensorModelService sensorModelService = mock(SensorModelService.class);
        MeasurementTypeService measurementTypeService = mock(MeasurementTypeService.class);
        SensorTypeService sensorTypeService = null;
        String expectedMessage = "Please enter a valid sensor type service.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ConfigurationService(sensorModelService, measurementTypeService, sensorTypeService));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldLoadDefaultSensorModels_WhenConfigurationServiceInstantiated() throws ConfigurationException {
        //Arrange
        SensorModelService sensorModelService = mock(SensorModelService.class);
        when(sensorModelService.createSensorModel(any(), any())).thenReturn(mock(SensorModel.class));

        SensorTypeService sensorTypeService = mock(SensorTypeService.class);
        MeasurementTypeService measurementTypeService = mock(MeasurementTypeService.class);

        Configurations configs = new Configurations();
        int defaultSensorModels = configs.properties(new File("config.properties")).getStringArray("sensor").length;

        try (MockedConstruction<ModelPath> modelPathMockedConstruction = mockConstruction(ModelPath.class, (mock, context) -> {
        });
             MockedConstruction<SensorModelName> sensorModelMockedConstruction = mockConstruction(SensorModelName.class, (mock, context) -> {
             })) {

            // Act
            ConfigurationService configurationService = new ConfigurationService(sensorModelService, measurementTypeService, sensorTypeService);
            verify(sensorModelService, times(defaultSensorModels)).createSensorModel(any(), any());
        }
    }
    @Test
    void shouldLoadDefaultMeasurementTypes_WhenConfigurationServiceInstantiated() throws ConfigurationException {
        //Arrange
        SensorModelService sensorModelService = mock(SensorModelService.class);
        SensorTypeService sensorTypeService = mock(SensorTypeService.class);
        MeasurementTypeService measurementTypeService = mock(MeasurementTypeService.class);
        when(measurementTypeService.createAndSaveMeasurementType(any(), any())).thenReturn(mock(MeasurementType.class));

        Configurations configs = new Configurations();
        int defaultSensorModels = configs.properties(new File("config.properties")).getStringArray("measurement").length;

        try (MockedConstruction<MeasurementTypeDescription> measurementDescriptionConstruction = mockConstruction(MeasurementTypeDescription.class, (mock, context) -> {
        });
             MockedConstruction<MeasurementTypeUnit> measurementTUnitConstruction = mockConstruction(MeasurementTypeUnit.class, (mock, context) -> {
             })) {

            // Act
            ConfigurationService configurationService = new ConfigurationService(sensorModelService, measurementTypeService, sensorTypeService);
            verify(measurementTypeService, times(defaultSensorModels)).createAndSaveMeasurementType(any(), any());
        }
    }
}