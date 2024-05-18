/**
 * Class to encode and decode system paths
 */

package smarthome.utils;

import java.util.Base64;

public class PathEncoder {

  public static String encode(String modelPath) {
    Validator.validateNotNull(modelPath, "Model Path");
    return Base64.getEncoder().encodeToString(modelPath.getBytes());
  }

  public static String decode(String token) {
    Validator.validateNotNull(token, "token");
    byte[] decodedBytes = Base64.getDecoder().decode(token);
    return new String(decodedBytes);
  }
}
