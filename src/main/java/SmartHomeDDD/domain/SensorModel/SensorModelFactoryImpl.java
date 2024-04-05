package SmartHomeDDD.domain.SensorModel;

import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelName;
import SmartHomeDDD.valueObject.SensorTypeID;

public class SensorModelFactoryImpl implements ISensorModelFactory {
    @Override
    public SensorModel createSensorModel(SensorModelName sensorModelName, ModelPath modelPath, SensorTypeID sensorTypeID) {
        return new SensorModel(sensorModelName, modelPath, sensorTypeID);
    }
}
