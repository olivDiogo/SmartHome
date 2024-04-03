package SmartHomeDDD.service;

import SmartHomeDDD.domain.Unit.Unit;
import SmartHomeDDD.domain.Unit.UnitFactory;
import SmartHomeDDD.repository.MeasurementTypeRepository;
import SmartHomeDDD.valueObject.UnitDescription;
import SmartHomeDDD.valueObject.UnitID;
import SmartHomeDDD.valueObject.UnitSymbol;

import java.util.List;
import java.util.Optional;

public class UnitService {

    private MeasurementTypeRepository _measurementTypeRepository;
    private UnitFactory _UnitFactory;

    /**
     * Constructor for MeasurementTypeService.
     * @param measurementTypeRepository
     * @param unitFactory
     */
    public UnitService(MeasurementTypeRepository measurementTypeRepository, UnitFactory unitFactory) {
        validateMeasurementTypeRepository(measurementTypeRepository);
        validateMeasurementTypeFactory(unitFactory);
    }

    private void validateMeasurementTypeRepository(MeasurementTypeRepository measurementTypeRepository) {
        if (measurementTypeRepository == null) {
            throw new IllegalArgumentException("Please enter a valid measurement type repository.");
        } else {
            this._measurementTypeRepository = measurementTypeRepository;
        }
    }

    private void validateMeasurementTypeFactory(UnitFactory unitFactory) {
        if (unitFactory == null) {
            throw new IllegalArgumentException("Please enter a valid measurement type factory.");
        } else {
            this._UnitFactory = unitFactory;
        }
    }

    /**
     * Creates a new MeasurementType and saves it in the repository.
     *
     * @param description The description of the measurement type.
     * @param unit The unit of the measurement type.
     * @return The created and saved MeasurementType object.
     */
    public Unit createAndSaveMeasurementType(UnitDescription description, UnitSymbol unit) {
        validateDescription(description);
        validateUnit(unit);

        Unit measurementUnit = _UnitFactory.createMeasurement(description, unit);
        return _measurementTypeRepository.save(measurementUnit);
    }

    /**
     * Validates that the description is not null or empty.
     *
     * @param description The description to validate.
     * @throws IllegalArgumentException if the description is null or empty.
     */
    private void validateDescription(UnitDescription description) {
        if (description == null || description.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Measurement type description cannot be null or empty.");
        }
    }

    /**
     * Validates that the unit is not null or empty.
     *
     * @param unit The unit to validate.
     * @throws IllegalArgumentException if the unit is null or empty.
     */
    private void validateUnit(UnitSymbol unit) {
        if (unit == null || unit.getUnit().trim().isEmpty()) {
            throw new IllegalArgumentException("Measurement type unit cannot be null or empty.");
        }
    }

    /**
     * Finds a MeasurementType by its ID.
     *
     * @param unitID The unique identifier of the MeasurementType.
     * @return An Optional containing the found MeasurementType, or an empty Optional if not found.
     */
    public Optional<Unit> findMeasurementTypeById(UnitID unitID) {
        if (unitID == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type ID.");
        }
        return _measurementTypeRepository.ofIdentity(unitID);
    }

    /**
     * Returns a list of all MeasurementTypes.
     *
     * @return A List containing all MeasurementTypes.
     */
    public List<Unit> getAllMeasurementTypes() {
        return _measurementTypeRepository.findAll();
    }

}
