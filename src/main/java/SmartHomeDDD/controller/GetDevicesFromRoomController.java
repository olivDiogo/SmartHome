package SmartHomeDDD.controller;

import SmartHomeDDD.DTO.DeviceDTO;
import SmartHomeDDD.DTO.RoomDTO;
import SmartHomeDDD.assembler.DeviceAssembler;
import SmartHomeDDD.assembler.RoomAssembler;
import SmartHomeDDD.domain.Device.Device;
import SmartHomeDDD.domain.Room.Room;
import SmartHomeDDD.service.DeviceService;
import SmartHomeDDD.service.RoomService;
import SmartHomeDDD.valueObject.RoomID;

import java.util.List;

public class GetDevicesFromRoomController {
    private RoomService _roomService;
    private DeviceService _deviceService;
    private RoomAssembler _roomAssembler;
    private DeviceAssembler _deviceAssembler;

    /**
     * Constructor for GetDevicesFromRoomController.
     * @param roomService is the service for the room.
     * @param deviceService is the service for the device.
     * @param roomAssembler is the assembler for the room.
     * @param deviceAssembler is the assembler for the device.
     */
    public GetDevicesFromRoomController(RoomService roomService, DeviceService deviceService, RoomAssembler roomAssembler, DeviceAssembler deviceAssembler) {
        _roomService = roomService;
        _deviceService = deviceService;
        _roomAssembler = roomAssembler;
        _deviceAssembler = deviceAssembler;
    }

    /**
     * Gets all rooms.
     * @return a list of rooms.
     */
    public List<RoomDTO> getRooms(){
        List<Room> rooms = _roomService.getRooms();

        List<RoomDTO> roomDTOList = _roomAssembler.domainToDTO(rooms);

        return List.copyOf(roomDTOList);
    }

    /**
     * Gets all devices from a room.
     * @param roomDTO is the room to get the devices from.
     * @return a list of devices.
     */
    public List<DeviceDTO> getDevicesFromRoom (RoomDTO roomDTO){
        RoomID roomID = new RoomID(roomDTO.roomId);

        if(!_roomService.getRoomById(roomID).isPresent()){
            throw new IllegalArgumentException("Room with ID " + roomID + " not found.");
        }

        List<Device> devices = _deviceService.getDevicesByRoomId(roomID);

        List<DeviceDTO> deviceDTOList = _deviceAssembler.domainToDTO(devices);

        return List.copyOf(deviceDTOList);
    }



}
