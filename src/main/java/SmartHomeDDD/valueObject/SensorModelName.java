package SmartHomeDDD.valueObject;

public class SensorModelName {

    private String _name;

    /**
     * Class constructor.
     * @param name The sensor model name to set.
     */
    public SensorModelName(String name) {
        validateSensorModelName(name);
        this._name = name.trim();
    }

    /**
     * Validates the sensor model name.
     * @param name
     */
    private void validateSensorModelName(String name) {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("The device name cannot be null, blank, or empty.");
        }

        if (!name.matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException("The device name can only contain letters and numbers.");
        }
    }

    /**
     * Gets the sensor model name.
     * @return
     */
    public String getSensorModelName() {
        return _name;
    }

    /**
     * Compares this instance with another instance.
     * @param o
     * @return
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SensorModelName that = (SensorModelName) o;

        return _name.equals(that._name);
    }
}
