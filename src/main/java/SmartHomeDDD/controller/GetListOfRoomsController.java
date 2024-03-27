package SmartHomeDDD.controller;

import SmartHomeDDD.DTO.RoomDTO;
import SmartHomeDDD.assembler.RoomAssembler;
import SmartHomeDDD.domain.Room.Room;
import SmartHomeDDD.service.RoomService;

import java.util.Collections;
import java.util.List;

public class GetListOfRoomsController {

    private RoomService _roomService;
    private RoomAssembler _roomAssembler;


    public GetListOfRoomsController(RoomService roomService, RoomAssembler roomAssembler) {
        this._roomService = roomService;
        this._roomAssembler = roomAssembler;
    }

    public List<RoomDTO> getRooms(){

        List<Room> listOfRooms = _roomService.getRooms();
        if (listOfRooms == null || listOfRooms.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if there are no devices.
        }
        List<RoomDTO> listOfRoomsDTO = _roomAssembler.domainToDTO(listOfRooms);

        return List.copyOf(listOfRoomsDTO);
    }
}
