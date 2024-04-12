package smartHome.valueObject;

import smartHome.ddd.IDomainID;

public class DeviceTypeID implements IDomainID {

    private final String id;

    /**
     * Class constructor
     *
     * @param deviceTypeID The device type ID to set.
     */

    public DeviceTypeID(String deviceTypeID) {
        validateId(deviceTypeID);
        this.id = deviceTypeID;
    }

    private void validateId(String deviceTypeID) {
        if (deviceTypeID == null || deviceTypeID.isBlank() || deviceTypeID.isEmpty())
            throw new IllegalArgumentException("'deviceTypeID' must be a non-empty string.");
    }


    /**
     * Compares this instance with another instance.
     *
     * @param object the other instance to compare with
     * @return true if the instances are equal, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof DeviceTypeID that)) return false;
        return id.equals(that.id);
    }

    /**
     * Gets the device type ID.
     *
     * @return The device type ID.
     */

    public String getID() {
        return id;
    }


    /**
     * @return The hash code of the device type ID.
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * @return The string representation of the device type ID.
     */

    @Override
    public String toString() {
        return id;
    }
}
