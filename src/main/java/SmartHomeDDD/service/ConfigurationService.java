package SmartHomeDDD.service;

import SmartHomeDDD.domain.SensorModel.SensorModel;
import SmartHomeDDD.domain.SensorModel.SensorModelFactory;
import SmartHomeDDD.domain.Unit.Unit;
import SmartHomeDDD.domain.Unit.UnitFactory;
import SmartHomeDDD.repository.MeasurementTypeRepository;
import SmartHomeDDD.repository.SensorModelRepository;
import SmartHomeDDD.valueObject.*;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class ConfigurationService {

    private SensorModelRepository _sensorModelRepository;
    private MeasurementTypeRepository _measurementTypeRepository;
    private SensorModelFactory _sensorModelFactory;
    private UnitFactory _unitFactory;

    public ConfigurationService(SensorModelRepository sensorModelRepository, MeasurementTypeRepository measurementTypeRepository, SensorModelFactory sensorModelFactory, UnitFactory unitFactory) throws InstantiationException{
        validateSensorModelRepository(sensorModelRepository);
        validateMeasurementTypeRepository(measurementTypeRepository);
        validateSensorModelFactory(sensorModelFactory);
        validateUnitFactory(unitFactory);
        loadDefaultSensorModels();
        loadDefaultMeasurementTypes();

    }


    private void validateSensorModelRepository(SensorModelRepository sensorModelRepository) {
        if (sensorModelRepository == null) {
            throw new IllegalArgumentException("Please enter a valid sensor model repository.");
        } else {
            this._sensorModelRepository = sensorModelRepository;
        }
    }
    private void validateMeasurementTypeRepository(MeasurementTypeRepository measurementTypeRepository) {
        if (measurementTypeRepository == null) {
            throw new IllegalArgumentException("Please enter a valid measurement type repository.");
        } else {
            this._measurementTypeRepository = measurementTypeRepository;
        }
    }
    private void validateSensorModelFactory(SensorModelFactory sensorModelFactory) {
        if (sensorModelFactory == null) {
            throw new IllegalArgumentException("Please enter a valid sensor model factory.");
        } else {
            this._sensorModelFactory = sensorModelFactory;
        }
    }
    private void validateUnitFactory(UnitFactory unitFactory) {
        if (unitFactory == null) {
            throw new IllegalArgumentException("Please enter a valid unit factory.");
        } else {
            this._unitFactory = unitFactory;
        }
    }

    private void loadDefaultSensorModels() throws InstantiationException {
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File("configDDD.properties")); // e.g. filePathname = "config.properties"

            // access configuration properties
            String[] arrayStringClassesSensors = config.getStringArray("sensor");
            for (String sensor : arrayStringClassesSensors) {
                String sensorPathStr = sensor.split(";") [0]; // String containing the path of the sensor
                String sensorModelName = sensorPathStr.substring(sensorPathStr.lastIndexOf('.') + 1); // String containing the sensor model name
                String sensorTypeIDstr = sensor.split(";") [1]; // String containing the sensor type ID

                ModelPath sensorPath = new ModelPath(sensorPathStr);
                SensorModelName sensorName = new SensorModelName(sensorModelName);
                SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDstr);

                SensorModel sensorModel = _sensorModelFactory.createSensorModel(sensorName, sensorPath, sensorTypeID);
                _sensorModelRepository.save(sensorModel);

            }
        } catch (ConfigurationException exception) {
            // Something went wrong
            throw new InstantiationException("something went wrong in reading the configuration: " + exception.getMessage());
        }
    }
    private void loadDefaultMeasurementTypes() throws InstantiationException {
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File("configDDD.properties")); // e.g. filePathname = "config.properties"

            // access configuration properties
            String[] arrayStringMeasurementTypes = config.getStringArray("measurement");
            for (String measurement : arrayStringMeasurementTypes) {
                String[] measurementTypes = measurement.split(";");

                String measurementTypeDescription = measurementTypes[0].split(":")[1];
                UnitDescription measurementDescription = new UnitDescription(measurementTypeDescription);

                String measurementTypeUnit = measurementTypes[1].split(":")[1];
                UnitSymbol measurementTUnit = new UnitSymbol(measurementTypeUnit);

                Unit measurementType = _unitFactory.createMeasurement(measurementDescription, measurementTUnit);
                _measurementTypeRepository.save(measurementType);
            }
        } catch (ConfigurationException exception) {
            // Something went wrong
            throw new InstantiationException("something went wrong in reading the configuration: " + exception.getMessage());
        }
    }

}
