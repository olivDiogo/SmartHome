package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.DomainID;

public class SensorID implements DomainID {
    private String _id;

    /**
     * Constructor for SensorID
     *
     * @param sensorID
     */
    public SensorID(String sensorID) {
        if (sensorID != null && !sensorID.isBlank() && !sensorID.isEmpty())
            this._id = sensorID.trim();
        else
            throw new IllegalArgumentException("The value of 'sensorID' should not null, blank, or empty.");
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
     * Equals method for SensorID
     *
     * @param o Object
     * @return boolean
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o instanceof SensorID) {
            SensorID objectSensorID = (SensorID) o;

            if (this._id.equals(objectSensorID._id))
                return true;
        }
        return false;
    }

    /**
     * HashCode method for SensorID
     *
     * @return the hashcode as an int
     */
    public int hashCode() {
        return _id.hashCode();
    }
}
