package SmartHomeDDD.service;

import SmartHomeDDD.domain.Unit.Unit;
import SmartHomeDDD.domain.SensorModel.SensorModel;
import SmartHomeDDD.valueObject.UnitDescription;
import SmartHomeDDD.valueObject.UnitSymbol;
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
        UnitService unitService = mock(UnitService.class);
        //Act
        new ConfigurationService(sensorModelService, unitService);
    }
    @Test
    void shouldThrowIllegalArgumentException_WhenSensorModelServiceIsNull() {
        // Arrange
        SensorModelService sensorModelService = null;
        UnitService unitService = mock(UnitService.class);
        String expectedMessage = "Please enter a valid sensor model service.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ConfigurationService(sensorModelService, unitService));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
}
    @Test
    void shouldThrowIllegalArgumentException_WhenMeasurementTypeServiceIsNull() {
        // Arrange
        SensorModelService sensorModelService = mock(SensorModelService.class);
        UnitService unitService = null;
        String expectedMessage = "Please enter a valid measurement type service.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ConfigurationService(sensorModelService, unitService));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldLoadDefaultSensorModels_WhenConfigurationServiceInstantiated() throws ConfigurationException {
        //Arrange
        SensorModelService sensorModelService = mock(SensorModelService.class);
        when(sensorModelService.createSensorModel(any(), any())).thenReturn(mock(SensorModel.class));

        UnitService unitService = mock(UnitService.class);

        Configurations configs = new Configurations();
        int defaultSensorModels = configs.properties(new File("config.properties")).getStringArray("sensor").length;

        try (MockedConstruction<ModelPath> modelPathMockedConstruction = mockConstruction(ModelPath.class, (mock, context) -> {
        });
             MockedConstruction<SensorModelName> sensorModelMockedConstruction = mockConstruction(SensorModelName.class, (mock, context) -> {
             })) {

            // Act
            ConfigurationService configurationService = new ConfigurationService(sensorModelService, unitService);
            verify(sensorModelService, times(defaultSensorModels)).createSensorModel(any(), any());
        }
    }
    @Test
    void shouldLoadDefaultMeasurementTypes_WhenConfigurationServiceInstantiated() throws ConfigurationException {
        //Arrange
        SensorModelService sensorModelService = mock(SensorModelService.class);
        UnitService unitService = mock(UnitService.class);
        when(unitService.createAndSaveMeasurementType(any(), any())).thenReturn(mock(Unit.class));

        Configurations configs = new Configurations();
        int defaultSensorModels = configs.properties(new File("config.properties")).getStringArray("measurement").length;

        try (MockedConstruction<UnitDescription> measurementDescriptionConstruction = mockConstruction(UnitDescription.class, (mock, context) -> {
        });
             MockedConstruction<UnitSymbol> measurementTUnitConstruction = mockConstruction(UnitSymbol.class, (mock, context) -> {
             })) {

            // Act
            ConfigurationService configurationService = new ConfigurationService(sensorModelService, unitService);
            verify(unitService, times(defaultSensorModels)).createAndSaveMeasurementType(any(), any());
        }
    }
}