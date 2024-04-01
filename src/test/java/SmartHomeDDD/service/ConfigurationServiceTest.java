package SmartHomeDDD.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
}