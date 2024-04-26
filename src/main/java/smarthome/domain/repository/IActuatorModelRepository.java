package smarthome.domain.repository;

import java.util.List;
import smarthome.ddd.IRepository;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;

public interface IActuatorModelRepository extends IRepository<ModelPath, ActuatorModel> {

  List<ActuatorModel> findBy_actuatorTypeID(ActuatorTypeID actuatorModelID);
}
