package smart_home.persistence.spring_data.actuator_type;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import smart_home.domain.actuator_type.ActuatorType;
import smart_home.domain.repository.IActuatorTypeRepository;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.ActuatorTypeDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.ActuatorTypeID;

import java.util.List;
import java.util.Optional;

public class ActuatorTypeSpringDataRepository implements IActuatorTypeRepository {

    private IActuatorTypeSpringDataRepository _repository;

    private IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> _dataModelAssembler;

    private EntityManagerFactory _factory;

    /**
     * Constructs a new repository instance with the specified entity manager factory and data model assembler.
     *
     * @param repository the repository to use
     * @param dataModelAssembler the converter to transform data models to domain models and vice versa
     */
    public ActuatorTypeSpringDataRepository(IActuatorTypeSpringDataRepository repository, IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> dataModelAssembler) {
        Validator.validateNotNull(dataModelAssembler, "Data model assembler");
        Validator.validateNotNull(repository, "Repository");
        _factory = Persistence.createEntityManagerFactory("smart_home");
        _dataModelAssembler = dataModelAssembler;
        _repository = repository;
    }

    /**
     * Validates the data model converter.
     *
     * @param dataModelAssembler the data model converter to validate
     * @throws IllegalArgumentException if the data model converter is null
     */
    private void validateDataModelAssembler(IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> dataModelAssembler) {
        Validator.validateNotNull(dataModelAssembler, "Data model assembler");
    }

    /**
     * Validates the repository.
     *
     * @param repository the repository to validate
     * @throws IllegalArgumentException if the repository is null
     */
    private void validateRepository(IActuatorTypeSpringDataRepository repository) {
        Validator.validateNotNull(repository, "Repository");
    }

    /**
     * Method to create an entity manager
     *
     * @return entity manager
     */
    private EntityManager getEntityManager() {
        return _factory.createEntityManager();
    }

    /**
     * Saves the specified actuator type entity to the database.
     *
     * @param entity the actuator type entity to save
     * @return the saved actuator type entity
     * @throws IllegalArgumentException if the entity is null
     */
    @Override
    public ActuatorType save(ActuatorType entity) {
        Validator.validateNotNull(entity, "Actuator type");
        ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel(entity);
        _repository.save(actuatorTypeDataModel);
        return entity;
    }

    /**
     * Finds all actuator type entities in the database.
     *
     * @return a list of all actuator type entities
     */
    @Override
    public List<ActuatorType> findAll() {
        List<ActuatorTypeDataModel> actuatorTypeDataModels = _repository.findAll();
        return _dataModelAssembler.toDomain(actuatorTypeDataModels);
    }

    /**
     * Method to find a domain entity by its unique identifier.
     *
     * @param objectID is the unique identifier of the domain entity.
     * @return the domain entity.
     */
    @Override
    public Optional<ActuatorType> ofIdentity(ActuatorTypeID objectID) {
        Optional<ActuatorTypeDataModel> actuatorTypeDataModel = _repository.findById(objectID.getID());

        if (actuatorTypeDataModel.isPresent()) {
            ActuatorType domain = _dataModelAssembler.toDomain(actuatorTypeDataModel.get());
            return Optional.of(domain);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Checks if an actuator type with the specified identity exists in the database.
     *
     * @param objectID the ID of the actuator type to check
     * @return true if the actuator type exists, false otherwise
     */
    @Override
    public boolean containsOfIdentity(ActuatorTypeID objectID) {
        return ofIdentity(objectID).isPresent();
    }
}
