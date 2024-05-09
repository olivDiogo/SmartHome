/**
 * This class represents the data transfer object for the Unit data
 * to be received from the client.
 */

package smarthome.utils.dto.data_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UnitDataDTO {

  @NotBlank (message = "Description cannot be empty")
  public String description;

  @NotBlank (message = "UnitSymbol cannot be empty")
  public String unitSymbol;
}
