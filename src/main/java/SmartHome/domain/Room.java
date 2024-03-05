package SmartHome.domain;

import SmartHome.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Room {
    private String _name;
    private int _floor;
    private DimensionsFactory _dimensionsFactory;
    private Dimensions _dimensions;
    private UUID _roomId;
    private List<Device> _deviceList;
    protected Room(String name, int floor, double width, double length, double height, DimensionsFactory dimensionsFactory) throws IllegalArgumentException {
        _dimensionsFactory = dimensionsFactory;
        setName(name);
        setDimension(width, length, height);
        setFloor(floor);
        this._deviceList = new ArrayList<>();
        this._roomId = UUID.randomUUID();
    }
    private void setDimension (double width, double length, double height) {
        Dimensions dimensions = _dimensionsFactory.createDimensions(width, length, height);
        if (dimensions == null) {
            throw new IllegalArgumentException("Invalid dimensions");
        }
        else this._dimensions = dimensions;
    }

    private void setName(String name) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Please enter a valid name for the room.");
        }
        this._name = name;
    }
    private void setFloor (int floor){
        this._floor = floor;
    }

    public String getName() {
        return this._name;
    }
    public Dimensions getDimensions() {
        return this._dimensions;
    }
    public UUID getRoomId() {
        return this._roomId;
    }
    public int getFloor() {
        return this._floor;
    }
    public Device addDevice(String name, DeviceFactory deviceFactory) {
        Device device = deviceFactory.createDevice(name);
        addDeviceToList(device);
        return device;
    }

    protected Device addDeviceToList(Device device) {
        _deviceList.add(device);
        return device;
    }
    public List<Device> getDevices() {
        return List.copyOf(this._deviceList);
    }

    @Override
    public String toString (){
        return "Room{" +
                "name='" + _name + '\'' +
                ", dimensions=" + _dimensions.toString() +
                ", floor=" + _floor +
                ", roomId=" + _roomId.toString() +
                '}';
    }


}
