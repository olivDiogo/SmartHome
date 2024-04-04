package SmartHomeDDD.valueObject;

import SmartHomeDDD.ddd.ValueObject;

public class SensorName implements ValueObject {
    private final String _name;

    /**
     * Class constructor
     *
     * @param name The sensor name to set.
     */
    public SensorName(String name) {
        validateSensorName(name);
        _name = name.trim();
    }

    /**
     * Sets the sensor name after validating it.
     *
     * @param name The sensor name to set.
     */
    private void validateSensorName(String name) {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("The sensor name cannot be null, blank, or empty.");
        }

        if (!name.matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException("The sensor name can only contain letters and numbers.");
        }
    }

    /**
     * Gets the sensor name.
     *
     * @return The sensor name.
     */
    public String getSensorName() {
        return _name;
    }

    /**
     * Compares this instance with another instance.
     *
     * @param o the other instance to compare with
     * @return true if the instances are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SensorName that = (SensorName) o;

        return _name.equals(that._name);
    }


    /**
     * @return The string representation of the sensor name.
     */
    public String toString() {
        return "SensorName{" +
                "_name='" + _name + '\'' +
                '}';
    }
}
