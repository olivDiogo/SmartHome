package SmartHome.dto;

import SmartHome.domain.Dimensions;
import SmartHome.domain.Room;

import java.util.UUID;

public class RoomDTO {
    public final String _name;
    public final Dimensions _dimensions;
    public final int _floor;
    public final UUID _roomId;

    /**
     * Constructor for the RoomDTO class.
     *
     * @param room The Room object from which to create the DTO.
     */
    public RoomDTO(Room room) {
        this._name = room.getName();
        this._dimensions = room.getDimensions();
        this._floor = room.getFloor();
        this._roomId = room.getRoomId();
    }

    /**
     * Returns a string representation of the RoomDTO object.
     *
     * @return A string representation of the RoomDTO object.
     */
    @Override
    public String toString() {
        return "Room{" +
                "name='" + _name + '\'' +
                ", dimensions=" + _dimensions.toString() +
                ", floor=" + _floor +
                ", roomId=" + _roomId.toString() +
                '}';
    }


}
