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
    public final String actuatorTypeDescription;
    public final String unit;

    /**
     * Constructs a new ActuatorTypeDTO object.
     *
     * @param actuatorTypeID The description of the actuator type.
     */
    public ActuatorTypeDTO(String actuatorTypeID, String actuatorTypeDescription, String unit) {
        this.actuatorTypeID = actuatorTypeID;
        this.actuatorTypeDescription = actuatorTypeDescription;
        this.unit = unit;
    }

    /**
     * Returns a string representation of the ActuatorTypeDTO object.
     *
     * @return A string representation of the ActuatorTypeDTO object.
     */
    @Override
    public String toString() {
        return actuatorTypeID + " " + actuatorTypeDescription + " " + unit;
    }
}

