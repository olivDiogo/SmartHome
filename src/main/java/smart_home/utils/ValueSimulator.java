package smart_home.utils;

import java.util.Random;

public class ValueSimulator {
    static Random random = new Random();

    private ValueSimulator() {
    }
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
    public static boolean generateRandomValue() {
        return random.nextBoolean();
    }
}
