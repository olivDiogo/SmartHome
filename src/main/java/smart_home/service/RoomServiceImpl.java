package smart_home.service;

import smart_home.ddd.IRepository;
import smart_home.domain.house.House;
import smart_home.domain.room.IRoomFactory;
import smart_home.domain.room.Room;
import smart_home.domain.service.IRoomService;
import smart_home.value_object.*;

import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements IRoomService {
    private final IRepository<RoomID, Room> roomRepository;
    private final IRoomFactory roomFactory;
    private final IRepository<HouseID, House> houseRepository;

    /**
     * Constructor for RoomService.
     *
     * @param roomRepository The repository for rooms.
     * @param roomFactory The factory for creating rooms.
     * @param houseRepository The repository for houses.
     */
    public RoomServiceImpl(IRepository<RoomID, Room> roomRepository, IRoomFactory roomFactory,
        IRepository<HouseID, House> houseRepository) {
        this.roomRepository = roomRepository;
        this.roomFactory = roomFactory;
        this.houseRepository = houseRepository;
    }

    /**
     * Adds a new room to the house with the provided house ID.
     *
     * @param houseID The ID of the house to which the room belongs.
     * @param roomName The name of the room.
     * @param dimension The dimensions of the room.
     * @param roomFloor The floor of the room.
     * @return The room that was added.
     */
    @Override
    public Room addRoom(HouseID houseID, RoomName roomName, Dimension dimension, RoomFloor roomFloor) {
        Optional<House> houseOptional = houseRepository.ofIdentity(houseID);
        if (houseOptional.isEmpty()) {
            throw new IllegalArgumentException("House with ID " + houseID + " not found.");
        }

        Room room = roomFactory.createRoom(houseID, roomName, dimension, roomFloor);
        roomRepository.save(room);
        return room;
    }

    /**
     * Returns all the rooms in the repository.
     *
     * @return A list of all rooms.
     */
    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    /**
     * Returns the room with the given id.
     *
     * @param roomID The ID of the room to return.
     * @return The room with the given ID.
     */
    @Override
    public Optional<Room> getRoomById(RoomID roomID) {
        return roomRepository.ofIdentity(roomID);
    }

}
