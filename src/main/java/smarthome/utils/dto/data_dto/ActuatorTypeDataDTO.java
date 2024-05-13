package smarthome.utils.dto.data_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ActuatorTypeDataDTO {

  @NotBlank(message = "ActuatorTypeDescription cannot be empty")
  private String actuatorTypeDescription;
  @NotBlank(message = "Unit cannot be empty")
  private String unit;
}
