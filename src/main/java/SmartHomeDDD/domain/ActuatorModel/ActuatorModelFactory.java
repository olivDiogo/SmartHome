package SmartHomeDDD.domain.ActuatorModel;

import SmartHomeDDD.valueObject.ActuatorModelName;
import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.valueObject.ModelPath;

public interface ActuatorModelFactory {

    public ActuatorModel createActuatorModel(ActuatorModelName actuatorModelName, ModelPath modelPath, ActuatorTypeID typeID);
}
