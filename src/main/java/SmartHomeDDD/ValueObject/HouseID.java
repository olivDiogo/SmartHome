package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.DomainID;

public class HouseID implements DomainID {
    private String _id;

    /**
     * Constructor for HouseID
     * @param houseID
     */
    public HouseID(String houseID){
        validateHouseID(houseID);
        this._id = houseID.trim();
    }

    /**
     * Validates the HouseID
     * It should not be null, blank, or empty
     *
     * @param houseID
     */
    private void validateHouseID(String houseID){
        if (houseID == null || houseID.isBlank() || houseID.isEmpty())
            throw new IllegalArgumentException("The value of 'houseID' should not null, blank, or empty.");
    }

    /**
     * Getter for ID
     * @return _id
     */
    public String getId() {
        return _id;
    }

    /**
     * Equals method for HouseID
     *
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;

        if (o instanceof HouseID) {
            HouseID objectHouseId = (HouseID) o;

            if (this._id.equals(objectHouseId._id))
                return true;
        }
        return false;
    }


    /**
     * HashCode method for HouseID
     *
     * @return the hashcode as an int
     */
    public int hashCode(){
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
