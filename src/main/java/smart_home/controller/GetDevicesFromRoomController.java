package smart_home.controller;

import smart_home.assembler.DeviceAssembler;
import smart_home.assembler.RoomAssembler;
import smart_home.domain.device.Device;
import smart_home.domain.room.Room;
import smart_home.dto.DeviceDTO;
import smart_home.dto.RoomDTO;
import smart_home.service.DeviceService;
import smart_home.service.RoomService;
import smart_home.value_object.RoomID;

import java.util.List;

public class GetDevicesFromRoomController {
    private RoomService _roomService;
    private DeviceService _deviceService;
    private RoomAssembler _roomAssembler;
    private DeviceAssembler _deviceAssembler;

    /**
     * Constructor for GetDevicesFromRoomController.
     *
     * @param roomService     is the service for the room.
     * @param deviceService   is the service for the device.
     * @param roomAssembler   is the assembler for the room.
     * @param deviceAssembler is the assembler for the device.
     */
    public GetDevicesFromRoomController(RoomService roomService, DeviceService deviceService, RoomAssembler roomAssembler, DeviceAssembler deviceAssembler) {
        validateRoomService(roomService);
        validateDeviceService(deviceService);
        validateRoomAssembler(roomAssembler);
        validateDeviceAssembler(deviceAssembler);
    }

    /**
     * Validates the room service.
     *
     * @param roomService is the room service.
     */
    private void validateRoomService(RoomService roomService) {
        if (roomService == null) {
            throw new IllegalArgumentException("RoomService is required");
        } else {
            this._roomService = roomService;
        }
    }

    /**
     * Validates the device service.
     *
     * @param deviceService is the device service.
     */
    private void validateDeviceService(DeviceService deviceService) {
        if (deviceService == null) {
            throw new IllegalArgumentException("DeviceService is required");
        } else {
            this._deviceService = deviceService;
        }
    }

    /**
     * Validates the room assembler.
     *
     * @param roomAssembler is the room assembler.
     */
    private void validateRoomAssembler(RoomAssembler roomAssembler) {
        if (roomAssembler == null) {
            throw new IllegalArgumentException("RoomAssembler is required");
        } else {
            this._roomAssembler = roomAssembler;
        }
    }

    /**
     * Validates the device assembler.
     *
     * @param deviceAssembler is the device assembler.
     */
    private void validateDeviceAssembler(DeviceAssembler deviceAssembler) {
        if (deviceAssembler == null) {
            throw new IllegalArgumentException("DeviceAssembler is required");
        } else {
            this._deviceAssembler = deviceAssembler;
        }
    }

    /**
     * Gets all rooms.
     *
     * @return a list of rooms.
     */
    public List<RoomDTO> getRooms() {
        List<Room> rooms = _roomService.getRooms();

        List<RoomDTO> roomDTOList = _roomAssembler.domainToDTO(rooms);

        return List.copyOf(roomDTOList);
    }

    /**
     * Gets all devices from a room.
     *
     * @param roomDTO is the room to get the devices from.
     * @return a list of devices.
     */
    public List<DeviceDTO> getDevicesFromRoom(RoomDTO roomDTO) {
        RoomID roomID = new RoomID(roomDTO.roomId);

        if (!_roomService.getRoomById(roomID).isPresent()) {
            throw new IllegalArgumentException("Room with ID " + roomID + " not found.");
        }

        List<Device> devices = _deviceService.getDevicesByRoomId(roomID);

        List<DeviceDTO> deviceDTOList = _deviceAssembler.domainToDTO(devices);

        return List.copyOf(deviceDTOList);
    }


}
