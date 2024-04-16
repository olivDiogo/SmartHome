package smart_home.domain.room;

import smart_home.value_object.*;

/**
 * Implementation of the {@link IRoomFactory} interface, responsible for creating
 * {@link Room} instances. This factory encapsulates the logic for room creation,
 * ensuring that all necessary validations and initializations are performed before
 * a {@link Room} object is returned to the caller.
 */
public class RoomFactoryImpl implements IRoomFactory {
    /**
     * Creates a new {@link Room} instance using the provided house ID, room name, dimension, and room floor.
     * This method ensures that a {@link Room} object is instantiated with valid and non-null
     * parameters, leveraging the Room constructor for validation and initialization.
     *
     * @param houseID   the house ID where the room is located, must not be null
     * @param roomName  the name of the room, must not be null
     * @param dimension the dimension of the room, must not be null
     * @param roomFloor the floor where the room is located, must not be null
     * @return a fully initialized {@link Room} instance
     * @throws IllegalArgumentException if any of the parameters are null, handled by the {@link Room} constructor
     */
    @Override
    public Room createRoom(HouseID houseID, RoomName roomName, Dimension dimension, RoomFloor roomFloor) throws IllegalArgumentException {
        return new Room(houseID, roomName, dimension, roomFloor);
    }

    /**
     * Creates a new {@link Room} instance using the provided house ID, room name, dimension, room floor, and room ID.
     * @param houseID
     * @param roomName
     * @param dimension
     * @param roomFloor
     * @param roomID
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Room createRoom(HouseID houseID, RoomName roomName, Dimension dimension, RoomFloor roomFloor, RoomID roomID) throws IllegalArgumentException {
        return new Room(houseID, roomName, dimension, roomFloor, roomID);
    }
}
