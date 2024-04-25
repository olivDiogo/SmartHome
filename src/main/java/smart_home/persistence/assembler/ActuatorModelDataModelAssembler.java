package smart_home.persistence.assembler;

import smart_home.domain.actuator_model.ActuatorModel;
import smart_home.domain.actuator_model.IActuatorModelFactory;
import smart_home.persistence.jpa.data_model.ActuatorModelDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.ActuatorModelID;
import smart_home.value_object.ActuatorModelName;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.ModelPath;

import java.util.ArrayList;
import java.util.List;

import static smart_home.utils.Validator.validateNotNull;

public class ActuatorModelDataModelAssembler
    implements IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> {

  private IActuatorModelFactory actuatorModelFactory;

  /**
   * Class constructor
   *
   * @param actuatorModelFactory is the factory used to create ActuatorModel instances.
   */
  public ActuatorModelDataModelAssembler(IActuatorModelFactory actuatorModelFactory) {
    validateNotNull(actuatorModelFactory, "Actuator Model Factory");
    this.actuatorModelFactory = actuatorModelFactory;
  }

  /**
   * Converts a ActuatorModelDataModel instance to a ActuatorModel instance.
   *
   * @param actuatorModelDataModel is the domain entity to be converted.
   * @return a ActuatorModel instance.
   */
  @Override
  public ActuatorModel toDomain(ActuatorModelDataModel actuatorModelDataModel) {
    validateNotNull(actuatorModelDataModel, "Actuator Model Data Model");

    ActuatorModelName actuatorModelName =
        new ActuatorModelName(actuatorModelDataModel.get_actuatorModelName());
    ModelPath modelPath = new ModelPath(actuatorModelDataModel.get_modelPath());
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorModelDataModel.get_actuatorTypeID());

    ActuatorModel actuatorModel =
        actuatorModelFactory.createActuatorModel(actuatorModelName, modelPath, actuatorTypeID);

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
    if (actuatorModelDataModels == null || actuatorModelDataModels.isEmpty()) {
      throw new IllegalArgumentException("The list of actuator models cannot be null or empty.");
    }

    List<ActuatorModel> actuatorModels = new ArrayList<>();

    for (ActuatorModelDataModel actuatorModelDataModel : actuatorModelDataModels) {
      ActuatorModel actuatorModel = toDomain(actuatorModelDataModel);
      actuatorModels.add(actuatorModel);
    }
    return actuatorModels;
  }
}
