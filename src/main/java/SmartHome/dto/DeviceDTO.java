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
        this._deviceFunctionalities = device.getDeviceFunctionalities();
        this._listStringClassesSensors = device.getSensorList();
    }

    /**
     * Constructor of DeviceDTO which includes the list of the device's sensor functionalities and the room where the device is located.
     *
     * @param device is an instance of Device
     * @param room   is an instance of Room
     */
    public DeviceDTO(Device device, Room room) {
        this._name = device.getName();
        this._status = device.getStatus();
        this._deviceID = device.getDeviceId();
        this._roomDTO = RoomAssembler.domain2DTO(room);
        this._deviceFunctionalities = device.getDeviceFunctionalities();
        this._listStringClassesSensors = device.getSensorList();
    }

    /**
     * Getter for the ID of the device
     *
     * @return The ID of the device
     */

    public UUID getID() {
        return this._deviceID;
    }

    /**
     * Getter for the device functionalities
     *
     * @return a list of strings with the functionalities of the device
     */
    public List<String> getFunctionalities() {
        return this._deviceFunctionalities;
    }

    /**
     * Returns a string representation of the DeviceDTO object.
     *
     * This method provides a string representation of the DeviceDTO object, which includes the name, status, roomDTO, and device functionalities.
     * The format of the string is "DeviceDTO{name='name', status=status, roomDTO=roomDTO, deviceFunctionalities=deviceFunctionalities}".
     *
     * @return a string representation of the DeviceDTO object.
     */
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
