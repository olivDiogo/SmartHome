package smartHome.service;

import smartHome.domain.sensorType.ISensorTypeFactory;
import smartHome.domain.sensorType.SensorType;
import smartHome.persistence.mem.SensorTypeRepository;
import smartHome.persistence.mem.UnitRepository;
import smartHome.valueObject.SensorTypeID;
import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;

import java.util.List;
import java.util.Optional;

public class SensorTypeService {
    private SensorTypeRepository _sensorTypeRepository;
    private ISensorTypeFactory _sensorTypeFactory;
    private UnitRepository _unitRepository;

    /**
     * Constructor for SensorTypeService.
     *
     * @param sensorTypeRepository is the repository for sensor types.
     * @param sensorTypeFactory    is the factory for sensor types.
     * @param unitRepository       is the repository for units.
     */
    public SensorTypeService(
            SensorTypeRepository sensorTypeRepository,
            ISensorTypeFactory sensorTypeFactory,
            UnitRepository unitRepository) {
        validateSensorTypeRepository(sensorTypeRepository);
        validateSensorTypeFactory(sensorTypeFactory);
        validateMeasurementTypeRepository(unitRepository);
    }

    /**
     * Validates the SensorTypeRepository.
     *
     * @param sensorTypeRepository The SensorTypeRepository to validate.
     */
    private void validateSensorTypeRepository(SensorTypeRepository sensorTypeRepository) {
        if (sensorTypeRepository == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type repository.");
        } else {
            this._sensorTypeRepository = sensorTypeRepository;
        }
    }

    /**
     * Validates the SensorTypeFactory.
     *
     * @param sensorTypeFactory The SensorTypeFactory to validate.
     */
    private void validateSensorTypeFactory(ISensorTypeFactory sensorTypeFactory) {
        if (sensorTypeFactory == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type factory.");
        } else {
            this._sensorTypeFactory = sensorTypeFactory;
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
     * Creates a new SensorType.
     *
     * @param name   The description of the sensor type.
     * @param unitID The unit of the sensor type.
     * @return The created SensorType object.
     */
    public SensorType createSensorType(TypeDescription name, UnitID unitID) {
        if (!_unitRepository.containsOfIdentity(unitID)) {
            throw new IllegalArgumentException("Please enter a valid measurement type.");
        }
        SensorType sensorType = _sensorTypeFactory.createSensorType(name, unitID);
        return sensorType;
    }

    /**
     * Saves a SensorType.
     *
     * @param sensorType The SensorType to save.
     * @return The saved SensorType object.
     */
    public SensorType saveSensorType(SensorType sensorType) {
        if (sensorType == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type.");
        }
        return _sensorTypeRepository.save(sensorType);
    }

    /**
     * Finds a SensorType by its ID.
     *
     * @param sensorTypeID The ID of the SensorType to find.
     * @return The SensorType object.
     */
    public Optional<SensorType> findSensorTypeByID(SensorTypeID sensorTypeID) {
        if (sensorTypeID == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type ID.");
        }
        return _sensorTypeRepository.ofIdentity(sensorTypeID);
    }

    /**
     * Finds all SensorTypes.
     *
     * @return A list of all SensorType objects.
     */
    public List<SensorType> findAllSensorTypes() {
        return _sensorTypeRepository.findAll();
    }
}
