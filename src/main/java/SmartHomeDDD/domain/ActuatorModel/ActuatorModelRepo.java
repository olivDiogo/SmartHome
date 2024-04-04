package SmartHomeDDD.domain.ActuatorModel;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.valueObject.ActuatorModelID;
import SmartHomeDDD.valueObject.ActuatorTypeID;

import java.util.List;

public interface ActuatorModelRepo extends Repository<ActuatorModelID, ActuatorModel> {

    public List<ActuatorModel> findByActuatorTypeId(ActuatorTypeID actuatorModelID);
}
