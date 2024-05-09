/**
 * This class represents the data transfer object for the Actuator data for an actuator with integer limits
 * to be received from the client.
 */

package smarthome.utils.dto.data_dto.actuator_data_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ActuatorDataWithIntegerLimitsDTOImp implements IActuatorDataDTO {

  @NotBlank(message = "DeviceID cannot be empty")
  public final String deviceID;

  @NotBlank(message = "ActuatorModelPath cannot be empty")
  public final String actuatorModelPath;

  @NotBlank(message = "ActuatorName cannot be empty")
  public final String actuatorName;

  @NotBlank(message = "ActuatorTypeID cannot be empty")
  public final String actuatorTypeID;

  @NotBlank(message = "MinLimit cannot be empty")
  public final String minLimit;

  @NotBlank(message = "MaxLimit cannot be empty")
  public final String maxLimit;
}
