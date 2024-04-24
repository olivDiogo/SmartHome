package smart_home.service;

import smart_home.ddd.IRepository;
import smart_home.domain.service.IUnitService;
import smart_home.domain.unit.IUnitFactory;
import smart_home.domain.unit.Unit;
import smart_home.value_object.UnitDescription;
import smart_home.value_object.UnitID;
import smart_home.value_object.UnitSymbol;

import java.util.List;
import java.util.Optional;

public class UnitServiceImpl implements IUnitService {

    private IRepository<UnitID, Unit> unitRepository;
    private IUnitFactory unitFactory;

    /**
     * Constructor for MeasurementTypeService.
     *
     * @param unitRepository The repository for the measurement type.
     * @param unitFactory   The factory for the measurement type.
     */
    public UnitServiceImpl(IRepository<UnitID, Unit> unitRepository, IUnitFactory unitFactory) {
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
            this.unitRepository = unitRepository;
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
            this.unitFactory = unitFactory;
        }
    }

    /**
     * Creates a new MeasurementType and saves it in the repository.
     *
     * @param description The description of the measurement type.
     * @param unit        The unit of the measurement type.
     * @return The created and saved MeasurementType object.
     */
    @Override
    public Unit addMeasurementType(UnitDescription description, UnitSymbol unit) {
        validateDescription(description);
        validateUnit(unit);

        Unit measurementUnit = unitFactory.createUnit(description, unit);
        return unitRepository.save(measurementUnit);
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
    @Override
    public Optional<Unit> getMeasurementTypeById(UnitID unitID) {
        if (unitID == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type ID.");
        }
        return unitRepository.ofIdentity(unitID);
    }

    /**
     * Returns a list of all MeasurementTypes.
     *
     * @return A List containing all MeasurementTypes.
     */
    @Override
    public List<Unit> getAllMeasurementTypes() {
        return unitRepository.findAll();
    }

}
