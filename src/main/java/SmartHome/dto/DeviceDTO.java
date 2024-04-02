package SmartHome.dto;

import SmartHome.domain.Device;
import SmartHome.domain.Room;

import java.util.List;
import java.util.UUID;

public class DeviceDTO {
    public String _name;
    public UUID _deviceID;
    public boolean _status;
    public RoomDTO _roomDTO;
    public List<String> _deviceFunctionalities;
    public List<String> _sensors;
    public List<String> _actuators;


    /**
     * Constructor for the DeviceDTO class.
     *
     * @param device The Device object from which to create the DTO.
     */
    public DeviceDTO(Device device) {
        this._name = device.getName();
        this._status = device.getStatus();
        this._deviceID = device.getDeviceId();
        this._deviceFunctionalities = device.getDeviceFunctionalities();
        this._sensors = device.getSensorList();
        this._actuators = device.getActuatorList();
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
        this._sensors = device.getSensorList();
    }


    /**
     * Returns a string representation of the DeviceDTO object.
     * <p>
     * This method provides a string representation of the DeviceDTO object, which includes the name, status, roomDTO, and device functionalities.
     * The format of the string is "DeviceDTO{name='name', status=status, roomDTO=roomDTO, deviceFunctionalities=deviceFunctionalities}".
     *
     * @return a string representation of the DeviceDTO object.
     */
    @Override
    public String toString() {
        return "Device{" +
                "_name='" + _name + '\'' +
                ", _status=" + _status +
                ", _sensors=" + _sensors +
                ", _deviceId=" + _deviceID +
                '}';
    }

}
