package SmartHome.domain;

import java.util.List;

public interface Sensor {
    SensorType getSensorType();

    Value getValue();
}
