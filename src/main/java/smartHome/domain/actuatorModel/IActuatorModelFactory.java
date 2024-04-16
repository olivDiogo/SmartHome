package smartHome.domain.actuatorModel;

import smartHome.valueObject.ActuatorModelID;
import smartHome.valueObject.ActuatorModelName;
import smartHome.valueObject.ActuatorTypeID;
import smartHome.valueObject.ModelPath;

public interface IActuatorModelFactory {

    ActuatorModel createActuatorModel(ActuatorModelName actuatorModelName, ModelPath modelPath, ActuatorTypeID typeID);

    ActuatorModel createActuatorModel(ActuatorModelID actuatorModelID, ActuatorModelName actuatorModelName, ModelPath modelPath, ActuatorTypeID typeID);
}
