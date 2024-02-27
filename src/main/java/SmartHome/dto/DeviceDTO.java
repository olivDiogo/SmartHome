package SmartHome.dto;

import SmartHome.domain.Device;
import SmartHome.domain.Room;

import java.util.List;
import java.util.UUID;

public class DeviceDTO {
    private String name;
    private UUID deviceID;
    private boolean status;
    private RoomDTO roomDTO;
    private List<String> deviceFunctionalities;



    public DeviceDTO(Device device) {
        this.name = device.getName();
        this.status = device.getStatus();
        this.deviceID = device.getDeviceId();
    }


    /**
     * Constructor of DeviceDTO which includes the list of the device's sensor functionalities and the room where the device is located.
     *
     * @param device is an instance of Device
     * @param room is an instance of Room
     */
    public DeviceDTO(Device device, Room room) {
        this.name = device.getName();
        this.status = device.getStatus();
        this.deviceID = device.getDeviceId();
        this.roomDTO = RoomAssembler.domain2DTO(room);
        this.deviceFunctionalities = device.getDeviceFunctionalities();
    }

    public UUID getID (){
        return this.deviceID;
    }

    public List<String> getFunctionalities(){
        return this.deviceFunctionalities;
    }

    @Override
    public String toString() {
        return "DeviceDTO{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", roomDTO=" + roomDTO +
                ", deviceFunctionalities=" + deviceFunctionalities +
                '}';
    }
}
