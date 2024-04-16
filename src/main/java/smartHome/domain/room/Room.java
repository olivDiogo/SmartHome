package smartHome.domain.room;

import smartHome.ddd.IAggregateRoot;
import smartHome.valueObject.*;

import java.util.UUID;

public class Room implements IAggregateRoot<RoomID> {
    private final HouseID _houseID;
    private final RoomFloor _roomFloor;
    private RoomID _roomID;
    private final RoomName _roomName;
    private final Dimension _dimension;

    /**
     * Constructs a new Room instance with the specified house ID, room name, dimension, and room
     * floor.
     *
     * @param houseID   The house ID where the room is located. Must not be null.
     * @param roomName  The name of the room. Must not be null.
     * @param dimension The dimension of the room. Must not be null.
     * @param roomFloor The floor where the room is located. Must not be null.
     */
    Room(HouseID houseID, RoomName roomName, Dimension dimension, RoomFloor roomFloor) {
        validateHouseID(houseID);
        validateRoomName(roomName);
        validateRoomDimension(dimension);
        validateRoomFloor(roomFloor);
        generateRoomID();
        _houseID = houseID;
        _roomFloor = roomFloor;
        _roomName = roomName;
        _dimension = dimension;
    }

    /**
     * Constructs a new Room instance with the specified house ID, room name, dimension, room floor, and room ID.
     * @param houseID
     * @param roomName
     * @param dimension
     * @param roomFloor
     * @param roomID
     */
    Room(HouseID houseID, RoomName roomName, Dimension dimension, RoomFloor roomFloor, RoomID roomID) {
        validateHouseID(houseID);
        validateRoomID(roomID);
        validateRoomName(roomName);
        validateRoomDimension(dimension);
        validateRoomFloor(roomFloor);
        _roomID = roomID;
        _houseID = houseID;
        _roomFloor = roomFloor;
        _roomName = roomName;
        _dimension = dimension;
    }

    /**
     * Generates a new RoomID object.
     */
    private void generateRoomID() {
        _roomID = new RoomID(UUID.randomUUID().toString());
    }

    /**
     * Validates the provided HouseID object.
     *
     * @param houseID The HouseID to be validated.
     */
    private void validateHouseID(HouseID houseID) {
        if (houseID == null) {
            throw new IllegalArgumentException("HouseID is required");
        }
    }

    /**
     * Validates the provided RoomID object.
     */
    private void validateRoomID(RoomID roomID) {
        if (roomID == null) {
            throw new IllegalArgumentException("RoomID is required");
        }
    }

    /**
     * Validates the provided RoomName object.
     *
     * @param roomName The RoomName to be validated.
     */
    private void validateRoomName(RoomName roomName) {
        if (roomName == null) {
            throw new IllegalArgumentException("RoomName is required");
        }
    }

    /**
     * Validates the provided Dimension object.
     *
     * @param dimension The Dimension to be validated.
     */
    private void validateRoomDimension(Dimension dimension) {
        if (dimension == null) {
            throw new IllegalArgumentException("Dimension is required");
        }
    }

    /**
     * Validates the provided RoomFloor object.
     *
     * @param roomFloor The RoomFloor to be validated.
     */
    private void validateRoomFloor(RoomFloor roomFloor) {
        if (roomFloor == null) {
            throw new IllegalArgumentException("RoomFloor is required");
        }
    }

    /**
     * Method to return the room ID.
     *
     * @return the room ID.
     */
    @Override
    public RoomID getID() {
        return _roomID;
    }

    /**
     * Method to return the house ID.
     *
     * @return the house ID.
     */
    public HouseID getHouseID() {
        return _houseID;
    }

    /**
     * Method to return the room name.
     *
     * @return the room name.
     */
    public RoomName getRoomName() {
        return _roomName;
    }

    /**
     * Method to return the room dimension.
     *
     * @return the room dimension.
     */
    public Dimension getDimension() {
        return _dimension;
    }

    /**
     * Method to return the room floor.
     *
     * @return the room floor.
     */
    public RoomFloor getRoomFloor() {
        return _roomFloor;
    }

    /**
     * Checks if this Room instance is equal to another object.
     *
     * @param object The object to compare.
     * @return true if the objects are equal, false if they are different.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Room) {
            Room room = (Room) object;
            return _roomID.equals(room._roomID);
        }
        return false;
    }

    /**
     * Method to return the values of the object in a string.
     *
     * @return the values of the object in a string.
     */
    @Override
    public String toString() {
        return "Room:" + "houseID= " + _houseID + ", roomID= " + _roomID + ", roomName= " + _roomName + ", dimension= " + _dimension + ", roomFloor= " + _roomFloor;
    }

    /**
     * Method to return the hash code of the object.
     *
     * @return the hash code of the object.
     */
    public int hashCode() {
        return _roomID.hashCode();
    }
}
