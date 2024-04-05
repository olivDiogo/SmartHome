package SmartHomeDDD.service;

import SmartHomeDDD.assembler.RoomAssembler;
import SmartHomeDDD.domain.House.House;
import SmartHomeDDD.domain.Room.Room;
import SmartHomeDDD.domain.Room.IRoomFactory;
import SmartHomeDDD.repository.HouseRepository;
import SmartHomeDDD.repository.RoomRepository;
import SmartHomeDDD.valueObject.*;

import java.util.List;
import java.util.Optional;

public class RoomService {
    private final RoomRepository _roomRepository;
    private final IRoomFactory _roomFactory;
    private final RoomAssembler _roomAssembler;
    private final HouseRepository _houseRepository;

    /**
     * Constructor for RoomService.
     *
     * @param roomRepository
     * @param roomFactory
     * @param roomAssembler
     * @param houseRepository
     */
    public RoomService(RoomRepository roomRepository, IRoomFactory roomFactory, RoomAssembler roomAssembler, HouseRepository houseRepository) {
        _roomRepository = roomRepository;
        _roomFactory = roomFactory;
        _roomAssembler = roomAssembler;
        _houseRepository = houseRepository;
    }

    /**
     * Adds a new room to the house with the provided house ID.
     *
     * @param houseID
     * @param roomName
     * @param dimension
     * @param roomFloor
     * @return
     */
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
     * @return
     */
    public List<Room> getRooms() {
        return _roomRepository.findAll();
    }

    /**
     * Returns the room with the given id.
     *
     * @param roomID
     * @return
     */
    public Optional<Room> getRoomById(RoomID roomID) {
        return _roomRepository.ofIdentity(roomID);
    }

}
