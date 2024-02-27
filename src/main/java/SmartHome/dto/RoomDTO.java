package SmartHome.dto;

import SmartHome.domain.Dimensions;
import SmartHome.domain.Room;

import java.util.UUID;

public class RoomDTO {
    public final String _name;
    public final Dimensions _dimensions;
    public final int _floor;
    private final UUID _roomId;

    public RoomDTO(Room room)
    {
        this._name = room.getName();
        this._dimensions = room.getDimensions();
        this._floor = room.getFloor();
        this._roomId = room.getRoomId();
    }

    public String getName() {
        return _name;
    }

    public Dimensions getDimensions() {
        return _dimensions;
    }

    public int getFloor() {
        return _floor;
    }

    public UUID getRoomId() {
        return _roomId;
    }


}
