package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.DomainID;

public class SensorTypeID implements DomainID {

    private String _id;

    /**
     * Constructor of the class SensorTypeID.
     *
     * @param sensorTypeID is the ID of the sensor type.
     */
    public SensorTypeID(String sensorTypeID) {
        if (sensorTypeID != null && !sensorTypeID.isBlank() && !sensorTypeID.isEmpty())
            this._id = sensorTypeID;
        else
            throw new IllegalArgumentException("'sensorTypeID' must be a non-empty string.");
    }

    /**
     * Equals method for SensorTypeID.
     *
     * @param object Object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof SensorTypeID) {
            SensorTypeID sensorTypeID = (SensorTypeID) object;

            if (this._id.equals(sensorTypeID._id))
                return true;
        }
        return false;
    }

    /**
     * Getter for ID.
     *
     * @return the ID of the sensor type.
     */
    @Override
    public String getId() {
        return this._id;
    }

    /**
     * HashCode method for SensorTypeID.
     *
     * @return the hashcode as an int.
     */
    public int hashCode() {
        return this._id.hashCode();
    }
}
