package SmartHomeDDD.valueObject;

import SmartHomeDDD.ddd.DomainID;

/**
 * Represents the unique identifier for a sensor model.
 */
public class SensorModelID implements DomainID {

    /** The ID of the sensor model. */
    private String _sensorModelID;

    /**
     * Constructs a new SensorModelID object.
     *
     * @param sensorModelID The ID of the sensor model.
     * @throws IllegalArgumentException if the provided sensorModelID is null, empty, or blank.
     */
    public SensorModelID(String sensorModelID) {
        validationSensorModelID(sensorModelID);
        this._sensorModelID = sensorModelID;
    }

    /**
     * Validates the sensor model ID.
     *
     * @param sensorModelID The ID of the sensor model.
     */
    private void validationSensorModelID(String sensorModelID) {
        if (sensorModelID == null || sensorModelID.isBlank() || sensorModelID.isEmpty())
            throw new IllegalArgumentException("The value of 'sensorModelID' should not null, blank, or empty.");
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

            if (this._sensorModelID.equals(sensorModelID._sensorModelID))
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
        return this._sensorModelID;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    public int hashCode() {
        return this._sensorModelID.hashCode();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    public String toString() {
        return "SensorModelID:" + _sensorModelID;
    }

}

