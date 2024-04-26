package smarthome.domain.sensor;

public interface ISensorFactory {
    ISensor create(Object... parameters);
}
