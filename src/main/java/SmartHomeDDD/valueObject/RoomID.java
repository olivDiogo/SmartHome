package SmartHomeDDD.valueObject;

import SmartHomeDDD.ddd.DomainID;

public class RoomID implements DomainID {
    private String _id;

    /**
     * Constructor of the class RoomID.
     *
     * @param roomID is the ID of the room.
     */
    public RoomID(String roomID) {
        validateRoomID(roomID);
        this._id = roomID;
    }

    /**
     * Validates the ID of the room.
     * @param roomID is the ID of the room.
     */
    private void validateRoomID (String roomID){
        if (roomID == null || roomID.isBlank() || roomID.isEmpty())
            throw new IllegalArgumentException("'roomID' must be a non-empty string.");
    }

    /**
     * Equals method for RoomID.
     *
     * @param object Object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof RoomID) {
            RoomID roomID = (RoomID) object;

            if (this._id.equals(roomID._id))
                return true;
        }
        return false;
    }

    /**
     * Getter for ID.
     *
     * @return _id.
     */
    @Override
    public String getId() {
        return _id;
    }

    /**
     * HashCode method for RoomID.
     *
     * @return the hashcode as an int.
     */
    public int hashCode() {
        return _id.hashCode();
    }

    /**
     * toString method for RoomID.
     *
     * @return the ID of the room.
     */
    public String toString() {
        return  _id;
    }
}
