package SmartHome.controller;

import SmartHome.domain.*;
import SmartHome.dto.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddSensorToDeviceController {
    private final House _house;
    private final CatalogueSensor _catalogue;
    private Map<RoomDTO, Room> _roomsDTOAndRooms = new HashMap<>();

    private Map<DeviceDTO, Device> _devicesDTOAndDevices = new HashMap<>();

    public AddSensorToDeviceController(House house, CatalogueSensor catalogue) throws InstantiationException {
        if (!isValidConstructorArguments(house, catalogue))
            throw (new InstantiationException("Invalid arguments"));

        _house = house;
        _catalogue = catalogue;
    }

    private boolean isValidConstructorArguments(House house, CatalogueSensor catalogue) {
        return house != null && catalogue != null;

        // implement here the rest of validations
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

    public List<String> getSensorsModels() {
        return _catalogue.getSensorModels();
    }

    public SensorDTO addSensorToDevice(DeviceDTO deviceDTO, String strSensorModel) throws InstantiationException {
        Device device = _devicesDTOAndDevices.get(deviceDTO);
        Sensor sensor = device.addSensor(strSensorModel, this._catalogue, new SensorFactory());
        if (sensor == null) {
            return null;
        }
        return SensorAssembler.domain2DTO(sensor);
    }

}