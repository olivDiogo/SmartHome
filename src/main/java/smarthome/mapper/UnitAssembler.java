package smarthome.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.ddd.IAssembler;
import smarthome.domain.unit.Unit;
import smarthome.utils.Validator;
import smarthome.utils.dto.UnitDTO;

/**
 * Assembler class for converting {@link Unit} domain entities to {@link UnitDTO} data transfer
 * objects.
 */
@Component
public class UnitAssembler implements IAssembler<Unit, UnitDTO> {

  /**
   * Converts a {@link Unit} domain entity to a {@link UnitDTO} data transfer object.
   *
   * @param unit is the domain entity to be converted.
   * @return The {@link UnitDTO} data transfer object.
   */
  @Override
  public UnitDTO domainToDTO(Unit unit) {
    Validator.validateNotNull(unit, "Unit");

    String unitID = unit.getID().toString();
    String unitSymbol = unit.getUnitSymbol().toString();
    String unitDescription = unit.getUnitDescription().toString();

    return new UnitDTO(unitID, unitDescription, unitSymbol);
  }

  /**
   * Converts a list of {@link Unit} domain entities to a list of {@link UnitDTO} data transfer
   * objects.
   *
   * @param units is the list of domain entities to be converted.
   * @return
   */

  @Override
  public List<UnitDTO> domainToDTO(List<Unit> units) {
    if (units == null) {
      throw new IllegalArgumentException("The list of Units cannot be null.");
    }

    List<UnitDTO> unitDTOS = new ArrayList<>();
    for (Unit unit : units) {
      UnitDTO unitDTO = domainToDTO(unit);
      unitDTOS.add(unitDTO);
    }
    return List.copyOf(unitDTOS);
  }
}
