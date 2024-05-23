package smarthome.domain.exceptions;

public class NoLogRecordsFoundException extends RuntimeException {

  public NoLogRecordsFoundException(String message) {
    super(message);
  }
}
