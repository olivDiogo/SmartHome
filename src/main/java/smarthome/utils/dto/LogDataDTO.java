package smarthome.utils.dto;

import jakarta.validation.constraints.NotBlank;

public class LogDataDTO {

  @NotBlank
  public String deviceID;
  @NotBlank
  public String timeStart;
  @NotBlank
  public String timeEnd;

  /**
   * Constructor of LogDataDTO
   *
   * @param deviceID  is the device ID
   * @param timeStart is the start time
   * @param timeEnd   is the end time
   */
  public LogDataDTO(String deviceID, String timeStart, String timeEnd) {
    this.deviceID = deviceID;
    this.timeStart = timeStart;
    this.timeEnd = timeEnd;
  }
}
