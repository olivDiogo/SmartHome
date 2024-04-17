package smart_home.controller;

import smart_home.assembler.RoomAssembler;
import smart_home.domain.room.Room;
import smart_home.dto.RoomDTO;
import smart_home.service.RoomServiceImpl;
import smart_home.value_object.Dimension;
import smart_home.value_object.HouseID;
import smart_home.value_object.RoomFloor;
import smart_home.value_object.RoomName;


/**
 * Controller class responsible for adding a room to a house.
 */
public class AddRoomToHouseController {

    private final RoomServiceImpl _roomServiceImpl;
    private final RoomAssembler _roomAssembler;

    /**
     * Constructs an AddRoomToHouseController with the specified services.
     *
     * @param roomServiceImpl   The service for managing rooms.
     * @param roomAssembler The assembler for converting between domain and DTO objects.
     */
    public AddRoomToHouseController(RoomServiceImpl roomServiceImpl, RoomAssembler roomAssembler) {
        _roomServiceImpl = roomServiceImpl;
        _roomAssembler = roomAssembler;
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
    public RoomDTO addRoom(String houseID, String name, int floor, int width, int length, int height) {
        HouseID houseID1 = new HouseID(houseID);
        RoomName roomName = new RoomName(name);
        RoomFloor roomFloor = new RoomFloor(floor);
        Dimension dimension = new Dimension(width, length, height);

        Room room = _roomServiceImpl.addRoom(houseID1, roomName, dimension, roomFloor);

        return _roomAssembler.domainToDTO(room);
    }
}

