package smarthome.utils.dto.actuator_data_dto;

public class ActuatorDataGenericDTOImp implements IActuatorDataDTO{
    /**
     * The data needed to create a actuator.
     */
    public final String deviceID;
    public final String actuatorModelPath;
    public final String actuatorName;
    public final String actuatorTypeID;

    /**
     * Constructs a new ActuatorDataDTO object with the specified actuator details.
     *
     * @param deviceID          The unique identifier of the device.
     * @param actuatorModelPath The file path to the actuator model's data.
     * @param actuatorName      The name of the actuator.
     * @param actuatorTypeID    The unique identifier of the actuator type.
     */

    public ActuatorDataGenericDTOImp(String deviceID, String actuatorModelPath, String actuatorName, String actuatorTypeID) {
        this.deviceID = deviceID;
        this.actuatorModelPath = actuatorModelPath;
        this.actuatorName = actuatorName;
        this.actuatorTypeID = actuatorTypeID;
    }
}
