package SmartHome.controller;

import SmartHome.domain.House;
import SmartHome.domain.Room;
import SmartHome.dto.RoomAssembler;
import SmartHome.dto.RoomDTO;

import java.util.List;

public class GetListOfRoomsController {

    private final House _house;

    /**
     * Constructor of the GetListOfRoomsController
     * @param house
     * @throws InstantiationException if the house is null
     */
    public GetListOfRoomsController(House house) throws InstantiationException {
        if( !isValidConstructorArguments(house) )
            throw( new InstantiationException("Invalid arguments"));

        _house = house;
    }

    /**
     * Check if the constructor arguments are valid
     * @param house
     * @return true if the house is not null
     */
    private boolean isValidConstructorArguments(House house){
        return house != null;
    }

    /**
     * Get the list of rooms in the house
     * @return Copy of List RoomDTOs
     */
    public List<RoomDTO> getRooms() {
        List<Room> rooms = _house.getRooms();
        List<RoomDTO> roomDTOS = List.copyOf(RoomAssembler.domain2DTO(rooms));
        return roomDTOS;
    }

}
