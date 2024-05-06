package smarthome.persistence.assembler;

import static smarthome.utils.Validator.validateNotNull;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.actuator_model.IActuatorModelFactory;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;
import smarthome.persistence.jpa.data_model.ActuatorModelDataModel;

@Component
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
        new ActuatorModelName(actuatorModelDataModel.getActuatorModelName());
    ModelPath modelPath = new ModelPath(actuatorModelDataModel.getModelPath());
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorModelDataModel.getActuatorTypeID());

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
