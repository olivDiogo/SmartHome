/**
 * This class represents the data transfer object for the log data
 * to be received from the client.
 */

package smarthome.utils.dto.data_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LogDataDTO {

  @NotBlank (message = "DeviceID cannot be empty")
  public String deviceID;

  @NotBlank (message = "LogTypeID cannot be empty")
  public String timeStart;

  @NotBlank (message = "LogTypeID cannot be empty")
  public String timeEnd;

}
