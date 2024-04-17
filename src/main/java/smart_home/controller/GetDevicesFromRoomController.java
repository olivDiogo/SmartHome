package smart_home.controller;

import smart_home.assembler.DeviceAssembler;
import smart_home.assembler.RoomAssembler;
import smart_home.domain.device.Device;
import smart_home.domain.room.Room;
import smart_home.dto.DeviceDTO;
import smart_home.dto.RoomDTO;
import smart_home.service.DeviceServiceImpl;
import smart_home.service.RoomServiceImpl;
import smart_home.value_object.RoomID;

import java.util.List;

public class GetDevicesFromRoomController {
    private RoomServiceImpl _roomServiceImpl;
    private DeviceServiceImpl _deviceServiceImpl;
    private RoomAssembler _roomAssembler;
    private DeviceAssembler _deviceAssembler;

    /**
     * Constructor for GetDevicesFromRoomController.
     *
     * @param roomServiceImpl     is the service for the room.
     * @param deviceServiceImpl   is the service for the device.
     * @param roomAssembler   is the assembler for the room.
     * @param deviceAssembler is the assembler for the device.
     */
    public GetDevicesFromRoomController(RoomServiceImpl roomServiceImpl, DeviceServiceImpl deviceServiceImpl, RoomAssembler roomAssembler, DeviceAssembler deviceAssembler) {
        validateRoomService(roomServiceImpl);
        validateDeviceService(deviceServiceImpl);
        validateRoomAssembler(roomAssembler);
        validateDeviceAssembler(deviceAssembler);
    }

    /**
     * Validates the room service.
     *
     * @param roomServiceImpl is the room service.
     */
    private void validateRoomService(RoomServiceImpl roomServiceImpl) {
        if (roomServiceImpl == null) {
            throw new IllegalArgumentException("RoomService is required");
        } else {
            this._roomServiceImpl = roomServiceImpl;
        }
    }

    /**
     * Validates the device service.
     *
     * @param deviceServiceImpl is the device service.
     */
    private void validateDeviceService(DeviceServiceImpl deviceServiceImpl) {
        if (deviceServiceImpl == null) {
            throw new IllegalArgumentException("DeviceService is required");
        } else {
            this._deviceServiceImpl = deviceServiceImpl;
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
        List<Room> rooms = _roomServiceImpl.getAllRooms();

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

        if (!_roomServiceImpl.getRoomById(roomID).isPresent()) {
            throw new IllegalArgumentException("Room with ID " + roomID + " not found.");
        }

        List<Device> devices = _deviceServiceImpl.getDevicesByRoomId(roomID);

        List<DeviceDTO> deviceDTOList = _deviceAssembler.domainToDTO(devices);

        return List.copyOf(deviceDTOList);
    }


}
