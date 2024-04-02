package SmartHomeDDD.service;
import SmartHomeDDD.repository.MeasurementTypeRepository;
import SmartHomeDDD.repository.SensorTypeRepository;
import SmartHomeDDD.valueObject.UnitID;
import SmartHomeDDD.valueObject.SensorTypeID;
import SmartHomeDDD.valueObject.TypeDescription;
import SmartHomeDDD.domain.SensorType.SensorType;
import SmartHomeDDD.domain.SensorType.SensorTypeFactory;

import java.util.List;
import java.util.Optional;

public class SensorTypeService {
    private SensorTypeRepository _sensorTypeRepository;
    private SensorTypeFactory _sensorTypeFactory;
    private MeasurementTypeRepository _measurementTypeRepository;

    public SensorTypeService(SensorTypeRepository sensorTypeRepository, SensorTypeFactory sensorTypeFactory, MeasurementTypeRepository measurementTypeRepository) {
        validateSensorTypeRepository(sensorTypeRepository);
        validateSensorTypeFactory(sensorTypeFactory);
        validateMeasurementTypeRepository(measurementTypeRepository);
    }
    private void validateSensorTypeRepository(SensorTypeRepository sensorTypeRepository) {
        if (sensorTypeRepository == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type repository.");
        } else {
            this._sensorTypeRepository = sensorTypeRepository;
        }
    }
    private void validateSensorTypeFactory(SensorTypeFactory sensorTypeFactory) {
        if (sensorTypeFactory == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type factory.");
        } else {
            this._sensorTypeFactory = sensorTypeFactory;
        }
    }
    private void validateMeasurementTypeRepository(MeasurementTypeRepository measurementTypeRepository) {
        if (measurementTypeRepository == null) {
            throw new IllegalArgumentException("Please enter a valid measurement type repository.");
        } else {
            this._measurementTypeRepository = measurementTypeRepository;
        }
    }

    public SensorType createSensorType(TypeDescription name, UnitID unitID) {
        if (!_measurementTypeRepository.containsOfIdentity(unitID)) {
            throw new IllegalArgumentException("Please enter a valid measurement type.");
        }
        SensorType sensorType = _sensorTypeFactory.createSensorType(name, unitID);
        return sensorType;
    }
    public SensorType saveSensorType(SensorType sensorType) {
        if (sensorType == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type.");
        }
        return _sensorTypeRepository.save(sensorType);
    }
    public Optional<SensorType> findSensorTypeByID(SensorTypeID sensorTypeID) {
        if (sensorTypeID == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type ID.");
        }
        return _sensorTypeRepository.ofIdentity(sensorTypeID);
    }
    public List<SensorType> findAllSensorTypes() {
        return _sensorTypeRepository.findAll();
    }
}
