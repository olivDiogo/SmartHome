package SmartHome.domain;

public class DimensionsFactory {

    /**
     * Creates a new Dimensions object with the given width, length, and height.
     *
     * @param width  The width of the room.
     * @param length The length of the room.
     * @param height The height of the room.
     * @return A new Dimensions object with the specified width, length, and height.
     */
    public Dimensions createDimensions(double width, double length, double height) {
        return new Dimensions(width, length, height);
    }
}
