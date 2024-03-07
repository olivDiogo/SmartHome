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

    /**
     * Constructor for the Room class.
     *
     * @param name              The name of the room.
     * @param floor             The floor where the room is located.
     * @param width             The width of the room.
     * @param length            The length of the room.
     * @param height            The height of the room.
     * @param dimensionsFactory The factory to create the dimensions of the room.
     * @throws IllegalArgumentException if the name is null or empty, or if the dimensions are invalid.
     */
    protected Room(String name, int floor, double width, double length, double height, DimensionsFactory dimensionsFactory) throws IllegalArgumentException {
        _dimensionsFactory = dimensionsFactory;
        setName(name);
        setDimension(width, length, height);
        setFloor(floor);
        this._deviceList = new ArrayList<>();
        this._roomId = UUID.randomUUID();
    }

    /**
     * Sets the dimensions of the room.
     *
     * @param width  The width of the room.
     * @param length The length of the room.
     * @param height The height of the room.
     * @throws IllegalArgumentException if the dimensions are invalid.
     */
    private void setDimension(double width, double length, double height) {
        Dimensions dimensions = _dimensionsFactory.createDimensions(width, length, height);
        if (dimensions == null) {
            throw new IllegalArgumentException("Invalid dimensions");
        } else this._dimensions = dimensions;
    }

    /**
     * Sets the name of the room.
     *
     * @param name The name of the room.
     * @throws IllegalArgumentException if the name is null or empty.
     */
    private void setName(String name) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Please enter a valid name for the room.");
        }
        this._name = name;
    }

    /**
     * Sets the floor where the room is located.
     *
     * @param floor The floor where the room is located.
     */
    private void setFloor(int floor) {
        this._floor = floor;
    }

    /**
     * Gets the name of the room.
     *
     * @return The name of the room.
     */
    public String getName() {
        return this._name;
    }

    /**
     * Gets the dimensions of the room.
     *
     * @return The dimensions of the room.
     */
    public Dimensions getDimensions() {
        return this._dimensions;
    }

    /**
     * Gets the ID of the room.
     *
     * @return The ID of the room.
     */
    public UUID getRoomId() {
        return this._roomId;
    }

    /**
     * Gets the floor where the room is located.
     *
     * @return The floor where the room is located.
     */
    public int getFloor() {
        return this._floor;
    }

    /**
     * Adds a device to the room.
     *
     * @param name          The name of the device.
     * @param deviceFactory The factory to create the device.
     * @return The device that was added.
     */
    public Device addDevice(String name, DeviceFactory deviceFactory) {
        Device device = deviceFactory.createDevice(name);
        addDeviceToList(device);
        return device;
    }

    /**
     * Adds a device to the list of devices in the room.
     *
     * @param device The device to add.
     * @return The device that was added.
     */
    protected Device addDeviceToList(Device device) {
        _deviceList.add(device);
        return device;
    }

    /**
     * Gets the list of devices in the room.
     *
     * @return The list of devices in the room.
     */
    public List<Device> getDevices() {
        return List.copyOf(this._deviceList);
    }

    /**
     * Returns a string representation of the Room object.
     * The string includes the name, dimensions, floor, and ID of the room.
     *
     * @return A string representation of the Room object.
     */
    @Override
    public String toString() {
        return "Room{" +
                "name='" + _name + '\'' +
                ", dimensions=" + _dimensions.toString() +
                ", floor=" + _floor +
                ", roomId=" + _roomId.toString() +
                '}';
    }


}
