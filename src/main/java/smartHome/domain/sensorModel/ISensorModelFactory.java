package smartHome.domain.sensorModel;

import smartHome.valueObject.ModelPath;
import smartHome.valueObject.SensorModelName;
import smartHome.valueObject.SensorTypeID;

public interface ISensorModelFactory {
    SensorModel createSensorModel(SensorModelName sensorModelName, ModelPath modelPath, SensorTypeID sensorTypeID);
}
