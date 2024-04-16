package smart_home.domain.repository;

import smart_home.ddd.IRepository;
import smart_home.domain.actuator_model.ActuatorModel;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.ModelPath;

import java.util.List;

public interface IActuatorModelRepository extends IRepository<ModelPath, ActuatorModel> {

    List<ActuatorModel> findByActuatorTypeId(ActuatorTypeID actuatorModelID);
}
