package SmartHomeDDD.domain.Sensor;

public interface ISensorFactory {
    ISensor create(Object... parameters);
}
