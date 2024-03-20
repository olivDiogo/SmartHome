package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.DomainID;

public class HouseID implements DomainID {
    private String _id;

    /**
     * Constructor for HouseID
     * @param houseID
     */
    public HouseID(String houseID){
        if (houseID != null && !houseID.isBlank() && !houseID.isEmpty())
            this._id = houseID.trim();
        else
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

}
