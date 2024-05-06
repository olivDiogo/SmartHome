package smarthome.controller.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.room.Room;
import smarthome.domain.service.IRoomService;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.utils.dto.RoomDTO;
import smarthome.utils.dto.RoomDataDTO;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

  private final IRoomService roomService;
  private final IAssembler<Room, RoomDTO> roomAssembler;

  /**
   * Constructor
   */
  @Autowired
  public RoomController(IRoomService roomService,
      IAssembler<Room, RoomDTO> roomAssembler) {
    this.roomAssembler = roomAssembler;
    this.roomService = roomService;
  }

  /**
   * Adds a new room to the house with the provided house ID.
   *
   * @param data
   * @return The room that was added.
   */
  @PostMapping("/add")
  public ResponseEntity<RoomDTO> addRoom(@Valid @RequestBody RoomDataDTO data){
    HouseID houseID = new HouseID(data.houseID);
    RoomName name = new RoomName(data.name);
    RoomFloor floor = new RoomFloor(data.floor);
    Dimension dimension = new Dimension(data.width, data.length, data.height);
    Room room = roomService.addRoom(houseID, name, dimension, floor);
    RoomDTO roomDTO = roomAssembler.domainToDTO(room);
    return ResponseEntity.status(HttpStatus.CREATED).body(roomDTO);
  }

  /**
   * Get all rooms
   *
   * @return a list of all rooms
   */
  @GetMapping("/all")
  public ResponseEntity<List<RoomDTO>> getAllRooms() {
    List<Room> rooms = roomService.getAllRooms();
    List<RoomDTO> roomDTOs = roomAssembler.domainToDTO(rooms);
    return ResponseEntity.status(HttpStatus.CREATED).body(roomDTOs);
  }

  /**
   * Get a room by ID
   *
   * @param idStr
   * @return the room with the given ID
   */
  @GetMapping("/{id}")
  public ResponseEntity<RoomDTO> getRoomById(@PathVariable("id") String idStr) {
    RoomID id = new RoomID(idStr);
    Optional<Room> room = roomService.getRoomById(id);
    if (room.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    else {
      RoomDTO roomDTO = roomAssembler.domainToDTO(room.get());
      return ResponseEntity.status(HttpStatus.CREATED).body(roomDTO);
    }
  }
}
