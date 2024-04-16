package smart_home.domain.sensor;

public interface ISensorFactory {
    ISensor create(Object... parameters);
}
