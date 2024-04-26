package smarthome.domain.room;

import smarthome.ddd.IAggregateRoot;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.utils.Validator;
import smarthome.domain.value_object.*;

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
      Validator.validateNotNull(houseID, "HouseID");
      Validator.validateNotNull(roomName, "RoomName");
      Validator.validateNotNull(dimension, "Dimension");
      Validator.validateNotNull(roomFloor, "RoomFloor");
        generateRoomID();
        _houseID = houseID;
        _roomFloor = roomFloor;
        _roomName = roomName;
        _dimension = dimension;
    }

    /**
     * Constructs a new Room instance with the specified house ID, room name, dimension, room floor, and room ID.
     * @param houseID The house ID where the room is located.
     * @param roomName The name of the room.
     * @param dimension The dimension of the room.
     * @param roomFloor The floor where the room is located.
     * @param roomID The room ID.
     */
    Room(HouseID houseID, RoomName roomName, Dimension dimension, RoomFloor roomFloor, RoomID roomID) {
        Validator.validateNotNull(houseID, "HouseID");
        Validator.validateNotNull(roomName, "RoomName");
        Validator.validateNotNull(dimension, "Dimension");
        Validator.validateNotNull(roomFloor, "RoomFloor");
        Validator.validateNotNull(roomID, "RoomID");
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
