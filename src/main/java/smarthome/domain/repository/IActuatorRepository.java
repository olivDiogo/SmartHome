package smarthome.domain.repository;

import smarthome.ddd.IRepository;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.value_object.ActuatorID;

public interface IActuatorRepository extends IRepository<ActuatorID, IActuator> {

}
