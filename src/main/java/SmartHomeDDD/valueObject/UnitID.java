package SmartHomeDDD.valueObject;

import SmartHomeDDD.ddd.DomainID;

/**
 * This class ensures that the measurement ID adheres to specific validation rules before it is assigned.
 */
public class UnitID implements DomainID {

    private final String _id;

    /**
     * Constructs a new MeasurementID instance after validating the provided ID.
     *
     * @param measurementID The string representation of the measurement ID.
     *                      It must not be null, empty, or blank.
     * @throws IllegalArgumentException if the measurementID is null, empty, or blank.
     */
    public UnitID(String measurementID) {
        validateID(measurementID);
        this._id = measurementID.trim();
    }

    /**
     * Validates the given measurement ID.
     *
     * @param measurementID The measurement ID to validate.
     * @throws IllegalArgumentException if the measurementID is null, empty, or blank.
     */
    private void validateID(String measurementID) {
        if (measurementID == null || measurementID.isBlank())
            throw new IllegalArgumentException("The value of 'measurementID' should not null, blank, or empty.");
    }

    /**
     * Retrieves the measurement ID.
     *
     * @return The measurement ID as a string.
     */
    public String getId() {
        return _id;
    }

    /**
     * Checks if this MeasurementID is equal to another object.
     * Two MeasurementID instances are considered equal if their IDs are equal.
     *
     * @param o The object to compare this instance against.
     * @return true if the given object is an instance of MeasurementID and has an equal ID; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o instanceof UnitID objectUnitId) {
            return this._id.equals(objectUnitId._id);
        }
        return false;
    }

    /**
     * Generates a hash code for this MeasurementID.
     *
     * @return The hash code of the measurement ID.
     */
    @Override
    public int hashCode() {
        return _id.hashCode();
    }
}
