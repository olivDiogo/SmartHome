package smart_home.persistence.spring_data;

import java.util.List;
import java.util.Optional;
import smart_home.domain.actuator_model.ActuatorModel;
import smart_home.domain.actuator_model.IActuatorModelFactory;
import smart_home.domain.repository.IActuatorModelRepository;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.ActuatorModelDataModel;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.ModelPath;

public class RepositoryActuatorModelSpringData implements IActuatorModelRepository {

  IRepositoryActuatorModelSpringData _repositoryActuatorModelSpringData;

  IActuatorModelFactory _actuatorModelFactory;

  IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> _dataModelConverter;

  //ActuatorModelDataModel _actuatorModelDataModel;

  public RepositoryActuatorModelSpringData(
      IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> dataModelConverter,
      IRepositoryActuatorModelSpringData repositoryActuatorModelSpringData) {
    validateDataModelConverter(dataModelConverter);
    this._dataModelConverter = dataModelConverter;
    this._repositoryActuatorModelSpringData = repositoryActuatorModelSpringData;
  }

  private void validateDataModelConverter(
      IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Data model converter cannot be null");
    }
  }

  @Override
  public ActuatorModel save(ActuatorModel actuatorModel) {
    if (actuatorModel == null) {
      throw new IllegalArgumentException("Actuator model cannot be null");
    }

    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel(actuatorModel);

    this._repositoryActuatorModelSpringData.save(actuatorModelDataModel);

    return actuatorModel;
  }

  @Override
  public List<ActuatorModel> findAll() {
    List<ActuatorModelDataModel> actuatorModelDataModels =
        this._repositoryActuatorModelSpringData.findAll();

    return _dataModelConverter.toDomain(actuatorModelDataModels);
  }

  @Override
  public Optional<ActuatorModel> ofIdentity(ModelPath modelID) {
    Optional<ActuatorModelDataModel> actuatorModelDataModel =
        this._repositoryActuatorModelSpringData.findById(modelID.toString());

    if (actuatorModelDataModel.isPresent()) {
      return Optional.of(_dataModelConverter.toDomain(actuatorModelDataModel.get()));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public boolean containsOfIdentity(ModelPath modelID) {

      return this._repositoryActuatorModelSpringData.existsById(modelID.getID());
  }

  @Override
  public List<ActuatorModel> findByActuatorTypeId(ActuatorTypeID actuatorModelID) {
    List<ActuatorModelDataModel> actuatorModelDataModels =
        this._repositoryActuatorModelSpringData.findBy_actuatorTypeID(actuatorModelID.getID());

    return _dataModelConverter.toDomain(actuatorModelDataModels);
  }
}
