package smarthome.service;

import java.util.List;
import java.util.Optional;
import smarthome.ddd.IRepository;
import smarthome.domain.house.House;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.service.IRoomService;
import smarthome.utils.Validator;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;

public class RoomServiceImpl implements IRoomService {

  private final IRepository<RoomID, Room> roomRepository;
  private final IRoomFactory roomFactory;
  private final IRepository<HouseID, House> houseRepository;

  /**
   * Constructor for RoomService.
   *
   * @param roomRepository  The repository for rooms.
   * @param roomFactory     The factory for creating rooms.
   * @param houseRepository The repository for houses.
   */
  public RoomServiceImpl(IRepository<RoomID, Room> roomRepository, IRoomFactory roomFactory,
      IRepository<HouseID, House> houseRepository) {

    Validator.validateNotNull(roomRepository, "Room repository");
    Validator.validateNotNull(roomFactory, "Room factory");
    Validator.validateNotNull(houseRepository, "House repository");
    this.roomRepository = roomRepository;
    this.roomFactory = roomFactory;
    this.houseRepository = houseRepository;
  }

  /**
   * Adds a new room to the house with the provided house ID.
   *
   * @param houseID   The ID of the house to which the room belongs.
   * @param roomName  The name of the room.
   * @param dimension The dimensions of the room.
   * @param roomFloor The floor of the room.
   * @return The room that was added.
   */
  @Override
  public Room addRoom(HouseID houseID, RoomName roomName, Dimension dimension,
      RoomFloor roomFloor) {
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
