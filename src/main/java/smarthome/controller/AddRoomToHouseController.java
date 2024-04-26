package smarthome.controller;

import smarthome.ddd.IAssembler;
import smarthome.domain.room.Room;
import smarthome.domain.service.IRoomService;
import smarthome.utils.dto.RoomDTO;
import smarthome.utils.Validator;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomName;


/**
 * Controller class responsible for adding a room to a house.
 */
public class AddRoomToHouseController {

  private final IRoomService _roomService;
  private final IAssembler<Room, RoomDTO> _roomAssembler;

  /**
   * Constructs an AddRoomToHouseController with the specified services.
   *
   * @param roomService   The service for managing rooms.
   * @param roomAssembler The assembler for converting between domain and DTO objects.
   */
  public AddRoomToHouseController(IRoomService roomService,
      IAssembler<Room, RoomDTO> roomAssembler) {
    Validator.validateNotNull(roomService, "Room service");
    Validator.validateNotNull(roomAssembler, "Room assembler");

    this._roomService = roomService;
    this._roomAssembler = roomAssembler;

  }

  /**
   * Adds a room to the specified house.
   *
   * @param houseID The ID of the house to which the room will be added.
   * @param name    The name of the room.
   * @param floor   The floor number where the room is located.
   * @param width   The width of the room.
   * @param length  The length of the room.
   * @param height  The height of the room.
   * @return The DTO representation of the added room.
   */
  public RoomDTO addRoom(String houseID, String name, int floor, int width, int length,
      int height) {
    HouseID houseID1 = new HouseID(houseID);
    RoomName roomName = new RoomName(name);
    RoomFloor roomFloor = new RoomFloor(floor);
    Dimension dimension = new Dimension(width, length, height);

    Room room = _roomService.addRoom(houseID1, roomName, dimension, roomFloor);

    return _roomAssembler.domainToDTO(room);
  }
}

