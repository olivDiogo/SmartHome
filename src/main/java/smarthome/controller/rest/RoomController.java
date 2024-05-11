package smarthome.controller.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.domain.room.Room;
import smarthome.domain.service.IDeviceService;
import smarthome.domain.service.IRoomService;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.utils.dto.DeviceDTO;
import smarthome.utils.dto.RoomDTO;
import smarthome.utils.dto.data_dto.RoomDataDTO;

@RestController
@RequestMapping("/room")
public class RoomController {

  private final IRoomService roomService;
  private final IAssembler<Room, RoomDTO> roomAssembler;
  private final IDeviceService deviceService;
  private final IAssembler<Device, DeviceDTO> deviceAssembler;

  /**
   * Constructor
   */
  @Autowired
  public RoomController(IRoomService roomService,
      IAssembler<Room, RoomDTO> roomAssembler, IDeviceService deviceService,
      IAssembler<Device, DeviceDTO> deviceAssembler) {
    this.roomAssembler = roomAssembler;
    this.roomService = roomService;
    this.deviceService = deviceService;
    this.deviceAssembler = deviceAssembler;
  }

  /**
   * Adds a new room to the house with the provided house ID.
   *
   * @param data
   * @return The room that was added.
   */
  @PostMapping("/add")
  public ResponseEntity<RoomDTO> addRoom(@Valid @RequestBody RoomDataDTO data) {
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
  public ResponseEntity<List<RoomDTO>> getAllRooms() throws EmptyReturnException {
    List<Room> rooms = roomService.getAllRooms();
    List<RoomDTO> roomDTOs = roomAssembler.domainToDTO(rooms);
    return ResponseEntity.ok(roomDTOs);
  }

  /**
   * Get a room by ID
   *
   * @param idStr is the room ID
   * @return the room with the given ID
   */
  @GetMapping("/{id}")
  public ResponseEntity<RoomDTO> getRoomById(@PathVariable("id") String idStr) {
    RoomID id = new RoomID(idStr);
    Optional<Room> room = roomService.getRoomById(id);
    if (room.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } else {
      RoomDTO roomDTO = roomAssembler.domainToDTO(room.get());
      return ResponseEntity.ok(roomDTO);
    }
  }

  /**
   * Get all devices in a room
   *
   * @param idStr is the room ID
   * @return a list of all devices in the room with the given ID
   */
  @GetMapping("/{id}/devices")
  public ResponseEntity<CollectionModel<DeviceDTO>> getDevicesInAGivenRoom(
      @PathVariable("id") String idStr) {
    RoomID id = new RoomID(idStr);
    roomService.getRoomById(id); // Check if room exists
    List<DeviceDTO> deviceDTOs = deviceService.getDevicesByRoomId(id)
        .stream()
        .map(deviceAssembler::domainToDTO)
        .toList();
    CollectionModel<DeviceDTO> resource = CollectionModel.of(deviceDTOs,
        linkTo(methodOn(RoomController.class).getDevicesInAGivenRoom(idStr)).withSelfRel());
    return ResponseEntity.ok(resource);
  }
}
