package smart_home.dto.actuator_data_dto;

public class ActuatorDataWithIntegerLimitsDTOImp implements IActuatorDataDTO {
  /** The data needed to create a actuator. */
  public final String deviceID;
  public final String actuatorModelPath;
  public final String actuatorName;
  public final String actuatorTypeID;
  public final String minLimit;
  public final String maxLimit;

  /**
   * Constructs a new ActuatorDataDTO object with the specified actuator details.
   *
   * @param deviceID The unique identifier of the device.
   * @param actuatorModelPath The file path to the actuator model's data.
   * @param actuatorName The name of the actuator.
   * @param actuatorTypeID The unique identifier of the actuator type.
   * @param minLimit The minimum limit of theactuator.
   * @param maxLimit The maximum limit of the actuator.
   */
  public ActuatorDataWithIntegerLimitsDTOImp(
      String deviceID,
      String actuatorModelPath,
      String actuatorName,
      String actuatorTypeID,
      String minLimit,
      String maxLimit) {
    this.deviceID = deviceID;
    this.actuatorModelPath = actuatorModelPath;
    this.actuatorName = actuatorName;
    this.actuatorTypeID = actuatorTypeID;
    this.minLimit = minLimit;
    this.maxLimit = maxLimit;
  }
}
