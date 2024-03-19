package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.ValueObject;

public class Dimension implements ValueObject {
    private int _width;
    private int _height;
    private int _depth;

    /**
     * Constructor of the class Dimension.
     *
     * @param width  is the width of the room.
     * @param height is the height of the room.
     * @param depth  is the depth of the room.
     */
    public Dimension(int width, int height, int depth) {
        setWidth(width);
        setHeight(height);
        setDepth(depth);

    }

    /**
     * Method to set the width of the room and verifies if the value is positive.
     *
     * @param width is the width of the room.
     * @return the width of the room.
     */
    private void setWidth(int width) {
        if (width > 0)
            this._width = width;
        else
            throw new IllegalArgumentException("'Width' must be positive.");
    }

    /**
     * Method to set the height of the room and verifies if the value is positive.
     *
     * @param height is the height of the room.
     * @return the height of the room.
     */
    private void setHeight(int height) {
        if (height > 0)
            this._height = height;
        else
            throw new IllegalArgumentException("'Height' must be positive.");
    }

    /**
     * Method to set the depth of the room and verifies if the value is positive.
     *
     * @param depth is the depth of the room.
     * @return the depth of the room.
     */
    private void setDepth(int depth) {
        if (depth > 0)
            this._depth = depth;
        else
            throw new IllegalArgumentException("'Depth' must be positive");
    }

    /**
     * Method to compares two objects.
     *
     * @param object is the object to compare.
     * @return true if the objects are equal, false if they are different.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof Dimension) {
            Dimension dimension = (Dimension) object;

            if (this._width == dimension._width && this._height == dimension._height && this._depth == dimension._depth)
                return true;
        }
        return false;
    }

    /**
     * Method to return the values of the object in a string.
     *
     * @return the values of the object in a string.
     */
    @Override
    public String toString() {
        return "Width: " + this._width + ", Height: " + this._height + ", Depth: " + this._depth;
    }
}
