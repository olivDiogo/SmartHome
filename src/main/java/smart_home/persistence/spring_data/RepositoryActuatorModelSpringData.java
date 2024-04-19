package smart_home.persistence.spring_data;

import java.util.List;
import java.util.Optional;
import smart_home.domain.actuator_model.ActuatorModel;
import smart_home.domain.repository.IActuatorModelRepository;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.ActuatorModelDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.ModelPath;

public class RepositoryActuatorModelSpringData implements IActuatorModelRepository {

  IRepositoryActuatorModelSpringData _repositoryActuatorModelSpringData;

  IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> _dataModelAssembler;

  public RepositoryActuatorModelSpringData(
      IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> dataModelAssembler,
      IRepositoryActuatorModelSpringData repositoryActuatorModelSpringData) {
    validateDataModelAssembler(dataModelAssembler);
    this._dataModelAssembler = dataModelAssembler;
    this._repositoryActuatorModelSpringData = repositoryActuatorModelSpringData;
  }

  private void validateDataModelAssembler(
      IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> entity) {
    Validator.validateNotNull(entity, "Data model converter");
  }

  @Override
  public ActuatorModel save(ActuatorModel actuatorModel) {
    Validator.validateNotNull(actuatorModel, "Actuator model");

    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel(actuatorModel);

    this._repositoryActuatorModelSpringData.save(actuatorModelDataModel);

    return actuatorModel;
  }

  @Override
  public List<ActuatorModel> findAll() {
    List<ActuatorModelDataModel> actuatorModelDataModels =
        this._repositoryActuatorModelSpringData.findAll();

    return _dataModelAssembler.toDomain(actuatorModelDataModels);
  }

  @Override
  public Optional<ActuatorModel> ofIdentity(ModelPath modelID) {
    Optional<ActuatorModelDataModel> actuatorModelDataModel =
        this._repositoryActuatorModelSpringData.findById(modelID.toString());

    if (actuatorModelDataModel.isPresent()) {
      return Optional.of(_dataModelAssembler.toDomain(actuatorModelDataModel.get()));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public boolean containsOfIdentity(ModelPath modelID) {

    return this._repositoryActuatorModelSpringData.existsById(modelID.getID());
  }

  @Override
  public List<ActuatorModel> findBy_actuatorTypeID(ActuatorTypeID actuatorModelID) {
    List<ActuatorModelDataModel> actuatorModelDataModels =
        this._repositoryActuatorModelSpringData.findBy_actuatorTypeID(actuatorModelID.getID());

    return _dataModelAssembler.toDomain(actuatorModelDataModels);
  }
}
