package smartHome.controller;

import smartHome.assembler.RoomAssembler;
import smartHome.domain.room.Room;
import smartHome.dto.RoomDTO;
import smartHome.service.RoomService;

import java.util.Collections;
import java.util.List;

public class GetListOfRoomsController {

    private RoomService _roomService;
    private RoomAssembler _roomAssembler;


    /**
     * Constructor for the GetListOfRoomsController class.
     *
     * @param roomService   The room service.
     * @param roomAssembler The room assembler.
     */
    public GetListOfRoomsController(RoomService roomService, RoomAssembler roomAssembler) {
        validateRoomService(roomService);
        validateRoomAssembler(roomAssembler);
    }

    /**
     * Validates the room service.
     *
     * @param roomService The room service.
     */
    private void validateRoomService(RoomService roomService) {
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
    private void validateRoomAssembler(RoomAssembler roomAssembler) {
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

        List<Room> listOfRooms = _roomService.getRooms();
        if (listOfRooms == null || listOfRooms.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if there are no devices.
        }
        List<RoomDTO> listOfRoomsDTO = _roomAssembler.domainToDTO(listOfRooms);

        return List.copyOf(listOfRoomsDTO);
    }
}
