package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

/**
 * ActuatorModelDTO is a DTO class that represents the actuator model.
 */
public class ActuatorModelDTO implements DTO {
    /**
     * Attributes of class ActuatorModelDTO.
     */
    public String actuatorModelID;
    /**
     * Attributes of class ActuatorModelDTO.
     */
    public String actuatorModelName;
    /**
     * Attributes of class ActuatorModelDTO.
     */
    public String actuatorModelPath;

    /**
     * Constructor of class ActuatorModelDTO.
     * @param actuatorModelID
     * @param actuatorModelName
     * @param actuatorModelPath
     */
    public ActuatorModelDTO(String actuatorModelID, String actuatorModelName, String actuatorModelPath) {
        this.actuatorModelID = actuatorModelID;
        this.actuatorModelName = actuatorModelName;
        this.actuatorModelPath = actuatorModelPath;
    }
}
