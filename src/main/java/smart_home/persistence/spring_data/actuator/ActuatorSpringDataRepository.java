package smart_home.persistence.spring_data.actuator;

import smart_home.domain.actuator.IActuator;
import smart_home.domain.repository.IActuatorRepository;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.ActuatorDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.ActuatorID;

import java.util.List;
import java.util.Optional;

public class ActuatorSpringDataRepository implements IActuatorRepository {

    private IActuatorSpringDataRepository _repository;

    private IDataModelAssembler<ActuatorDataModel, IActuator> _assembler;

    /**
     * Constructs a new repository instance with the specified entity manager factory and data model assembler.
     *
     * @param repository the repository to use
     * @param dataModelAssembler the converter to transform data models to domain models and vice versa
     */
    public ActuatorSpringDataRepository(IActuatorSpringDataRepository repository, IDataModelAssembler<ActuatorDataModel, IActuator> dataModelAssembler) {
        Validator.validateNotNull(dataModelAssembler, "Data model assembler");
        _assembler = dataModelAssembler;
        Validator.validateNotNull(repository, "Repository");
        _repository = repository;
    }

    /**
     * Saves the specified actuator entity to the database.
     *
     * @param actuator the actuator entity to save
     * @return the saved actuator entity
     * @throws IllegalArgumentException if the entity is null
     */
    @Override
    public IActuator save(IActuator actuator) {
        Validator.validateNotNull(actuator, "Actuator");
        ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuator);
        _repository.save(actuatorDataModel);
        return actuator;
    }

    /**
     * Finds all actuator entities in the database.
     *
     * @return a list of all actuator entities
     */
    @Override
    public List<IActuator> findAll() {
        List<ActuatorDataModel> actuatorDataModels = this._repository.findAll();
        List<IActuator> actuators = _assembler.toDomain(actuatorDataModels);
        return actuators;
    }

    /**
     * Finds the actuator entity with the specified identity.
     *
     * @param objectID the identity of the actuator entity to find
     * @return an optional containing the actuator entity if found, empty otherwise
     * @throws IllegalArgumentException if the identity is null
     */
    @Override
    public Optional<IActuator> ofIdentity(ActuatorID objectID) {
        Optional<ActuatorDataModel> actuatorDataModel = _repository.findById(objectID.getID());
        if(actuatorDataModel.isPresent()) {
            ActuatorDataModel actuatorDataModel1 = actuatorDataModel.get();
            IActuator actuator = _assembler.toDomain(actuatorDataModel.get());
            return Optional.of(actuator);
        } else {
            return Optional.empty();
        }

    }

    /**
     * Checks if the actuator entity with the specified identity exists.
     *
     * @param objectID the identity of the actuator entity to check
     * @return true if the actuator entity exists, false otherwise
     */
    @Override
    public boolean containsOfIdentity(ActuatorID objectID) {
        return this._repository.existsById(objectID.getID());
    }

}
