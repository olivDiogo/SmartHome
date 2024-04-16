package smartHome.persistence.jpa.dataModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import smartHome.domain.room.Room;

@Entity
public class RoomDataModel {
    @Id
    private String _roomID;
    @Column
    private String _houseID;
    @Column
    private String _roomName;
    @Column
    private int _width;
    @Column
    private int _depth;
    @Column
    private int _height;
    @Column
    private int _floor;

    /**
     * Empty class constructor
     */
    public RoomDataModel() {
    }

    /**
     * Class constructor with parameter room
     *
     * @param room
     */
    public RoomDataModel(Room room){
        validateRoom(room);
        this._roomID = room.getID().getID();
        this._houseID = room.getHouseID().getID();
        this._roomName = room.getRoomName().getRoomName();
        this._width = room.getDimension().getWidth();
        this._depth = room.getDimension().getDepth();
        this._height = room.getDimension().getHeight();
        this._floor = room.getRoomFloor().getFloor();
    }

    /**
     * Method to validate room
     * @param room
     */
    private void validateRoom(Room room){
        if(room == null){
            throw new IllegalArgumentException("Room cannot be null.");
        }
    }

    /**
     * Method to return the room ID.
     * @return
     */
    public String getRoomID() {
        return this._roomID;
    }

    /**
     * Method to return the house ID.
     * @return
     */
    public String getHouseID() {
        return this._houseID;
    }

    /**
     * Method to return the room name.
     * @return
     */
    public String getRoomName() {
        return this._roomName;
    }

    /**
     * Method to return the room width.
     */
    public int getWidth() {
        return this._width;
    }

    /**
     * Method to return the room depth.
     * @return
     */
    public int getDepth() {
        return this._depth;
    }

    /**
     * Method to return the room height.
     * @return
     */
    public int getHeight() {
        return this._height;
    }

    /**
     * Method to return the room floor.
     * @return
     */
    public int getFloor() {
        return this._floor;
    }

}
