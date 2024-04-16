package smart_home.dto;

import smart_home.ddd.IDTO;

/**
 * ActuatorModelDTO is a DTO class that represents the actuator model.
 */
public class ActuatorModelDTO implements IDTO {
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
     *
     * @param actuatorModelID
     * @param actuatorModelName
     * @param actuatorModelPath
     */
    public ActuatorModelDTO(
            String actuatorModelID, String actuatorModelName, String actuatorModelPath) {
        this.actuatorModelID = actuatorModelID;
        this.actuatorModelName = actuatorModelName;
        this.actuatorModelPath = actuatorModelPath;
    }

    @Override
    public String toString() {
        return actuatorModelID + " " + actuatorModelName + " " + actuatorModelPath;
    }
}
