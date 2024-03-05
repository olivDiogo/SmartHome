package SmartHome.sensors;
import SmartHome.domain.Value;

/**
 * Represents a value for a percentage position sensor.
 * This class ensures that the percentage value is within the range of 0 to 100.
 */
public class PercentagePositionSensorValue implements Value, Cloneable {

    private double _percented; // The percentage value

    /**
     * Constructs a PercentagePositionSensorValue with the specified percentage value.
     * @param percented The percentage value to be validated and set.
     * @throws IllegalArgumentException if the specified percentage value is not within the range of 0 to 100.
     */
    public PercentagePositionSensorValue(double percented) {
        validatePositionValue(percented);
    }

    /**
     * Validates and sets the percentage value.
     * @param percented The percentage value to be validated and set.
     * @throws IllegalArgumentException if the specified percentage value is not within the range of 0 to 100.
     */
    private void validatePositionValue(double percented) {
        if (percented < 0 || percented > 100) {
            throw new IllegalArgumentException("Percented value must be between 0 and 100");
        }
        this._percented = percented;
    }

    /**
     * Clones the PercentagePositionSensorValue object.
     * @return A clone of the PercentagePositionSensorValue object.
     */
    @Override
    public PercentagePositionSensorValue clone() {
        try {
            // Call the Object clone() method
            return (PercentagePositionSensorValue) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen since we are Cloneable
            throw new AssertionError();
        }
    }

    /**
     * Returns a string representation of the PercentagePositionSensorValue.
     * @return The percentage value followed by the '%' symbol.
     */
    @Override
    public String toString() {
        return _percented + "%";
    }
}

