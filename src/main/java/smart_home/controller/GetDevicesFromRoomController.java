package smart_home.controller;

import smart_home.ddd.IAssembler;
import smart_home.domain.device.Device;
import smart_home.domain.room.Room;
import smart_home.domain.service.IDeviceService;
import smart_home.domain.service.IRoomService;
import smart_home.dto.DeviceDTO;
import smart_home.dto.RoomDTO;
import smart_home.utils.Validator;
import smart_home.value_object.RoomID;

import java.util.List;

public class GetDevicesFromRoomController {
    private final IRoomService _roomService;
    private final IDeviceService _deviceService;
    private final IAssembler<Room, RoomDTO> _roomAssembler;
    private final IAssembler<Device, DeviceDTO> _deviceAssembler;

    /**
     * Constructor for GetDevicesFromRoomController.
     *
     * @param roomService     is the service for the room.
     * @param deviceService   is the service for the device.
     * @param roomAssembler   is the assembler for the room.
     * @param deviceAssembler is the assembler for the device.
     */
    public GetDevicesFromRoomController(IRoomService roomService, IDeviceService deviceService, IAssembler<Room, RoomDTO> roomAssembler, IAssembler<Device, DeviceDTO> deviceAssembler) {
      Validator.validateNotNull(roomService, "Room service");
      Validator.validateNotNull(deviceService, "Device service");
      Validator.validateNotNull(roomAssembler, "Room assembler");
      Validator.validateNotNull(deviceAssembler, "Device assembler");

      this._deviceAssembler = deviceAssembler;
      this._deviceService = deviceService;
      this._roomAssembler = roomAssembler;
      this._roomService = roomService;

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

        if (_roomService.getRoomById(roomID).isEmpty()) {
            throw new IllegalArgumentException("Room with ID " + roomID + " not found.");
        }

        List<Device> devices = _deviceService.getDevicesByRoomId(roomID);

        List<DeviceDTO> deviceDTOList = _deviceAssembler.domainToDTO(devices);

        return List.copyOf(deviceDTOList);
    }


}
