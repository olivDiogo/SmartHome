package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.ValueObject;

public class DeviceName implements ValueObject {
   private final String _name;

    /**
     * Constructor of the DeviceName class.
     * @param name The name of the device. Must not be null, empty, or blank.
     */
    public DeviceName(String name) {
        validateDeviceName(name);
        this._name = name.trim();
    }

    /**
     * Sets the device name.
     * @param name The name of the device. Must not be null, empty, or blank.
     */
    private void validateDeviceName(String name) {
        if (name == null || name.isEmpty() || name.isBlank()){
            throw new IllegalArgumentException("The device name cannot be null, blank, or empty.");
        }

        if (!name.matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException("The device name can only contain letters and numbers.");
        }
    }

    /**
     * Gets the name of the device.
     * @return The name of the device.
     */
    public String getName() {
        return _name;
    }

    /**
     * Compares the current object with another object of the same type.
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof DeviceName) {
            DeviceName deviceName = (DeviceName) o;
            if (this._name.equals(deviceName._name))
                return true;
        }
        return false;
    }

    /**
     * Returns the string representation of the object.
      * @return The string representation of the object.
     */
    @Override
    public String toString () {
        return "Device name: " + _name;
    }
}
