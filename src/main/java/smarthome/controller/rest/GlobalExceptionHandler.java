package smarthome.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import smarthome.utils.dto.ErrorResponseDTO;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handle IllegalArgumentException
   * @param ex
   * @return
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
    ErrorResponseDTO errorResponse = new ErrorResponseDTO(
        HttpStatus.BAD_REQUEST.value(),
        ex.getMessage());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}