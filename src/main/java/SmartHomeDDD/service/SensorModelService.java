package SmartHomeDDD.service;

import SmartHomeDDD.domain.SensorModel.SensorModel;
import SmartHomeDDD.domain.SensorModel.SensorModelFactory;
import SmartHomeDDD.domain.SensorModel.SensorModelRepo;
import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelName;
import SmartHomeDDD.valueObject.SensorTypeID;

import java.util.List;
import java.util.Optional;

public class SensorModelService {
    private SensorModelRepo _sensorModelRepository;
    private SensorModelFactory _factorySensorModel;

    /**
     * Constructor for SensorModelService.
     *
     * @param sensorModelRepository is the repository for sensor models.
     * @param factorySensorModel    is the factory for sensor models.
     */
    public SensorModelService(SensorModelRepo sensorModelRepository, SensorModelFactory factorySensorModel) {
        validateSensorModelRepository(sensorModelRepository);
        validateFactorySensorModel(factorySensorModel);

    }

    /**
     * Validates the SensorModelRepository.
     *
     * @param sensorModelRepository The SensorModelRepository to validate.
     */
    private void validateSensorModelRepository(SensorModelRepo sensorModelRepository) {
        if (sensorModelRepository == null) {
            throw new IllegalArgumentException("Please enter a valid sensor model repository.");
        } else {
            this._sensorModelRepository = sensorModelRepository;
        }
    }

    /**
     * Validates the SensorModelFactory.
     *
     * @param factorySensorModel The SensorModelFactory to validate.
     */
    private void validateFactorySensorModel(SensorModelFactory factorySensorModel) {
        if (factorySensorModel == null) {
            throw new IllegalArgumentException("Please enter a valid sensor model factory.");
        } else {
            this._factorySensorModel = factorySensorModel;
        }
    }

    /**
     * Creates a new SensorModel and saves it in the repository and saves it.
     *
     * @param sensorModelName The name of the sensor model.
     * @param sensorPath      The path of the sensor model.
     * @param sensorTypeID    The type of the sensor model.
     * @return
     */
    protected SensorModel createSensorModel(SensorModelName sensorModelName, ModelPath sensorPath, SensorTypeID sensorTypeID) {
        SensorModel sensorModel = _factorySensorModel.createSensorModel(sensorModelName, sensorPath, sensorTypeID);
        _sensorModelRepository.save(sensorModel);
        return sensorModel;
    }


    /**
     * Get all sensor models.
     *
     * @return List of all sensor models.
     */
    public List<SensorModel> getAllSensorModels() {
        return _sensorModelRepository.findAll();
    }

    /**
     * Get a sensor model by its path.
     *
     * @param modelPath The path of the sensor model.
     * @return The sensor model.
     */
    public Optional<SensorModel> getSensorModel(ModelPath modelPath) {
        return _sensorModelRepository.ofIdentity(modelPath);
    }

    /**
     * Get all sensor models by sensor type ID.
     *
     * @param sensorTypeID The sensor type ID.
     * @return List of sensor models.
     */
    public List<SensorModel> getSensorModelsBySensorTypeId(SensorTypeID sensorTypeID) {
        return _sensorModelRepository.findBySensorTypeId(sensorTypeID);
    }
}
