package smart_home.dto;

import java.util.Map;

public class ActuatorDataDTO {
    /**
     * The data needed to create a actuator.
     */
    public final String deviceID;
    public final String actuatorModelPath;
    public final String actuatorName;
    public final String actuatorTypeID;
    public Map<String, String> additionalParameters;

    /**
     * Constructs a new ActuatorDataDTO object with the specified actuator details.
     *
     * @param deviceID          The unique identifier of the device.
     * @param actuatorModelPath The file path to the actuator model's data.
     * @param actuatorName      The name of the actuator.
     * @param actuatorTypeID    The unique identifier of the actuator type.
     */
    public ActuatorDataDTO(String deviceID, String actuatorModelPath, String actuatorName, String actuatorTypeID) {
        this(deviceID, actuatorModelPath, actuatorName, actuatorTypeID, null);
    }

    public ActuatorDataDTO(String deviceID, String actuatorModelPath, String actuatorName, String actuatorTypeID, Map additionalParameters) {
        this.deviceID = deviceID;
        this.actuatorModelPath = actuatorModelPath;
        this.actuatorName = actuatorName;
        this.actuatorTypeID = actuatorTypeID;
    }
}
