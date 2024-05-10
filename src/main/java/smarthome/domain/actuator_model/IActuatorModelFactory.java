package smarthome.domain.actuator_model;

import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;

public interface IActuatorModelFactory {

  ActuatorModel createActuatorModel(ModelPath modelPath, ActuatorModelName actuatorModelName,
      ActuatorTypeID typeID);
}
