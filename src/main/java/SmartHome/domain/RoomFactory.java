package SmartHome.domain;

public class RoomFactory {
    public Room createRoom(String name, int floor, double length, double width, double height) {
        return new Room(name, floor, length, width, height, new DimensionsFactory());
    }
}
