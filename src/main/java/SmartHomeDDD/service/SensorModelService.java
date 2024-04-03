package SmartHomeDDD.service;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.SensorModel.SensorModel;
import SmartHomeDDD.domain.SensorModel.SensorModelFactory;
import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelID;
import SmartHomeDDD.valueObject.SensorModelName;
import SmartHomeDDD.valueObject.SensorTypeID;

import java.util.List;
import java.util.Optional;

public class SensorModelService {
    private Repository<SensorModelID, SensorModel> _sensorModelRepository;
    private SensorModelFactory _factorySensorModel;

    public SensorModelService(Repository<SensorModelID, SensorModel> sensorModelRepository, SensorModelFactory factorySensorModel) {
        validateSensorModelRepository(sensorModelRepository);
        validateFactorySensorModel(factorySensorModel);

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
    protected SensorModel createSensorModel(SensorModelName sensorModelName, ModelPath sensorPath, SensorTypeID sensorTypeID) {
        SensorModel sensorModel = _factorySensorModel.createSensorModel(sensorModelName, sensorPath, sensorTypeID);
        _sensorModelRepository.save(sensorModel);
        return sensorModel;
    }


    public List<SensorModel> getAllSensorModels() {
        return _sensorModelRepository.findAll();
    }
    public Optional<SensorModel> getSensorModel(SensorModelID sensorModelID) {
        return _sensorModelRepository.ofIdentity(sensorModelID);
    }
}
