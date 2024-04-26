package smarthome.persistence.assembler;

import static smarthome.utils.Validator.validateNotNull;

import java.util.ArrayList;
import java.util.List;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.actuator_model.IActuatorModelFactory;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;
import smarthome.persistence.jpa.data_model.ActuatorModelDataModel;

public class ActuatorModelDataModelAssembler
    implements IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> {

  private final IActuatorModelFactory actuatorModelFactory;

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
