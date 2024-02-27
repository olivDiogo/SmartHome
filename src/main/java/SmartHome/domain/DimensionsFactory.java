package SmartHome.domain;

public class DimensionsFactory {
    public Dimensions createDimensions(double width, double length, double height) {
        return new Dimensions(width, length, height);
    }
}
