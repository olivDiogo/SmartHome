package smarthome.domain.repository;

import smarthome.ddd.IRepository;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;

import java.util.List;

public interface IActuatorModelRepository extends IRepository<ModelPath, ActuatorModel> {

    List<ActuatorModel> findBy_actuatorTypeID(ActuatorTypeID actuatorModelID);
}
