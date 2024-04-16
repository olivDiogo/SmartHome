package smart_home.repository;

import smart_home.ddd.IRepository;
import smart_home.domain.actuator_type.ActuatorType;
import smart_home.value_object.ActuatorTypeID;

public interface IActuatorTypeRepository extends IRepository<ActuatorTypeID, ActuatorType> {
}
