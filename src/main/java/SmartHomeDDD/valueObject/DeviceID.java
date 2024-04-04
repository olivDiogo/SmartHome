package SmartHomeDDD.valueObject;

import SmartHomeDDD.ddd.DomainID;

public class DeviceID implements DomainID {
    private final String _id;

    /**
     * Constructor for DeviceID
     *
     * @param deviceID
     */
    public DeviceID(String deviceID) {
        validateDeviceID(deviceID);
        this._id = deviceID.trim();
    }

    /**
     * Validates the DeviceID. It should not be null, blank, or empty.
     *
     * @param deviceID String
     */
    private void validateDeviceID(String deviceID) {
        if (deviceID == null || deviceID.isBlank() || deviceID.isEmpty())
            throw new IllegalArgumentException("The value of 'deviceID' should not null, blank, or empty.");
    }

    /**
     * Getter for ID
     *
     * @return id
     */
    public String getId() {
        return _id;
    }

    /**
     * Equals method for DeviceID
     *
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o instanceof DeviceID objectDeviceID) {

            return this._id.equals(objectDeviceID._id);
        }
        return false;
    }

    /**
     * HashCode method for DeviceID
     *
     * @return the hashcode as an int
     */
    public int hashCode() {
        return _id.hashCode();
    }

    /**
     * toString method for DeviceID
     *
     * @return the id as a string
     */
    @Override
    public String toString() {
        return _id;
    }
}
