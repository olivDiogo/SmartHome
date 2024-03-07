package SmartHome.domain;

public class Dimensions {
    private double _width;
    private double _length;
    private double _height;

    /**
     * Constructor for the Dimensions class.
     *
     * @param width  the width of the room.
     * @param length the length of the room.
     * @param height the height of the room.
     * @throws IllegalArgumentException if the width, length or height is less than or equal to 0.
     */
    public Dimensions(double width, double length, double height) throws IllegalArgumentException {
        setWidth(width);
        setLength(length);
        setHeight(height);
    }

    /**
     * Set the height of the room.
     *
     * @param height the height of the room.
     * @throws IllegalArgumentException if the height is less than 0.
     */
    private void setHeight(double height) throws IllegalArgumentException {
        if (height < 0) {
            throw new IllegalArgumentException("Height must be a non-negative value");
        }
        this._height = height;
    }

    /**
     * Set the length of the room.
     *
     * @param length the length of the room.
     * @throws IllegalArgumentException if the length is less than or equal to 0.
     */
    private void setLength(double length) throws IllegalArgumentException {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be a positive value");
        }
        this._length = length;
    }

    /**
     * Set the width of the room.
     *
     * @param width the width of the room.
     * @throws IllegalArgumentException if the width is less than or equal to 0.
     */
    private void setWidth(double width) throws IllegalArgumentException {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be a positive value");
        }
        this._width = width;
    }

    /**
     * Get the height of the room.
     *
     * @return the height of the room.
     */
    public double getHeight() {
        return _height;
    }

    /**
     * Get the length of the room.
     *
     * @return the length of the room.
     */
    public double getLength() {
        return _length;
    }

    /**
     * Get the width of the room.
     *
     * @return the width of the room.
     */
    public double getWidth() {
        return _width;
    }

    /**
     * Returns a string representation of the Dimensions object.
     * The string includes the width, length, and height of the room.
     *
     * @return A string representation of the Dimensions object.
     */
    @Override
    public String toString() {
        return "Dimensions{" +
                "_width=" + _width +
                ", _length=" + _length +
                ", _height=" + _height +
                '}';
    }
}
