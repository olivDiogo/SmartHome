package smarthome.utils;

import java.security.SecureRandom;

public class ValueSimulator {
    static SecureRandom random = new SecureRandom();

    public static double generateRandomValue(double lowerBond, double upperBond) {
        if (lowerBond > upperBond) {
            throw new IllegalArgumentException("Lower bond should be less than upper bond");
        }

        return lowerBond + (upperBond - lowerBond) * random.nextDouble();
    }
    public static int generateRandomValue(int lowerBond, int upperBond) {
        if (lowerBond > upperBond) {
            throw new IllegalArgumentException("Lower bond should be less than upper bond");
        }
        return lowerBond + random.nextInt(upperBond - lowerBond);
    }
    public static boolean generateRandomBoolean() {
        return random.nextBoolean();
    }
}
