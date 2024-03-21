package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

/**
 * Data Transfer Object (DTO) representing information about a room.
 */
public class RoomDTO implements DTO {

    /**
     * The name of the room.
     */
    public final String _roomName;

    /**
     * The dimensions of the room.
     */
    public final String _dimensions;

    /**
     * The floor of the room.
     */
    public final String _floor;

    /**
     * The unique identifier of the room.
     */
    public final String _roomId;

    /**
     * Constructs a new RoomDTO object with the specified room details.
     *
     * @param roomName      The name of the room.
     * @param roomDimension The dimensions of the room.
     * @param roomFloor     The floor of the room.
     * @param roomID        The unique identifier of the room.
     */
    public RoomDTO(String roomName, String roomDimension, String roomFloor, String roomID) {
        this._roomName = roomName;
        this._dimensions = roomDimension;
        this._floor = roomFloor;
        this._roomId = roomID;
    }


    public String getRoomName() {
        return _roomName;
    }

    public String getDimensions() {
        return _dimensions;
    }

    public String getFloor() {
        return _floor;
    }

    public String getRoomId() {
        return _roomId;
    }
}

