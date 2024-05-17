package smarthome.domain.exceptions;

public class ExceptionUtils {
  public static String generateNotFoundMessage(String domainObject, String objectId) {
    return "No " + domainObject + " found with ID " + objectId;
  }
}

