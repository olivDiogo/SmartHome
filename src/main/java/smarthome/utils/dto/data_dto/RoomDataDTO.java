/**
 * This class represents the data transfer object for the room data
 * to be received from the client.
 */

package smarthome.utils.dto.data_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RoomDataDTO {

  @NotBlank (message = "Name cannot be empty")
  public String name;

  @NotBlank (message = "Floor cannot be empty")
  public int floor;

  @NotBlank (message = "Width cannot be empty")
  public int width;

  @NotBlank (message = "Length cannot be empty")
  public int length;

  @NotBlank (message = "Height cannot be empty")
  public int height;
}
