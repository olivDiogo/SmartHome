package smarthome.utils.dto.data_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class DeviceDataDTO {

  @NotBlank (message = "DeviceTypeID cannot be empty")
  public final String deviceTypeID;

  @NotBlank (message = "DeviceName cannot be empty")
  public final String deviceName;

  @NotBlank (message = "DeviceStatus cannot be empty")
  public final boolean deviceStatus;

  @NotBlank (message = "RoomID cannot be empty")
  public final String roomID;
}
