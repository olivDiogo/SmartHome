package SmartHomeDDD.service;

public class ConfigurationService {
    private SensorModelService _sensorModelService;
    private MeasurementTypeService _measurementTypeService;
    private SensorTypeService _sensorTypeService;

    public ConfigurationService(SensorModelService sensorModelService, MeasurementTypeService measurementTypeService, SensorTypeService sensorTypeService) {
        validateSensorModelService(sensorModelService);
        validateMeasurementTypeService(measurementTypeService);
        validateSensorTypeService(sensorTypeService);
    }

    private void validateSensorModelService(SensorModelService sensorModelService) {
        if (sensorModelService == null) {
            throw new IllegalArgumentException("Please enter a valid sensor model service.");
        } else {
            this._sensorModelService = sensorModelService;
        }
    }

    private void validateMeasurementTypeService(MeasurementTypeService measurementTypeService) {
        if (measurementTypeService == null) {
            throw new IllegalArgumentException("Please enter a valid measurement type service.");
        } else {
            this._measurementTypeService = measurementTypeService;
        }
    }

    private void validateSensorTypeService(SensorTypeService sensorTypeService) {
        if (sensorTypeService == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type service.");
        } else {
            this._sensorTypeService = sensorTypeService;
        }
    }
}
