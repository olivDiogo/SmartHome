package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

/**
 * Data Transfer Object (DTO) representing information about an actuator.
 */
public class ActuatorDTO implements DTO {

    /**
     * The unique identifier of the actuator.
     */
    public final String id;
    /**
     * The type of the actuator.
     */
    public final String actuatorType;
    /**
     * The name of the actuator.
     */
    public final String actuatorName;
    /**
     * The description of the actuator.
     */
    public final String actuatorDescription;
    /**
     * Constructs a new ActuatorDTO object with the specified actuator details.
     *
     * @param actuatorID          The unique identifier of the actuator.
     * @param actuatorType        The type of the actuator.
     * @param actuatorName        The name of the actuator.
     * @param actuatorDescription The description of the actuator.
     */
    public ActuatorDTO(String actuatorID, String actuatorType, String actuatorName, String actuatorDescription) {
        this.id = actuatorID;
        this.actuatorType = actuatorType;
        this.actuatorName = actuatorName;
        this.actuatorDescription = actuatorDescription;
    }
}