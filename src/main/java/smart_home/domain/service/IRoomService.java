package smart_home.domain.service;

import smart_home.ddd.IService;
import smart_home.domain.room.Room;
import smart_home.value_object.*;

import java.util.List;
import java.util.Optional;

public interface IRoomService extends IService {
    /**
     * Adds a new room to the house with the provided house ID.
     *
     * @param houseID
     * @param roomName
     * @param dimension
     * @param roomFloor
     * @return the room that was added.
     */
    Room addRoom(HouseID houseID, RoomName roomName, Dimension dimension, RoomFloor roomFloor);

    /**
     * Returns all the rooms in the repository.
     *
     * @return a list of all rooms.
     */
    List<Room> getAllRooms();

    /**
     * Returns the room with the provided room ID.
     *
     * @param roomID
     * @return the room with the provided room ID.
     */
    Optional<Room> getRoomById(RoomID roomID);
}
