package smart_home.controller;

import smart_home.ddd.IAssembler;
import smart_home.domain.device.Device;
import smart_home.domain.room.Room;
import smart_home.domain.service.IDeviceService;
import smart_home.domain.service.IRoomService;
import smart_home.dto.DeviceDTO;
import smart_home.dto.RoomDTO;
import smart_home.value_object.RoomID;

import java.util.List;

public class GetDevicesFromRoomController {
    private IRoomService _roomService;
    private IDeviceService _deviceService;
    private IAssembler<Room, RoomDTO> _roomAssembler;
    private IAssembler<Device, DeviceDTO> _deviceAssembler;

    /**
     * Constructor for GetDevicesFromRoomController.
     *
     * @param roomService     is the service for the room.
     * @param deviceService   is the service for the device.
     * @param roomAssembler   is the assembler for the room.
     * @param deviceAssembler is the assembler for the device.
     */
    public GetDevicesFromRoomController(IRoomService roomService, IDeviceService deviceService, IAssembler<Room, RoomDTO> roomAssembler, IAssembler<Device, DeviceDTO> deviceAssembler) {
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
    private void validateRoomService(IRoomService roomService) {
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
    private void validateDeviceService(IDeviceService deviceService) {
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
    private void validateRoomAssembler(IAssembler<Room, RoomDTO> roomAssembler) {
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
    private void validateDeviceAssembler(IAssembler<Device, DeviceDTO> deviceAssembler) {
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
        List<Room> rooms = _roomService.getAllRooms();

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
