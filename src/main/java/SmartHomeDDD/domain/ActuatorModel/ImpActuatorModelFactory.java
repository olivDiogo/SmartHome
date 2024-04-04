package SmartHomeDDD.domain.ActuatorModel;

import SmartHomeDDD.valueObject.ActuatorModelName;
import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.valueObject.ModelPath;

public class ImpActuatorModelFactory implements ActuatorModelFactory{

@Override
    public ActuatorModel createActuatorModel(ActuatorModelName actuatorModelName, ModelPath modelPath, ActuatorTypeID typeID) {
        return new ActuatorModel(actuatorModelName, modelPath);
    }

}
