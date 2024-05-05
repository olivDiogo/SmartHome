package smarthome.utils.dto;

import jakarta.validation.constraints.NotBlank;

public class RoomDataDTO {

  @NotBlank
  public final String houseID;

  @NotBlank
  public final String name;

  @NotBlank
  public final int floor;

  @NotBlank
  public final int width;

  @NotBlank
  public final int length;

  @NotBlank
  public final int height;

  /**
   * Constructor
   */
  public RoomDataDTO(String houseID, String name, int floor, int width, int length, int height) {
    this.houseID = houseID;
    this.name = name;
    this.floor = floor;
    this.width = width;
    this.length = length;
    this.height = height;
  }
}
