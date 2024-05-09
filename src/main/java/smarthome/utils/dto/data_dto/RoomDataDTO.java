/**
 * This class represents the data transfer object for the room data
 * to be received from the client.
 */

package smarthome.utils.dto.data_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoomDataDTO {

  @NotBlank (message = "HouseID cannot be empty")
  public final String houseID;

  @NotBlank (message = "Name cannot be empty")
  public final String name;

  @NotBlank (message = "Floor cannot be empty")
  public final int floor;

  @NotBlank (message = "Width cannot be empty")
  public final int width;

  @NotBlank (message = "Length cannot be empty")
  public final int length;

  @NotBlank (message = "Height cannot be empty")
  public final int height;
}
