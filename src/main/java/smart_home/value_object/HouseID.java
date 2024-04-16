package smart_home.value_object;

import smart_home.ddd.IDomainID;

public class HouseID implements IDomainID {
    private final String _id;

    /**
     * Constructor for HouseID
     *
     * @param houseID
     */
    public HouseID(String houseID) {
        validateHouseID(houseID);
        this._id = houseID.trim();
    }

    /**
     * Validates the HouseID
     * It should not be null, blank, or empty
     *
     * @param houseID
     */
    private void validateHouseID(String houseID) {
        if (houseID == null || houseID.isBlank() || houseID.isEmpty())
            throw new IllegalArgumentException("The value of 'houseID' should not null, blank, or empty.");
    }

    /**
     * Getter for ID
     *
     * @return _id
     */
    public String getID() {
        return _id;
    }

    /**
     * Equals method for HouseID
     *
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o instanceof HouseID objectHouseId) {

            return this._id.equals(objectHouseId._id);
        }
        return false;
    }


    /**
     * HashCode method for HouseID
     *
     * @return the hashcode as an int
     */
    public int hashCode() {
        return _id.hashCode();
    }

    /**
     * toString method for HouseID
     *
     * @return the HouseID as a string
     */
    @Override
    public String toString() {
        return _id;
    }
}
