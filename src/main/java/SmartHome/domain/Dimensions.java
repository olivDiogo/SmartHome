package SmartHome.domain;

public class Dimensions {
    private double _width;
    private double _length;
    private double _height;
    public Dimensions(double width, double length, double height) throws IllegalArgumentException {
        setWidth(width);
        setLength(length);
        setHeight(height);
    }
    private void setHeight(double height) throws IllegalArgumentException {
        if (height < 0) {
            throw new IllegalArgumentException("Height must be a non-negative value");
        }
        this._height = height;
    }
    private void setLength(double length) throws IllegalArgumentException {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be a positive value");
        }
        this._length = length;
    }
    private void setWidth(double width) throws IllegalArgumentException {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be a positive value");
        }
        this._width = width;
    }
    @Override
    public String toString() {
        return "Dimensions{" +
                "_width=" + _width +
                ", _length=" + _length +
                ", _height=" + _height +
                '}';
    }
}
