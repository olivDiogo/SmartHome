package SmartHomeDDD.controller;

import SmartHomeDDD.DTO.DeviceDTO;
import SmartHomeDDD.DTO.RoomDTO;
import SmartHomeDDD.assembler.DeviceAssembler;
import SmartHomeDDD.assembler.RoomAssembler;
import SmartHomeDDD.domain.Device.Device;
import SmartHomeDDD.domain.Room.Room;
import SmartHomeDDD.service.DeviceService;
import SmartHomeDDD.service.RoomService;
import SmartHomeDDD.valueObject.DeviceName;
import SmartHomeDDD.valueObject.DeviceStatus;
import SmartHomeDDD.valueObject.DeviceTypeID;
import SmartHomeDDD.valueObject.RoomID;

import java.util.List;
import java.util.Optional;

/**
 * Controller class responsible for handling device addition to rooms within a smart home domain.
 */
public class AddDeviceToRoomController {

    private RoomService _roomService;
    private RoomAssembler _roomAssembler;
    private DeviceService _deviceService;
    private DeviceAssembler _deviceAssembler;

    /**
     * Constructs a new AddDeviceToRoomController with necessary service and assembler dependencies.
     *
     * @param roomService    Service for managing room-related operations.
     * @param roomAssembler  Assembler for converting room entities to DTOs.
     * @param deviceService  Service for managing device-related operations.
     * @param deviceAssembler Assembler for converting device entities to DTOs.
     */
    public AddDeviceToRoomController(RoomService roomService, RoomAssembler roomAssembler, DeviceService deviceService, DeviceAssembler deviceAssembler) {
        _roomService = roomService;
        _roomAssembler = roomAssembler;
        _deviceService = deviceService;
        _deviceAssembler = deviceAssembler;
    }

    /**
     * Retrieves all rooms as a list of RoomDTOs.
     *
     * @return a list of RoomDTOs.
     */
    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = _roomService.getRooms();
        return _roomAssembler.domainToDTO(rooms);
    }

    /**
     * Adds a device to a room identified by its ID, creating a new device entity in the process.
     *
     * @param roomID        The ID of the room to add the device to.
     * @param deviceName    The name of the new device.
     * @param deviceStatus  The initial status of the new device.
     * @return A DeviceDTO representing the added device.
     * @throws IllegalArgumentException if the specified room does not exist.
     */
    public DeviceDTO addDeviceToRoom(String roomID, String deviceName, boolean deviceStatus, String deviceTypeID)  {
        RoomID roomIdVO = new RoomID(roomID);
        DeviceName deviceNameVO = new DeviceName(deviceName);
        DeviceStatus deviceStatusVO = new DeviceStatus(deviceStatus);
        DeviceTypeID deviceTypeIDVO = new DeviceTypeID(deviceTypeID);

        Optional<Room> roomOptional = _roomService.getRoomById(roomIdVO);
        if (roomOptional.isEmpty()) {
            throw new IllegalArgumentException("Room with ID " + roomID + " not found.");
        }

        Device device = _deviceService.addDevice(roomIdVO, deviceNameVO, deviceStatusVO, deviceTypeIDVO);

        return _deviceAssembler.domainToDTO(device);
    }

}
