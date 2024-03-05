package SmartHome.controller;

import SmartHome.domain.*;
import SmartHome.dto.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddActuatorToDeviceController {

    private final House _house;
    private final CatalogueActuator _catalogue;
    private Map<RoomDTO, Room> _roomsDTOAndRooms = new HashMap<>();

    private Map<DeviceDTO, Device> _devicesDTOAndDevices = new HashMap<>();

    public AddActuatorToDeviceController(House house, CatalogueActuator catalogue) throws InstantiationException {
        if (!isValidConstructorArguments(house, catalogue))
            throw (new InstantiationException("Invalid arguments"));

        _house = house;
        _catalogue = catalogue;
    }

    private boolean isValidConstructorArguments(House house, CatalogueActuator catalogue) {
        return house != null && catalogue != null;
    }

    public List<RoomDTO> getRooms() {
        List<Room> rooms = _house.getRooms();
        _roomsDTOAndRooms = RoomAssembler.domain2DTOMap(rooms);
        return _roomsDTOAndRooms.keySet().stream().toList();
    }

    public List<DeviceDTO> getDevicesFromRoom(RoomDTO roomDTO) {
        Room room = _roomsDTOAndRooms.get(roomDTO);
        _devicesDTOAndDevices = DeviceAssembler.domain2DTOMapper(room.getDevices());
        List<DeviceDTO> devices = _devicesDTOAndDevices.keySet().stream().toList();
        return devices;
    }

    public List<String> getActuatorModels() {
        return _catalogue.getActuatorModels();
    }

    public ActuatorDTO addActuatorToDevice(DeviceDTO deviceDTO, String strActuatorModel) throws InstantiationException {
        Device device = _devicesDTOAndDevices.get(deviceDTO);
        Actuator actuator = device.addActuator(strActuatorModel, this._catalogue, new ActuatorFactory());
        if (actuator == null) {
            return null;
        }
        return ActuatorAssembler.domain2DTO(actuator);
    }
}
