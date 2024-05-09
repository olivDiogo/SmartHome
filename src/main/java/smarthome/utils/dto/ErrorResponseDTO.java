package smarthome.utils.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponseDTO {

  public final int status;
  public final String message;

}
