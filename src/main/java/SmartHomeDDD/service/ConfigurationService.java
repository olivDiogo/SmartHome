package SmartHomeDDD.service;

import SmartHomeDDD.domain.SensorModel.SensorModel;
import SmartHomeDDD.valueObject.MeasurementTypeDescription;
import SmartHomeDDD.valueObject.MeasurementTypeUnit;
import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelName;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class ConfigurationService {
    private SensorModelService _sensorModelService;
    private MeasurementTypeService _measurementTypeService;
    private SensorTypeService _sensorTypeService;

    public ConfigurationService(SensorModelService sensorModelService, MeasurementTypeService measurementTypeService, SensorTypeService sensorTypeService) {
        validateSensorModelService(sensorModelService);
        validateMeasurementTypeService(measurementTypeService);
        validateSensorTypeService(sensorTypeService);
        try {
            loadDefaultSensorModels();
            loadDefaultMeasurementTypes();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

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
    private void loadDefaultSensorModels() throws InstantiationException {
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File("config.properties")); // e.g. filePathname = "config.properties"

            // access configuration properties
            String[] arrayStringClassesSensors = config.getStringArray("sensor");
            for (String sensor : arrayStringClassesSensors) {
                ModelPath sensorPath = new ModelPath(sensor);

                String sensorModelName = sensor.substring(sensor.lastIndexOf('.') + 1);
                SensorModelName sensorName = new SensorModelName(sensorModelName);

                _sensorModelService.createSensorModel(sensorName, sensorPath);

            }
        } catch (ConfigurationException exception) {
            // Something went wrong
            throw new InstantiationException("something went wrong in reading the configuration: " + exception.getMessage());
        }
    }
    private void loadDefaultMeasurementTypes() throws InstantiationException {
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File("config.properties")); // e.g. filePathname = "config.properties"

            // access configuration properties
            String[] arrayStringMeasurementTypes = config.getStringArray("measurement");
            for (String measurement : arrayStringMeasurementTypes) {
                String[] measurementTypes = measurement.split(";");

                String measurementTypeDescription = measurementTypes[0].split(":")[1];
                MeasurementTypeDescription measurementDescription = new MeasurementTypeDescription(measurementTypeDescription);

                String measurementTypeUnit = measurementTypes[1].split(":")[1];
                MeasurementTypeUnit measurementTUnit = new MeasurementTypeUnit(measurementTypeUnit);

                _measurementTypeService.createAndSaveMeasurementType(measurementDescription, measurementTUnit);

            }
        } catch (ConfigurationException exception) {
            // Something went wrong
            throw new InstantiationException("something went wrong in reading the configuration: " + exception.getMessage());
        }
    }

}
