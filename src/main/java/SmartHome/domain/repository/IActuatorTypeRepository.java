package smartHome.domain.repository;

import smartHome.ddd.IRepository;
import smartHome.domain.actuatorType.ActuatorType;
import smartHome.valueObject.ActuatorTypeID;

public interface IActuatorTypeRepository extends IRepository<ActuatorTypeID, ActuatorType> {
}
