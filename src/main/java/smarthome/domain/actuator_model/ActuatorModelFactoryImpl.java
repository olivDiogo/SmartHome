package smarthome.domain.actuator_model;

import org.springframework.stereotype.Component;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;

@Component
public class ActuatorModelFactoryImpl implements IActuatorModelFactory {

  @Override
  public ActuatorModel createActuatorModel(ModelPath modelPath, ActuatorModelName actuatorModelName,
      ActuatorTypeID typeID) {
    return new ActuatorModel(modelPath, actuatorModelName, typeID);
  }


}
