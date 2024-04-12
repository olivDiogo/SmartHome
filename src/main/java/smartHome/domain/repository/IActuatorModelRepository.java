package smartHome.domain.repository;

import smartHome.ddd.IRepository;
import smartHome.domain.actuatorModel.ActuatorModel;
import smartHome.valueObject.ActuatorTypeID;
import smartHome.valueObject.ModelPath;

import java.util.List;

public interface IActuatorModelRepository extends IRepository<ModelPath, ActuatorModel> {

    List<ActuatorModel> findByActuatorTypeId(ActuatorTypeID actuatorModelID);
}
