package smart_home.persistence.jpa.repository;

import jakarta.persistence.*;
import smart_home.domain.actuator_type.ActuatorType;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.ActuatorTypeDataModel;
import smart_home.domain.repository.IActuatorTypeRepository;
import smart_home.value_object.ActuatorTypeID;

import java.util.List;
import java.util.Optional;

public class RepositoryActuatorTypeJPAImpl implements IActuatorTypeRepository {
    private EntityManagerFactory _factory;
    private IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> _dataModelConverter;

    /**
     * Constructs a new repository instance with the specified entity manager factory and data model converter.
     *
     *
     * @param dataModelConverter  the converter to transform data models to domain models and vice versa
     */
    public RepositoryActuatorTypeJPAImpl(IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> dataModelConverter) {
        validateDataModelConverter(dataModelConverter);
        _factory = Persistence.createEntityManagerFactory("smart_home");
        _dataModelConverter = dataModelConverter;
    }

    /**
     * Validates the data model converter.
     *
     * @param dataModelConverter the data model converter to validate
     * @throws IllegalArgumentException if the data model converter is null
     */
    private void validateDataModelConverter(IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> dataModelConverter) {
        if (dataModelConverter == null) {
            throw new IllegalArgumentException("The data model converter must not be null.");
        }
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
        if (entity == null) {
            throw new IllegalArgumentException("The provided entity must not be null.");
        }
        ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel(entity);
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(actuatorTypeDataModel);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
        return entity;
    }

    /**
     * Retrieves all actuator type entities from the database.
     *
     * @return a list of actuator type entities
     */
    @Override
    public List<ActuatorType> findAll() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM ActuatorTypeDataModel e");
            List<ActuatorTypeDataModel> listDataModel = query.getResultList();
            return _dataModelConverter.toDomain(listDataModel);
        } finally {
            em.close();
        }
    }

    /**
     * Finds an actuator type entity by its identity.
     *
     * @param objectID the ID of the actuator type
     * @return an optional containing the found actuator type, or an empty optional if no actuator type is found
     */
    @Override
    public Optional<ActuatorType> ofIdentity(ActuatorTypeID objectID) {
        EntityManager em = getEntityManager();
        try {
            ActuatorTypeDataModel actuatorTypeDataModel = em.find(ActuatorTypeDataModel.class, objectID);
            if (actuatorTypeDataModel == null) {
                return Optional.empty();
            }
            ActuatorType actuatorType = _dataModelConverter.toDomain(actuatorTypeDataModel);
            return Optional.of(actuatorType);
        } finally {
            em.close();
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

    /**
     * Returns a new instance of EntityManager.
     *
     * @return a new EntityManager instance
     */
    private EntityManager getEntityManager() {
        return _factory.createEntityManager();
    }
}
