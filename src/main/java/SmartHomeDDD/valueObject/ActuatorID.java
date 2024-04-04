package SmartHomeDDD.valueObject;

import SmartHomeDDD.ddd.DomainID;

public class ActuatorID implements DomainID {
    private final String _id;


    /**
     * Constructor of the class ActuatorID.
     *
     * @param actuatorID is the ID of the actuator.
     */
    public ActuatorID(String actuatorID) {
        validateId(actuatorID);
        this._id = actuatorID;
    }

    /**
     * Validates the ID of the actuator.
     *
     * @param actuatorID is the ID of the actuator.
     */
    private void validateId(String actuatorID) {
        if (actuatorID == null || actuatorID.isBlank() || actuatorID.isEmpty())
            throw new IllegalArgumentException("'actuatorID' must be a non-empty string.");

    }

    /**
     * Equals method for ActuatorID.
     *
     * @param object Object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof ActuatorID actuatorID) {

            return this._id.equals(actuatorID._id);
        }
        return false;
    }

    /**
     * Getter for ID.
     *
     * @return the ID of the actuator.
     */
    @Override
    public String getId() {
        return this._id;
    }

    /**
     * HashCode method for ActuatorID.
     *
     * @return int.
     */
    public int hashCode() {
        return this._id.hashCode();
    }

    /**
     * toString method for ActuatorID.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "ActuatorID: " + this._id;
    }
}
