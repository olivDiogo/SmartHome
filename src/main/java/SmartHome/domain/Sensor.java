package SmartHome.domain;

import java.util.List;

public interface Sensor {
    SensorType getSensorType();

    List<String> getSensorList();

    Value getValue();
}
