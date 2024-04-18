package smart_home.controller;

import smart_home.assembler.RoomAssembler;
import smart_home.ddd.IAssembler;
import smart_home.domain.room.Room;
import smart_home.domain.service.IRoomService;
import smart_home.dto.RoomDTO;

import java.util.Collections;
import java.util.List;

public class GetListOfRoomsController {

    private IRoomService _roomService;
    private IAssembler<Room, RoomDTO> _roomAssembler;


    /**
     * Constructor for the GetListOfRoomsController class.
     *
     * @param roomService   The room service.
     * @param roomAssembler The room assembler.
     */
    public GetListOfRoomsController(IRoomService roomService, IAssembler<Room, RoomDTO> roomAssembler) {
        validateRoomService(roomService);
        validateRoomAssembler(roomAssembler);
    }

    /**
     * Validates the room service.
     *
     * @param roomService The room service.
     */
    private void validateRoomService(IRoomService roomService) {
        if (roomService == null) {
            throw new IllegalArgumentException("Please enter a valid room service.");
        } else {
            this._roomService = roomService;
        }
    }

    /**
     * Validates the room assembler.
     *
     * @param roomAssembler The room assembler.
     */
    private void validateRoomAssembler(IAssembler<Room, RoomDTO> roomAssembler) {
        if (roomAssembler == null) {
            throw new IllegalArgumentException("Please enter a valid room assembler.");
        } else {
            this._roomAssembler = roomAssembler;
        }
    }

    /**
     * Gets the list of rooms.
     *
     * @return The list of rooms.
     */
    public List<RoomDTO> getRooms() {

        List<Room> listOfRooms = _roomService.getAllRooms();
        if (listOfRooms == null || listOfRooms.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if there are no devices.
        }
        List<RoomDTO> listOfRoomsDTO = _roomAssembler.domainToDTO(listOfRooms);

        return List.copyOf(listOfRoomsDTO);
    }
}
