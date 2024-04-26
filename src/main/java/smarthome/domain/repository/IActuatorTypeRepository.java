package smarthome.domain.repository;

import smarthome.ddd.IRepository;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.value_object.ActuatorTypeID;

public interface IActuatorTypeRepository extends IRepository<ActuatorTypeID, ActuatorType> {

}
