package SmartHomeDDD.domain.Room;

import SmartHomeDDD.valueObject.*;
import SmartHomeDDD.ddd.AggregateRoot;

import java.util.UUID;

public class Room implements AggregateRoot<RoomID> {
    private HouseID _houseID;
    private RoomID _roomID;
    private RoomName _roomName;
    private Dimension _dimension;
    private RoomFloor _roomFloor;

    /**
     * Constructs a new Room instance with the specified house ID, room name, dimension, and room floor.
     *
     * @param houseID   The house ID where the room is located. Must not be null.
     * @param roomName  The name of the room. Must not be null.
     * @param dimension The dimension of the room. Must not be null.
     * @param roomFloor The floor where the room is located. Must not be null.
     */
     Room(HouseID houseID, RoomName roomName, Dimension dimension, RoomFloor roomFloor) {
         validateHouseID(houseID);
        generateRoomID();
        validateRoomName(roomName);
        validateRoomDimension(dimension);
        validateRoomFloor(roomFloor);
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
        } else {
            _houseID = houseID;
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
        } else {
            _roomName = roomName;
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
        } else {
            _dimension = dimension;
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
        } else {
            _roomFloor = roomFloor;
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
     * @param o The object to compare.
     * @return true if the objects are equal, false if they are different.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Room room = (Room) o;
        return _roomID.toString().equals(room._roomID.toString());
    }

    /**
     * Method to return the values of the object in a string.
     *
     * @return the values of the object in a string.
     */
    @Override
    public String toString() {
        return "Room{" +
                "_roomID=" + _roomID +
                ", _houseID=" + _houseID +
                ", _roomName=" + _roomName +
                ", _dimension=" + _dimension +
                ", _roomFloor=" + _roomFloor +
                '}';
    }

}
