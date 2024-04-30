package smarthome.utils;

import java.security.SecureRandom;

/**
 * This class is used to simulate values for testing purposes.
 */
public class ValueSimulator {

  static SecureRandom random = new SecureRandom();

  /**
   * Generates a random value between the lower and upper bond.
   * @param lowerBond the lower bond.
   *                  Should be less than the upper bond.
   * @param upperBond the upper bond.
   *                 Should be greater than the lower bond.
   * @return a random value between the lower and upper bond.
   */
  public static double generateRandomValue(double lowerBond, double upperBond) {
    if (Math.min(lowerBond, upperBond) == upperBond) {
      throw new IllegalArgumentException("Lower bond should be less than upper bond");
    }

    return lowerBond + (upperBond - lowerBond) * random.nextDouble();
  }

  /**
   * Generates a random value between the lower and upper bond.
   * @param lowerBond the lower bond.
   *                  Should be less than the upper bond.
   * @param upperBond the upper bond.
   *                 Should be greater than the lower bond.
   * @return a random value between the lower and upper bond.
   */

  public static int generateRandomValue(int lowerBond, int upperBond) {
    if (Math.min(lowerBond, upperBond) == upperBond) {
      throw new IllegalArgumentException("Lower bond should be less than upper bond");
    }
    return lowerBond + random.nextInt(upperBond - lowerBond);
  }

  /**
   * Generates a random boolean value.
   * @return
   */

  public static boolean generateRandomBoolean() {
    return random.nextBoolean();
  }
}
