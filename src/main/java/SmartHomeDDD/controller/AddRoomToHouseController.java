package SmartHomeDDD.controller;

import SmartHomeDDD.DTO.RoomDTO;
import SmartHomeDDD.assembler.RoomAssembler;
import SmartHomeDDD.domain.Room.Room;
import SmartHomeDDD.service.RoomService;
import SmartHomeDDD.valueObject.Dimension;
import SmartHomeDDD.valueObject.HouseID;
import SmartHomeDDD.valueObject.RoomFloor;
import SmartHomeDDD.valueObject.RoomName;


/**
 * Controller class responsible for adding a room to a house.
 */
public class AddRoomToHouseController {

    private final RoomService _roomService;
    private final RoomAssembler _roomAssembler;

    /**
     * Constructs an AddRoomToHouseController with the specified services.
     *
     * @param roomService   The service for managing rooms.
     * @param roomAssembler The assembler for converting between domain and DTO objects.
     */
    public AddRoomToHouseController(RoomService roomService, RoomAssembler roomAssembler) {
        _roomService = roomService;
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

        Room room = _roomService.addRoom(houseID1, roomName, dimension, roomFloor);

        return _roomAssembler.domainToDTO(room);
    }
}

