package smarthome.utils.dto;

public class LogDataDTO {

  public String deviceID;
  public String timeStart;
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
