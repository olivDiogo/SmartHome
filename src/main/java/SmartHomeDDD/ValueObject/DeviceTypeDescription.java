package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.ValueObject;

public class DeviceTypeDescription implements ValueObject {
    private final String _description;

    /**
     * Constructor of DeviceTypeDescription
     *
     * @param description The description of the device type
     */
    public DeviceTypeDescription(String description) {
        validate(description);
        this._description = description;
    }

    /**
     * Validates the description
     *
     * @param description The description of the device type
     */
    private void validate(String description) {
        if (description == null || description.isBlank() || description.trim().isEmpty()) {
            throw new IllegalArgumentException("The value of 'description' should not null, blank, or empty.");
        }  else if (!description.matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException("The description can only contain letters and numbers.");
        }
    }

    /**
     * Gets the description of the device type
     *
     * @return The description of the device type
     */
    public String getDescription() {
        return this._description;}

    /**
     * Gets the description of the device type
     *
     * @param o The object to compare with
     * @return True if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o instanceof DeviceTypeDescription) {
            DeviceTypeDescription deviceTypeDescription = (DeviceTypeDescription) o;
            if (this._description.equals(deviceTypeDescription._description))
                return true;
        }
        return false;
    }

    /**
     * Returns the description of the device type
     *
     * @return The description of the device type
     */
    @Override
    public String toString() {
        return this._description;
    }

}
