package SmartHomeDDD.domain.SensorModel;

import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelName;

public class ImpSensorModelFactory implements SensorModelFactory{
    @Override
    public SensorModel createSensorModel(SensorModelName sensorModelName, ModelPath modelPath) {
        return new SensorModel(sensorModelName, modelPath);
    }
}
