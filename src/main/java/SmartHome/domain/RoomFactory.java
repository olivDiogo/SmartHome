package SmartHome.domain;

public class RoomFactory {
    /**
     * Creates a new Room object with the given name, floor, length, width, and height.
     *
     * @param name   The name of the room.
     * @param floor  The floor where the room is located.
     * @param length The length of the room.
     * @param width  The width of the room.
     * @param height The height of the room.
     * @return A new Room object with the specified name, floor, length, width, and height.
     */
    public Room createRoom(String name, int floor, double length, double width, double height) {
        return new Room(name, floor, length, width, height, new DimensionsFactory());
    }
}
