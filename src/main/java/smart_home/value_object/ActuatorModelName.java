package smart_home.value_object;

import smart_home.ddd.IValueObject;

public class ActuatorModelName implements IValueObject{

    private final String _name;

    /**
     * Class constructor.
     *
     * @param name The sensor model name to set.
     */
    public ActuatorModelName(String name) {
        validateActuatorModelName(name);
        this._name = name.trim();
    }

    /**
     * Validates the sensor model name.
     *
     * @param name
     */
    private void validateActuatorModelName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The device name cannot be null, blank, or empty.");
        }

        if (!name.matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException("The device name can only contain letters and numbers.");
        }
    }

    /**
     * Gets the sensor model name.
     *
     * @return
     */
    public String getActuatorModelName() {
        return _name;
    }

    /**
     * Compares this instance with another instance.
     *
     * @param o
     * @return
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object instanceof ActuatorModelName actuatorModelName) {

            return this._name.equals(actuatorModelName._name);
        }
        return false;
    }

    /**
     * @return The hash code of the object.
     */
    @Override
    public int hashCode() {
        return _name.hashCode();
    }

}
