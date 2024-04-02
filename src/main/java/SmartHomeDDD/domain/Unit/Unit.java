package SmartHomeDDD.domain.Unit;

import SmartHomeDDD.valueObject.UnitID;
import SmartHomeDDD.valueObject.UnitSymbol;
import SmartHomeDDD.valueObject.UnitDescription;
import SmartHomeDDD.ddd.AggregateRoot;

import java.util.UUID;

/**
 * Represents a type of measurement in the SmartHomeDDD domain. This class includes information about
 * the measurement's unit and its description. It acts as an aggregate root in the domain-driven design (DDD) context.
 */
public class Unit implements AggregateRoot<UnitID> {

    private UnitID _unitID;
    private UnitSymbol _unitSymbol;
    private UnitDescription _unitDescription;

    /**
     * Constructs a new instance of MeasurementType with the specified unit description and measurement unit.
     * This constructor ensures the measurement type is fully initialized and valid.
     *
     * @param unitDescription The description of the measurement unit, not null.
     * @param unitSymbol The unit of measurement, not null.
     * @throws IllegalArgumentException if either the unit description or measurement unit is null.
     */
    Unit(UnitDescription unitDescription, UnitSymbol unitSymbol) {
        generateID();
        validateUnitSymbol(unitSymbol);
        validateUnitDescription(unitDescription);
        this._unitSymbol = unitSymbol;
        this._unitDescription = unitDescription;
    }
    /**
     * Validates that the measurement unit is not null.
     *
     * @param unitSymbol The symbol of unit to validate.
     * @throws IllegalArgumentException if the symbol is null.
     */
    private void validateUnitSymbol(UnitSymbol unitSymbol) {
        if (unitSymbol == null) {
            throw new IllegalArgumentException("Measurement unit is required");
        }
    }
    /**
     * Validates that the unit description is not null.
     *
     * @param unitDescription The description to validate.
     * @throws IllegalArgumentException if the unit description is null.
     */
    private void validateUnitDescription(UnitDescription unitDescription) {
        if (unitDescription == null) {
            throw new IllegalArgumentException("Unit description is required");
        }
    }
    /**
     * Generates a unique identifier for the measurement type.
     */
    private void generateID() {
        _unitID = new UnitID(UUID.randomUUID().toString());
    }

    /**
     * Returns the unique identifier for the measurement type.
     *
     * @return The measurement type's ID.
     */
    @Override
    public UnitID getID() {
        return _unitID;
    }

    /**
     * Returns the description associated with this measurement type.
     *
     * @return The unit of measurement.
     */
    public UnitDescription getUnitDescription() {
        return _unitDescription;
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
            return this._unitID.equals(unit._unitID);
        }
        return false;
    }

    /**
     * Returns a string representation of the measurement type.
     *
     * @return A string representation of the measurement type.
     */
    @Override
    public String toString() {
        return "MeasurementType{" +
                "_measurementUnit=" + _unitSymbol +
                ", _unitDescription=" + _unitDescription +
                ", _measurementID=" + _unitID +
                '}';
    }

}
