package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.DomainID;

public class DeviceID implements DomainID {
    private String _id;

    /**
     * Constructor for DeviceID
     *
     * @param deviceID
     */
    public DeviceID(String deviceID) {
        if (deviceID != null && !deviceID.isBlank() && !deviceID.isEmpty())
            this._id = deviceID.trim();
        else
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
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o instanceof DeviceID) {
            DeviceID objectDeviceID = (DeviceID) o;

            if (this._id.equals(objectDeviceID._id))
                return true;
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
}
