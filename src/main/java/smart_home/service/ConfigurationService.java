package smart_home.service;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import smart_home.domain.sensor_model.ISensorModelFactory;
import smart_home.domain.sensor_model.SensorModel;
import smart_home.domain.unit.IUnitFactory;
import smart_home.domain.unit.Unit;
import smart_home.persistence.mem.SensorModelRepository;
import smart_home.persistence.mem.UnitRepository;
import smart_home.value_object.*;

import java.io.File;

public class ConfigurationService {

    private SensorModelRepository _sensorModelRepository;
    private UnitRepository _unitRepository;
    private ISensorModelFactory _sensorModelFactory;
    private IUnitFactory _unitFactory;

    /**
     * Constructor for ConfigurationService.
     *
     * @param sensorModelRepository is the repository for sensor models.
     * @param unitRepository        is the repository for units.
     * @param sensorModelFactory    is the factory for sensor models.
     * @param unitFactory           is the factory for units.
     * @throws InstantiationException if something went wrong in reading the configuration.
     */
    public ConfigurationService(SensorModelRepository sensorModelRepository, UnitRepository unitRepository, ISensorModelFactory sensorModelFactory, IUnitFactory unitFactory) throws InstantiationException {
        validateSensorModelRepository(sensorModelRepository);
        validateMeasurementTypeRepository(unitRepository);
        validateSensorModelFactory(sensorModelFactory);
        validateUnitFactory(unitFactory);
        loadDefaultSensorModels();
        loadDefaultMeasurementTypes();

    }


    /**
     * Validates the SensorModelRepository.
     *
     * @param sensorModelRepository The SensorModelRepository to validate.
     */
    private void validateSensorModelRepository(SensorModelRepository sensorModelRepository) {
        if (sensorModelRepository == null) {
            throw new IllegalArgumentException("Please enter a valid sensor model repository.");
        } else {
            this._sensorModelRepository = sensorModelRepository;
        }
    }

    /**
     * Validates the MeasurementTypeRepository.
     *
     * @param unitRepository The MeasurementTypeRepository to validate.
     */
    private void validateMeasurementTypeRepository(UnitRepository unitRepository) {
        if (unitRepository == null) {
            throw new IllegalArgumentException("Please enter a valid measurement type repository.");
        } else {
            this._unitRepository = unitRepository;
        }
    }

    /**
     * Validates the SensorModelFactory.
     *
     * @param sensorModelFactory The SensorModelFactory to validate.
     */
    private void validateSensorModelFactory(ISensorModelFactory sensorModelFactory) {
        if (sensorModelFactory == null) {
            throw new IllegalArgumentException("Please enter a valid sensor model factory.");
        } else {
            this._sensorModelFactory = sensorModelFactory;
        }
    }

    /**
     * Validates the MeasurementTypeFactory.
     *
     * @param unitFactory The MeasurementTypeFactory to validate.
     */
    private void validateUnitFactory(IUnitFactory unitFactory) {
        if (unitFactory == null) {
            throw new IllegalArgumentException("Please enter a valid unit factory.");
        } else {
            this._unitFactory = unitFactory;
        }
    }

    /**
     * Load the default sensor models from the configuration file.
     *
     * @throws InstantiationException if something went wrong in reading the configuration.
     */
    private void loadDefaultSensorModels() throws InstantiationException {
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File("configDDD.properties")); // e.g. filePathname = "config.properties"

            // access configuration properties
            String[] arrayStringClassesSensors = config.getStringArray("sensor");
            for (String sensor : arrayStringClassesSensors) {
                String sensorPathStr = sensor.split(";")[0]; // String containing the path of the sensor
                String sensorModelName = sensorPathStr.substring(sensorPathStr.lastIndexOf('.') + 1); // String containing the sensor model name
                String sensorTypeIDstr = sensor.split(";")[1]; // String containing the sensor type ID
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

    /**
     * Load the default measurement types from the configuration file.
     *
     * @throws InstantiationException if something went wrong in reading the configuration.
     */
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

                Unit measurementType = _unitFactory.createUnit(measurementDescription, measurementTUnit);
                _unitRepository.save(measurementType);
            }
        } catch (ConfigurationException exception) {
            // Something went wrong
            throw new InstantiationException("something went wrong in reading the configuration: " + exception.getMessage());
        }
    }

}
