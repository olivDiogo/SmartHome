package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

/**
 * Data Transfer Object (DTO) representing information about a room.
 */
public class RoomDTO implements DTO {

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


    public String getRoomName() {
        return roomName;
    }

    public String getDimensions() {
        return dimensions;
    }

    public String getFloor() {
        return floor;
    }

    public String getRoomId() {
        return roomId;
    }
}

