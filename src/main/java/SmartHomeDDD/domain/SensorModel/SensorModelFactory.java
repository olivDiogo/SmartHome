package SmartHomeDDD.domain.SensorModel;

import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelName;

public interface SensorModelFactory {
    public SensorModel createSensorModel(SensorModelName sensorModelName, ModelPath modelPath);
}
