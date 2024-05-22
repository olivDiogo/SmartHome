package smarthome.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import smarthome.ddd.IRepository;
import smarthome.domain.unit.IUnitFactory;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.utils.Validator;

@Service
public class UnitServiceImpl implements IUnitService {

  private final IRepository<UnitID, Unit> unitRepository;
  private final IUnitFactory unitFactory;

  /**
   * Constructor for MeasurementTypeService.
   *
   * @param unitRepository The repository for the measurement type.
   * @param unitFactory    The factory for the measurement type.
   */
  public UnitServiceImpl(IRepository<UnitID, Unit> unitRepository, IUnitFactory unitFactory) {
    Validator.validateNotNull(unitRepository, "MeasurementType repository");
    Validator.validateNotNull(unitFactory, "MeasurementType factory");

    this.unitRepository = unitRepository;
    this.unitFactory = unitFactory;
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
