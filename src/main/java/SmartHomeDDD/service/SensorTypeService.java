package SmartHomeDDD.service;

import SmartHomeDDD.domain.SensorType.SensorType;
import SmartHomeDDD.domain.SensorType.SensorTypeFactory;
import SmartHomeDDD.repository.UnitRepository;
import SmartHomeDDD.repository.SensorTypeRepository;
import SmartHomeDDD.valueObject.SensorTypeID;
import SmartHomeDDD.valueObject.TypeDescription;
import SmartHomeDDD.valueObject.UnitID;

import java.util.List;
import java.util.Optional;

public class SensorTypeService {
  private SensorTypeRepository _sensorTypeRepository;
  private SensorTypeFactory _sensorTypeFactory;
  private UnitRepository _unitRepository;

  public SensorTypeService(
      SensorTypeRepository sensorTypeRepository,
      SensorTypeFactory sensorTypeFactory,
      UnitRepository unitRepository) {
    validateSensorTypeRepository(sensorTypeRepository);
    validateSensorTypeFactory(sensorTypeFactory);
    validateMeasurementTypeRepository(unitRepository);
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

  private void validateMeasurementTypeRepository(UnitRepository unitRepository) {
    if (unitRepository == null) {
      throw new IllegalArgumentException("Please enter a valid measurement type repository.");
    } else {
      this._unitRepository = unitRepository;
    }
  }

  public SensorType createSensorType(TypeDescription name, UnitID unitID) {
    if (!_unitRepository.containsOfIdentity(unitID)) {
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
