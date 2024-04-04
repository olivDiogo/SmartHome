package SmartHomeDDD.domain.ActuatorModel;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.valueObject.ModelPath;

import java.util.List;

public interface ActuatorModelRepo extends Repository<ModelPath, ActuatorModel> {

    List<ActuatorModel> findByActuatorTypeId(ActuatorTypeID actuatorModelID);
}
