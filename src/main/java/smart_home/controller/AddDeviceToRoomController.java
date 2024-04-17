package smart_home.controller;

import smart_home.assembler.DeviceAssembler;
import smart_home.assembler.RoomAssembler;
import smart_home.domain.device.Device;
import smart_home.domain.room.Room;
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

    private RoomServiceImpl _roomServiceImpl;
    private RoomAssembler _roomAssembler;
    private DeviceServiceImpl _deviceServiceImpl;
    private DeviceAssembler _deviceAssembler;

    /**
     * Constructs a new AddDeviceToRoomController with necessary service and assembler dependencies.
     * validates the room service, room assembler, device service, and device assembler.
     *
     * @param roomServiceImpl     Service for managing room-related operations.
     * @param roomAssembler   Assembler for converting room entities to DTOs.
     * @param deviceServiceImpl   Service for managing device-related operations.
     * @param deviceAssembler Assembler for converting device entities to DTOs.
     */
    public AddDeviceToRoomController(RoomServiceImpl roomServiceImpl, RoomAssembler roomAssembler, DeviceServiceImpl deviceServiceImpl, DeviceAssembler deviceAssembler) {
        validateRoomService(roomServiceImpl);
        validateRoomAssembler(roomAssembler);
        validateDeviceService(deviceServiceImpl);
        validateDeviceAssembler(deviceAssembler);
    }

    /**
     * Validates the room service.
     *
     * @param roomServiceImpl
     */
    private void validateRoomService(RoomServiceImpl roomServiceImpl) {
        if (roomServiceImpl == null) {
            throw new IllegalArgumentException("Please enter a valid room service.");
        } else {
            this._roomServiceImpl = roomServiceImpl;
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
     * @param deviceServiceImpl
     */
    private void validateDeviceService(DeviceServiceImpl deviceServiceImpl) {
        if (deviceServiceImpl == null) {
            throw new IllegalArgumentException("Please enter a valid device service.");
        } else {
            this._deviceServiceImpl = deviceServiceImpl;
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
        List<Room> rooms = _roomServiceImpl.getAllRooms();
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

        Optional<Room> roomOptional = _roomServiceImpl.getRoomById(roomIdVO);
        if (roomOptional.isEmpty()) {
            throw new IllegalArgumentException("Room with ID " + roomIdVO + " not found.");
        }

        Device device = _deviceServiceImpl.addDevice(roomIdVO, deviceNameVO, deviceStatusVO, deviceTypeIDVO);

        return _deviceAssembler.domainToDTO(device);
    }

}
