package smartHome.valueObject;

import smartHome.ddd.IDomainID;

public class ActuatorTypeID implements IDomainID {

    private final String _id;

    /**
     * Constructor for the ActuatorTypeID class.
     *
     * @param actuatorTypeID is the ID of the actuator type.
     */
    public ActuatorTypeID(String actuatorTypeID) throws IllegalArgumentException {
        validationActuatorTypeID(actuatorTypeID);
        this._id = actuatorTypeID.trim();

    }

    /**
     * Validates the actuator type ID.
     *
     * @param actuatorTypeID is the ID of the actuator type.
     */
    private void validationActuatorTypeID(String actuatorTypeID) {
        if (actuatorTypeID == null || actuatorTypeID.isBlank() || actuatorTypeID.isEmpty())
            throw new IllegalArgumentException("The value of 'actuatorTypeID' should not null, blank, or empty.");
    }

    /**
     * Equals method for ActuatorTypeID.
     *
     * @param object Object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof ActuatorTypeID actuatorTypeID) {

            return this._id.equals(actuatorTypeID._id);
        }
        return false;
    }

    /**
     * Getter for ID.
     *
     * @return the ID of the actuator type.
     */
    @Override
    public String getID() {
        return this._id;
    }

    /**
     * HashCode method for ActuatorTypeID.
     *
     * @return
     */
    public int hashCode() {
        return this._id.hashCode();
    }

    /**
     * toString method for ActuatorTypeID.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return _id;
    }

}
