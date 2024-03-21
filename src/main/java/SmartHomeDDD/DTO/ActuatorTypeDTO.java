package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

/**
 * Data Transfer Object (DTO) representing an actuator type.
 */
public class ActuatorTypeDTO implements DTO {

    /**
     * Unique identifier for the actuator type.
     */
    public final String _id;

    /**
     * Description of the actuator type.
     */
    public final String _actuatorTypeDescription;

    /**
     * Constructs a new ActuatorTypeDTO object.
     *
     * @param actuatorTypeID           The unique identifier for the actuator type.
     * @param actuatorTypeDescription  The description of the actuator type.
     */
    public ActuatorTypeDTO(String actuatorTypeID, String actuatorTypeDescription) {
        this._id = actuatorTypeID;
        this._actuatorTypeDescription = actuatorTypeDescription;
    }

    /**
     * Returns a string representation of the ActuatorTypeDTO object.
     *
     * @return A string representation of the ActuatorTypeDTO object.
     */
    @Override
    public String toString() {
        return "ActuatorTypeDTO{" +
                "_id='" + _id + '\'' +
                ", _actuatorTypeDescription='" + _actuatorTypeDescription + '\'' +
                '}';
    }
}

