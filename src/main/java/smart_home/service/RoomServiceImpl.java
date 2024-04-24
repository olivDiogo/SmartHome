package smart_home.service;

import smart_home.ddd.IAssembler;
import smart_home.ddd.IRepository;
import smart_home.domain.house.House;
import smart_home.domain.room.IRoomFactory;
import smart_home.domain.room.Room;
import smart_home.domain.service.IRoomService;
import smart_home.dto.RoomDTO;
import smart_home.value_object.*;

import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements IRoomService {
    private final IRepository<RoomID, Room> _roomRepository;
    private final IRoomFactory _roomFactory;
    private final IRepository<HouseID, House> _houseRepository;

    /**
     * Constructor for RoomService.
     *
     * @param roomRepository
     * @param roomFactory
     * @param houseRepository
     */
    public RoomServiceImpl(IRepository<RoomID, Room> roomRepository, IRoomFactory roomFactory,
        IRepository<HouseID, House> houseRepository) {
        _roomRepository = roomRepository;
        _roomFactory = roomFactory;
        _houseRepository = houseRepository;
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
        Optional<House> houseOptional = _houseRepository.ofIdentity(houseID);
        if (houseOptional.isEmpty()) {
            throw new IllegalArgumentException("House with ID " + houseID + " not found.");
        }

        Room room = _roomFactory.createRoom(houseID, roomName, dimension, roomFloor);
        _roomRepository.save(room);
        return room;
    }

    /**
     * Returns all the rooms in the repository.
     *
     * @return A list of all rooms.
     */
    @Override
    public List<Room> getAllRooms() {
        return _roomRepository.findAll();
    }

    /**
     * Returns the room with the given id.
     *
     * @param roomID The ID of the room to return.
     * @return The room with the given ID.
     */
    @Override
    public Optional<Room> getRoomById(RoomID roomID) {
        return _roomRepository.ofIdentity(roomID);
    }

}
