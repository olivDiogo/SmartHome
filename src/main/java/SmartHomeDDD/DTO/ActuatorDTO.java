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
    public final String actuatorTypeID;
    /**
     * The name of the actuator.
     */
    public final String actuatorName;
    /**
     * The description of the actuator.
     */
    public final String modelPath;

    public final String deviceID;

    /**
     * Constructs a new ActuatorDTO object with the specified actuator details.
     *
     * @param actuatorID     The unique identifier of the actuator.
     * @param actuatorTypeID The type of the actuator.
     * @param actuatorName   The name of the actuator.
     * @param modelPath      The description of the actuator.
     */
    public ActuatorDTO(String actuatorID, String actuatorTypeID, String actuatorName, String modelPath, String deviceID) {
        this.id = actuatorID;
        this.actuatorTypeID = actuatorTypeID;
        this.actuatorName = actuatorName;
        this.modelPath = modelPath;
        this.deviceID = deviceID;
    }
}