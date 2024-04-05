package SmartHomeDDD.domain.SensorModel;

import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelName;
import SmartHomeDDD.valueObject.SensorTypeID;

public interface ISensorModelFactory {
    SensorModel createSensorModel(SensorModelName sensorModelName, ModelPath modelPath, SensorTypeID sensorTypeID);
}
