package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.valueObject.DeviceID;
import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorName;
import SmartHomeDDD.valueObject.SensorTypeID;

public interface SensorFactory {
    //    Sensor create(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorType, SensorName sensorName, Object... additionalParameters);
    Sensor create(Object... parameters);
}
