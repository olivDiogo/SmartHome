package smartHome.service;

import smartHome.ddd.IRepository;
import smartHome.domain.unit.IUnitFactory;
import smartHome.domain.unit.Unit;
import smartHome.valueObject.UnitDescription;
import smartHome.valueObject.UnitID;
import smartHome.valueObject.UnitSymbol;

import java.util.List;
import java.util.Optional;

public class UnitService {

    private IRepository<UnitID, Unit> _unitRepository;
    private IUnitFactory _UnitFactory;

    /**
     * Constructor for MeasurementTypeService.
     *
     * @param unitRepository
     * @param unitFactory
     */
    public UnitService(IRepository<UnitID, Unit> unitRepository, IUnitFactory unitFactory) {
        validateMeasurementTypeRepository(unitRepository);
        validateMeasurementTypeFactory(unitFactory);
    }

    /**
     * Validates the MeasurementTypeRepository.
     *
     * @param unitRepository The MeasurementTypeRepository to validate.
     */
    private void validateMeasurementTypeRepository(IRepository<UnitID, Unit> unitRepository) {
        if (unitRepository == null) {
            throw new IllegalArgumentException("Please enter a valid measurement type repository.");
        } else {
            this._unitRepository = unitRepository;
        }
    }

    /**
     * Validates the MeasurementTypeFactory.
     *
     * @param unitFactory The MeasurementTypeFactory to validate.
     */
    private void validateMeasurementTypeFactory(IUnitFactory unitFactory) {
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
     * @param unit        The unit of the measurement type.
     * @return The created and saved MeasurementType object.
     */
    public Unit createAndSaveMeasurementType(UnitDescription description, UnitSymbol unit) {
        validateDescription(description);
        validateUnit(unit);

        Unit measurementUnit = _UnitFactory.createUnit(description, unit);
        return _unitRepository.save(measurementUnit);
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
        return _unitRepository.ofIdentity(unitID);
    }

    /**
     * Returns a list of all MeasurementTypes.
     *
     * @return A List containing all MeasurementTypes.
     */
    public List<Unit> getAllMeasurementTypes() {
        return _unitRepository.findAll();
    }

}
