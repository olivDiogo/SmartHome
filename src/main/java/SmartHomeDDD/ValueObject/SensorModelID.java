package SmartHomeDDD.ValueObject;

/**
 * Represents the unique identifier for a sensor model.
 */
public class SensorModelID {

    /** The ID of the sensor model. */
    private String _id;

    /**
     * Constructs a new SensorModelID object.
     *
     * @param sensorModelID The ID of the sensor model.
     * @throws IllegalArgumentException if the provided sensorModelID is null, empty, or blank.
     */
    public SensorModelID(String sensorModelID) {
        if (sensorModelID != null && !sensorModelID.isBlank() && !sensorModelID.isEmpty())
            this._id = sensorModelID;
        else
            throw new IllegalArgumentException("'sensorModelID' must be a non-empty string.");
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return true if this object is the same as the object argument; false otherwise.
     */
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof SensorModelID) {
            SensorModelID sensorModelID = (SensorModelID) object;

            if (this._id.equals(sensorModelID._id))
                return true;
        }
        return false;
    }

    /**
     * Returns the ID of the sensor model.
     *
     * @return The ID of the sensor model.
     */
    public String getId() {
        return this._id;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    public int hashCode() {
        return this._id.hashCode();
    }
}

