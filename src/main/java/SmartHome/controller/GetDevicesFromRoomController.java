package SmartHome.controller;

import SmartHome.domain.Device;
import SmartHome.domain.House;
import SmartHome.domain.Room;
import SmartHome.dto.DeviceAssembler;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomAssembler;
import SmartHome.dto.RoomDTO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GetDevicesFromRoomController {
    private final House _house;
    private Map<RoomDTO, Room> _roomsDTOAndRooms;

    /**
     * Constructor for GetDevicesFromRoomController.
     *
     * @param house is the house to get the devices from.
     * @throws InstantiationException if the house is null.
     */
    public GetDevicesFromRoomController(House house) throws IllegalArgumentException {
        if (!isValidConstructorArguments(house))
            throw new IllegalArgumentException("Invalid arguments");
        _house = house;
        this._roomsDTOAndRooms = new LinkedHashMap<>();
    }

    /**
     * Checks if the constructor arguments are valid.
     *
     * @param house is the house to get the devices from.
     * @return true if the house is not null, false otherwise.
     */
    private boolean isValidConstructorArguments(House house) {
        return house != null;
    }


    /**
     * Returns a list of rooms from the house.
     *
     * @return a list of rooms from the house.
     */
    public List<RoomDTO> getRooms() {
        List<Room> rooms = _house.getRooms();
        _roomsDTOAndRooms = RoomAssembler.domain2DTOMap(rooms);
        List<RoomDTO> roomsDTO = _roomsDTOAndRooms.keySet().stream().toList();
        return roomsDTO;
    }


    /**
     * Returns a list of devices from a room.
     *
     * @param roomDTO is the room to get the devices from.
     * @return a list of devices from a room.
     */
    public List<DeviceDTO> getDevicesFromRoom(RoomDTO roomDTO) {

        if (_roomsDTOAndRooms.keySet().stream().toList().contains(roomDTO)) {
            Room room = _roomsDTOAndRooms.get(roomDTO);
            List<Device> devices = room.getDevices();
            List<DeviceDTO> devicesDTO = DeviceAssembler.domain2DTO(devices);
            return devicesDTO;
        } else {
            return null;
        }
    }





}