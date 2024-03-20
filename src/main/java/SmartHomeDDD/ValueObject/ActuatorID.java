package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.DomainID;

public class ActuatorID implements DomainID {
    private String _id;


    /**
     * Constructor of the class ActuatorID.
     * @param actuatorID is the ID of the actuator.
     */
    public ActuatorID(String actuatorID) {
        if (actuatorID != null && !actuatorID.isBlank() && !actuatorID.isEmpty())
            this._id = actuatorID;
        else
            throw new IllegalArgumentException("'actuatorID' must be a non-empty string.");
    }

    /**
     * Equals method for ActuatorID.
     * @param object Object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof ActuatorID) {
            ActuatorID actuatorID = (ActuatorID) object;

            if (this._id.equals(actuatorID._id))
                return true;
        }
        return false;
    }

    /**
     * Getter for ID.
     * @return the ID of the actuator.
     */
    @Override
    public String getId() {
        return this._id;
    }

    /**
     * HashCode method for ActuatorID.
     * @return
     */
    public int hashCode() {
        return this._id.hashCode();
    }
}
