package smarthome.controller;

import smarthome.ddd.IAssembler;
import smarthome.domain.room.Room;
import smarthome.domain.service.IRoomService;
import smarthome.utils.dto.RoomDTO;
import smarthome.utils.Validator;

import java.util.Collections;
import java.util.List;

public class GetListOfRoomsController {

    private final IRoomService _roomService;
    private final IAssembler<Room, RoomDTO> _roomAssembler;


    /**
     * Constructor for the GetListOfRoomsController class.
     *
     * @param roomService   The room service.
     * @param roomAssembler The room assembler.
     */
    public GetListOfRoomsController(IRoomService roomService, IAssembler<Room, RoomDTO> roomAssembler) {
      Validator.validateNotNull(roomService, "Room service");
      Validator.validateNotNull(roomAssembler, "Room assembler");

      this._roomAssembler = roomAssembler;
      this._roomService = roomService;
    }

    /**
     * Gets the list of rooms.
     *
     * @return The list of rooms.
     */
    public List<RoomDTO> getRooms() {

        List<Room> listOfRooms = _roomService.getAllRooms();
        if (listOfRooms == null || listOfRooms.isEmpty()) {
            return Collections.emptyList();
        }
        List<RoomDTO> listOfRoomsDTO = _roomAssembler.domainToDTO(listOfRooms);

        return List.copyOf(listOfRoomsDTO);
    }
}
