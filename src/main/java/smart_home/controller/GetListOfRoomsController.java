package smart_home.controller;

import smart_home.assembler.RoomAssembler;
import smart_home.domain.room.Room;
import smart_home.dto.RoomDTO;
import smart_home.service.RoomServiceImpl;

import java.util.Collections;
import java.util.List;

public class GetListOfRoomsController {

    private RoomServiceImpl _roomServiceImpl;
    private RoomAssembler _roomAssembler;


    /**
     * Constructor for the GetListOfRoomsController class.
     *
     * @param roomServiceImpl   The room service.
     * @param roomAssembler The room assembler.
     */
    public GetListOfRoomsController(RoomServiceImpl roomServiceImpl, RoomAssembler roomAssembler) {
        validateRoomService(roomServiceImpl);
        validateRoomAssembler(roomAssembler);
    }

    /**
     * Validates the room service.
     *
     * @param roomServiceImpl The room service.
     */
    private void validateRoomService(RoomServiceImpl roomServiceImpl) {
        if (roomServiceImpl == null) {
            throw new IllegalArgumentException("Please enter a valid room service.");
        } else {
            this._roomServiceImpl = roomServiceImpl;
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

        List<Room> listOfRooms = _roomServiceImpl.getAllRooms();
        if (listOfRooms == null || listOfRooms.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if there are no devices.
        }
        List<RoomDTO> listOfRoomsDTO = _roomAssembler.domainToDTO(listOfRooms);

        return List.copyOf(listOfRoomsDTO);
    }
}
