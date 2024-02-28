package SmartHome.dto;

import SmartHome.domain.Device;
import SmartHome.domain.Room;

import java.util.List;
import java.util.UUID;

public class DeviceDTO {
    private String _name;
    private UUID _deviceID;
    private boolean _status;
    private RoomDTO _roomDTO;
    private List<String> _deviceFunctionalities;
    private List<String> _listStringClassesSensors;




    public DeviceDTO(Device device) {
        this._name = device.getName();
        this._status = device.getStatus();
        this._deviceID = device.getDeviceId();
    }


    /**
     * Constructor of DeviceDTO which includes the list of the device's sensor functionalities and the room where the device is located.
     *
     * @param device is an instance of Device
     * @param room is an instance of Room
     */
    public DeviceDTO(Device device, Room room) {
        this._name = device.getName();
        this._status = device.getStatus();
        this._deviceID = device.getDeviceId();
        this._roomDTO = RoomAssembler.domain2DTO(room);
        this._deviceFunctionalities = device.getDeviceFunctionalities();
        this._listStringClassesSensors = device.getSensorList();
    }

    public UUID getID (){
        return this._deviceID;
    }

    public List<String> getFunctionalities(){
        return this._deviceFunctionalities;
    }

    public List<String> getSensorList(){
        return this._listStringClassesSensors;
    }

    @Override
    public String toString() {
        return "DeviceDTO{" +
                "name='" + _name + '\'' +
                ", status=" + _status +
                ", roomDTO=" + _roomDTO +
                ", deviceFunctionalities=" + _deviceFunctionalities +
                '}';
    }
}
