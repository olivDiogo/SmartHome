package smarthome.utils.dto.data_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ActuatorTypeDataDTO {

  @NotBlank(message = "ActuatorTypeDescription cannot be empty")
  public String actuatorTypeDescription;
  @NotBlank(message = "Unit cannot be empty")
  public String unit;
}
