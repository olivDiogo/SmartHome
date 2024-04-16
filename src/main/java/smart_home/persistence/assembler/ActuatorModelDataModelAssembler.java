package smart_home.persistence.assembler;

import smart_home.domain.actuator_model.ActuatorModel;
import smart_home.domain.actuator_model.IActuatorModelFactory;
import smart_home.persistence.jpa.data_model.ActuatorModelDataModel;
import smart_home.value_object.ActuatorModelID;
import smart_home.value_object.ActuatorModelName;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.ModelPath;

import java.util.ArrayList;
import java.util.List;

public class ActuatorModelDataModelAssembler
        implements IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> {

    private IActuatorModelFactory _actuatorModelFactory;

    /**
     * Class constructor
     *
     * @param _actuatorModelFactory is the factory used to create ActuatorModel instances.
     */
    public ActuatorModelDataModelAssembler(IActuatorModelFactory _actuatorModelFactory) {
        this._actuatorModelFactory = _actuatorModelFactory;
    }


    /**
     * Converts a ActuatorModelDataModel instance to a ActuatorModel instance.
     *
     * @param actuatorModelDataModel is the domain entity to be converted.
     * @return a ActuatorModel instance.
     */
    @Override
    public ActuatorModel toDomain(ActuatorModelDataModel actuatorModelDataModel) {
        ActuatorModelID actuatorModelID = new ActuatorModelID(actuatorModelDataModel.get_actuatorModelID());
        ActuatorModelName actuatorModelName =
                new ActuatorModelName(actuatorModelDataModel.get_actuatorModelName());
        ModelPath modelPath = new ModelPath(actuatorModelDataModel.get_modelPath());
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorModelDataModel.get_actuatorTypeID());

        ActuatorModel actuatorModel =
                _actuatorModelFactory.createActuatorModel(
                        actuatorModelID, actuatorModelName, modelPath, actuatorTypeID);

        return actuatorModel;
    }

    /**
     * Converts a list of ActuatorModelDataModel instances to a list of ActuatorModel instances.
     *
     * @param actuatorModelDataModels is the list of domain entities to be converted.
     * @return a list of ActuatorModel instances.
     */
    @Override
    public List<ActuatorModel> toDomain(List<ActuatorModelDataModel> actuatorModelDataModels) {
        List<ActuatorModel> actuatorModels = new ArrayList<>();

        for (ActuatorModelDataModel actuatorModelDataModel : actuatorModelDataModels) {
            ActuatorModel actuatorModel = toDomain(actuatorModelDataModel);
            actuatorModels.add(actuatorModel);
        }
        return actuatorModels;
    }
}
