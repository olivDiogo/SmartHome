package smartHome.controller;

import smartHome.assembler.DeviceAssembler;
import smartHome.assembler.RoomAssembler;
import smartHome.domain.device.Device;
import smartHome.domain.room.Room;
import smartHome.dto.DeviceDTO;
import smartHome.dto.DeviceDataDTO;
import smartHome.dto.RoomDTO;
import smartHome.service.DeviceService;
import smartHome.service.RoomService;
import smartHome.valueObject.DeviceName;
import smartHome.valueObject.DeviceStatus;
import smartHome.valueObject.DeviceTypeID;
import smartHome.valueObject.RoomID;

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
     * validates the room service, room assembler, device service, and device assembler.
     *
     * @param roomService     Service for managing room-related operations.
     * @param roomAssembler   Assembler for converting room entities to DTOs.
     * @param deviceService   Service for managing device-related operations.
     * @param deviceAssembler Assembler for converting device entities to DTOs.
     */
    public AddDeviceToRoomController(RoomService roomService, RoomAssembler roomAssembler, DeviceService deviceService, DeviceAssembler deviceAssembler) {
        validateRoomService(roomService);
        validateRoomAssembler(roomAssembler);
        validateDeviceService(deviceService);
        validateDeviceAssembler(deviceAssembler);
    }

    /**
     * Validates the room service.
     *
     * @param roomService
     */
    private void validateRoomService(RoomService roomService) {
        if (roomService == null) {
            throw new IllegalArgumentException("Please enter a valid room service.");
        } else {
            this._roomService = roomService;
        }
    }

    /**
     * Validates the room assembler.
     *
     * @param roomAssembler
     */
    private void validateRoomAssembler(RoomAssembler roomAssembler) {
        if (roomAssembler == null) {
            throw new IllegalArgumentException("Please enter a valid room assembler.");
        } else {
            this._roomAssembler = roomAssembler;
        }
    }

    /**
     * Validates the device service.
     *
     * @param deviceService
     */
    private void validateDeviceService(DeviceService deviceService) {
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
        List<Room> rooms = _roomService.getRooms();
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
