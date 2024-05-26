/**
 * This class represents the data transfer object for the Actuator data for a generic Actuator to be
 * received from the client.
 */

package smarthome.utils.dto.data_dto.actuator_data_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ActuatorDataGenericDTOImp implements IActuatorDataDTO {

  @NotBlank(message = "DeviceID cannot be empty")
  public final String deviceID;

  @NotBlank(message = "ActuatorModelPath cannot be empty")
  public final String actuatorModelPath;

  @NotBlank(message = "ActuatorTypeID cannot be empty")
  public final String actuatorTypeID;

  @NotBlank(message = "ActuatorName cannot be empty")
  public final String actuatorName;
}

