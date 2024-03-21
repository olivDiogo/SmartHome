package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

/**
 * Data Transfer Object (DTO) representing information about an actuator.
 */
public class ActuatorDTO implements DTO {

    /**
     * The unique identifier of the actuator.
     */
    public final String _id;
    /**
     * The type of the actuator.
     */
    public final String _actuatorType;
    /**
     * The name of the actuator.
     */
    public final String _actuatorName;
    /**
     * The description of the actuator.
     */
    public final String _actuatorDescription;
    /**
     * Constructs a new ActuatorDTO object with the specified actuator details.
     *
     * @param actuatorID          The unique identifier of the actuator.
     * @param actuatorType        The type of the actuator.
     * @param actuatorName        The name of the actuator.
     * @param actuatorDescription The description of the actuator.
     */
    public ActuatorDTO(String actuatorID, String actuatorType, String actuatorName, String actuatorDescription) {
        this._id = actuatorID;
        this._actuatorType = actuatorType;
        this._actuatorName = actuatorName;
        this._actuatorDescription = actuatorDescription;
    }
}