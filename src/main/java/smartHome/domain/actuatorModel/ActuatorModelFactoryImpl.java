package smartHome.domain.actuatorModel;

import smartHome.valueObject.ActuatorModelName;
import smartHome.valueObject.ActuatorTypeID;
import smartHome.valueObject.ModelPath;

public class ActuatorModelFactoryImpl implements IActuatorModelFactory {

    @Override
    public ActuatorModel createActuatorModel(ActuatorModelName actuatorModelName, ModelPath modelPath, ActuatorTypeID typeID) {
        return new ActuatorModel(actuatorModelName, modelPath, typeID);
    }

}
