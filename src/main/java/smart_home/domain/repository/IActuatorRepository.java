package smart_home.domain.repository;

import smart_home.ddd.IRepository;
import smart_home.domain.actuator.IActuator;
import smart_home.value_object.ActuatorID;

public interface IActuatorRepository extends IRepository<ActuatorID, IActuator> {
}
