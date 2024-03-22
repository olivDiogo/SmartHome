package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

/**
 * Data Transfer Object (DTO) representing an actuator type.
 */
public class ActuatorTypeDTO implements DTO {

    /**
     * Description of the actuator type.
     */
    public final String actuatorTypeID;

    /**
     * Constructs a new ActuatorTypeDTO object.
     *
     *
     * @param actuatorTypeID  The description of the actuator type.
     */
    public ActuatorTypeDTO(String actuatorTypeID) {
        this.actuatorTypeID = actuatorTypeID;
    }


    /**
     * Returns a string representation of the ActuatorTypeDTO object.
     *
     * @return A string representation of the ActuatorTypeDTO object.
     */
    @Override
    public String toString() {
        return "ActuatorTypeDTO{" +
                ", _actuatorTypeDescription='" + actuatorTypeID + '\'' +
                '}';
    }
}

