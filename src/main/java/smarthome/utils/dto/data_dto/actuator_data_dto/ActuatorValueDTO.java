package smarthome.utils.dto.data_dto.actuator_data_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ActuatorValueDTO implements IActuatorDataDTO {

  @NotBlank(message = "DeviceID cannot be empty")
  public final String deviceID;

  @NotBlank(message = "ActuatorID cannot be empty")
  public final String actuatorID;

  public final int value;

}
