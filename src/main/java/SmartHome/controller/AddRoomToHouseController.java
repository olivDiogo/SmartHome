package SmartHome.controller;

import SmartHome.domain.House;
import SmartHome.domain.Room;
import SmartHome.dto.RoomAssembler;
import SmartHome.dto.RoomDTO;

public class AddRoomToHouseController {

    private final House _house;


    public AddRoomToHouseController(House house) throws IllegalArgumentException{
        if(isValidConstructorArguments(house))
            throw new IllegalArgumentException("Arguments cannot be null!");
        _house = house;
    }
    private boolean isValidConstructorArguments(House house) {
        return house == null;
    }

    public RoomDTO  addRoomToHouse(String strName, int nFloor, double dLength, double dWidth, double dHeight) {
        Room room = _house.addRoom(strName, nFloor, dLength, dWidth, dHeight);
        RoomDTO roomDTO = RoomAssembler.domain2DTO(room);
        return roomDTO;
    }

}
