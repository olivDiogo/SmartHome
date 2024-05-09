/**
 * This class represents the data transfer object for the sensor type data
 * to be received from the client.
 */

package smarthome.utils.dto.data_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SensorTypeDataDTO {

  @NotBlank(message = "SensorTypeDescription cannot be empty")
  public String description;

  @NotBlank(message = "UnitID cannot be empty")
  public String unitID;
}
