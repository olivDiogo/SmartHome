package smarthome.controller.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import smarthome.domain.value_object.RoomName;
import smarthome.utils.dto.RoomDTO;
import smarthome.utils.dto.RoomDataDTO;

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
}
