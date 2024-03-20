package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.ValueObject;

/**
 * Represents a unique identifier for a measurement within a smart home system.
 * This class ensures that the identifier is valid and not empty.
 */
public class MeasurementID implements ValueObject {

    private final String _measurementID;

    /**
     * Constructs a MeasurementID object with a specified unit ID after validating it.
     *
     * @param unitID the unique identifier for the measurement to be set.
     * @throws IllegalArgumentException if the unit ID is null, blank, or empty.
     */
    public MeasurementID(String unitID) {
        validateUnitID(unitID);
        this._measurementID = unitID;
    }

    /**
     * Validates the unit ID to ensure it's a valid, non-empty string.
     *
     * @param unitID the unit ID to validate.
     * @throws IllegalArgumentException if the unit ID is null, blank, or empty.
     */
    private void validateUnitID(String unitID) {
        if (unitID == null || unitID.isBlank() || unitID.isEmpty()) {
            throw new IllegalArgumentException("Invalid unit ID.");
        }
    }

    /**
     * Checks if this MeasurementID object is equal to another object.
     *
     * @param object the object to compare with.
     * @return true if both objects are MeasurementID instances with the same unit ID.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof MeasurementID) {
            MeasurementID unit = (MeasurementID) object;
            return this._measurementID.equals(unit._measurementID);
        }
        return false;
    }

    /**
     * @return the unique identifier for the measurement.
     */
    public String getId() {
        return this._measurementID;
    }

    /**
     * Generates a hash code for this MeasurementID object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return this._measurementID.hashCode();
    }

    /**
     * Returns a string representation of the MeasurementID object.
     *
     * @return a string in the format "Measurement{_measurementID='ID'}".
     */
    @Override
    public String toString() {
        return "Measurement{" +
                "_measurementID='" + _measurementID + '\'' +
                '}';
    }
}
