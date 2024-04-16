package smart_home.domain.actuator_model;

import smart_home.value_object.ActuatorModelID;
import smart_home.value_object.ActuatorModelName;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.ModelPath;

public interface IActuatorModelFactory {

    ActuatorModel createActuatorModel(ActuatorModelName actuatorModelName, ModelPath modelPath, ActuatorTypeID typeID);

    ActuatorModel createActuatorModel(ActuatorModelID actuatorModelID, ActuatorModelName actuatorModelName, ModelPath modelPath, ActuatorTypeID typeID);
}
