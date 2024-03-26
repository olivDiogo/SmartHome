package SmartHomeDDD.service;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.SensorModel.SensorModel;
import SmartHomeDDD.domain.SensorModel.SensorModelFactory;
import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelID;
import SmartHomeDDD.valueObject.SensorModelName;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class SensorModelService {
    private Repository<SensorModelID, SensorModel> _sensorModelRepository;
    private SensorModelFactory _factorySensorModel;

    public SensorModelService(Repository<SensorModelID, SensorModel> sensorModelRepository, SensorModelFactory factorySensorModel) {
        validateSensorModelRepository(sensorModelRepository);
        validateFactorySensorModel(factorySensorModel);
        try {
            loadDefaultSensorModels();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    private void validateSensorModelRepository(Repository<SensorModelID, SensorModel> sensorModelRepository) {
        if (sensorModelRepository == null) {
            throw new IllegalArgumentException("Please enter a valid sensor model repository.");
        } else {
            this._sensorModelRepository = sensorModelRepository;
        }
    }
    private void validateFactorySensorModel(SensorModelFactory factorySensorModel) {
        if (factorySensorModel == null) {
            throw new IllegalArgumentException("Please enter a valid sensor model factory.");
        } else {
            this._factorySensorModel = factorySensorModel;
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

                SensorModel sensorModel = _factorySensorModel.createSensorModel(sensorName, sensorPath);
                _sensorModelRepository.save(sensorModel);
            }
        } catch (ConfigurationException exception) {
            // Something went wrong
            throw new InstantiationException("something went wrong in reading the configuration: " + exception.getMessage());
        }
    }

    public List<SensorModel> getAllSensorModels() {
        return _sensorModelRepository.findAll();
    }
    public Optional<SensorModel> getSensorModel(SensorModelID sensorModelID) {
        return _sensorModelRepository.ofIdentity(sensorModelID);
    }
}
