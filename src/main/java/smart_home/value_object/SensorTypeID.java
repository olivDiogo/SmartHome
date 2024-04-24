package smart_home.value_object;

import smart_home.ddd.IDomainID;

public class SensorTypeID implements IDomainID {

    private final String id;

    /**
     * Constructor of the class SensorTypeID.
     *
     * @param sensorTypeID is the ID of the sensor type.
     */
    public SensorTypeID(String sensorTypeID) {
        validateId(sensorTypeID);
        this.id = sensorTypeID;
    }

    /**
     * Method to validate the ID.
     *
     * @param sensorTypeID is the ID of the sensor type.
     */
    private void validateId(String sensorTypeID) {
        if (sensorTypeID == null || sensorTypeID.isBlank())
            throw new IllegalArgumentException("'sensorTypeID' must be a non-empty string.");
    }

    /**
     * Equals method for SensorTypeID.
     *
     * @param object Object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof SensorTypeID sensorTypeID) {

            return this.id.equals(sensorTypeID.id);
        }
        return false;
    }

    /**
     * Getter for ID.
     *
     * @return the ID of the sensor type.
     */
    @Override
    public String getID() {
        return this.id;
    }

    /**
     * HashCode method for SensorTypeID.
     *
     * @return the hashcode as an int.
     */
    public int hashCode() {
        return this.id.hashCode();
    }

    /**
     * Method to return the string representation of the object.
     *
     * @return the string representation of the object.
     */
    @Override
    public String toString() {
        return this.id;
    }
}
