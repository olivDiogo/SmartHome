/**
 * This class represents the data transfer object for the device data
 * to be received from the client.
 */

package smarthome.utils.dto.data_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DeviceDataDTO {

  @NotBlank (message = "DeviceTypeID cannot be empty")
  public String deviceTypeDescription;

  @NotBlank (message = "DeviceName cannot be empty")
  public String deviceName;

  @NotBlank (message = "RoomID cannot be empty")
  public String roomID;
}
