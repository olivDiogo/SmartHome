package smarthome.utils.dto;

public class ErrorResponseDTO {

  public final int status;
  public final String message;

  /**
   * Constructor for ErrorResponse
   *
   * @param status  is the status
   * @param message is the message
   */
  public ErrorResponseDTO(int status, String message) {
    this.status = status;
    this.message = message;
  }
}
