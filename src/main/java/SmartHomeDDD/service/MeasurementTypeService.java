package SmartHomeDDD.service;

import SmartHomeDDD.domain.MeasurementType.MeasurementType;
import SmartHomeDDD.domain.MeasurementType.MeasurementTypeFactory;
import SmartHomeDDD.repository.MeasurementTypeRepository;
import SmartHomeDDD.valueObject.MeasurementID;
import SmartHomeDDD.valueObject.MeasurementTypeDescription;
import SmartHomeDDD.valueObject.MeasurementTypeUnit;

import java.util.List;
import java.util.Optional;

public class MeasurementTypeService {

    private MeasurementTypeRepository _measurementTypeRepository;
    private MeasurementTypeFactory _measurementTypeFactory;

    /**
     * Constructor for MeasurementTypeService.
     * @param measurementTypeRepository
     * @param measurementTypeFactory
     */
    public MeasurementTypeService(MeasurementTypeRepository measurementTypeRepository, MeasurementTypeFactory measurementTypeFactory) {
        validateMeasurementTypeRepository(measurementTypeRepository);
        validateMeasurementTypeFactory(measurementTypeFactory);
    }

    private void validateMeasurementTypeRepository(MeasurementTypeRepository measurementTypeRepository) {
        if (measurementTypeRepository == null) {
            throw new IllegalArgumentException("Please enter a valid measurement type repository.");
        } else {
            this._measurementTypeRepository = measurementTypeRepository;
        }
    }

    private void validateMeasurementTypeFactory(MeasurementTypeFactory measurementTypeFactory) {
        if (measurementTypeFactory == null) {
            throw new IllegalArgumentException("Please enter a valid measurement type factory.");
        } else {
            this._measurementTypeFactory = measurementTypeFactory;
        }
    }

    /**
     * Creates a new MeasurementType and saves it in the repository.
     *
     * @param description The description of the measurement type.
     * @param unit The unit of the measurement type.
     * @return The created and saved MeasurementType object.
     */
    public MeasurementType createAndSaveMeasurementType(MeasurementTypeDescription description, MeasurementTypeUnit unit) {
        validateDescription(description);
        validateUnit(unit);

        MeasurementType measurementType = _measurementTypeFactory.createMeasurement(description, unit);
        return _measurementTypeRepository.save(measurementType);
    }

    /**
     * Validates that the description is not null or empty.
     *
     * @param description The description to validate.
     * @throws IllegalArgumentException if the description is null or empty.
     */
    private void validateDescription(MeasurementTypeDescription description) {
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
    private void validateUnit(MeasurementTypeUnit unit) {
        if (unit == null || unit.getUnit().trim().isEmpty()) {
            throw new IllegalArgumentException("Measurement type unit cannot be null or empty.");
        }
    }

    /**
     * Finds a MeasurementType by its ID.
     *
     * @param measurementID The unique identifier of the MeasurementType.
     * @return An Optional containing the found MeasurementType, or an empty Optional if not found.
     */
    public Optional<MeasurementType> findMeasurementTypeById(MeasurementID measurementID) {
        if (measurementID == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type ID.");
        }
        return _measurementTypeRepository.ofIdentity(measurementID);
    }

    /**
     * Returns a list of all MeasurementTypes.
     *
     * @return A List containing all MeasurementTypes.
     */
    public List<MeasurementType> findAllMeasurementTypes() {
        return _measurementTypeRepository.findAll();
    }

}
