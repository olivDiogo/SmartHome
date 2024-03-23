package SmartHomeDDD.domain.MeasurementType;

import SmartHomeDDD.ValueObject.MeasurementID;
import SmartHomeDDD.ValueObject.MeasurementTypeUnit;
import SmartHomeDDD.ValueObject.MeasurementTypeDescription;
import SmartHomeDDD.ddd.AggregateRoot;

import java.util.UUID;

/**
 * Represents a type of measurement in the SmartHomeDDD domain. This class includes information about
 * the measurement's unit and its description. It acts as an aggregate root in the domain-driven design (DDD) context.
 */
public class MeasurementType implements AggregateRoot<MeasurementID> {

    private MeasurementID _measurementID;
    private MeasurementTypeUnit _measurementUnit;
    private MeasurementTypeDescription _unitDescription;

    /**
     * Constructs a new instance of MeasurementType with the specified unit description and measurement unit.
     * This constructor ensures the measurement type is fully initialized and valid.
     *
     * @param unitDescription The description of the measurement unit, not null.
     * @param measurementUnit The unit of measurement, not null.
     * @throws IllegalArgumentException if either the unit description or measurement unit is null.
     */
    MeasurementType(MeasurementTypeDescription unitDescription, MeasurementTypeUnit measurementUnit) {
        generateID();
        validateMeasurementUnit(measurementUnit);
        validateUnitDescription(unitDescription);
        this._measurementUnit = measurementUnit;
        this._unitDescription = unitDescription;
    }
    /**
     * Validates that the measurement unit is not null.
     *
     * @param measurementUnit The unit of measurement to validate.
     * @throws IllegalArgumentException if the measurement unit is null.
     */
    private void validateMeasurementUnit(MeasurementTypeUnit measurementUnit) {
        if (measurementUnit == null) {
            throw new IllegalArgumentException("Measurement unit is required");
        }
    }
    /**
     * Validates that the unit description is not null.
     *
     * @param unitDescription The description to validate.
     * @throws IllegalArgumentException if the unit description is null.
     */
    private void validateUnitDescription(MeasurementTypeDescription unitDescription) {
        if (unitDescription == null) {
            throw new IllegalArgumentException("Unit description is required");
        }
    }
    /**
     * Generates a unique identifier for the measurement type.
     */
    private void generateID() {
        _measurementID = new MeasurementID(UUID.randomUUID().toString());
    }

    /**
     * Returns the unique identifier for the measurement type.
     *
     * @return The measurement type's ID.
     */
    @Override
    public MeasurementID getID() {
        return _measurementID;
    }

    /**
     * Returns the description associated with this measurement type.
     *
     * @return The unit of measurement.
     */
    public MeasurementTypeDescription getUnitDescription() {
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
        if (this == object) {
            return true;
        }
        if (object instanceof MeasurementType) {
            MeasurementType measurementType = (MeasurementType) object;
            return this._measurementUnit.equals(measurementType._measurementUnit) && this._unitDescription.equals(measurementType._unitDescription) && this._measurementID.equals(measurementType._measurementID);
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
                "_measurementUnit=" + _measurementUnit +
                ", _unitDescription=" + _unitDescription +
                ", _measurementID=" + _measurementID +
                '}';
    }

}
