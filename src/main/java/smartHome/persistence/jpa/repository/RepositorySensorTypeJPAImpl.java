package smartHome.persistence.jpa.repository;

import jakarta.persistence.*;
import smartHome.domain.repository.ISensorTypeRepository;
import smartHome.domain.sensorType.SensorType;
import smartHome.persistence.assembler.IDataModelConverter;
import smartHome.persistence.jpa.dataModel.SensorTypeDataModel;
import smartHome.valueObject.SensorTypeID;

import java.util.List;
import java.util.Optional;

public class RepositorySensorTypeJPAImpl implements ISensorTypeRepository {
    private IDataModelConverter<SensorTypeDataModel, SensorType> _dataModelConverter;
    private EntityManagerFactory _factory;

    /**
     * Creates an instance of {@link RepositorySensorTypeJPAImpl} with the provided data model converter.
     *
     * @param dataModelConverter
     */
    public RepositorySensorTypeJPAImpl(IDataModelConverter<SensorTypeDataModel, SensorType> dataModelConverter) {
        validateDataModelConverter(dataModelConverter);

        this._dataModelConverter = dataModelConverter;
        this._factory = Persistence.createEntityManagerFactory("smartHome");
    }

    /**
     * Validates the data model converter
     *
     * @param entity the data model converter
     */
    private void validateDataModelConverter(IDataModelConverter<SensorTypeDataModel, SensorType> entity) {
        if (entity == null)
            throw new IllegalArgumentException("Data model converter cannot be null.");
    }

    /**
     * Get the entity manager
     *
     * @return the entity manager
     */
    private EntityManager getEntityManager() {
        EntityManager manager = this._factory.createEntityManager();

        return manager;
    }

    /**
     * Saves the sensor type in the database.
     *
     * @param sensorType is the domain entity to be saved.
     * @return SensorType
     */
    @Override
    public SensorType save(SensorType sensorType) {
        if (sensorType == null) {
            throw new IllegalArgumentException("Sensor Type cannot be null");
        }

        SensorTypeDataModel sensorTypeDataModel = new SensorTypeDataModel(sensorType);

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(sensorTypeDataModel);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
        return sensorType;
    }

    /**
     * Finds all sensorTypes.
     *
     * @return a list of sensorType.
     */
    @Override
    public List<SensorType> findAll() {
        EntityManager em = getEntityManager();
        try {
            Query query = getEntityManager().createQuery("SELECT e FROM SensorTypeDataModel e");

            List<SensorTypeDataModel> listDataModel = query.getResultList();

            List<SensorType> listDomain = _dataModelConverter.toDomain(listDataModel);

            return listDomain;
        } finally {
            em.close();
        }

    }

    /**
     * Find a SensorType by its identity.
     *
     * @param sensorTypeID the identity of the SensorType to find.
     * @return the SensorType if found, otherwise Optional.empty().
     */
    @Override
    public Optional<SensorType> ofIdentity(SensorTypeID sensorTypeID) {
        EntityManager em = getEntityManager();
        try {
            SensorTypeDataModel sensorTypeDataModel = em.find(SensorTypeDataModel.class, sensorTypeID);

            if (sensorTypeDataModel == null)
                return Optional.empty();

            SensorType sensorType = _dataModelConverter.toDomain(sensorTypeDataModel);

            return Optional.of(sensorType);
        } finally {
            em.close();
        }
    }

    /**
     * Check if a SensorType exists by its identity.
     *
     * @param sensorTypeID the identity of the SensorType to check.
     * @return true if the SensorType exists, otherwise false.
     */
    @Override
    public boolean containsOfIdentity(SensorTypeID sensorTypeID) {
        Optional<SensorType> sensorDataModel = ofIdentity(sensorTypeID);

        return sensorDataModel.isPresent();
    }
}
