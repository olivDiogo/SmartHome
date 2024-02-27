package SmartHome.controller;

import SmartHome.domain.Device;
import SmartHome.domain.DeviceFactory;
import SmartHome.domain.House;
import SmartHome.domain.Room;
import SmartHome.dto.DeviceAssembler;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomAssembler;
import SmartHome.dto.RoomDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AddDeviceToRoomController {

    private House _house;
    private Map<RoomDTO, Room> _roomsDTOAndRooms;

    public AddDeviceToRoomController(House house) throws IllegalArgumentException{
        if (isValidConstructorArguments(house))
            throw new IllegalArgumentException("Arguments cannot be null!");
        this._house = house;
    }

    private boolean isValidConstructorArguments(House house) {
        return house == null;
    }
    public List<RoomDTO> getRoomList() {
        List<Room> allRooms = _house.getRooms();

        _roomsDTOAndRooms = RoomAssembler.domain2DTOMap(allRooms);

        return List.copyOf(_roomsDTOAndRooms.keySet().stream().toList());
    }

    public Optional<DeviceDTO> addDeviceToRoom(RoomDTO roomDTO, String deviceName) {
        Optional<Room> room = Optional.ofNullable(_roomsDTOAndRooms.get(roomDTO));

        if (room.isPresent()) {
            try {
                Device device = room.get().addDevice(deviceName, new DeviceFactory());
                return Optional.of(DeviceAssembler.domain2DTO(device));
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
