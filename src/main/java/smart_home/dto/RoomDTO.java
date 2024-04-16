package smart_home.dto;

import smart_home.ddd.IDTO;

/**
 * Data Transfer Object (DTO) representing information about a room.
 */
public class RoomDTO implements IDTO {

    /**
     * The name of the room.
     */
    public final String roomName;

    /**
     * The dimensions of the room.
     */
    public final String dimensions;

    /**
     * The floor of the room.
     */
    public final String floor;

    /**
     * The unique identifier of the room.
     */
    public final String roomId;

    /**
     * Constructs a new RoomDTO object with the specified room details.
     *
     * @param roomName      The name of the room.
     * @param roomDimension The dimensions of the room.
     * @param roomFloor     The floor of the room.
     * @param roomID        The unique identifier of the room.
     */
    public RoomDTO(String roomName, String roomDimension, String roomFloor, String roomID) {
        this.roomName = roomName;
        this.dimensions = roomDimension;
        this.floor = roomFloor;
        this.roomId = roomID;
    }

    /**
     * Returns a string representation of the RoomDTO object.
     *
     * @return A string representation of the RoomDTO object.
     */
    @Override
    public String toString() {
        return roomName + ' ' + dimensions + ' ' + floor + ' ' + roomId;
    }
}
