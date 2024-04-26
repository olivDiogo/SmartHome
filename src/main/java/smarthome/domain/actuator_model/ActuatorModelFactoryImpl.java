package smarthome.domain.actuator_model;

import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;

public class ActuatorModelFactoryImpl implements IActuatorModelFactory {

  @Override
  public ActuatorModel createActuatorModel(ActuatorModelName actuatorModelName, ModelPath modelPath,
      ActuatorTypeID typeID) {
    return new ActuatorModel(actuatorModelName, modelPath, typeID);
  }


}
