package smart_home.controller;

import smart_home.assembler.DeviceAssembler;
import smart_home.assembler.RoomAssembler;
import smart_home.ddd.IAssembler;
import smart_home.domain.device.Device;
import smart_home.domain.room.Room;
import smart_home.domain.service.IDeviceService;
import smart_home.domain.service.IRoomService;
import smart_home.dto.DeviceDTO;
import smart_home.dto.DeviceDataDTO;
import smart_home.dto.RoomDTO;
import smart_home.service.DeviceServiceImpl;
import smart_home.service.RoomServiceImpl;
import smart_home.value_object.DeviceName;
import smart_home.value_object.DeviceStatus;
import smart_home.value_object.DeviceTypeID;
import smart_home.value_object.RoomID;

import java.util.List;
import java.util.Optional;

/**
 * Controller class responsible for handling device addition to rooms within a smart home domain.
 */
public class AddDeviceToRoomController {

    private IRoomService _roomService;
    private IAssembler<Room, RoomDTO> _roomAssembler;
    private IDeviceService _deviceService;
    private DeviceAssembler _deviceAssembler;

    /**
     * Constructs a new AddDeviceToRoomController with necessary service and assembler dependencies.
     * validates the room service, room assembler, device service, and device assembler.
     *
     * @param roomService     Service for managing room-related operations.
     * @param roomAssembler   Assembler for converting room entities to DTOs.
     * @param deviceServiceImpl   Service for managing device-related operations.
     * @param deviceAssembler Assembler for converting device entities to DTOs.
     */
    public AddDeviceToRoomController(IRoomService roomService, IAssembler<Room, RoomDTO> roomAssembler, DeviceServiceImpl deviceServiceImpl, DeviceAssembler deviceAssembler) {
        validateRoomService(roomService);
        validateRoomAssembler(roomAssembler);
        validateDeviceService(deviceServiceImpl);
        validateDeviceAssembler(deviceAssembler);
    }

    /**
     * Validates the room service.
     *
     * @param roomService The room service to validate.
     */
    private void validateRoomService(IRoomService roomService) {
        if (roomService == null) {
            throw new IllegalArgumentException("Please enter a valid room service.");
        } else {
            this._roomService = roomService;
        }
    }

    /**
     * Validates the room assembler.
     *
     * @param roomAssembler The room assembler to validate.
     */
    private void validateRoomAssembler(IAssembler<Room, RoomDTO> roomAssembler) {
        if (roomAssembler == null) {
            throw new IllegalArgumentException("Please enter a valid room assembler.");
        } else {
            this._roomAssembler = roomAssembler;
        }
    }

    /**
     * Validates the device service.
     *
     * @param deviceService The device service to validate.
     */
    private void validateDeviceService(IDeviceService deviceService) {
        if (deviceService == null) {
            throw new IllegalArgumentException("Please enter a valid device service.");
        } else {
            this._deviceService = deviceService;
        }
    }

    /**
     * Validates the device assembler.
     *
     * @param deviceAssembler
     */
    private void validateDeviceAssembler(DeviceAssembler deviceAssembler) {
        if (deviceAssembler == null) {
            throw new IllegalArgumentException("Please enter a valid device assembler.");
        } else {
            this._deviceAssembler = deviceAssembler;
        }
    }

    /**
     * Retrieves all rooms as a list of RoomDTOs.
     *
     * @return a list of RoomDTOs.
     */
    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = _roomService.getAllRooms();
        return _roomAssembler.domainToDTO(rooms);
    }

    /**
     * Adds a device to a room identified by its ID, creating a new device entity in the process.
     *
     * @param deviceDataDTO The data needed to create a device.
     * @return A DeviceDTO representing the added device.
     * @throws IllegalArgumentException if the specified room does not exist.
     */
    public DeviceDTO addDeviceToRoom(DeviceDataDTO deviceDataDTO) {

        RoomID roomIdVO = new RoomID(deviceDataDTO.roomID);
        DeviceName deviceNameVO = new DeviceName(deviceDataDTO.deviceName);
        DeviceStatus deviceStatusVO = new DeviceStatus(deviceDataDTO.deviceStatus);
        DeviceTypeID deviceTypeIDVO = new DeviceTypeID(deviceDataDTO.deviceTypeID);

        Optional<Room> roomOptional = _roomService.getRoomById(roomIdVO);
        if (roomOptional.isEmpty()) {
            throw new IllegalArgumentException("Room with ID " + roomIdVO + " not found.");
        }

        Device device = _deviceService.addDevice(roomIdVO, deviceNameVO, deviceStatusVO, deviceTypeIDVO);

        return _deviceAssembler.domainToDTO(device);
    }
}
