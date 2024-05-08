package smarthome.domain.unit;

import smarthome.ddd.IAggregateRoot;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.utils.Validator;

/**
 * Represents a type of measurement in the SmartHomeDDD domain. This class includes information
 * about the measurement's unit and its description. It acts as an aggregate root in the
 * domain-driven design (DDD) context.
 */
public class Unit implements IAggregateRoot<UnitID> {

  private final UnitSymbol unitSymbol;
  private final UnitDescription description;
  private UnitID unitID;

  /**
   * Constructs a new instance of MeasurementType with the specified unit description and
   * measurement unit. This constructor ensures the measurement type is fully initialized and
   * valid.
   *
   * @param unitDescription The description of the measurement unit, not null.
   * @param unitSymbol      The unit of measurement, not null.
   * @throws IllegalArgumentException if either the unit description or measurement unit is null.
   */
  public Unit(UnitDescription unitDescription, UnitSymbol unitSymbol) {
    Validator.validateNotNull(unitDescription, "UnitDescription");
    Validator.validateNotNull(unitSymbol, "UnitSymbol");
    this.unitSymbol = unitSymbol;
    this.description = unitDescription;
    generateID(unitDescription);

  }

  /**
   * Constructs a new instance of MeasurementType with the specified unit description, measurement
   * unit, and unit ID. This constructor ensures the measurement type is fully initialized and
   * valid.
   *
   * @param unitDescription The description of the measurement unit, not null.
   * @param unitSymbol      The unit of measurement, not null.
   * @param unitID          The unique identifier for the measurement type, not null.
   * @throws IllegalArgumentException if either the unit description, measurement unit, or unit ID
   *                                  is null.
   */
  Unit(UnitDescription unitDescription, UnitSymbol unitSymbol, UnitID unitID) {
    Validator.validateNotNull(unitDescription, "UnitDescription");
    Validator.validateNotNull(unitSymbol, "UnitSymbol");
    Validator.validateNotNull(unitID, "UnitID");
    this.unitSymbol = unitSymbol;
    this.description = unitDescription;
    this.unitID = unitID;
  }


  /**
   * Generates a unique identifier for the measurement type.
   */
  private void

  generateID(UnitDescription unitDescription) {
    unitID = new UnitID(unitDescription.toString());
  }

  /**
   * Returns the unique identifier for the measurement type.
   *
   * @return The measurement type's ID.
   */
  @Override
  public UnitID getID() {
    return unitID;
  }

  /**
   * Returns the description associated with this measurement type.
   *
   * @return The unit of measurement.
   */
  public UnitDescription getUnitDescription() {
    return description;
  }

  /**
   * Returns the symbol associated with this measurement type.
   *
   * @return The unit of measurement.
   */
  public UnitSymbol getUnitSymbol() {
    return unitSymbol;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @param object The reference object with which to compare.
   * @return true if this object is the same as the object argument; false otherwise.
   */

  @Override
  public boolean equals(Object object) {
    if (object instanceof Unit unit) {
      return this.unitID.equals(unit.unitID);
    }
    return false;
  }

  /**
   * Returns a hash code value for the object.
   *
   * @return A hash code value for this object.
   */
  @Override
  public int hashCode() {
    return unitID.hashCode();
  }

  /**
   * Returns a string representation of the measurement type.
   *
   * @return A string representation of the measurement type.
   */
  @Override
  public String toString() {
    return "Unit:" +
        "unitSymbol=" + unitSymbol +
        ", unitDescription=" + description +
        ", unitID=" + unitID;
  }

}
