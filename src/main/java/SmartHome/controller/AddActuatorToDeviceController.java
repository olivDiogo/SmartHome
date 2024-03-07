package SmartHome.controller;

import SmartHome.domain.*;
import SmartHome.dto.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AddActuatorToDeviceController {

    private final House _house;
    private final CatalogueActuator _catalogue;
    private Map<RoomDTO, Room> _roomsDTOAndRooms = new LinkedHashMap<>();
    private Map<DeviceDTO, Device> _devicesDTOAndDevices = new LinkedHashMap<>();

    /**
     * Constructor for AddActuatorToDeviceController.
     *
     * @param house     is the House.
     * @param catalogue is the actuator catalogue.
     * @throws InstantiationException if the House or the catalogue are null.
     */
    public AddActuatorToDeviceController(House house, CatalogueActuator catalogue) throws InstantiationException {
        if (!isValidConstructorArguments(house, catalogue))
            throw (new InstantiationException("Invalid arguments"));

        this._house = house;
        this._catalogue = catalogue;
    }

    /**
     * Validates if the constructor arguments are valid.
     *
     * @param house     is the house.
     * @param catalogue is the actuator catalogue.
     * @return true if the arguments are valid.
     */
    private boolean isValidConstructorArguments(House house, CatalogueActuator catalogue) {
        return house != null && catalogue != null;
    }

    /**
     * Returns a list of rooms from the house.
     *
     * @return a list of rooms from the house.
     */
    public List<RoomDTO> getRooms() {
        List<Room> rooms = _house.getRooms();
        _roomsDTOAndRooms = RoomAssembler.domain2DTOMap(rooms);

        List<RoomDTO> roomsDTOList = _roomsDTOAndRooms.keySet().stream().toList();
        return roomsDTOList;
    }

    /**
     * Returns a list of devices from a room.
     *
     * @param roomDTO is the room to get the devices from.
     * @return a list of devices from a room.
     */
    public List<DeviceDTO> getDevicesFromRoom(RoomDTO roomDTO) {
        Room room = _roomsDTOAndRooms.get(roomDTO);
        _devicesDTOAndDevices = DeviceAssembler.domain2DTOMapper(room.getDevices());

        List<DeviceDTO> devices = _devicesDTOAndDevices.keySet().stream().toList();
        return devices;
    }

    /**
     * Gets a list of actuator models.
     *
     * @return a list of actuator models.
     */
    public List<String> getActuatorModels() {
        return _catalogue.getActuatorModels();
    }

    /**
     * Adds an actuator to a device.
     *
     * @param deviceDTO        is the device to add an actuator.
     * @param strActuatorModel is the actuator model.
     * @return an actuatorDTO.
     * @throws InstantiationException if the actuator object is null.
     */
    public ActuatorDTO addActuatorToDevice(DeviceDTO deviceDTO, String strActuatorModel) throws InstantiationException {
        if (!deviceDTO._status) {
            return null;
        }
        Device device = _devicesDTOAndDevices.get(deviceDTO);
        if (device == null) {
            return null;
        }

        Actuator actuator = device.addActuator(strActuatorModel, this._catalogue, new ActuatorFactory());
        if (actuator == null) {
            return null;
        }

        ActuatorDTO actuatorDTO = ActuatorAssembler.domain2DTO(actuator);
        return actuatorDTO;
    }
}
