package smartHome.domain.sensor;

public interface ISensorFactory {
    ISensor create(Object... parameters);
}
